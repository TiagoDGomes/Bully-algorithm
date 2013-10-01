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
    private int idCoordenador;

    public int getIdCoordenador() {
        return idCoordenador;
    }

    public void setIdCoordenador(int idCoordenador) {
        this.idCoordenador = idCoordenador;
    }
    private boolean coordenadorAtivo;
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
                        idCoordenador = msg.getIdEmissor();
                        coordenadorAtivo = true;
                        System.out.println("coordenador: " + idCoordenador);
                        notificarListenerSobreRecebimento(msg);
                        notificarListenerSobreNovoCoordenador(idCoordenador);
                    } else if (msg instanceof MensagemResponse) {
                        MensagemResponse mr = (MensagemResponse) msg;
                        if (mr.getIdDestino() == id) {
                            coordenadorAtivo = true;
                            System.out.println("resposta recebida; coordenador ativo");
                            notificarListenerSobreRecebimento(msg);
                        }
                    } else if (msg instanceof MensagemAlive) {
                        coordenadorAtivo = true;
                        System.out.println("processo maior presente");
                        notificarListenerSobreRecebimento(msg);
                    }
                } else if (msg.getIdEmissor() < id) {
                    if (msg instanceof MensagemEleicao) {
                        enviarMensagemAlive(msg.getIdEmissor());
                        System.out.println("sou processo maior (" + id + " > " + msg.getIdEmissor() + ")");
                        notificarListenerSobreRecebimento(msg);
                        enviarMensagemEleicao();
                    } else if ((msg instanceof MensagemRequest) && (isCoordenador())) {
                        enviarMensagemResponse(msg.getIdEmissor());
                        System.out.println("respondendo a " + msg.getIdEmissor());
                        notificarListenerSobreRecebimento(msg);
                    }
                }
            }
        };
        enviarMensagemEleicao();
    }

    private boolean isCoordenador() {
        return (id == idCoordenador);
    }

    private void enviar(Mensagem m) {
        socket.enviar(m);
        notificarListenerSobreEnvio(m);
    }

    public void enviarMensagemEleicao() {
        Mensagem m = new MensagemEleicao(id);
        enviar(m);
        coordenadorAtivo = false;        
        Runnable r = new Runnable() {
            //<editor-fold defaultstate="collapsed" desc="aguardar se há coordenador">
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                    if (!coordenadorAtivo) {
                        enviarMensagemCoordenador();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //</editor-fold>
        };
        
        new Thread(r).start();
    }

    public void enviarMensagemCoordenador() {
        Mensagem m = new MensagemCoordenador(id);
        idCoordenador = id;
        coordenadorAtivo = true;
        enviar(m);
        notificarListenerSobreNovoCoordenador(idCoordenador);
    }

    public void enviarMensagemAlive(int idDestino) {
        Mensagem m = new MensagemAlive(id, idDestino);
        enviar(m);
    }

    public void enviarMensagemRequest(int idDestino) {
        Mensagem m = new MensagemRequest(id, idDestino);
        enviar(m);
        coordenadorAtivo = false;
        Runnable r = new Runnable() {
            //<editor-fold defaultstate="collapsed" desc="aguardar se o coordenador responde">
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                    if (!coordenadorAtivo) {
                        enviarMensagemEleicao();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //</editor-fold>
        };
        new Thread(r).start();
    }

    public void enviarMensagemResponse(int idDestino) {
        Mensagem m = new MensagemResponse(id, idDestino);
        enviar(m);
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

    private void notificarListenerSobreNovoCoordenador(int idCoordenador) {
        if (listener != null) {
            listener.eventoNovoCoordenador(idCoordenador);
        }
    }
}
