package algoritmoeleicao.view;

import algoritmoeleicao.Processo;
import algoritmoeleicao.mensagem.Mensagem;
import algoritmoeleicao.mensagem.MensagemAlive;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class JProcesso extends javax.swing.JFrame {

    Processo processo;

    public JProcesso() {
        initComponents();
        lblEleicao.setVisible(false);
        lblRequest.setVisible(false);
        lblAlive.setVisible(false);
        lblLider.setVisible(false);
        lblCoordenador.setVisible(false);
        lblResponse.setVisible(false);
        lblLiderID.setText("");
        btRequest.setVisible(false);
        setSize(217, 400);
        setLocationRelativeTo(null);

    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void eventoRecebido(Mensagem m) {
        if (m instanceof MensagemAlive) {
        }
    }

    private void blink(final JLabel lbl) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <= 5; i++) {
                        Thread.sleep(200);
                        lbl.setVisible(!lbl.isVisible());
                    }
                    lbl.setVisible(true);
                    Thread.sleep(1200);
                    lbl.setVisible(false);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JProcesso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        new Thread(r).start();

    }

    public void setLabelVisible(JLabel lbl, boolean v) {
        lbl.setVisible(v);
        if (v) {
            blink(lbl);
        }
    }

    public void setEleicao(boolean eleicao) {
        setLabelVisible(lblEleicao, eleicao);
    }

    public void setRequest(boolean request) {
        setLabelVisible(lblRequest, request);
    }

    public void setAlive(boolean alive) {
        setLabelVisible(lblAlive, alive);
    }

    public void setLider(boolean lider) {
        this.lblLider.setVisible(lider);
    }

    public void setCoordenador(boolean lider) {
        setLabelVisible(lblCoordenador, lider);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRequest = new javax.swing.JLabel();
        lblEleicao = new javax.swing.JLabel();
        lblLider = new javax.swing.JLabel();
        lblAlive = new javax.swing.JLabel();
        chkSelecionaProcesso = new javax.swing.JCheckBox();
        btRequest = new javax.swing.JButton();
        lblCoordenador = new javax.swing.JLabel();
        cmbID = new javax.swing.JComboBox();
        lblLiderID = new javax.swing.JLabel();
        lblResponse = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblRequest1 = new javax.swing.JLabel();
        lblResponse1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRequest.setFont(lblRequest.getFont().deriveFont(lblRequest.getFont().getStyle() | java.awt.Font.BOLD));
        lblRequest.setForeground(new java.awt.Color(0, 0, 204));
        lblRequest.setText("Request ->");
        getContentPane().add(lblRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, -1));

        lblEleicao.setFont(lblEleicao.getFont().deriveFont(lblEleicao.getFont().getStyle() | java.awt.Font.BOLD));
        lblEleicao.setForeground(new java.awt.Color(0, 0, 255));
        lblEleicao.setText("Eleição ->");
        getContentPane().add(lblEleicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        lblLider.setFont(lblLider.getFont().deriveFont(lblLider.getFont().getStyle() | java.awt.Font.BOLD));
        lblLider.setForeground(new java.awt.Color(255, 0, 0));
        lblLider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLider.setText("Sou líder");
        getContentPane().add(lblLider, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 154, -1));

        lblAlive.setFont(lblAlive.getFont());
        lblAlive.setForeground(new java.awt.Color(102, 102, 0));
        lblAlive.setText("<- Alive");
        getContentPane().add(lblAlive, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, -1, -1));

        chkSelecionaProcesso.setText("Processo #");
        chkSelecionaProcesso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSelecionaProcessoActionPerformed(evt);
            }
        });
        getContentPane().add(chkSelecionaProcesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, -1));

        btRequest.setText("Request");
        btRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRequestActionPerformed(evt);
            }
        });
        getContentPane().add(btRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 330, 190, -1));

        lblCoordenador.setForeground(new java.awt.Color(0, 102, 0));
        lblCoordenador.setText("<- Coordenador");
        getContentPane().add(lblCoordenador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, 10));

        cmbID.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        cmbID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbIDActionPerformed(evt);
            }
        });
        getContentPane().add(cmbID, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 13, 60, -1));

        lblLiderID.setFont(lblLiderID.getFont().deriveFont(lblLiderID.getFont().getStyle() | java.awt.Font.BOLD));
        lblLiderID.setForeground(new java.awt.Color(0, 0, 204));
        lblLiderID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblLiderID.setText("Líder atual:");
        getContentPane().add(lblLiderID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 154, -1));

        lblResponse.setFont(lblResponse.getFont());
        lblResponse.setForeground(new java.awt.Color(0, 102, 0));
        lblResponse.setText("<- Response");
        getContentPane().add(lblResponse, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 10, 230));

        lblRequest1.setFont(lblRequest1.getFont());
        lblRequest1.setForeground(new java.awt.Color(0, 0, 204));
        lblRequest1.setText("Request ->");
        getContentPane().add(lblRequest1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        lblResponse1.setFont(lblResponse1.getFont());
        lblResponse1.setForeground(new java.awt.Color(0, 102, 0));
        lblResponse1.setText("<- Response");
        getContentPane().add(lblResponse1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void chkSelecionaProcessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSelecionaProcessoActionPerformed
        if (chkSelecionaProcesso.getModel().isSelected()) {
            btRequest.setVisible(true);
            processo = new Processo();
            id = Integer.parseInt(cmbID.getModel().getSelectedItem().toString());
            processo.setId(id);
            processo.registrarFrameListener(this);
            processo.iniciar();
            chkSelecionaProcesso.setEnabled(false);
            cmbID.setEnabled(false);
        }
    }//GEN-LAST:event_chkSelecionaProcessoActionPerformed

    public void setIdLider(int id) {
        lblLider.setVisible(id == this.id);
        lblLiderID.setText("Líder atual: " + id);
    }

    public static void main(String[] args) {
        
        //<editor-fold defaultstate="collapsed" desc="LookAndFeel">
        //
        //        try {
        //            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        //                if ("Windows".equals(info.getName())) {
        //                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
        //                    break;
        //                }
        //            }
        //        } catch (Exception ex) {
        //        }
        //</editor-fold>
        
        JProcesso j = new JProcesso();
        j.setVisible(true);


    }
    private void cmbIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbIDActionPerformed
        
    }//GEN-LAST:event_cmbIDActionPerformed

    private void btRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRequestActionPerformed
        processo.enviarRequest();
    }//GEN-LAST:event_btRequestActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRequest;
    private javax.swing.JCheckBox chkSelecionaProcesso;
    private javax.swing.JComboBox cmbID;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAlive;
    private javax.swing.JLabel lblCoordenador;
    private javax.swing.JLabel lblEleicao;
    private javax.swing.JLabel lblLider;
    private javax.swing.JLabel lblLiderID;
    private javax.swing.JLabel lblRequest;
    private javax.swing.JLabel lblRequest1;
    private javax.swing.JLabel lblResponse;
    private javax.swing.JLabel lblResponse1;
    // End of variables declaration//GEN-END:variables
}
