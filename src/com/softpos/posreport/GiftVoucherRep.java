package com.softpos.posreport;

import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import database.MySQLConnect;
import java.sql.Statement;
import javax.swing.JOptionPane;
import program.POSHWSetup;
import program.PPrint;
import program.PUtility;
import program.PublicVar;
import program.Value;

public class GiftVoucherRep extends javax.swing.JDialog {
    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy(HH:mm)", Locale.ENGLISH);
    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    Date date = new Date();
    PPrint prn = new PPrint();
    private POSHWSetup POSHW;

    /** Creates new form GiftVoucherRep */
    public GiftVoucherRep(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtMacNo1.setText("");
        txtMacNo2.setText("ZZZ");
        txtCashNo1.setText("");
        txtCashNo2.setText("ZZZZZZ");
        
        POSHW = POSHWSetup.Bean(Value.getMacno());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMacNo1 = new javax.swing.JTextField();
        txtMacNo2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCashNo1 = new javax.swing.JTextField();
        txtCashNo2 = new javax.swing.JTextField();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานการรับชำระด้วยบัตรของขวัญ ");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 3));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("หมายเลขเครื่อง");

        txtMacNo1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMacNo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMacNo1KeyPressed(evt);
            }
        });

        txtMacNo2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMacNo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMacNo2KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("รหัสพนักงาน");

        txtCashNo1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCashNo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCashNo1KeyPressed(evt);
            }
        });

        txtCashNo2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCashNo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCashNo2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCashNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCashNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCashNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCashNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK.setText("F5- พิมพ์");
        bntOK.setFocusable(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntExit.setText("ESC- ออก");
        bntExit.setFocusable(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void txtMacNo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMacNo1KeyPressed
if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtMacNo2.requestFocus();

    }
}//GEN-LAST:event_txtMacNo1KeyPressed

private void txtMacNo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMacNo2KeyPressed
if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtCashNo1.requestFocus();
    }
}//GEN-LAST:event_txtMacNo2KeyPressed

private void txtCashNo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashNo1KeyPressed
if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtCashNo2.requestFocus();
    }
}//GEN-LAST:event_txtCashNo1KeyPressed

private void txtCashNo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashNo2KeyPressed
if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtMacNo1.requestFocus();
    }
}//GEN-LAST:event_txtCashNo2KeyPressed

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    bntOKClick();
}//GEN-LAST:event_bntOKActionPerformed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed

public void bntOKClick() {
        String MacNo1 = txtMacNo1.getText();
        String MacNo2 = txtMacNo2.getText();
        String CashNo1 = txtCashNo1.getText() ;
        String CashNo2 = txtCashNo2.getText() ;
        
        if(Value.printdriver){
            JOptionPane.showMessageDialog(null, Value.driverNotSupport);
        }else if (!Value.getComPort().equals("NONE")) {
            if (prn.OpenPrint(Value.getComPort())) {
                prn.InitPrinter();
                prn.print(POSHW.getHeading1());
                prn.print(POSHW.getHeading2());
                prn.print(POSHW.getHeading3());
                prn.print(POSHW.getHeading4());
                prn.print("REG ID :" + Value.MACNO);
                prn.print("       รายงานการรับชำระด้วยบัตรของขวัญ");
                prn.print("          (Gift Voucher Report)");
                prn.print("หมายเลขเครื่อง :" + MacNo1 + " ..." + MacNo2);
                prn.print("รหัสพนักงาน    :" + CashNo1 + " ..." + CashNo2);
                prn.print(" ");
                prn.print(DatefmtThai.format(date) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                prn.print("----------------------------------------");
                prn.print("Code                  NO          Amount");
                prn.print("----------------------------------------");
                int Sumtotal = 0 ;
                Double SumtotalAmount = 0.0 ;
                try {
                    Statement stmt =  MySQLConnect.con.createStatement();
                    String SqlQuery = "select *from t_gift where (macno>='" + MacNo1 + "') and (macno<='" + MacNo2 + "') and (cashier>='"+CashNo1+"') and (cashier<='"+CashNo2+"') and (fat='V') order by giftbarcode";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        do {
                             prn.print(PUtility.DataFullR(rec.getString("giftbarcode"), 27)+"  "+PUtility.DataFull(DecFmt.format(rec.getDouble("giftamt")), 9)) ;
                             Sumtotal++ ;
                             SumtotalAmount = SumtotalAmount+rec.getDouble("giftamt") ;
                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                prn.print("----------------------------------------");
                prn.print("Sum-Total Gift "+PUtility.DataFull(IntFmt.format(Sumtotal), 6)+"     "+PUtility.DataFull(DecFmt.format(SumtotalAmount), 13));
                prn.print("----------------------------------------");
                prn.print(" ");
                prn.print(" ");
                prn.print(" ");                
                prn.CutPaper();
                prn.closePrint();
            }
        }

        txtMacNo1.requestFocus();
    }

    public void bntExitClick() {
        this.dispose();
    }

    public void inputfrombnt(String str) {
        if (txtMacNo1.hasFocus()) {
            String tempstr;
            tempstr = txtMacNo1.getText();
            tempstr = tempstr + str;
            txtMacNo1.setText(tempstr);
        }
        if (txtMacNo2.hasFocus()) {
            String tempstr;
            tempstr = txtMacNo2.getText();
            tempstr = tempstr + str;
            txtMacNo2.setText(tempstr);
        }
        if (txtCashNo1.hasFocus()) {
            String tempstr;
            tempstr = txtCashNo1.getText();
            tempstr = tempstr + str;
            txtCashNo1.setText(tempstr);
        }
        if (txtCashNo2.hasFocus()) {
            String tempstr;
            tempstr = txtMacNo2.getText();
            tempstr = tempstr + str;
            txtCashNo2.setText(tempstr);
        }

    }

    public void ProcessChkKey(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            bntExitClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            bntOKClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtMacNo1.hasFocus()) {
                txtMacNo2.requestFocus();
            }
            if (txtMacNo2.hasFocus()) {
                txtCashNo1.requestFocus();
            }
            if (txtCashNo1.hasFocus()) {
                txtCashNo2.requestFocus();
            }
            if (txtCashNo2.hasFocus()) {
                txtMacNo1.requestFocus();
            }

        }
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GiftVoucherRep dialog = new GiftVoucherRep(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCashNo1;
    private javax.swing.JTextField txtCashNo2;
    private javax.swing.JTextField txtMacNo1;
    private javax.swing.JTextField txtMacNo2;
    // End of variables declaration//GEN-END:variables

}
