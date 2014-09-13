package com.softpos.floorplan;

import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.table.JTableHeader;


public class ShortKeyDialog extends javax.swing.JDialog {

    public ShortKeyDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        loadTableDefault();
        tb1.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ตีย์ลัดต่างๆ ที่ใช้ระบบ");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        jLabel1.setText("Key ก่อนเปิดโต๊ะอาหาร");

        tb1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        tb1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"F1", "Open Floor Plan (เปิดโต๊ะ)"},
                {"F2", "Customer Count (ระบุจำนวนลูกค้า)"},
                {"F11", "Member (ระบบสมาชิก)"},
                {"F8", "Paid In (นำเงินเข้าลิ้นชักเก็บเงิน)"},
                {"F9", "Paid Out (นำเงินออกจากลิ้นชักเก็บเงิน)"},
                {"F6", "Duplicate Bill (พิมพ์สำเนาบิล)"},
                {"F7", "Refund Bill (ยกเลิกบิล)"},
                {"F12", "AR Payment (ชำระแบบลูกหนี้การค้า)"},
                {"ESC", "Logout (ออกจากระบบ)"},
                {"F5", "Table Available (ตรวจสอบโต๊ะ)"}
            },
            new String [] {
                "Key_Name", "Key_Control"
            }
        ));
        tb1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tb1);
        if (tb1.getColumnModel().getColumnCount() > 0) {
            tb1.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb1.getColumnModel().getColumn(1).setPreferredWidth(350);
        }

        tb2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        tb2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"F1", "Find PLU List (ค้นหารายการสินค้า)"},
                {"F3", "Hold Table (พักโต๊ะ)"},
                {"F4", "Payment Screen (รับชำระเงิน)"},
                {"F6", "Print Chekc Bill (พิมพ์บิลตรวจสอบ)"},
                {"F9", "Customer Count (ระบุจำนวนลูกค้า)"}
            },
            new String [] {
                "Key_Name", "Key_Control"
            }
        ));
        jScrollPane2.setViewportView(tb2);
        if (tb2.getColumnModel().getColumnCount() > 0) {
            tb2.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb2.getColumnModel().getColumn(1).setPreferredWidth(350);
        }

        jButton1.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        jButton1.setText("Close / ปิดหน้าต่าง");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        jLabel2.setText("Key หลังจากเปิดโต๊ะอาหาร");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tb1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            dispose();
        }
    }//GEN-LAST:event_tb1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShortKeyDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShortKeyDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShortKeyDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShortKeyDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ShortKeyDialog dialog = new ShortKeyDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tb1;
    private javax.swing.JTable tb2;
    // End of variables declaration//GEN-END:variables

    private void loadTableDefault() {
        JTableHeader tbH1 = tb1.getTableHeader();
        tbH1.setFont(new Font("Norasi", Font.BOLD, 14));        
        tb1.setRowHeight(25);
        tb1.setShowGrid(true);
        
        JTableHeader tbH2 = tb2.getTableHeader();
        tbH2.setFont(new Font("Norasi", Font.BOLD, 14));
        tb2.setRowHeight(25);
        tb2.setShowGrid(true);
    }
}
