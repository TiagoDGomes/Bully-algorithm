package algoritmoeleicao;

import algoritmoeleicao.view.JPrincipal;
import javax.swing.JOptionPane;

public class Principal {
    public static Processo processo = new Processo();
    public static void main(String[] args) {
        
        String i = JOptionPane.showInputDialog("Digite um ID");
        try {
            int id = Integer.parseInt(i);
            processo.setId(id);
            Thread t = new Thread(processo);
            t.start();
            JPrincipal j = new JPrincipal();
            j.setVisible(true);
        } catch (NumberFormatException numberFormatException) {
        }

    }
}
