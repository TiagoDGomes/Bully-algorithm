package processo;

import auxiliar.SimpleSocket;
import mensagem.Mensagem;
import mensagem.MensagemAlive;
import mensagem.MensagemCoordenador;
import mensagem.MensagemEleicao;
import mensagem.MensagemRequest;
import mensagem.MensagemResponse;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensagem.ListenerMensagem;

public class Processo {

    private int id;
    private int idLider;

    public int getIdLider() {
        return idLider;
    }

    public void setIdLider(int idLider) {
        this.idLider = idLider;
    }
    private boolean liderAtivo;
    private SimpleSocket socket;
    private ListenerMensagem listener;

    public ListenerMensagem getListener() {
        return listener;
    }

    public void setListener(ListenerMensagem listener) {
        this.listener = listener;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Processo() {
    }

    public void iniciar() {
        socket = new SimpleSocket(23222) {
            @Override
            public void comandoRecebido(Serializable dados) {
                Mensagem msg = (Mensagem) dados;
                System.out.println(msg);
                if (msg.getIdEmissor() > id) {
                    if (msg instanceof MensagemCoordenador) {
                        idLider = msg.getIdEmissor();
                        liderAtivo = true;
                        System.out.println("lider: " + idLider);
                        notificarListenerSobreRecebimento(msg);
                        notificarListenerSobreNovoLider(idLider);
                    } else if (msg instanceof MensagemResponse) {
                        MensagemResponse mr = (MensagemResponse) msg;
                        if (mr.getIdDestino() == id) {
                            liderAtivo = true;
                            System.out.println("resposta recebida; lider ativo");
                            notificarListenerSobreRecebimento(msg);
                        }
                    } else if (msg instanceof MensagemAlive) {
                        liderAtivo = true;
                        System.out.println("processo maior presente");
                        notificarListenerSobreRecebimento(msg);
                    }
                } else if (msg.getIdEmissor() < id) {
                    if (msg instanceof MensagemEleicao) {
                        enviarMensagemAlive(msg.getIdEmissor());
                        System.out.println("sou processo maior (" + id + " > " + msg.getIdEmissor() + ")");
                        notificarListenerSobreRecebimento(msg);
                        enviarMensagemEleicao();
                    } else if ((msg instanceof MensagemRequest) && (isLider())) {
                        enviarMensagemResponse(msg.getIdEmissor());
                        System.out.println("respondendo a " + msg.getIdEmissor());
                        notificarListenerSobreRecebimento(msg);
                    }
                }
            }
        };
        enviarMensagemEleicao();
    }

    private boolean isLider() {
        return (id == idLider);
    }

    private void enviar(Mensagem m) {
        socket.enviar(m);
        notificarListenerSobreEnvio(m);
    }

    public void enviarMensagemEleicao() {
        Mensagem m = new MensagemEleicao(id);
        enviar(m);
        testarLider();
    }

    public void enviarMensagemCoordenador() {
        Mensagem m = new MensagemCoordenador(id);
        idLider = id;
        liderAtivo = true;
        enviar(m);
        notificarListenerSobreNovoLider(idLider);
    }

    public void enviarMensagemAlive(int idDestino) {
        Mensagem m = new MensagemAlive(id, idDestino);
        enviar(m);
    }

    public void enviarMensagemRequest(int idDestino) {
        Mensagem m = new MensagemRequest(id, idDestino);
        enviar(m);
        testarLider();
    }

    public void enviarMensagemResponse(int idDestino) {
        Mensagem m = new MensagemResponse(id, idDestino);
        enviar(m);
    }

    public void testarLider() {
        liderAtivo = false;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    if (!liderAtivo) {
                        enviarMensagemCoordenador();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        new Thread(r).start();
    }

    private void notificarListenerSobreRecebimento(Mensagem m) {
        if (listener != null) {
            listener.eventoNovaMensagemRecebida(m);
        }
    }

    private void notificarListenerSobreEnvio(Mensagem m) {
        if (listener != null) {
            listener.eventoNovaMensagemEnviada(m);
        }
    }

    private void notificarListenerSobreNovoLider(int idLider) {
        if (listener != null) {
            listener.eventoNovoCoordenador(idLider);
        }
    }
}
