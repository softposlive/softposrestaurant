package program;

import database.MySQLConnect;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class SplitBillPayment extends javax.swing.JDialog {
    
    private String tableNo;
    private String table2;

    public SplitBillPayment(java.awt.Frame parent, boolean modal, String tableNo) {
        super(parent, modal);
        initComponents();
        
        this.tableNo = tableNo;
        init();
        table2 = "";
        txtTable1.requestFocus();
    }
    
    public String getTable2(){
        return table2;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtTable1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTop = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtTable2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSecond = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("โปรแกรมแยกรายการชำระเงิน");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Table");

        txtTable1.setEditable(false);
        txtTable1.setBackground(new java.awt.Color(255, 255, 255));
        txtTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTable1KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("( รายการอาหารของโต๊ะหลัก )");

        btnOk.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnOk.setText("F5-ตกลง (OK)");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel.setText("F3-ยกเลิก (Cancel)");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        tbTop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "P", "V", "R_ETD", "PLU Code", "Description", "QTY", "Price", "Amount", "PRType", "Discount", "Baht", "R_Index"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTopMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbTop);
        if (tbTop.getColumnModel().getColumnCount() > 0) {
            tbTop.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Table");

        txtTable2.setEditable(false);
        txtTable2.setBackground(new java.awt.Color(255, 255, 255));
        txtTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("( รายการอาหารของโต๊ะที่ต้องการแยกชำระเงิน )");

        tbSecond.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "P", "V", "R_ETD", "PLU Code", "Description", "QTY", "Price", "Amount", "PRType", "Discount", "Baht", "R_Index"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSecond.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSecondMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbSecond);
        if (tbSecond.getColumnModel().getColumnCount() > 0) {
            tbSecond.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTable2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel4))))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTable2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        //cancel order
        BalanceControl bcontrol = new BalanceControl();
        bcontrol.restoreBalance(txtTable1.getText(), txtTable2.getText());
        
        table2 = "";
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTable1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            table2 = "";
            dispose();
        }
    }//GEN-LAST:event_txtTable1KeyPressed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        if(tbSecond.getRowCount()>0){
            Value.TableSelected = txtTable2.getText();
            table2 = txtTable2.getText();
            dispose();
        }else{
            table2 = "";
            dispose();
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void tbTopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTopMouseClicked
        int rowSelected = tbTop.getSelectedRow();
        if(rowSelected!=-1){
            String table = txtTable1.getText();
            String PCode = tbTop.getValueAt(rowSelected, 3).toString();
            String R_Index = tbTop.getValueAt(rowSelected, 11).toString();

            if (table != null || PCode != null || R_Index != null) {
                BalanceControl balanceControl = new BalanceControl();

                // Move product
                if(balanceControl.copyProductTo(txtTable1.getText(), txtTable2.getText(), R_Index, PCode)){
                    balanceControl.deleteProduct(table, PCode, R_Index);
                }

                // Hold old table
                balanceControl.updateTableHold(table, "");
                
                loadTable(txtTable1.getText());
                loadTableSecond();
            }
        }
    }//GEN-LAST:event_tbTopMouseClicked

    private void tbSecondMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSecondMouseClicked
        int rowSelected = tbSecond.getSelectedRow();
        if(rowSelected!=-1){        
            String table = txtTable1.getText();
            String PCode = tbSecond.getValueAt(rowSelected, 3).toString();
            String R_Index = tbSecond.getValueAt(rowSelected, 11).toString();
            String newTable = txtTable2.getText();

            if (table != null || PCode != null || R_Index != null) {
                BalanceControl balanceControl = new BalanceControl();

                // Move product
                if(balanceControl.copyProductTo(txtTable2.getText(), txtTable1.getText(), R_Index, PCode)){
                    balanceControl.deleteProduct(newTable, PCode, R_Index);
                }
                
                loadTable(txtTable1.getText());
                loadTableSecond();
            }
        }
    }//GEN-LAST:event_tbSecondMouseClicked

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
            java.util.logging.Logger.getLogger(SplitBillPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SplitBillPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SplitBillPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplitBillPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SplitBillPayment dialog = new SplitBillPayment(new javax.swing.JFrame(), true, "1");
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
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbSecond;
    private javax.swing.JTable tbTop;
    private javax.swing.JTextField txtTable1;
    private javax.swing.JTextField txtTable2;
    // End of variables declaration//GEN-END:variables

    public void loadTable(String table){
        DefaultTableModel model = (DefaultTableModel) tbTop.getModel();
        tbTop.setRowHeight(25);
        tbTop.setFont(new Font("Norasi", Font.PLAIN, 14));
        
        int size = model.getRowCount();
        for(int i=0;i<size;i++){
            model.removeRow(0);
        }

        BalanceControl bControl = new BalanceControl();
        ArrayList<BalanceBean> balance = bControl.getAllBalance(txtTable1.getText());
        for (int i = 0; i < balance.size(); i++) {
            BalanceBean bean = (BalanceBean) balance.get(i);
            if(bean.getR_Void().equals("V")){
                continue;
            }

            //load data to modal
            model.addRow(new Object[]{
                "",
                bean.getR_Void(),bean.getR_ETD(), bean.getR_PluCode(), ThaiUtil.ASCII2Unicode(bean.getR_PName()), 
                bean.getR_Quan(),bean.getR_Price(),bean.getR_Total(),bean.getR_PrType(),bean.getR_Discount(), bean.getR_DiscBath(), 
                bean.getR_Index()
            });
        }

        loadTableSecond();
    }
    
    private void loadTableSecond() {
        DefaultTableModel model = (DefaultTableModel) tbSecond.getModel();
        tbSecond.setRowHeight(25);
        tbSecond.setFont(new Font("Norasi", Font.PLAIN, 14));
        
        int size = model.getRowCount();
        for(int i=0;i<size;i++){
            model.removeRow(0);
        }

        BalanceControl bControl = new BalanceControl();
        ArrayList<BalanceBean> balance = bControl.getAllBalance(txtTable2.getText());
        for (int i = 0; i < balance.size(); i++) {
            BalanceBean bean = (BalanceBean) balance.get(i);
            if(bean.getR_Void().equals("V")){
                continue;
            }

            //load data to modal
            model.addRow(new Object[]{
                "",
                bean.getR_Void(),bean.getR_ETD(), bean.getR_PluCode(), ThaiUtil.ASCII2Unicode(bean.getR_PName()), 
                bean.getR_Quan(),bean.getR_Price(),bean.getR_Total(),bean.getR_PrType(),bean.getR_Discount(), bean.getR_DiscBath(), 
                bean.getR_Index()
            });
        }
    }

    private void init() {
        txtTable1.setText(tableNo);
        txtTable2.setText(txtTable1.getText()+"-1");
        
        //create table split
        TableFileControl tableFile = new TableFileControl();
        TableFileBean tBean = tableFile.getData(txtTable1.getText());
        tableFile.createNewTableSplit(tBean, txtTable2.getText());
        
        //backup data to temp balance
        BalanceControl balance = new BalanceControl();
        if(balance.backupBalance(txtTable1.getText())){
            loadTable(txtTable1.getText());
        }else{
            dispose();
        }
    }
}
