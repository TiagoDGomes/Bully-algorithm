package algoritmoeleicao;

import algoritmoeleicao.mensagem.Mensagem;
import algoritmoeleicao.mensagem.MensagemAlive;
import algoritmoeleicao.mensagem.MensagemCoordenador;
import algoritmoeleicao.mensagem.MensagemEleicao;
import algoritmoeleicao.mensagem.MensagemRequest;
import algoritmoeleicao.mensagem.MensagemResponse;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.awt.windows.ThemeReader;

/**
 *
 * @author Tiago
 */
public class Processo implements Runnable {

    private int id = 3;
    private int idLider = 5;
    private boolean liderAtivo = false;
    private SimpleSocket socket;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void enviarMensagemDeEleicao() {
        escrever("Enviando mensagem de eleição...");
        socket.enviar(new MensagemEleicao(id));
        testarLider();

    }

    public void testarLider() {
        Thread t = new Thread() {
            @Override
            public void run() {
                esperar(3);
                if (liderAtivo) {
                    escrever("Líder: " + idLider);
                } else {
                    escrever("Não há líder. Eu sou o novo líder! Enviar mensagem aos demais.");
                    socket.enviar(new MensagemCoordenador(id));
                    idLider = id;
                }
            }

            public void esperar(int tempo) {
                try {
                    //<editor-fold defaultstate="collapsed" desc="Esperar">
                    Thread.sleep(tempo * 1000);
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
            escrever("Definindo líder " + idLider + "...");
            liderAtivo = true;



            // Senão, se for uma mensagem de desistir
        } else if (msg instanceof MensagemAlive) {
            MensagemAlive ma = (MensagemAlive) msg;
            if (ma.getIdDestino() == id) {
                desistir();
            }

        } else if (msg instanceof MensagemRequest) {
            socket.enviar(new MensagemResponse(id, msg.getIdEmissor()));

        } else if (msg instanceof MensagemResponse) {
            liderAtivo = true;

        }


    }

    public Processo() {
        this.socket = new SimpleSocket(12345) {
            //<editor-fold defaultstate="collapsed" desc="Tratar o recebimento">
            @Override
            public void comandoRecebido(Serializable dados) {
                // mensagem recebida
                
                Mensagem m = (Mensagem) dados;
                if (m.getIdEmissor() != id){ /*if ((m instanceof MensagemEleicao && m.getIdEmissor() < id)
                    || ((m.getIdEmissor() > id) && ((m instanceof MensagemCoordenador) || (m instanceof MensagemAlive)))||
                    (m instanceof MensagemRequest)) { */ {
                       System.out.println("Mensagem recebida. " + dados.getClass().getSimpleName());
                       tratarMensagem(m);
                   }
                }
                //}


            }
            //</editor-fold>
        };

    }

    public void enviarRequest() {
        escrever("Novo request...");
        socket.enviar(new MensagemRequest(id, idLider));
        testarLider();
    }

    private void desistir() {
        escrever("Desistindo de ser líder... Há alguém com prioridade maior que eu.");
    }

    private void escrever(String texto) {
        System.out.println(texto);
    }

    @Override
    public void run() {
        if (isLider()) {
            // Fazer bulling...
            escrever("Bulling! Eu sou o líder!");
            socket.enviar(new MensagemCoordenador(id));
        } else {
            enviarMensagemDeEleicao();
        }


    }
}
