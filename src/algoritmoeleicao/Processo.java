package algoritmoeleicao;

import algoritmoeleicao.mensagem.Mensagem;
import algoritmoeleicao.mensagem.MensagemAlive;
import algoritmoeleicao.mensagem.MensagemCoordenador;
import algoritmoeleicao.mensagem.MensagemEleicao;
import algoritmoeleicao.mensagem.MensagemRequest;
import algoritmoeleicao.mensagem.MensagemResponse;
import algoritmoeleicao.view.JProcesso;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago
 */
public class Processo {

    private int id = 3;
    private int idLider = 5;
    private boolean liderAtivo = false;
    private SimpleSocket socket;
    private JProcesso listener;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void enviarMensagemDeEleicao() {
        escrever("Enviando mensagem de eleição...");
        listener.setEleicao(true);
        socket.enviar(new MensagemEleicao(id));
        testarLider();

    }

    public void enviarMensagemCoordenador() {
        escrever("Enviando mensagem de coordenador...");
        socket.enviar(new MensagemCoordenador(id));
        listener.setCoordenador(true);
        listener.setIdLider(id);
        idLider = id;
    }

    public void enviarRequest() {
        escrever("Enviando novo request...");
        socket.enviar(new MensagemRequest(id, idLider));
        testarLider();

    }

    public void registrarFrameListener(JProcesso j) {
        listener = j;
    }

    public void testarLider() {
        Thread t = new Thread() {
            @Override
            public void run() {
                if (id != idLider) {
                    escrever("Aguardando líder...");
                    esperar(3);
                    if (liderAtivo) {
                        escrever("Líder: " + idLider);
                    } else {
                        escrever("Não há líder. Eu sou o novo líder! Enviar mensagem aos demais.");
                        enviarMensagemCoordenador();
                    }
                }
            }

            public void esperar(int tempo) {
                try {
                    //<editor-fold defaultstate="collapsed" desc="Esperar">
                    Thread.sleep(tempo * 2000);
                    //</editor-fold>
                } catch (InterruptedException ex) {
                    Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.start();
    }

    public boolean isLider() {
        return (id == idLider);
    }

    private void tratarMensagem(Mensagem msg) {
        // Se for uma mensagem de eleição e de alguem com um id menor
        listener.eventoRecebido(msg);
        if (msg instanceof MensagemEleicao) {
            escrever("Mensagem de eleição recebida de " + msg.getIdEmissor());
            // Enviar uma mensagem para desistir ao emissor
            escrever("Desista " + msg.getIdEmissor() + "!");

            socket.enviar(new MensagemAlive(id, msg.getIdEmissor()));
            // Enviar uma mensagem de eleição aos maiores, passando o seu id           
            escrever("Eleição.");
            enviarMensagemDeEleicao();
            // Senão, se for uma mensagem de coordenador
        } else if (msg instanceof MensagemCoordenador) {
            // Definir o líder
            idLider = msg.getIdEmissor();
            escrever("MensagemCoordenador recebida: Definindo líder " + idLider + "...");
            liderAtivo = true;
            listener.setIdLider(idLider);


        } else if (msg instanceof MensagemAlive) {
            MensagemAlive ma = (MensagemAlive) msg;
            if (ma.getIdDestino() == id) {
                desistir();
            }
            listener.setAlive(true);
        } else if (msg instanceof MensagemRequest) {
            socket.enviar(new MensagemResponse(id, msg.getIdEmissor()));

        } else if (msg instanceof MensagemResponse) {
            liderAtivo = true;

        }


    }

    private void desistir() {
        escrever("Desistindo de ser líder... Há alguém com prioridade maior que eu.");
        liderAtivo = true;
    }

    private void escrever(String texto) {
        System.out.println(texto);
    }

    public void iniciar() {
        this.socket = new SimpleSocket(12345) {
            //<editor-fold defaultstate="collapsed" desc="Tratar o recebimento">
            @Override
            public void comandoRecebido(Serializable dados) {
                // mensagem recebida
                System.out.println("Mensagem recebida. " + dados);
                Mensagem m = (Mensagem) dados;
                if (m.getIdEmissor() != id) { /*if ((m instanceof MensagemEleicao && m.getIdEmissor() < id)
                     || ((m.getIdEmissor() > id) && ((m instanceof MensagemCoordenador) || (m instanceof MensagemAlive)))||
                     (m instanceof MensagemRequest)) { */ {

                        tratarMensagem(m);
                    }
                }
                //}


            }
            //</editor-fold>
        };
        if (isLider()) {
            // Fazer bulling...
            escrever("Bulling! Eu sou o líder!");
            enviarMensagemCoordenador();

            //listener.setCoordenador(true);
        } else {
            enviarMensagemDeEleicao();
        }


    }
}
