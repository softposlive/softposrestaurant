package com.softpos.floorplan;

import java.awt.event.KeyEvent;
import com.softpos.posreport.ArPaymentRep;
import com.softpos.posreport.AutoSumXRep;
import com.softpos.posreport.AutoXRep;
import com.softpos.posreport.CashierRep;
import com.softpos.posreport.CouponRep;
import com.softpos.posreport.CreditRep;
import com.softpos.posreport.DeptRep;
import program.GetUserAction;
import com.softpos.posreport.GiftVoucherRep;
import com.softpos.posreport.HourlyByPlu;
import com.softpos.posreport.HourlyRep;
import com.softpos.posreport.InvRep;
import com.softpos.posreport.PLURep;
import printReport.PPrint;
import program.PUtility;
import com.softpos.posreport.PromotionRep;
import program.PublicVar;
import com.softpos.posreport.TerminalRep;
import com.softpos.posreport.TopSaleRep;
import program.UserRecord;
import program.Value;
import com.softpos.posreport.VoidRep;
import util.MSG;

public class DailyRep extends javax.swing.JDialog {

    PPrint prn = new PPrint();

    public DailyRep(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        FunctionList = new javax.swing.JList();
        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("รายงานการขายประจำวัน (Daily Report)");
        setResizable(false);

        jPanel4.setBackground(new java.awt.Color(102, 153, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, java.awt.Color.lightGray));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Daily Sales Report");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        FunctionList.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 3, true));
        FunctionList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        FunctionList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "     รายงานโต๊ะค้างยังไม่ได้ชำระเงิน (Table On Action)", "     รายงานยอดการเงินของเครื่อง (Terminal Report)", "     รายงานยอดขายของพนักงานขาย (Cashier Report)", "     รายงานการขายตามกลุ่มสินค้า (Department/Group Report)", "     รายงานการขายตามรหัสสินค้า (PLU Report)", "     รายงานการขายตามช่วงเวลา (Hourly Report)", "     รายงานการพิมพ์ใบเสร็จรับเงิน (Reciept Report)", "     รายงานการ Void  (Void Report)", "     รายงานการรับชำระเงินด้วยบัตรเครดิต  (Credit Report)", "     รายงานอันดับสินค้าขายดี  (Top Sale Report)", "     รายงานการรับชำระเงินจากลูกหนี้ภายนอก  (AR Payment Report)", "     รายงานการขายรายชั่วโมง  (Hourly By PLU Report)", "     รายงานส่วนลดโปรโมชั่น (Promotion Discount Report)", "     รายงานส่วนลดบัตรคูปองพิเศษ (Coupon Discount Report)", "     รายงานสรุปยอดการขายอัตโนมัติเฉพาะเครื่อง (Automatic X-Report)", "     รายงานสรุปยอดการขายอัตโนมัติรวมทุกเครื่อง (Automatic Sum X-Report)" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        FunctionList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        FunctionList.setFixedCellHeight(32);
        FunctionList.setOpaque(false);
        FunctionList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FunctionListMouseClicked(evt);
            }
        });
        FunctionList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FunctionListKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(FunctionList);

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK.setText("Enter เลือกรายการ");
        bntOK.setFocusable(false);
        bntOK.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntOK.setRequestFocusEnabled(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntExit.setText("ESC ออกจากหน้าจอ");
        bntExit.setFocusable(false);
        bntExit.setMargin(new java.awt.Insets(1, 1, 1, 1));
        bntExit.setRequestFocusEnabled(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("(เลือกรายการโดยใช้ ลูกศรขื้น,ลูกศรลง,คลิกเม้าส์,สัมผัสหน้าจอ)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(480, 480, 480)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleParent(FunctionList);

        setSize(new java.awt.Dimension(953, 672));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void FunctionListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FunctionListKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        dispose();
    }else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        bntOkClick();
    }
}//GEN-LAST:event_FunctionListKeyPressed

    private void FunctionListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FunctionListMouseClicked
        bntOkClick();
    }//GEN-LAST:event_FunctionListMouseClicked

    private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
        dispose();
    }//GEN-LAST:event_bntExitActionPerformed

    private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
        bntOkClick();
    }//GEN-LAST:event_bntOKActionPerformed

    public void bntOkClick() {
        if (FunctionList.getSelectedIndex() == 0) {
            if(Value.useprint){
                prn.PrintTableAction();
            }
        }
        if (FunctionList.getSelectedIndex() == 1) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale10.equals("Y")) {
                TerminalRep frm = new TerminalRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale10.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            TerminalRep frm = new TerminalRep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 2) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale11.equals("Y")) {
                CashierRep frm = new CashierRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale11.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            CashierRep frm = new CashierRep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 3) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale12.equals("Y")) {
                DeptRep frm = new DeptRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale12.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            DeptRep frm = new DeptRep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 4) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale13.equals("Y")) {
                PLURep frm = new PLURep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale13.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            PLURep frm = new PLURep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 5) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale14.equals("Y")) {
                HourlyRep frm = new HourlyRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale14.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            HourlyRep frm = new HourlyRep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 6) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale15.equals("Y")) {
                InvRep frm = new InvRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale15.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            InvRep frm = new InvRep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 7) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale16.equals("Y")) {
                VoidRep frm = new VoidRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale16.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            VoidRep frm = new VoidRep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 8) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale17.equals("Y")) {
                CreditRep frm = new CreditRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale17.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            CreditRep frm = new CreditRep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 9) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale17.equals("Y")) {
                GiftVoucherRep frm = new GiftVoucherRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale17.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            GiftVoucherRep frm = new GiftVoucherRep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 10) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale18.equals("Y")) {
                TopSaleRep frm = new TopSaleRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale18.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            TopSaleRep frm = new TopSaleRep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;

        }
        if (FunctionList.getSelectedIndex() == 11) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale19.equals("Y")) {
                ArPaymentRep frm = new ArPaymentRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale19.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            ArPaymentRep frm = new ArPaymentRep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;

        }
        if (FunctionList.getSelectedIndex() == 12) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale20.equals("Y")) {
                HourlyByPlu frm = new HourlyByPlu(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale20.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            HourlyByPlu frm = new HourlyByPlu(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }

        if (FunctionList.getSelectedIndex() == 13) {
            PromotionRep frm = new PromotionRep(null, true);
            frm.setVisible(true);
            FunctionList.requestFocus();
        }
        if (FunctionList.getSelectedIndex() == 14) {
            CouponRep frm = new CouponRep(null, true);
            frm.setVisible(true);
            FunctionList.requestFocus();
        }
        if (FunctionList.getSelectedIndex() == 15) {
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale21.equals("Y")) {
                AutoXRep frm = new AutoXRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale21.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            AutoXRep frm = new AutoXRep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;
        }
        if (FunctionList.getSelectedIndex() == 16) {
            if (!PUtility.ShowConfirmMsg("การพิมพ์รายงานอัตโนมัติรวมทุกเครื่อง เป็นการสิ้นสุดการขายสินค้าของวันนั้น หลังจากพิมพ์รายงานนี้แล้วจะไม่สามารถทำรายการขายได้อีก \n ยืนยันการพิมพ์รายงานสรุปยอดการขายหรือไม่ (Y/N)?... ")) {
                return;
            }
            PublicVar.TempUserRec = PublicVar.TUserRec;
            if (PublicVar.TUserRec.Sale22.equals("Y")) {
                AutoSumXRep frm = new AutoSumXRep(null, true);
                frm.setVisible(true);
                FunctionList.requestFocus();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale22.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            AutoSumXRep frm = new AutoSumXRep(null, true);
                            frm.setVisible(true);
                            FunctionList.requestFocus();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
            PublicVar.TUserRec = PublicVar.TempUserRec;

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DailyRep dialog = new DailyRep(new javax.swing.JFrame(), true);
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
    private javax.swing.JList FunctionList;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
