package auxiliar;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import mensagem.ListenerMensagem;
import mensagem.Mensagem;
import mensagem.MensagemAlive;
import mensagem.MensagemCoordenador;
import mensagem.MensagemEleicao;
import mensagem.MensagemRequest;
import mensagem.MensagemResponse;
import processo.Processo;

public class JProcesso extends javax.swing.JFrame implements ListenerMensagem {

    Processo processo;

    public JProcesso() {
        //super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        lblReceiveAlive.setVisible(false);
        lblSendAlive.setVisible(false);
        lblReceiveEleicao.setVisible(false);
        lblSendEleicao.setVisible(false);
        lblReceiveCoordenador.setVisible(false);
        lblSendCoordenador.setVisible(false);
        lblReceiveRequest.setVisible(false);
        lblSendRequest.setVisible(false);
        lblReceiveResponse.setVisible(false);
        lblSendResponse.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        lblProcesso = new javax.swing.JLabel();
        cmbId = new javax.swing.JComboBox();
        btStart = new javax.swing.JButton();
        panelMaior = new javax.swing.JPanel();
        lblSendEleicao = new javax.swing.JLabel();
        lblReceiveAlive = new javax.swing.JLabel();
        lblSendRequest = new javax.swing.JLabel();
        lblReceiveResponse = new javax.swing.JLabel();
        lblReceiveCoordenador = new javax.swing.JLabel();
        panelMenor = new javax.swing.JPanel();
        lblReceiveEleicao = new javax.swing.JLabel();
        lblSendAlive = new javax.swing.JLabel();
        lblReceiveRequest = new javax.swing.JLabel();
        lblSendResponse = new javax.swing.JLabel();
        lblSendCoordenador = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblLider = new javax.swing.JLabel();
        btRequest = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProcesso.setText("Processo:");

        cmbId.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));

        btStart.setText("Iniciar");
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });

        panelMaior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSendEleicao.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblSendEleicao.setForeground(new java.awt.Color(0, 0, 204));
        lblSendEleicao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSendEleicao.setText("Eleição ->");
        panelMaior.add(lblSendEleicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 190, -1));

        lblReceiveAlive.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblReceiveAlive.setText("<- Alive");
        panelMaior.add(lblReceiveAlive, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 53, 246, -1));

        lblSendRequest.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblSendRequest.setForeground(new java.awt.Color(0, 0, 153));
        lblSendRequest.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSendRequest.setText("Request ->");
        panelMaior.add(lblSendRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 81, 190, -1));

        lblReceiveResponse.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblReceiveResponse.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblReceiveResponse.setText("<- Response");
        panelMaior.add(lblReceiveResponse, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 109, 190, -1));

        lblReceiveCoordenador.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblReceiveCoordenador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblReceiveCoordenador.setText("<- Coordenador");
        panelMaior.add(lblReceiveCoordenador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 144, 190, -1));

        panelMenor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblReceiveEleicao.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblReceiveEleicao.setForeground(new java.awt.Color(0, 0, 204));
        lblReceiveEleicao.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblReceiveEleicao.setText("Eleição ->");
        panelMenor.add(lblReceiveEleicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 190, -1));

        lblSendAlive.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblSendAlive.setText("<- Alive");
        panelMenor.add(lblSendAlive, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 46, 190, -1));

        lblReceiveRequest.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblReceiveRequest.setForeground(new java.awt.Color(0, 0, 153));
        lblReceiveRequest.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblReceiveRequest.setText("Request ->");
        panelMenor.add(lblReceiveRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 81, 190, -1));

        lblSendResponse.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblSendResponse.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSendResponse.setText("<- Response");
        panelMenor.add(lblSendResponse, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 109, 180, -1));

        lblSendCoordenador.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblSendCoordenador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSendCoordenador.setText("<- Coordenador");
        panelMenor.add(lblSendCoordenador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 144, 190, -1));

        lblLider.setFont(lblLider.getFont().deriveFont(lblLider.getFont().getStyle() | java.awt.Font.BOLD));
        lblLider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLider.setText("Líder: desconhecido");

        btRequest.setText("Request");
        btRequest.setEnabled(false);
        btRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRequestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btRequest)
                .addGap(179, 179, 179))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLider)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btRequest)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelMenor, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelMaior, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(lblProcesso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btStart)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProcesso)
                    .addComponent(btStart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelMaior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(panelMenor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        processo = new Processo();
        processo.setId(Integer.parseInt((String) cmbId.getModel().getSelectedItem()));
        processo.setListener(this);
        processo.iniciar();
        btStart.setEnabled(false);
        cmbId.setEnabled(false);
        btRequest.setEnabled(true);
    }//GEN-LAST:event_btStartActionPerformed

    private void btRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRequestActionPerformed
        processo.enviarMensagemRequest(processo.getIdLider());
    }//GEN-LAST:event_btRequestActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JProcesso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JProcesso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JProcesso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JProcesso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JProcesso dialog = new JProcesso();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRequest;
    private javax.swing.JButton btStart;
    private javax.swing.JComboBox cmbId;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblLider;
    private javax.swing.JLabel lblProcesso;
    private javax.swing.JLabel lblReceiveAlive;
    private javax.swing.JLabel lblReceiveCoordenador;
    private javax.swing.JLabel lblReceiveEleicao;
    private javax.swing.JLabel lblReceiveRequest;
    private javax.swing.JLabel lblReceiveResponse;
    private javax.swing.JLabel lblSendAlive;
    private javax.swing.JLabel lblSendCoordenador;
    private javax.swing.JLabel lblSendEleicao;
    private javax.swing.JLabel lblSendRequest;
    private javax.swing.JLabel lblSendResponse;
    private javax.swing.JPanel panelMaior;
    private javax.swing.JPanel panelMenor;
    // End of variables declaration//GEN-END:variables

    @Override
    public void eventoNovaMensagemEnviada(Mensagem m) {
        if (m instanceof MensagemEleicao) {
            left2right(lblSendEleicao);
        } else if (m instanceof MensagemRequest) {
            left2right(lblSendRequest);
        }else if (m instanceof MensagemResponse) {
            right2left(lblSendResponse);
        }else if (m instanceof MensagemCoordenador) {
            right2left(lblSendCoordenador);
        }else if (m instanceof MensagemAlive) {
            right2left(lblSendAlive);
        }
    }

    @Override
    public void eventoNovaMensagemRecebida(Mensagem m) {
        if (m instanceof MensagemEleicao) {
            left2right(lblReceiveEleicao);
        } else if (m instanceof MensagemRequest) {
            left2right(lblReceiveRequest);
        }else if (m instanceof MensagemResponse) {
            right2left(lblReceiveResponse);
        }else if (m instanceof MensagemCoordenador) {
            right2left(lblReceiveCoordenador);
        }else if (m instanceof MensagemAlive) {
            right2left(lblReceiveAlive);
        }
    }
    @Override
    public void eventoNovoCoordenador(int id) {
        lblLider.setText("Líder ativo: " + id);
    }
    private void left2right(final JLabel lbl){
        //<editor-fold defaultstate="collapsed" desc="animação label: esquerda para direita">
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    lbl.setVisible(true);
                    for (int i = 0; i <= 5; i++) {
                        Thread.sleep(200);
                        lbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        Thread.sleep(200);
                        lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        Thread.sleep(200);
                        lbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
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
        //</editor-fold>
    }
    private void right2left(final JLabel lbl){
        //<editor-fold defaultstate="collapsed" desc="animação label: direita para a esquerda">
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    lbl.setVisible(true);
                    for (int i = 0; i <= 5; i++) {
                        Thread.sleep(200);
                        lbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        Thread.sleep(200);
                        lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        Thread.sleep(200);
                        lbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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
        //</editor-fold>
    }
    private void blink(final JLabel lbl) {
        //<editor-fold defaultstate="collapsed" desc="animação label: piscar">
        
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
        //</editor-fold>
    }


}
