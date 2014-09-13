package program;

import com.softpos.member.MemberBean;
import database.MySQLConnect;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import util.MSG;

public class VoidPopupDialog extends javax.swing.JDialog {
    
    public static String []VOID_MSG = new String[]{"",""};
    private String tableNo = "";
    private MemberBean memberBean;

    public VoidPopupDialog(java.awt.Frame parent, boolean modal, String tableNo, MemberBean memberBean) {
        super(parent, modal);
        initComponents();
        
        this.memberBean=memberBean;
        this.tableNo = tableNo;
        loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbVoid = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("รายละเอียดการ Void");
        setResizable(false);

        tbVoid.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "รายละเอียด"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbVoid.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbVoid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVoidMouseClicked(evt);
            }
        });
        tbVoid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbVoidKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbVoid);
        if (tbVoid.getColumnModel().getColumnCount() > 0) {
            tbVoid.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbVoid.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 250, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        VOID_MSG[0] = "";
        VOID_MSG[1] = "";
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int rowSel = tbVoid.getSelectedRow();
        if(rowSel!=-1){
            VOID_MSG[0] = tbVoid.getValueAt(rowSel, 0).toString();
            VOID_MSG[1] = tbVoid.getValueAt(rowSel, 1).toString();
            BalanceControl.updateProSerTable(tableNo, memberBean);
            
            dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbVoidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbVoidKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jButton1ActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            jButton2ActionPerformed(null);
        }
    }//GEN-LAST:event_tbVoidKeyPressed

    private void tbVoidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVoidMouseClicked
        if(evt.getClickCount()==2){
            jButton1ActionPerformed(null);
        }
    }//GEN-LAST:event_tbVoidMouseClicked

    public static void main(String args[]) {
        new MySQLConnect();
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
            java.util.logging.Logger.getLogger(VoidPopupDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VoidPopupDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VoidPopupDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VoidPopupDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VoidPopupDialog dialog = new VoidPopupDialog(new javax.swing.JFrame(), true, "1", null);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbVoid;
    // End of variables declaration//GEN-END:variables

    private void loadData() {
        DefaultTableModel model = (DefaultTableModel)tbVoid.getModel();
        tbVoid.setRowHeight(30);
        tbVoid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        int size = model.getRowCount();
        for(int i=0;i<size;i++){
            model.removeRow(0);
        }
        try {
            String sql = "select * from voidmsg";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            while(rs.next()){
                model.addRow(new Object[]{
                    rs.getString("VCode"),
                    ThaiUtil.ASCII2Unicode(rs.getString("VName"))
                });
            }
            
            rs.close();
        } catch (Exception e) {
            MSG.ERR_MSG(this, e.getMessage());
        }
    }
}
