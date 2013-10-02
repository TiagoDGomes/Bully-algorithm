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

    public static final int BULLY_ID = 5;
    private boolean coordenadorAtivo;
    private int id;
    private int idCoordenador;
    private SimpleSocket socket;
    private ListenerMensagem listener;

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public int getIdCoordenador() {
        return idCoordenador;
    }

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

    private boolean isCoordenador() {
        return (id == idCoordenador);
    }
    //</editor-fold>

    public void iniciar() {
        socket = new SimpleSocket(23222) {
            //<editor-fold defaultstate="collapsed" desc="Tratamento de mensagens recebidas"> 
            @Override
            public void comandoRecebido(Serializable dados) {

                Mensagem msg = (Mensagem) dados;

                if (msg.getIdEmissor() > id) { // Processos maiores enviando mensagens...
                    if (msg instanceof MensagemCoordenador) {

                        //<editor-fold defaultstate="collapsed" desc="Mensagem de definição de coordenador recebida.">
                        idCoordenador = msg.getIdEmissor();
                        coordenadorAtivo = true;
                        System.out.println("[R] MensagemCoordenador - " + idCoordenador);
                        notificarListenerSobreRecebimento(msg);
                        notificarListenerSobreNovoCoordenador(idCoordenador);
                        //</editor-fold>

                    } else if (msg instanceof MensagemResponse) {

                        //<editor-fold defaultstate="collapsed" desc="O coordenador respondeu">
                        MensagemResponse mr = (MensagemResponse) msg;
                        if (mr.getIdDestino() == id) {
                            coordenadorAtivo = true;
                            System.out.println("resposta recebida; coordenador ativo");
                            notificarListenerSobreRecebimento(msg);
                        }
                        //</editor-fold>

                    } else if (msg instanceof MensagemAlive) {

                        //<editor-fold defaultstate="collapsed" desc="A eleição revelou que o coordenador está ativo e que deve desistir da eleição">
                        MensagemAlive ma = (MensagemAlive) msg;
                        if (ma.getIdDestino() == id) {
                            System.out.println("[R] MensagemAlive - Um processo maior está presente: " + ma.getIdEmissor());
                            coordenadorAtivo = true;
                            notificarListenerSobreRecebimento(msg);
                        }
                        //</editor-fold>

                    }
                } else if (msg.getIdEmissor() < id) { // Processos menores enviando mensagens...
                    if (msg instanceof MensagemEleicao) {

                        //<editor-fold defaultstate="collapsed" desc="Um processo de ID menor solicitou eleição. Enviar mensagem para desistir">
                        System.out.println("[R] MensagemEleicao - Mas sou um processo maior (" + id + " > " + msg.getIdEmissor() + ")");
                        enviarMensagemAlive(msg.getIdEmissor());
                        notificarListenerSobreRecebimento(msg);
                        enviarMensagemEleicao();
                        //</editor-fold>

                    } else if ((msg instanceof MensagemRequest) && (isCoordenador())) { //  Tratar Requests se este processo for coordenador

                        //<editor-fold defaultstate="collapsed" desc="Um processo solicitou Request. Tem que enviar Response, ou o processo que solicitou irá fazer eleição">
                        System.out.println("[R] MensagemRequest - responder a " + msg.getIdEmissor());
                        enviarMensagemResponse(msg.getIdEmissor());
                        notificarListenerSobreRecebimento(msg);

                        //</editor-fold>

                    }
                }
            }
            //</editor-fold>
        };
        if (id == BULLY_ID) {
            enviarMensagemCoordenador();
        } else {
            enviarMensagemEleicao();
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Métodos para o envio das mensagens">
    private void enviar(Mensagem m) {
        socket.enviar(m);
        notificarListenerSobreEnvio(m);
    }

    public void enviarMensagemEleicao() {
        Mensagem m = new MensagemEleicao(id);
        enviar(m);
        //<editor-fold defaultstate="collapsed" desc="Aguardar o resultado da eleição. Se não responderem, tornar-se coordenador">
        coordenadorAtivo = false;
        Runnable r = new Runnable() {
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
        };
        new Thread(r).start();
        //</editor-fold>

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
        //<editor-fold defaultstate="collapsed" desc="Aguardar se o coordenador responde. Se não responder, fazer eleição">
        coordenadorAtivo = false;
        Runnable r = new Runnable() {
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
        };
        new Thread(r).start();
        //</editor-fold>
    }

    public void enviarMensagemResponse(int idDestino) {
        Mensagem m = new MensagemResponse(id, idDestino);
        enviar(m);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Notificações ao listener">
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
    //</editor-fold>
}
