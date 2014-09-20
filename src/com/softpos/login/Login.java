package com.softpos.login;

import database.MySQLConnect;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.softpos.floorplan.FloorPlanDialog;
import printReport.PPrint;
import program.PublicVar;
import program.ThaiUtil;
import program.UserRecord;
import program.Value;
import soft.virtual.KeyBoardDialog;
import util.MSG;

public class Login extends javax.swing.JDialog {

    Timer timer;
    SimpleDateFormat DatefmtShow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    DecimalFormat IntFmt = new DecimalFormat("#,##0");

    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        txtUser.setText("");
        txtPass.setText("");
        Value.MACNO = Value.getMacno();
        txtMacNo.setText("MAC NO. " + Value.MACNO);
        txtUser.requestFocus();
        TimeOfDay time = new TimeOfDay();
        timer = new Timer(1000, time);
        timer.start();

        checkUpdate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pbCheckUpdate = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        lbPass = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtPass = new javax.swing.JPasswordField();
        lbUser = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel();
        txtMacNo = new javax.swing.JTextField();
        txtShowDate = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        calc = new javax.swing.JPanel();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnPlus = new javax.swing.JButton();
        btnBS = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btnSub = new javax.swing.JButton();
        btnESC = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnStar = new javax.swing.JButton();
        btnTab = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnDot = new javax.swing.JButton();
        btnCLR = new javax.swing.JButton();
        btnSlash = new javax.swing.JButton();
        btnEnter = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Login System");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pbCheckUpdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(pbCheckUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 430, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 0), 1, true));

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lbPass.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbPass.setForeground(new java.awt.Color(0, 51, 204));
        lbPass.setText("รหัสผ่าน :");

        txtUser.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUserMouseClicked(evt);
            }
        });
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserKeyPressed(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        btnLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLoginKeyPressed(evt);
            }
        });

        txtPass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPassMouseClicked(evt);
            }
        });
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        lbUser.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbUser.setForeground(new java.awt.Color(0, 51, 204));
        lbUser.setText("ชื่อผู้ใช้งาน :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPass, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbUser, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(txtPass))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUser))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPass, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 450, -1));

        lbTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("กรุณาป้อนรหัสผู้ใช้งาน และ รหัสผ่าน  เพื่อเข้าใช้งาน");
        jPanel1.add(lbTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 408, -1));

        txtMacNo.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txtMacNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMacNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtMacNo.setFocusable(false);
        txtMacNo.setRequestFocusEnabled(false);
        txtMacNo.setVerifyInputWhenFocusTarget(false);
        txtMacNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMacNoActionPerformed(evt);
            }
        });
        jPanel1.add(txtMacNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 223, 30));

        txtShowDate.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        txtShowDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtShowDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtShowDate.setFocusable(false);
        txtShowDate.setRequestFocusEnabled(false);
        txtShowDate.setVerifyInputWhenFocusTarget(false);
        txtShowDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtShowDateActionPerformed(evt);
            }
        });
        jPanel1.add(txtShowDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 460, 181, 30));

        jPanel2.setBackground(new java.awt.Color(255, 102, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setForeground(java.awt.Color.white);

        jLabel6.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Software Restaurant SOFTPOS©2014");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 508, 432, -1));

        calc.setOpaque(false);
        calc.setRequestFocusEnabled(false);
        calc.setVerifyInputWhenFocusTarget(false);
        calc.setLayout(new java.awt.GridLayout(4, 5));

        btn7.setBackground(new java.awt.Color(255, 255, 255));
        btn7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn7.setForeground(new java.awt.Color(0, 51, 153));
        btn7.setText("7");
        btn7.setFocusable(false);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        calc.add(btn7);

        btn8.setBackground(new java.awt.Color(255, 255, 255));
        btn8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn8.setForeground(new java.awt.Color(0, 51, 153));
        btn8.setText("8");
        btn8.setFocusable(false);
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        calc.add(btn8);

        btn9.setBackground(new java.awt.Color(255, 255, 255));
        btn9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn9.setForeground(new java.awt.Color(0, 51, 153));
        btn9.setText("9");
        btn9.setFocusable(false);
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        calc.add(btn9);

        btnPlus.setBackground(new java.awt.Color(255, 255, 255));
        btnPlus.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnPlus.setForeground(new java.awt.Color(0, 51, 153));
        btnPlus.setText("+");
        btnPlus.setFocusable(false);
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });
        calc.add(btnPlus);

        btnBS.setBackground(new java.awt.Color(255, 255, 255));
        btnBS.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBS.setForeground(new java.awt.Color(0, 51, 153));
        btnBS.setText("<<");
        btnBS.setFocusable(false);
        btnBS.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnBS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBSActionPerformed(evt);
            }
        });
        calc.add(btnBS);

        btn4.setBackground(new java.awt.Color(255, 255, 255));
        btn4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn4.setForeground(new java.awt.Color(0, 51, 153));
        btn4.setText("4");
        btn4.setFocusable(false);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        calc.add(btn4);

        btn5.setBackground(new java.awt.Color(255, 255, 255));
        btn5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn5.setForeground(new java.awt.Color(0, 51, 153));
        btn5.setText("5");
        btn5.setFocusable(false);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        calc.add(btn5);

        btn6.setBackground(new java.awt.Color(255, 255, 255));
        btn6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn6.setForeground(new java.awt.Color(0, 51, 153));
        btn6.setText("6");
        btn6.setFocusable(false);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        calc.add(btn6);

        btnSub.setBackground(new java.awt.Color(255, 255, 255));
        btnSub.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnSub.setForeground(new java.awt.Color(0, 51, 153));
        btnSub.setText("-");
        btnSub.setFocusable(false);
        btnSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubActionPerformed(evt);
            }
        });
        calc.add(btnSub);

        btnESC.setBackground(new java.awt.Color(255, 255, 255));
        btnESC.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnESC.setForeground(new java.awt.Color(0, 51, 153));
        btnESC.setText("ESC");
        btnESC.setFocusable(false);
        btnESC.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnESC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnESCActionPerformed(evt);
            }
        });
        calc.add(btnESC);

        btn1.setBackground(new java.awt.Color(255, 255, 255));
        btn1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn1.setForeground(new java.awt.Color(0, 51, 153));
        btn1.setText("1");
        btn1.setFocusable(false);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        calc.add(btn1);

        btn2.setBackground(new java.awt.Color(255, 255, 255));
        btn2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn2.setForeground(new java.awt.Color(0, 51, 153));
        btn2.setText("2");
        btn2.setFocusable(false);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        calc.add(btn2);

        btn3.setBackground(new java.awt.Color(255, 255, 255));
        btn3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn3.setForeground(new java.awt.Color(0, 51, 153));
        btn3.setText("3");
        btn3.setFocusable(false);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        calc.add(btn3);

        btnStar.setBackground(new java.awt.Color(255, 255, 255));
        btnStar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnStar.setForeground(new java.awt.Color(0, 51, 153));
        btnStar.setText("x");
        btnStar.setFocusable(false);
        btnStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStarActionPerformed(evt);
            }
        });
        calc.add(btnStar);

        btnTab.setBackground(new java.awt.Color(255, 255, 255));
        btnTab.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTab.setForeground(new java.awt.Color(0, 51, 153));
        btnTab.setText("Setup");
        btnTab.setFocusable(false);
        btnTab.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabActionPerformed(evt);
            }
        });
        calc.add(btnTab);

        btn0.setBackground(new java.awt.Color(255, 255, 255));
        btn0.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn0.setForeground(new java.awt.Color(0, 51, 153));
        btn0.setText("0");
        btn0.setFocusable(false);
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });
        calc.add(btn0);

        btnDot.setBackground(new java.awt.Color(255, 255, 255));
        btnDot.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnDot.setForeground(new java.awt.Color(0, 51, 153));
        btnDot.setText(".");
        btnDot.setFocusable(false);
        btnDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDotActionPerformed(evt);
            }
        });
        calc.add(btnDot);

        btnCLR.setBackground(new java.awt.Color(255, 255, 255));
        btnCLR.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCLR.setForeground(new java.awt.Color(0, 51, 153));
        btnCLR.setText("CLR");
        btnCLR.setFocusable(false);
        btnCLR.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnCLR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCLRActionPerformed(evt);
            }
        });
        calc.add(btnCLR);

        btnSlash.setBackground(new java.awt.Color(255, 255, 255));
        btnSlash.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnSlash.setForeground(new java.awt.Color(0, 51, 153));
        btnSlash.setText("/");
        btnSlash.setFocusable(false);
        btnSlash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSlashActionPerformed(evt);
            }
        });
        calc.add(btnSlash);

        btnEnter.setBackground(new java.awt.Color(207, 226, 242));
        btnEnter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEnter.setForeground(new java.awt.Color(0, 51, 153));
        btnEnter.setText("Enter");
        btnEnter.setFocusable(false);
        btnEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });
        calc.add(btnEnter);

        jPanel1.add(calc, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 360, 220));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/special_clean_HD_wallpaper 2.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtShowDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtShowDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtShowDateActionPerformed

    private void txtMacNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMacNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMacNoActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clearTemp();
        System.exit(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            clearTemp();
            System.exit(0);
        } else {
            if (!txtUser.getText().equals("")) {
                keyboardcheck(evt, "txtUser");
            } else {
                txtUser.requestFocus();
            }
        }
    }//GEN-LAST:event_txtUserKeyPressed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        if (!txtPass.getText().equals("")) {
            keyboardcheck(evt, "txtPass");
        } else {
            txtPass.requestFocus();
        }
    }//GEN-LAST:event_txtPassKeyPressed

    private void btnLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            checkUserLogin();
        }
    }//GEN-LAST:event_btnLoginKeyPressed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        checkUserLogin();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterActionPerformed
        if (txtUser.hasFocus()) {
            txtPass.requestFocus();
        } else if (txtPass.hasFocus()) {
            btnLogin.requestFocus();
        } else if (btnLogin.hasFocus()) {
            btnLoginActionPerformed(null);
        }
    }//GEN-LAST:event_btnEnterActionPerformed

    private void txtUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUserMouseClicked
        if (evt.getClickCount() == 2) {
            new KeyBoardDialog(null, true, 4).get(txtUser, 4);
        }
    }//GEN-LAST:event_txtUserMouseClicked

    private void txtPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPassMouseClicked
        if (evt.getClickCount() == 2) {
            new KeyBoardDialog(null, true, 4).get(txtPass, 4);
        }
    }//GEN-LAST:event_txtPassMouseClicked

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        input("7");
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        input("8");
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        input("9");
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        input("4");
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        input("5");
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        input("6");
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        input("1");
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        input("2");
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        input("3");
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        input("0");
    }//GEN-LAST:event_btn0ActionPerformed

    private void btnDotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDotActionPerformed
        input(".");
    }//GEN-LAST:event_btnDotActionPerformed

    private void btnCLRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCLRActionPerformed
        if (txtUser.hasFocus()) {
            txtUser.setText("");
        }
        if (txtPass.hasFocus()) {
            txtPass.setText("");
        }
    }//GEN-LAST:event_btnCLRActionPerformed

    private void btnSlashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSlashActionPerformed
        input("/");
    }//GEN-LAST:event_btnSlashActionPerformed

    private void btnStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStarActionPerformed
        input("*");
    }//GEN-LAST:event_btnStarActionPerformed

    private void btnSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubActionPerformed
        input("-");
    }//GEN-LAST:event_btnSubActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
        input("+");
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnBSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBSActionPerformed
        if (txtUser.hasFocus()) {
            String tempstr;
            String tempstr2 = "";
            tempstr = txtUser.getText();
            for (int i = 0; i < tempstr.length() - 1; i++) {
                tempstr2 = tempstr2 + tempstr.charAt(i);
            }
            txtUser.setText(tempstr2);
        }
        if (txtPass.hasFocus()) {
            char[] pass = txtPass.getPassword();
            String password = "";
            for (int i = 0; i < pass.length - 1; i++) {
                password = password + pass[i];
            }
            txtPass.setText(password);
        }
    }//GEN-LAST:event_btnBSActionPerformed

    private void btnESCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnESCActionPerformed
        clearTemp();
        System.exit(0);
    }//GEN-LAST:event_btnESCActionPerformed

    private void btnTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabActionPerformed
        FileSettingDialog fsd = new FileSettingDialog(null, true);
        fsd.setVisible(true);
    }//GEN-LAST:event_btnTabActionPerformed

    public static void main(String args[]) {
        new MySQLConnect();
        //create file to check program is exist
        File f = new File("softrestaurant.running");
        if (f.exists()) {
            int confirm = JOptionPane.showConfirmDialog(null, "โปรแกรมมีการเปิดใช้งานอยู่ ท่านต้องการเปิดซ้ำใช่หรือไม่ ?", "ตรวจสอบการทำงานโปรแกรม", JOptionPane.OK_CANCEL_OPTION);
            if (confirm == JOptionPane.OK_OPTION) {
                //running program continue;
            } else {
                System.exit(0);
            }
        } else {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                MSG.ERR(null, ex.getMessage());
            }
        }

        Font myfont = new Font("Norasi", Font.PLAIN, 14);
        UIManager.put("Label.font", myfont);
        UIManager.put("Button.font", myfont);

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("OptionPane.messageFont", new javax.swing.plaf.FontUIResource(new java.awt.Font(
                    "Norasi", java.awt.Font.PLAIN, 14)));
        } catch (ClassNotFoundException e) {
            MSG.ERR(null, e.getMessage());
        } catch (InstantiationException e) {
            MSG.ERR(null, e.getMessage());
        } catch (IllegalAccessException e) {
            MSG.ERR(null, e.getMessage());
        } catch (UnsupportedLookAndFeelException e) {
            MSG.ERR(null, e.getMessage());
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login dialog = new Login(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnBS;
    private javax.swing.JButton btnCLR;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDot;
    private javax.swing.JButton btnESC;
    private javax.swing.JButton btnEnter;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnPlus;
    private javax.swing.JButton btnSlash;
    private javax.swing.JButton btnStar;
    private javax.swing.JButton btnSub;
    private javax.swing.JButton btnTab;
    private javax.swing.JPanel calc;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUser;
    private javax.swing.JProgressBar pbCheckUpdate;
    private javax.swing.JTextField txtMacNo;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtShowDate;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

    private void checkUserLogin() {
        String OnAct;
        String MacNoOnAct;
        String loginname = txtUser.getText();
        String password = txtPass.getText();

        if ((loginname.length() == 0) || (password.length() == 0)) {
            MSG.ERR(this, "กรุณาป้อนรหัสผู้ใช้งาน(Username)/รหัสผ่าน(Password)");
            clearlogin();
        } else {
            try {
                String sql = "select * from posuser "
                        + "where username= '" + ThaiUtil.Unicode2ASCII(loginname) + "' "
                        + "and password='" + ThaiUtil.Unicode2ASCII(password) + "'";
                ResultSet rec = MySQLConnect.getResultSet(sql);
                rec.first();
                if (rec.getRow() == 0) {
                    MSG.ERR(this, "รหัสผู้ใช้งาน (Username) และรหัสผ่าน (Password) ไม่ถูกต้อง !!! ");
                    clearlogin();
                } else {
                    PublicVar._RealUser = rec.getString("username");
                    PublicVar._Password = rec.getString("password");
                    PublicVar._UserName = ThaiUtil.ASCII2Unicode(rec.getString("name"));
                    OnAct = rec.getString("onact");
                    MacNoOnAct = rec.getString("macno");
                    rec.close();
                    if (OnAct.equals("Y") && (!MacNoOnAct.equals(Value.MACNO))) {
                        MSG.ERR(this, "รหัสพนักงาน " + loginname + " เข้าใช้งานอยู่แล้วที่เครื่องหมายเลข " + MacNoOnAct);
                        clearlogin();
                    } else {
                        UserRecord TUserRec = new UserRecord();
                        if (TUserRec.GetUserAction(loginname)) {
                            if (TUserRec.Sale1.equals("Y")) {
                                PublicVar.TUserRec = TUserRec;
                                PPrint Prn = new PPrint();
                                Prn.printLogin();
                                UpdateLogin(loginname);
                                Value.USERCODE = txtUser.getText();
                                FloorPlanDialog floorPlan = new FloorPlanDialog();
                                floorPlan.setVisible(true);
                                
                                dispose();
                            } else {
                                MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...ระบบการขายได้...!!!");
                                clearlogin();
                            }
                        } else {
                            MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                            clearlogin();
                        }
                    }
                }
            } catch (Exception e) {
                MSG.ERR(this, e.getMessage());
                clearlogin();
            }
        }
    }

    private void clearlogin() {
        txtUser.setText("");
        txtPass.setText("");
        txtUser.requestFocus();
    }

    private void input(String str) {
        if (txtUser.hasFocus()) {
            String tempstr = txtUser.getText();
            tempstr = tempstr + str;
            txtUser.setText(tempstr);
        }
        if (txtPass.hasFocus()) {
            char[] pass = txtPass.getPassword();
            String password = "";
            for (int i = 0; i < pass.length; i++) {
                password = password + pass[i];
            }
            password = password + str;
            txtPass.setText(password);
        }
    }

    private void UpdateLogin(String UserCode) {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SQLQuery = "update posuser set "
                    + "onact='Y',"
                    + "macno='" + Value.MACNO + "' "
                    + "where username='" + UserCode + "'";
            stmt.executeUpdate(SQLQuery);
            stmt.close();

            Value.CASHIER = UserCode;
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
            clearlogin();
        }
    }

    private void keyboardcheck(KeyEvent evt, String c_loginname) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (c_loginname.equals("txtUser")) {
                txtPass.requestFocus();
            } else if (c_loginname.equals("txtPass")) {
                btnLogin.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                clearTemp();
                System.exit(0);
            }
        }
    }

    private void clearTemp() {
        new File("softrestaurant.running").delete();
        MySQLConnect.close();
    }

    private void checkUpdate() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                //check ftp file date
                try {
                    pbCheckUpdate.setStringPainted(true);
                    pbCheckUpdate.setMinimum(0);
                    pbCheckUpdate.setMaximum(100);

                    for (int i = 1; i <= 100; i++) {
                        pbCheckUpdate.setValue(i);
                        pbCheckUpdate.setString("Check Update: (" + i + " %)");
                        try {
                            Thread.sleep(25);
                        } catch (Exception e) {
                        }
                    }

                    pbCheckUpdate.setString("โปรแกรมเวอร์ชันล่าสุด");
                } catch (Exception e) {
                }
            }
        }).start();
    }

    class TimeOfDay implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            SimpleDateFormat tf = new SimpleDateFormat("dd/MM/yyyy (HH:mm:ss)", Locale.ENGLISH);
            String St = tf.format(new Date());
            txtShowDate.setText(St);
        }
    }
}
