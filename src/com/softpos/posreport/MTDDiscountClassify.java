package com.softpos.posreport;

import java.awt.Frame;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import util.DateChooseDialog;
import database.MySQLConnect;
import java.sql.Statement;
import program.POSHWSetup;
import program.PPrint;
import program.PUtility;
import program.PublicVar;
import program.Value;
import soft.virtual.KeyBoardDialog;
import util.MSG;

public class MTDDiscountClassify extends javax.swing.JDialog {

    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat DatefmtShow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat Timefmt = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
    Date TDate1 = new Date();
    Date TDate2 = new Date();
    PPrint prn = new PPrint();
    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    private POSHWSetup POSHW;

    /**
     * Creates new form MTDDiscountClassify
     */
    public MTDDiscountClassify(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtDate1.setText(DatefmtShow.format(date));
        txtDate2.setText(DatefmtShow.format(date));
        InitScreen();

        POSHW = POSHWSetup.Bean(Value.getMacno());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bntExit = new javax.swing.JButton();
        bntOK = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtDate1 = new javax.swing.JFormattedTextField();
        txtDate2 = new javax.swing.JFormattedTextField();
        cmdDateChoose1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cmdDateChoose2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานการขายแบบแยกประเภทส่วนลด (MTD Sale & Discount Classify Report)");

        bntExit.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        bntExit.setText("ESC- ออก");
        bntExit.setFocusable(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        bntOK.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        bntOK.setText("F5- พิมพ์");
        bntOK.setFocusable(false);
        bntOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bntOKMouseReleased(evt);
            }
        });
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ช่วงวันที่ๆต้องการ (Date)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Norasi", 0, 16))); // NOI18N

        txtDate1.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        txtDate1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDate1MouseClicked(evt);
            }
        });
        txtDate1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDate1KeyPressed(evt);
            }
        });

        txtDate2.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        txtDate2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDate2MouseClicked(evt);
            }
        });
        txtDate2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDate2KeyPressed(evt);
            }
        });

        cmdDateChoose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose1.setFocusable(false);
        cmdDateChoose1.setRequestFocusEnabled(false);
        cmdDateChoose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel5.setText("ถึง");

        cmdDateChoose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar.png"))); // NOI18N
        cmdDateChoose2.setFocusable(false);
        cmdDateChoose2.setRequestFocusEnabled(false);
        cmdDateChoose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDateChoose2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bntExit, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(bntOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    bntOKClick();
}//GEN-LAST:event_bntOKActionPerformed

private void txtDate2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate2KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtDate1.requestFocus();
    }
}//GEN-LAST:event_txtDate2KeyPressed

private void cmdDateChoose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose1ActionPerformed
    Point point = cmdDateChoose2.getLocation();
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    txtDate1.setText(ShowDatefmt.format(dcd.getSelectDate().getTime()));
    txtDate1.requestFocus();
}//GEN-LAST:event_cmdDateChoose1ActionPerformed

private void cmdDateChoose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose2ActionPerformed
    Point point = cmdDateChoose2.getLocation();
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(), true, point);
    dcd.setVisible(true);
    // dcd.showDialog(new LookAndFeelFrame(), true, point);
    txtDate2.setText(ShowDatefmt.format(dcd.getSelectDate().getTime()));
    txtDate2.requestFocus();
}//GEN-LAST:event_cmdDateChoose2ActionPerformed

private void bntOKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntOKMouseReleased
// TODO add your handling code here:

}//GEN-LAST:event_bntOKMouseReleased

    private void txtDate1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtDate2.requestFocus();
    }
    }//GEN-LAST:event_txtDate1KeyPressed

    private void txtDate1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDate1MouseClicked
        if(evt.getClickCount()==2){
            KeyBoardDialog.get(txtDate1);
        }
    }//GEN-LAST:event_txtDate1MouseClicked

    private void txtDate2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDate2MouseClicked
        if(evt.getClickCount()==2){
            KeyBoardDialog.get(txtDate2);
        }
    }//GEN-LAST:event_txtDate2MouseClicked

    public void InitScreen() {

        txtDate1.requestFocus();
    }

    public void inputfrombnt(String str) {

        if (txtDate1.hasFocus()) {
            String tempstr = "";
            tempstr = txtDate1.getText();
            tempstr = tempstr + str;
            txtDate1.setText(tempstr);
        }
        if (txtDate2.hasFocus()) {
            String tempstr = "";
            tempstr = txtDate2.getText();
            tempstr = tempstr + str;
            txtDate2.setText(tempstr);
        }

    }

    public void bntExitClick() {
        this.dispose();
    }

    public void Action() {
        String TDate = txtDate1.getText();

        try {
            TDate1 = DatefmtShow.parse(txtDate1.getText());
            TDate2 = DatefmtShow.parse(txtDate2.getText());
            if (!PUtility.ChkValidDate(TDate1)) {
                txtDate1.requestFocus();
            }
            if (!PUtility.ChkValidDate(TDate2)) {
                txtDate2.requestFocus();
            }
            ProcessProc();
            InitScreen();
        } catch (ParseException ex) {
            MSG.ERR(this, "กรุณาป้อนวันที่ให้ถูกต้อง (Format=dd/MM/yyyy EXP 01/01/2009)");
        }
    }

    public void bntOKClick() {
        Action();
    }

    public void ProcessProc() {
        Date SaleDate = new Date();
        String PTCode = "";
        Double SumD = 0.0;
        Double SumC = 0.0;
        if (!Value.getComPort().equals("NONE")) {
            if (prn.OpenPrint(Value.getComPort())) {
                prn.InitPrinter();
                prn.print(POSHW.getHeading1());
                prn.print(POSHW.getHeading2());
                prn.print(POSHW.getHeading3());
                prn.print(POSHW.getHeading4());
                prn.print("REG ID :" + Value.MACNO);
                prn.print("    รายงานการขายแบบแยกประเภทส่วนลด");
                prn.print("          รวมทุกเครื่อง  (MTD)");
                prn.print("ช่วงวันที่ : " + DatefmtShow.format(TDate1) + "..." + DatefmtShow.format(TDate2));
                prn.print(" ");
                Date dateP = new Date();
                prn.print(DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                prn.print("----------------------------------------");
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String SqlQuery = "Select s_bran,sum(dept_sum),sum(normal_sale),sum(promotion_sale),sum(normal_disc),sum(promotion_disc),sum(net_sale),sum(normal_net),sum(promotion_net),sum(earnest) from gpheader "
                            + "where (s_date>='" + Datefmt.format(TDate1) + "') and (s_date<='" + Datefmt.format(TDate2) + "') group by s_bran ";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        do {
                            prn.print(PUtility.DataFullR("ยอดขายตามป้าย(ก่อนส่วนลด)        ", 26) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(dept_sum)")), 13));
                            prn.print(PUtility.DataFullR("ยอด Gross ปกติ                 ", 26) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(normal_sale)")), 13));
                            prn.print(PUtility.DataFullR("ยอด Gross Promotion           ", 26) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(promotion_sale)")), 13));
                            prn.print(PUtility.DataFullR("ส่วนลด GP ปกติ                  ", 26) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(normal_disc)")), 13));
                            prn.print(PUtility.DataFullR("ส่วนลด GP Promotion            ", 26) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(promotion_disc)")), 13));
                            prn.print(PUtility.DataFullR("หักเงินมัดจำ                     ", 26) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(earnest)")), 13));
                            prn.print(PUtility.DataFullR("ยอดขายสุทธิ                     ", 26) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(net_sale)")), 13));
                            prn.print("*****ยอดส่งห้าง*****");
                            prn.print(PUtility.DataFullR("ยอดขายสุทธิ GP ปกติ              ", 26) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(normal_net)")), 13));
                            prn.print(PUtility.DataFullR("ยอดขายสุทธิ GP Promotion        ", 26) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(promotion_net)")), 13));
                            prn.print(PUtility.DataFullR("ยอดขายสุทธิ                     ", 26) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(net_sale)")), 13));
                            prn.print("-----------------------------------------");
                            prn.print("     ***** รายละเอียดการให้ส่วนลด *****     ");
                            prn.print("-----------------------------------------");
                            prn.print("ประเภท/ชื่อรายการ");
                            prn.print("     ยอดขาย  จำนวนชิ้น   ส่วนลด        สุทธิ");
                            prn.print("-----------------------------------------");
                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String SqlQuery = "select s_bran,p_type,p_code,p_name,sum(p_gross),sum(p_qty),sum(p_disc),sum(p_net) from gpdetail "
                            + "where (s_date>='" + Datefmt.format(TDate1) + "') and (s_date<='" + Datefmt.format(TDate2) + "') group by p_type,p_code ";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        prn.print("1) Promotion ");
                        do {
                            if (rec.getString("p_type").equals("-P")) {
                                SumC = SumC + rec.getDouble("sum(p_disc)");
                                SumD = SumD + rec.getDouble("sum(p_net)");
                                prn.print("  --" + rec.getString("p_name"));
                                prn.print("  " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_gross)")), 10) + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(p_qty)")), 6) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_disc)")), 10) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_net)")), 12));

                            }

                        } while (rec.next());
                    }
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        prn.print("2) ลดตามรายการ ");
                        do {
                            if (rec.getString("p_type").equals("-I")) {
                                SumC = SumC + rec.getDouble("sum(p_disc)");
                                SumD = SumD + rec.getDouble("sum(p_net)");
                                prn.print("  " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_gross)")), 10) + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(p_qty)")), 6) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_disc)")), 10) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_net)")), 12));
                            }

                        } while (rec.next());
                    }
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        prn.print("3) ส่วนลดเทศกาล ");
                        do {
                            if (rec.getString("p_type").equals("-F")) {
                                SumC = SumC + rec.getDouble("sum(p_disc)");
                                SumD = SumD + rec.getDouble("sum(p_net)");
                                prn.print("  " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_gross)")), 10) + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(p_qty)")), 6) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_disc)")), 10) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_net)")), 12));
                            }

                        } while (rec.next());
                    }

                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        prn.print("4) ส่วนลดคูปอง ");
                        do {
                            if (rec.getString("p_type").equals("-S")) {
                                SumC = SumC + rec.getDouble("sum(p_disc)");
                                SumD = SumD + rec.getDouble("sum(p_net)");
                                prn.print("  " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_gross)")), 10) + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(p_qty)")), 6) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_disc)")), 10) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_net)")), 12));
                            }

                        } while (rec.next());
                    }

                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        prn.print("5) ส่วนลดคูปองพิเศษ ");
                        do {
                            if (rec.getString("p_type").equals("-C")) {
                                SumC = SumC + rec.getDouble("sum(p_disc)");
                                SumD = SumD + rec.getDouble("sum(p_net)");
                                prn.print("  " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_gross)")), 10) + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(p_qty)")), 6) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_disc)")), 10) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_net)")), 12));
                            }

                        } while (rec.next());
                    }

                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        prn.print("6) ส่วนลดพนักงาน ");
                        do {
                            if (rec.getString("p_type").equals("-E")) {
                                SumC = SumC + rec.getDouble("sum(p_disc)");
                                SumD = SumD + rec.getDouble("sum(p_net)");
                                prn.print("  " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_gross)")), 10) + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(p_qty)")), 6) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_disc)")), 10) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_net)")), 12));
                            }

                        } while (rec.next());
                    }

                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        prn.print("7) ส่วนลดสมาชิก ");
                        do {
                            if (rec.getString("p_type").equals("-M")) {
                                SumC = SumC + rec.getDouble("sum(p_disc)");
                                SumD = SumD + rec.getDouble("sum(p_net)");
                                prn.print("  " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_gross)")), 10) + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(p_qty)")), 6) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_disc)")), 10) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_net)")), 12));
                            }

                        } while (rec.next());
                    }

                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        prn.print("8) ส่วนลด Trainee ");
                        do {
                            if (rec.getString("p_type").equals("-T")) {
                                SumC = SumC + rec.getDouble("sum(p_disc)");
                                SumD = SumD + rec.getDouble("sum(p_net)");
                                prn.print("  " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_gross)")), 10) + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(p_qty)")), 6) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_disc)")), 10) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_net)")), 12));
                            }

                        } while (rec.next());
                    }

                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        prn.print("9) ส่วนลดบาท ");
                        do {
                            if (rec.getString("p_type").equals("-B")) {
                                SumC = SumC + rec.getDouble("sum(p_disc)");
                                SumD = SumD + rec.getDouble("sum(p_net)");
                                prn.print("  " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_gross)")), 10) + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(p_qty)")), 6) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_disc)")), 10) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_net)")), 12));
                            }

                        } while (rec.next());
                    }

                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        prn.print("10) หักคืนเงินมัดจำ ");
                        do {
                            if (rec.getString("p_type").equals("-A")) {
                                SumC = SumC + rec.getDouble("sum(p_disc)");
                                SumD = SumD + rec.getDouble("sum(p_net)");
                                prn.print("  " + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_gross)")), 10) + PUtility.DataFull(IntFmt.format(rec.getDouble("sum(p_qty)")), 6) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_disc)")), 10) + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(p_net)")), 12));
                            }

                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                prn.print("----------------------------------------");
                prn.print("                  " + PUtility.DataFull(DecFmt.format(SumC), 10) + PUtility.DataFull(DecFmt.format(SumD), 12));
                prn.print("----------------------------------------");
                prn.print(" ");
                prn.print(" ");
                prn.print(" ");
                
                prn.CutPaper();
                prn.closePrint();
            } else {
//                PUtility.showError("เครื่องพิมพ์ใบกำกับภาษีไม่สามารถพิมพ์ได้ ...");
            }
        }
        InitScreen();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MTDDiscountClassify dialog = new MTDDiscountClassify(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton cmdDateChoose1;
    private javax.swing.JButton cmdDateChoose2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JFormattedTextField txtDate1;
    private javax.swing.JFormattedTextField txtDate2;
    // End of variables declaration//GEN-END:variables

}
