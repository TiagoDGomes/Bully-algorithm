
package mensagem;

public interface ListenerMensagem {
    public void eventoNovaMensagemEnviada(Mensagem m);
    public void eventoNovaMensagemRecebida(Mensagem m);
    public void eventoNovoCoordenador(int id);    
}
