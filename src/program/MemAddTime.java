package program;

import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import database.MySQLConnect;
import java.sql.Statement;

public class MemAddTime extends javax.swing.JDialog {

    SimpleDateFormat DatefmtThai = new SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.ENGLISH);
    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    SimpleDateFormat GetYear = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    SimpleDateFormat GetMonth = new SimpleDateFormat("MM", Locale.ENGLISH);
    SimpleDateFormat GetDate = new SimpleDateFormat("dd", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    Date date = new Date();
    PPrint prn = new PPrint();
    private POSHWSetup POSHW;

    /** Creates new form MemAddTime */
    public MemAddTime(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ClearVariable();
        POSHW = POSHWSetup.Bean(Value.getMacno());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        M_End = new javax.swing.JFormattedTextField();
        M_NewDate = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        M_Code = new javax.swing.JTextField();
        M_Name = new javax.swing.JTextField();
        M_Score = new javax.swing.JFormattedTextField();
        M_Amount = new javax.swing.JFormattedTextField();
        M_Date = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        ShowMsg = new javax.swing.JLabel();
        Cash = new javax.swing.JRadioButton();
        Score = new javax.swing.JRadioButton();
        M_ScoreAmount = new javax.swing.JFormattedTextField();
        bntDelete = new javax.swing.JButton();
        bntOK = new javax.swing.JButton();
        BntExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("โปรแกรมต่ออายุบัตรสมาชิก");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel1.setText("รหัสสมาชิก");

        jLabel2.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel2.setText("ชื่อสมาชิก");

        jLabel3.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel3.setText("แต้มสะสมถึงเมื่อวานนี้");

        jPanel2.setBackground(new java.awt.Color(131, 166, 245));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel4.setText("วันหมดอายุ");

        jLabel5.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel5.setText("ต่ออายุถึงวันที่");

        M_End.setBackground(new java.awt.Color(247, 248, 199));
        M_End.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        M_End.setFocusable(false);
        M_End.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        M_End.setMargin(new java.awt.Insets(0, 3, 0, 0));

        M_NewDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        M_NewDate.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        M_NewDate.setMargin(new java.awt.Insets(0, 3, 0, 0));
        M_NewDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                M_NewDateKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(M_End, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(M_NewDate, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(M_End, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(M_NewDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel6.setText("ค่าธรรมเนียมการต่ออายุ");

        M_Code.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        M_Code.setText("jTextField1");
        M_Code.setMargin(new java.awt.Insets(0, 3, 0, 0));
        M_Code.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                M_CodeFocusLost(evt);
            }
        });
        M_Code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                M_CodeKeyPressed(evt);
            }
        });

        M_Name.setBackground(new java.awt.Color(247, 248, 199));
        M_Name.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        M_Name.setText("jTextField2");
        M_Name.setFocusable(false);
        M_Name.setMargin(new java.awt.Insets(0, 3, 0, 0));

        M_Score.setBackground(new java.awt.Color(247, 248, 199));
        M_Score.setText("jFormattedTextField1");
        M_Score.setFocusable(false);
        M_Score.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        M_Score.setMargin(new java.awt.Insets(0, 3, 0, 0));

        M_Amount.setText("jFormattedTextField4");
        M_Amount.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        M_Amount.setMargin(new java.awt.Insets(0, 3, 0, 0));
        M_Amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                M_AmountKeyPressed(evt);
            }
        });

        M_Date.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        M_Date.setFocusable(false);
        M_Date.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        M_Date.setMargin(new java.awt.Insets(0, 3, 0, 0));
        M_Date.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                M_DateFocusLost(evt);
            }
        });
        M_Date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                M_DateKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel7.setText("วันที่ต่ออายุ");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/magnifying glass.jpg"))); // NOI18N
        jButton5.setFocusable(false);
        jButton5.setRequestFocusEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        ShowMsg.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        ShowMsg.setForeground(new java.awt.Color(238, 54, 13));
        ShowMsg.setText("jLabel8");

        buttonGroup1.add(Cash);
        Cash.setSelected(true);
        Cash.setText("เงินสด");
        Cash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CashMouseClicked(evt);
            }
        });

        buttonGroup1.add(Score);
        Score.setText("ใช้แต้มสะสม");
        Score.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ScoreMouseClicked(evt);
            }
        });

        M_ScoreAmount.setText("jFormattedTextField4");
        M_ScoreAmount.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        M_ScoreAmount.setMargin(new java.awt.Insets(0, 3, 0, 0));
        M_ScoreAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                M_ScoreAmountKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(M_Score, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(M_Code, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(3, 3, 3)
                        .addComponent(M_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(M_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(157, 157, 157)
                        .addComponent(ShowMsg))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cash)
                            .addComponent(Score))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(M_ScoreAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(M_Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(M_Code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(M_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(M_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(M_Score, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ShowMsg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cash)
                    .addComponent(M_Amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Score)
                    .addComponent(M_ScoreAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bntDelete.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        bntDelete.setText("F3 ลบข้อมูล (Delete)");
        bntDelete.setFocusable(false);
        bntDelete.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntDeleteActionPerformed(evt);
            }
        });

        bntOK.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        bntOK.setText("F5 ตกลง (OK)");
        bntOK.setFocusable(false);
        bntOK.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        BntExit.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        BntExit.setText("ESC ออก (Exit)");
        BntExit.setFocusable(false);
        BntExit.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BntExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bntDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(BntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(465, 390));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void M_CodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_M_CodeKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    } else
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        M_CodeExit();
    } else
    if (evt.getKeyCode()==KeyEvent.VK_F1) {
       FindMemberClick() ; 
    }
}//GEN-LAST:event_M_CodeKeyPressed

private void bntDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntDeleteActionPerformed
    bntDeleteClick();
}//GEN-LAST:event_bntDeleteActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    FindMemberClick();
}//GEN-LAST:event_jButton5ActionPerformed

private void BntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BntExitActionPerformed
    bntExitClick();
}//GEN-LAST:event_BntExitActionPerformed

private void M_DateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_M_DateKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        ClearVariable();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (!PUtility.ChkDate(M_Date.getText())) {
            M_Date.requestFocus();
        } else {
            M_DateExit();
            M_NewDate.requestFocus();
        }
    }
}//GEN-LAST:event_M_DateKeyPressed

private void M_CodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_M_CodeFocusLost
    M_CodeExit();
}//GEN-LAST:event_M_CodeFocusLost

private void M_DateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_M_DateFocusLost
    M_DateExit();
}//GEN-LAST:event_M_DateFocusLost

private void M_NewDateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_M_NewDateKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        ClearVariable();
    } else
    if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
        M_Amount.requestFocus();
    }
}//GEN-LAST:event_M_NewDateKeyPressed

private void M_AmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_M_AmountKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        ClearVariable();
    } else
    if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
        M_NewDate.requestFocus();
    }
}//GEN-LAST:event_M_AmountKeyPressed

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    bntOKClick() ;
}//GEN-LAST:event_bntOKActionPerformed

private void M_ScoreAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_M_ScoreAmountKeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_M_ScoreAmountKeyPressed

private void CashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CashMouseClicked
// TODO add your handling code here:
    if (Cash.isSelected()) {
        M_Amount.setValue(100);
        M_ScoreAmount.setValue(0);
        M_Amount.requestFocus();
    } else {
        M_Amount.setValue(0);
        M_ScoreAmount.setValue(200);
        M_ScoreAmount.requestFocus();
    }
}//GEN-LAST:event_CashMouseClicked

private void ScoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScoreMouseClicked
// TODO add your handling code here:
    if (!Score.isSelected()) {
        M_Amount.setValue(100);
        M_ScoreAmount.setValue(0);
        M_Amount.requestFocus();
    } else {
        M_Amount.setValue(0);
        M_ScoreAmount.setValue(200);
        M_ScoreAmount.requestFocus();
    }
}//GEN-LAST:event_ScoreMouseClicked
    public void M_DateExit() {
        String TempCode = M_Code.getText();
        String TDate1 = M_Date.getText() ;
        Date TempDate = new Date();
        try {
            TempDate = ShowDatefmt.parse(TDate1);
        } catch (Exception e) {
        }
        if (!M_Code.getText().equals("")) {
            try {
                Statement stmt =  MySQLConnect.con.createStatement();
                String SQLQuery = "Select *from memaddtime where (m_code='" + TempCode + "') and  (m_date='" + Datefmt.format(TempDate) + "')";
                ResultSet rec = stmt.executeQuery(SQLQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    ShowMsg.setText("เพิ่มรายการใหม่");
                } else {
                    M_End.setText(ShowDatefmt.format(rec.getDate("m_enddate")));
                    M_NewDate.setText(ShowDatefmt.format(rec.getDate("m_newdate")));
                    M_Amount.setText(DecFmt.format(rec.getDouble("m_amount")));
                    M_ScoreAmount.setText(DecFmt.format(rec.getDouble("m_scoreamount"))) ;
                    M_Date.setFocusable(false);
                    if (rec.getString("addtype").equals("1")) {
                        Cash.setSelected(true);
                    } else {
                        Score.setSelected(true);  
                    }
                    ShowMsg.setText("รายการเก่า");
                //GetDataValue();
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                PUtility.showError(e.getMessage());
            }
        }
    }

    public void ClearVariable() {
        ShowMsg.setText("");
        M_Code.setText("");
        M_Name.setText("");
        M_Date.setText(ShowDatefmt.format(date));
        M_Score.setText("");
        M_End.setText("");
        M_NewDate.setText("");
        M_Amount.setText("0");
        M_ScoreAmount.setText("0") ;
        Cash.setSelected(true) ;
        M_Date.setFocusable(false);
        M_NewDate.setFocusable(false);
        M_Amount.setFocusable(false);
        M_Code.setFocusable(true);
        M_Code.requestFocus();
    }

    public void FindMemberClick() {
        FindMember frm = new FindMember(null, true);
        frm.setVisible(true);
        if (!PublicVar.ReturnString.equals("")) {
            M_Code.setText(PublicVar.ReturnString);
        }
    }

    public void bntOKClick() {
         if (ChkValidDate()) {
            String TempCode = M_Code.getText();
            Date TempDate = new Date();
            Date TempNewDate = new Date();
            Date TempEndDate = new Date();
            String TDate1 = M_Date.getText();
            String TDate2 = M_NewDate.getText();
            String TDate3 = M_End.getText();
            try {
                TempDate = ShowDatefmt.parse(TDate1);
                TempNewDate = ShowDatefmt.parse(TDate2);
                TempEndDate = ShowDatefmt.parse(TDate3);
            } catch (Exception ex) {
                
            }
            
            if (!TempCode.equals("")) {
                if (PUtility.ShowConfirmMsg("คุณต้องการบันทึกรายการข้อมูลนี้หรือไม่...")) {
                    if (!Value.getComPort().equals("NONE")) {
                        if (prn.OpenPrint(Value.getComPort())) {
                            prn.InitPrinter();
                            prn.print(POSHW.getHeading1());
                            prn.print(POSHW.getHeading2());
                            prn.print(POSHW.getHeading3());
                            prn.print(POSHW.getHeading4());
                            prn.print("REG ID :" + Value.MACNO);
                            prn.print(DatefmtThai.format(date) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                            prn.print("----------------------------------------");
                            prn.print("       *** ใบต่ออายุบัตรสมาชิก ***");
                            prn.print("     ============================       ");
                            prn.print(" รหัสสมาชิก   " + M_Code.getText());
                            prn.print(" ชื่อสมาชิก    " + M_Name.getText());
                            prn.print(" วันที่ต่ออายุ   " + ShowDatefmt.format(TempDate));
                            prn.print(" วันหมดอายุ   " + ShowDatefmt.format(TempEndDate));
                            prn.print(" ต่ออายุถึงวันที่ " + ShowDatefmt.format(TempNewDate));
                            prn.print(" ค่าธรรมเนียมการต่ออายุ ");
                            if (Cash.isSelected()) {
                                prn.print("        เงินสด : "+DecFmt.format(Double.parseDouble(M_Amount.getText()))) ;
                            } else {
                                prn.print("    ใช้แต้มสะสม : "+DecFmt.format(Double.parseDouble(M_ScoreAmount.getText()))) ;
                            }  
                            prn.print("----------------------------------------");
                            prn.print("");
                            prn.print("");
                            prn.print("");
                            
                            prn.CutPaper();
                            prn.closePrint();
                            String Addtype = "1" ;
                            if (Cash.isSelected()) {
                                Addtype = "1" ;
                            } else {
                                Addtype = "2" ;  
                            }
                            try {
                                Statement stmt =  MySQLConnect.con.createStatement();
                                String SQLQuery = "insert into memaddtime (m_code,m_date,m_enddate,m_newdate,m_amount,m_user,addtype,m_scoreamount) " +
                                        "values ('" + TempCode + "','" + Datefmt.format(TempDate) + "','" + Datefmt.format(TempEndDate) + "','" + Datefmt.format(TempNewDate) + "'," + Double.parseDouble(M_Amount.getText()) + ",'" + PublicVar._User + "','"+Addtype+"',"+Double.parseDouble(M_ScoreAmount.getText())+") ";
                                        //"values ('" + TempCode + "','" + Datefmt.format(TempDate) + "','" + Datefmt.format(TempEndDate) + "','" + Datefmt.format(TempNewDate) + "'," + Double.parseDouble(M_Amount.getText()) + ",'" + PublicVar._User + "','"+Addtype+"',"+Double.parseDouble(M_ScoreAmount.getText())+") ";
                                stmt.executeUpdate(SQLQuery);
                                stmt.close();
                            } catch (SQLException e) {
                                PUtility.showError(e.getMessage());
                            }
                            try {
                                Statement stmt =  MySQLConnect.con.createStatement();
                                String SQLQuery = "update memmaster set m_end='" + Datefmt.format(TempNewDate) + "' where (m_code='" + TempCode + "')";
                                stmt.executeUpdate(SQLQuery);
                                stmt.close();
                            } catch (SQLException e) {
                                PUtility.showError(e.getMessage());
                            }
                            //}
                            ClearVariable();
                        }
                    }
                }
            }
        }
    }

    public void bntDeleteClick() {
        String TempCode = M_Code.getText();
        Date TempDate = new Date();
        Date TempEndDate = new Date();
        String TDate1 = M_Date.getText() ;
        String TDate3 = M_End.getText() ;
        try {
            TempDate = ShowDatefmt.parse(TDate1);
            TempEndDate = ShowDatefmt.parse(TDate3);
        } catch (Exception e) {
        }
        if (SeekMemAddTime()) {
        if (!TempCode.equals("")) {
            if (PUtility.ShowConfirmMsg("คุณต้องการลบรายการข้อมูลนี้หรือไม่...")) {
                if (!Value.getComPort().equals("NONE")) {
                    if (prn.OpenPrint(Value.getComPort())) {
                        prn.InitPrinter();
                        prn.print(POSHW.getHeading1());
                        prn.print(POSHW.getHeading2());
                        prn.print(POSHW.getHeading3());
                        prn.print(POSHW.getHeading4());
                        prn.print("REG ID :" + Value.MACNO);
                        prn.print(DatefmtThai.format(date) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                        prn.print("----------------------------------------");
                        prn.print("     *** ใบยกเลิกการต่ออายุสมาชิก ***");
                        prn.print("     ============================       ");
                        prn.print("   รหัสสมาชิก "+M_Code.getText()) ;
                        prn.print("   ชื่อสมาชิก  "+M_Name.getText()) ;
                        prn.print("   วันที่ต่ออายุ "+ShowDatefmt.format(TempDate)) ;
                        prn.print(" ") ;
                        prn.print("----------------------------------------");
                        prn.print("");
                        prn.print("");
                        prn.print("");
                        
                        prn.CutPaper();
                        prn.closePrint();
                        try {
                            Statement stmt =  MySQLConnect.con.createStatement();
                            String SQLQuery = "delete from memaddtime where (m_code='" + TempCode + "') and  (m_date='" + Datefmt.format(TempDate) + "')";
                            stmt.executeUpdate(SQLQuery);
                            stmt.close();
                        } catch (SQLException e) {
                            PUtility.showError(e.getMessage());
                        }
                         try {
                            Statement stmt =  MySQLConnect.con.createStatement();
                            String SQLQuery = "update memmaster set m_end='"+Datefmt.format(TempEndDate)+"' where (m_code='" + TempCode + "')"  ;
                            stmt.executeUpdate(SQLQuery);
                            stmt.close();
                        } catch (SQLException e) {
                            PUtility.showError(e.getMessage());
                        }
                        ClearVariable() ;
                    } else {
//                        PUtility.showError("เครื่องพิมพ์ใบกำกับภาษีไม่สามารถพิมพ์ได้ ...");
                    }
                }
            }
        }
        }
    }
    public Boolean SeekMemAddTime() {
        Boolean RetVal = false ;
        String TempCode = M_Code.getText();
        String TDate1 = M_Date.getText() ;
        
        Date TempDate = new Date();
        try {
            TempDate = ShowDatefmt.parse(TDate1);
        } catch (Exception e) {
        }
        if (!M_Code.getText().equals("")) {
            try {
                Statement stmt =  MySQLConnect.con.createStatement();
                String SQLQuery = "Select *from memaddtime where (m_code='" + TempCode + "') and  (m_date='" + Datefmt.format(TempDate) + "')";
                ResultSet rec = stmt.executeQuery(SQLQuery);
                rec.first();
                if (rec.getRow() == 0) {
                    RetVal = false ;
                } else {
                    RetVal = true ;
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                PUtility.showError(e.getMessage());
            }
        } 
        return RetVal ;
    }

    public void bntExitClick() {
        this.dispose();
    }

    public void GetDataValue() {
        M_Code.setFocusable(false);
        M_Date.setFocusable(true);
        M_NewDate.setFocusable(true);
        M_Amount.setFocusable(true);
        M_Date.requestFocus();
    }

    public void M_CodeExit() {
        String TempCode = M_Code.getText();
        if (!TempCode.equals("")) {
            if (M_Code.getText().length() >= 7) {
                try {
                    Statement stmt =  MySQLConnect.con.createStatement();
                    String SQLQuery = "Select *from memmaster where m_code='" + TempCode + "'";
                    ResultSet rec = stmt.executeQuery(SQLQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                        PUtility.ShowMsg("ไม่พบข้อมูลสามาชิกรหัส " + M_Code.getText() + " ในฐานข้อมูลหลัก");
                        M_Code.requestFocus();
                    } else {
                        M_Name.setText(rec.getString("m_name"));
                        M_Score.setText(IntFmt.format(rec.getDouble("m_score")));
                        M_End.setText(ShowDatefmt.format(rec.getDate("m_end")));
                        String StrEnd = ShowDatefmt.format(rec.getDate("m_end"));
                        String StrCur = ShowDatefmt.format(date);
                        if (StrEnd.compareTo(StrCur) > 0) {
                            Date TempEnd = new Date();
                            TempEnd = rec.getDate("m_end");
                            String TempYear = GetYear.format(TempEnd);
                            String TempDate = GetDate.format(TempEnd);
                            String TempMonth = GetMonth.format(TempEnd);
                            int newYear = (Integer.parseInt(TempYear)) + 2;
                            //TempYear = newYear.
                            M_NewDate.setText(TempDate + "/" + TempMonth + "/" + newYear);

                        } else {
                            String TempYear = GetYear.format(date);
                            String TempDate = GetDate.format(date);
                            String TempMonth = GetMonth.format(date);
                            int newYear = (Integer.parseInt(TempYear)) + 2;
                            //TempYear = newYear.
                            M_NewDate.setText(TempDate + "/" + TempMonth + "/" + newYear);
                        }
                        GetDataValue();
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
            } else {
                PUtility.ShowMsg("รหัสสมาชิกต้องมีขนาดมากกว่า 7 หลัก...");
                M_Code.requestFocus();
            }
        }
    }
    public Boolean ChkValidDate() {
        Boolean RetVal = true;
        if (!PUtility.ChkDate(M_Date.getText())) {
            M_Date.requestFocus();
            RetVal = false;
        }
        if (!PUtility.ChkDate(M_NewDate.getText())) {
            M_NewDate.requestFocus();
            RetVal = false;
        }
        if (!PUtility.ChkDate(M_End.getText())) {
            RetVal = false;
        }
        return RetVal;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                MemAddTime dialog = new MemAddTime(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BntExit;
    private javax.swing.JRadioButton Cash;
    private javax.swing.JFormattedTextField M_Amount;
    private javax.swing.JTextField M_Code;
    private javax.swing.JFormattedTextField M_Date;
    private javax.swing.JFormattedTextField M_End;
    private javax.swing.JTextField M_Name;
    private javax.swing.JFormattedTextField M_NewDate;
    private javax.swing.JFormattedTextField M_Score;
    private javax.swing.JFormattedTextField M_ScoreAmount;
    private javax.swing.JRadioButton Score;
    private javax.swing.JLabel ShowMsg;
    private javax.swing.JButton bntDelete;
    private javax.swing.JButton bntOK;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
