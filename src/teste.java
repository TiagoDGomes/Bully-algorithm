
import algoritmoeleicao.SimpleSocket;
import java.io.Serializable;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tiago
 */
public class teste {

    public static void main(String[] args) {
        final SimpleSocket s = new SimpleSocket(12345) {
            @Override
            public void comandoRecebido(Serializable dados) {
                System.out.println(dados);
            }
        };
        
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Scanner scan = new Scanner(System.in);
                    String texto = scan.nextLine();
                    s.enviar(texto);
                }
            }
        };
    }
}
