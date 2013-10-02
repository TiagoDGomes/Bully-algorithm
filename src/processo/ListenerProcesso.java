
package processo;
import mensagem.Mensagem;

public interface ListenerProcesso {
    public void eventoNovaMensagemEnviada(Mensagem m);
    public void eventoNovaMensagemRecebida(Mensagem m);
    public void eventoNovoCoordenador(int id);    
}
