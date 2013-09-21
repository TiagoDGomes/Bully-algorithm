/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmoeleicao.mensagem;

/**
 *
 * @author Tiago
 */
public abstract class MensagemDestinada extends Mensagem {

    public MensagemDestinada(int idEmissor, int idDestino) {
        super(idEmissor);
        this.idDestino = idDestino;
    }
    public int idDestino;

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }
}
