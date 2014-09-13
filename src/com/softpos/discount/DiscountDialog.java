package com.softpos.discount;

import com.softpos.cupon.CuponBean;
import com.softpos.member.MemberBean;
import database.MySQLConnect;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import program.BalanceControl;
import program.CouponDiscount;
import program.NumberControl;
import program.POSConfigSetup;

public class DiscountDialog extends javax.swing.JDialog {

    private String tableNo;
    private DecimalFormat dec1 = new DecimalFormat("#,##0.00");
    private DiscountBean discBean = null;
    private CuponBean cuponBean = null;
    private POSConfigSetup posConfig = null;
    private double totalAmount;
    private MemberBean memberBean;

    public DiscountDialog(java.awt.Frame parent, boolean modal, String tableNo, double totalAmount, MemberBean memberBean) {
        super(parent, modal);
        initComponents();
        
        this.memberBean = memberBean;

        this.tableNo = tableNo;
        discBean = new DiscountBean();
        cuponBean = new CuponBean();
        discBean.setCuponBean(cuponBean);
        this.totalAmount = totalAmount;

        posConfig = POSConfigSetup.Bean();
    }

    public DiscountBean getDiscountBean() {
        return discBean;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnFest = new javax.swing.JButton();
        btnEmp = new javax.swing.JButton();
        btnMem = new javax.swing.JButton();
        btnTrain = new javax.swing.JButton();
        btnCupon = new javax.swing.JButton();
        txtFest = new javax.swing.JTextField();
        txtFestAmt = new javax.swing.JTextField();
        txtEmp = new javax.swing.JTextField();
        txtEmpAmt = new javax.swing.JTextField();
        txtMem = new javax.swing.JTextField();
        txtMemAmt = new javax.swing.JTextField();
        txtTrain = new javax.swing.JTextField();
        txtTrainAmt = new javax.swing.JTextField();
        txtCupon = new javax.swing.JTextField();
        txtCuponAmt = new javax.swing.JTextField();
        btnClose = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnBaht = new javax.swing.JButton();
        btnCuponSpecial = new javax.swing.JButton();
        txtBahtAmt = new javax.swing.JTextField();
        txtCuponDiscount = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ส่วนลดรายการต่าง ๆ");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMaximumSize(new java.awt.Dimension(500, 550));

        btnFest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFest.setText("(F1) ส่วนลดเทศกาล");
        btnFest.setAlignmentY(0.0F);
        btnFest.setFocusable(false);
        btnFest.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFest.setMargin(new java.awt.Insets(0, 5, 0, 0));
        btnFest.setRequestFocusEnabled(false);
        btnFest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFestActionPerformed(evt);
            }
        });

        btnEmp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEmp.setText("(F2) ส่วนลดพนักงาน");
        btnEmp.setAlignmentY(0.0F);
        btnEmp.setFocusable(false);
        btnEmp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEmp.setMargin(new java.awt.Insets(0, 5, 0, 0));
        btnEmp.setRequestFocusEnabled(false);
        btnEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpActionPerformed(evt);
            }
        });

        btnMem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMem.setText("(F3) ส่วนลดสมาชิก (VIP)");
        btnMem.setAlignmentY(0.0F);
        btnMem.setFocusable(false);
        btnMem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMem.setMargin(new java.awt.Insets(0, 5, 0, 0));
        btnMem.setRequestFocusEnabled(false);
        btnMem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMemActionPerformed(evt);
            }
        });

        btnTrain.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTrain.setText("(F4) ส่วนลด Trainee");
        btnTrain.setAlignmentY(0.0F);
        btnTrain.setFocusable(false);
        btnTrain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTrain.setMargin(new java.awt.Insets(0, 5, 0, 0));
        btnTrain.setRequestFocusEnabled(false);
        btnTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrainActionPerformed(evt);
            }
        });

        btnCupon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCupon.setText("(F5) ส่วนลดคูปอง");
        btnCupon.setAlignmentY(0.0F);
        btnCupon.setFocusable(false);
        btnCupon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCupon.setMargin(new java.awt.Insets(0, 5, 0, 0));
        btnCupon.setRequestFocusEnabled(false);
        btnCupon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuponActionPerformed(evt);
            }
        });

        txtFest.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtFest.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFest.setFocusable(false);
        txtFest.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFestKeyPressed(evt);
            }
        });

        txtFestAmt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtFestAmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtFestAmt.setText("0.00");
        txtFestAmt.setFocusable(false);

        txtEmp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtEmp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmp.setFocusable(false);
        txtEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmpKeyPressed(evt);
            }
        });

        txtEmpAmt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtEmpAmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEmpAmt.setText("0.00");
        txtEmpAmt.setFocusable(false);

        txtMem.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtMem.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMem.setFocusable(false);
        txtMem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMemKeyPressed(evt);
            }
        });

        txtMemAmt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtMemAmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMemAmt.setText("0.00");
        txtMemAmt.setFocusable(false);

        txtTrain.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTrain.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTrain.setFocusable(false);
        txtTrain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTrainKeyPressed(evt);
            }
        });

        txtTrainAmt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTrainAmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTrainAmt.setText("0.00");
        txtTrainAmt.setFocusable(false);

        txtCupon.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtCupon.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCupon.setFocusable(false);
        txtCupon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCuponKeyPressed(evt);
            }
        });

        txtCuponAmt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtCuponAmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCuponAmt.setText("0.00");
        txtCuponAmt.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addComponent(btnTrain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCupon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtFest, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFestAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmpAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtMem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMemAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTrainAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCupon, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCuponAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFest, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFestAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFest, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmpAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMemAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnMem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTrainAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCupon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCuponAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCupon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnClose.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnClose.setText("ปิดหน้าต่าง (Exit)");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSave.setText("บันทึก (Save)");
        btnSave.setFocusable(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnBaht.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBaht.setText("(F6) ส่วนลดบาท");
        btnBaht.setAlignmentY(0.0F);
        btnBaht.setFocusable(false);
        btnBaht.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBaht.setMargin(new java.awt.Insets(0, 5, 0, 0));
        btnBaht.setRequestFocusEnabled(false);
        btnBaht.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBahtActionPerformed(evt);
            }
        });

        btnCuponSpecial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCuponSpecial.setText("(F7) ส่วนลดบัตรคูปองพิเศษ");
        btnCuponSpecial.setAlignmentY(0.0F);
        btnCuponSpecial.setFocusable(false);
        btnCuponSpecial.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCuponSpecial.setMargin(new java.awt.Insets(0, 5, 0, 0));
        btnCuponSpecial.setRequestFocusEnabled(false);
        btnCuponSpecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuponSpecialActionPerformed(evt);
            }
        });

        txtBahtAmt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtBahtAmt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBahtAmt.setText("0.00");
        txtBahtAmt.setFocusable(false);
        txtBahtAmt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBahtAmtKeyPressed(evt);
            }
        });

        txtCuponDiscount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtCuponDiscount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCuponDiscount.setText("0.00");
        txtCuponDiscount.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCuponSpecial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBaht, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBahtAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCuponDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBaht, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBahtAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCuponSpecial, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCuponDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton10.setText("CE");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton11.setText("/");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton12.setText("*");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton13.setText("-");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton14.setText("Enter");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setText("9");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3.setText("8");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText("7");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6.setText("4");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5.setText("5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4.setText("6");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8.setText("3");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton9.setText("2");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7.setText("1");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton15.setText("0");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton16.setText(".");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton17.setText("+");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBahtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBahtActionPerformed
        if (posConfig.getP_DiscBathChk().equals("Y")) {
            txtBahtAmt.setFocusable(true);
            txtBahtAmt.selectAll();
            txtBahtAmt.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "ระบบถูกกำหนดว่า ห้ามให้ส่วนลดรายการนี้ !");
        }
    }//GEN-LAST:event_btnBahtActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        discBean = null;
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        double FestDiscount = Double.parseDouble(txtFestAmt.getText());
        double EmpDiscount = Double.parseDouble(txtEmpAmt.getText());
        double MemDiscount = Double.parseDouble(txtMemAmt.getText());
        double TrainDiscount = Double.parseDouble(txtTrainAmt.getText());
        double CuponDiscount = Double.parseDouble(txtCuponAmt.getText());
        
        double BahtDiscount = Double.parseDouble(txtBahtAmt.getText());
        double CuponSpecialDiscount = Double.parseDouble(txtCuponDiscount.getText());
        
        discBean.setStrFestDiscount(txtFest.getText());
        discBean.setStrEmpDiscount(txtEmp.getText());
        discBean.setStrMemDiscount(txtMem.getText());
        discBean.setStrTrainDiscount(txtTrain.getText());
        discBean.setStrCuponDiscount(txtCupon.getText());

        discBean.setFestDiscount(FestDiscount);
        discBean.setEmpDiscount(EmpDiscount);
        discBean.setMemDiscount(MemDiscount);
        discBean.setTrainDiscount(TrainDiscount);
        discBean.setCuponDiscount(CuponDiscount);
        
        discBean.setBahtDiscount(BahtDiscount);        
        discBean.setCuponSpecialDiscount(CuponSpecialDiscount);
        
        if(chkInputAmt()){
            // update tablefile for discount
            try {
                String sql = "update tablefile set "
                        + "EmpDisc='"+discBean.getStrEmpDiscount()+"', EmpDiscAmt='"+discBean.getEmpDiscount()+"',"
                        + "FastDisc='"+discBean.getStrFestDiscount()+"', FastDiscAmt='"+discBean.getFestDiscount()+"',"
                        + "TrainDisc='"+discBean.getStrTrainDiscount()+"', TrainDiscAmt='"+discBean.getTrainDiscount()+"',"
                        + "MemDisc='"+discBean.getStrMemDiscount()+"', MemDiscAmt='"+discBean.getMemDiscount()+"',"
                        + "SubDisc='"+discBean.getStrCuponDiscount()+"', SubDiscAmt='"+discBean.getCuponDiscount()+"',"
                        + "DiscBath='"+discBean.getBahtDiscount()+"',"
                        + "CuponDiscAmt='"+discBean.getCuponSpecialDiscount()+"' "
                        + "where TCode='"+tableNo+"'";
                MySQLConnect.exeUpdate(sql);
                BalanceControl.updateProSerTable(tableNo, memberBean);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            dispose();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCuponSpecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuponSpecialActionPerformed
        CouponDiscount cd = new CouponDiscount(null, true, tableNo);
        cd.setVisible(true);

        if (cd.getCuponBean() != null) {
            cuponBean = cd.getCuponBean();
            discBean.setCuponBean(cuponBean);
            txtCuponDiscount.setText(dec1.format(cd.getCuponDiscount()));
        }
    }//GEN-LAST:event_btnCuponSpecialActionPerformed

    private void btnCuponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuponActionPerformed
        // check permission        
        if (posConfig.getP_SubDiscGet().equals("Y")) {
//            txtCupon.setFocusable(true);
            txtCupon.requestFocus();
            txtCupon.selectAll();
        } else {
            JOptionPane.showMessageDialog(this, "ระบบถูกกำหนดว่า ห้ามให้ส่วนลดรายการนี้ !");
        }
    }//GEN-LAST:event_btnCuponActionPerformed

    private void btnTrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrainActionPerformed
        // check permission        
        if (posConfig.getP_TrainDiscGet().equals("Y")) {
//            txtTrain.setFocusable(true);
            txtTrain.requestFocus();
            txtTrain.selectAll();
        } else {
            JOptionPane.showMessageDialog(this, "ระบบถูกกำหนดว่า ห้ามให้ส่วนลดรายการนี้ !");
        }
    }//GEN-LAST:event_btnTrainActionPerformed

    private void btnMemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMemActionPerformed
        // check permission        
        if (posConfig.getP_MemDiscGet().equals("Y")) {
//            txtMem.setFocusable(true);
            txtMem.requestFocus();
            txtMem.selectAll();
        } else {
            JOptionPane.showMessageDialog(this, "ระบบถูกกำหนดว่า ห้ามให้ส่วนลดรายการนี้ !");
        }
    }//GEN-LAST:event_btnMemActionPerformed

    private void btnEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpActionPerformed
        // check permission        
        if (posConfig.getP_EmpDiscGet().equals("Y")) {
//            txtEmp.setFocusable(true);
            txtEmp.requestFocus();
            txtEmp.selectAll();
        } else {
            JOptionPane.showMessageDialog(this, "ระบบถูกกำหนดว่า ห้ามให้ส่วนลดรายการนี้ !");
        }
    }//GEN-LAST:event_btnEmpActionPerformed

    private void btnFestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFestActionPerformed
        // check permission        
        if (posConfig.getP_FastDiscGet().equals("Y")) {
//            txtFest.setFocusable(true);
            txtFest.requestFocus();
            txtFest.selectAll();
        } else {
            JOptionPane.showMessageDialog(this, "ระบบถูกกำหนดว่า ห้ามให้ส่วนลดรายการนี้ !");
        }
    }//GEN-LAST:event_btnFestActionPerformed

    private void txtFestKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFestKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFest.setText(nextDiscountFest());
        }
    }//GEN-LAST:event_txtFestKeyPressed

    private void txtEmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtEmp.setText(nextEmpFest());
        }
    }//GEN-LAST:event_txtEmpKeyPressed

    private void txtMemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMem.setText(nextMemFest());
        }
    }//GEN-LAST:event_txtMemKeyPressed

    private void txtTrainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTrainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTrain.setText(nextTrainFest());
        }
    }//GEN-LAST:event_txtTrainKeyPressed

    private void txtCuponKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuponKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCupon.setText(nextSubFest());
        }
    }//GEN-LAST:event_txtCuponKeyPressed

    private void txtBahtAmtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBahtAmtKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            chkInputAmt();
        }
    }//GEN-LAST:event_txtBahtAmtKeyPressed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        input("/");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        input("*");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        input("-");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        input("9");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        input("8");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        input("7");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        input("4");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        input("5");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        input("6");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        input("3");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        input("2");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        input("1");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        input("0");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        input(".");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        input("+");
    }//GEN-LAST:event_jButton17ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(DiscountDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiscountDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiscountDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiscountDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DiscountDialog dialog = new DiscountDialog(new javax.swing.JFrame(), true, "1", 0.00, null);
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
    private javax.swing.JButton btnBaht;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCupon;
    private javax.swing.JButton btnCuponSpecial;
    private javax.swing.JButton btnEmp;
    private javax.swing.JButton btnFest;
    private javax.swing.JButton btnMem;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTrain;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtBahtAmt;
    private javax.swing.JTextField txtCupon;
    private javax.swing.JTextField txtCuponAmt;
    private javax.swing.JTextField txtCuponDiscount;
    private javax.swing.JTextField txtEmp;
    private javax.swing.JTextField txtEmpAmt;
    private javax.swing.JTextField txtFest;
    private javax.swing.JTextField txtFestAmt;
    private javax.swing.JTextField txtMem;
    private javax.swing.JTextField txtMemAmt;
    private javax.swing.JTextField txtTrain;
    private javax.swing.JTextField txtTrainAmt;
    // End of variables declaration//GEN-END:variables

    private String nextDiscountFest() {
        String[] sp = txtFest.getText().split("/");
        String strFestDisc = "00/00/00";
        
        int DISC_EE = 0;
        int DISC_TT = 0;
        int DISC_DD = 0;
        if (sp.length == 3) {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = Integer.parseInt(sp[1]);
            DISC_DD = Integer.parseInt(sp[2]);            
        } else if (sp.length == 2) {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = Integer.parseInt(sp[1]);
            DISC_DD = 0;
        } else {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = 0;
            DISC_DD = 0;
        }
        
        strFestDisc = DISC_EE+"/"+DISC_TT+"/"+DISC_DD;

        double TOTAL_EE = 0.00;
        double TOTAL_DD = 0.00;
        double TOTAL_TT = 0.00;
        try {
            String sql = "select R_Normal, sum(R_Total) as R_Total "
                    + "from balance where R_Table='" + tableNo + "' "
                    + "group by R_Normal;";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            while (rs.next()) {
                String type = rs.getString("R_Normal");
                double total = rs.getDouble("R_Total");

                if (type.equals("N")) {
                    TOTAL_EE = total;
                } else {
                    TOTAL_DD = total;
                }
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        TOTAL_EE = (TOTAL_EE * DISC_EE) / 100;
        TOTAL_TT = (TOTAL_TT * DISC_TT) / 100;
        TOTAL_DD = (TOTAL_DD * DISC_DD) / 100;

        double totalDiscount = TOTAL_EE + TOTAL_TT + TOTAL_DD;

//        txtFestAmt.setFocusable(true);
        txtFestAmt.requestFocus();
        txtFestAmt.setText("" + totalDiscount);
        txtFestAmt.selectAll();
        
        return strFestDisc;
    }
    
    private String nextEmpFest() {
        String[] sp = txtEmp.getText().split("/");
        String strEmpDisc = "00/00/00";
        
        int DISC_EE = 0;
        int DISC_TT = 0;
        int DISC_DD = 0;
        if (sp.length == 3) {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = Integer.parseInt(sp[1]);
            DISC_DD = Integer.parseInt(sp[2]);            
        } else if (sp.length == 2) {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = Integer.parseInt(sp[1]);
            DISC_DD = 0;
        } else {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = 0;
            DISC_DD = 0;
        }
        
        strEmpDisc = DISC_EE+"/"+DISC_TT+"/"+DISC_DD;

        double TOTAL_EE = 0.00;
        double TOTAL_DD = 0.00;
        double TOTAL_TT = 0.00;
        try {
            String sql = "select R_Normal, sum(R_Total) as R_Total "
                    + "from balance where R_Table='" + tableNo + "' "
                    + "group by R_Normal;";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            while (rs.next()) {
                String type = rs.getString("R_Normal");
                double total = rs.getDouble("R_Total");

                if (type.equals("N")) {
                    TOTAL_EE = total;
                } else {
                    TOTAL_DD = total;
                }
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        TOTAL_EE = (TOTAL_EE * DISC_EE) / 100;
        TOTAL_TT = (TOTAL_TT * DISC_TT) / 100;
        TOTAL_DD = (TOTAL_DD * DISC_DD) / 100;

        double totalDiscount = TOTAL_EE + TOTAL_TT + TOTAL_DD;

//        txtEmpAmt.setFocusable(true);
        txtEmpAmt.requestFocus();
        txtEmpAmt.setText("" + totalDiscount);
        txtEmpAmt.selectAll();
        
        return strEmpDisc;
    }

    private String nextMemFest() {
        String[] sp = txtMem.getText().split("/");
        String strMemDisc = "0/0/0";
        
        int DISC_EE = 0;
        int DISC_TT = 0;
        int DISC_DD = 0;
        
        if (sp.length == 3) {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = Integer.parseInt(sp[1]);
            DISC_DD = Integer.parseInt(sp[2]);            
        } else if (sp.length == 2) {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = Integer.parseInt(sp[1]);
            DISC_DD = 0;
        } else {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = 0;
            DISC_DD = 0;
        }
        strMemDisc = DISC_EE+"/"+DISC_TT+"/"+DISC_DD;

        double TOTAL_EE = 0.00;
        double TOTAL_DD = 0.00;
        double TOTAL_TT = 0.00;
        try {
            String sql = "select R_Normal, sum(R_Total) as R_Total "
                    + "from balance where R_Table='" + tableNo + "' "
                    + "group by R_Normal;";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            while (rs.next()) {
                String type = rs.getString("R_Normal");
                double total = rs.getDouble("R_Total");

                if (type.equals("N")) {
                    TOTAL_EE = total;
                } else {
                    TOTAL_DD = total;
                }
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        TOTAL_EE = (TOTAL_EE * DISC_EE) / 100;
        TOTAL_TT = (TOTAL_TT * DISC_TT) / 100;
        TOTAL_DD = (TOTAL_DD * DISC_DD) / 100;

        double totalDiscount = TOTAL_EE + TOTAL_TT + TOTAL_DD;

        txtMemAmt.requestFocus();
        txtMemAmt.setText("" + totalDiscount);
        txtMemAmt.selectAll();
        
        return strMemDisc;
    }

    private String nextTrainFest() {
        String[] sp = txtTrain.getText().split("/");
        String strTrainAmt = "00/00/00";
        
        int DISC_EE = 0;
        int DISC_TT = 0;
        int DISC_DD = 0;
        if (sp.length == 3) {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = Integer.parseInt(sp[1]);
            DISC_DD = Integer.parseInt(sp[2]);            
        } else if (sp.length == 2) {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = Integer.parseInt(sp[1]);
            DISC_DD = 0;
        } else {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = 0;
            DISC_DD = 0;
        }
        
        strTrainAmt = DISC_EE+"/"+DISC_TT+"/"+DISC_DD;

        double TOTAL_EE = 0.00;
        double TOTAL_DD = 0.00;
        double TOTAL_TT = 0.00;
        try {
            String sql = "select R_Normal, sum(R_Total) as R_Total "
                    + "from balance where R_Table='" + tableNo + "' "
                    + "group by R_Normal;";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            while (rs.next()) {
                String type = rs.getString("R_Normal");
                double total = rs.getDouble("R_Total");

                if (type.equals("N")) {
                    TOTAL_EE = total;
                } else {
                    TOTAL_DD = total;
                }
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        TOTAL_EE = (TOTAL_EE * DISC_EE) / 100;
        TOTAL_TT = (TOTAL_TT * DISC_TT) / 100;
        TOTAL_DD = (TOTAL_DD * DISC_DD) / 100;

        double totalDiscount = TOTAL_EE + TOTAL_TT + TOTAL_DD;

//        txtTrainAmt.setFocusable(true);
        txtTrainAmt.requestFocus();
        txtTrainAmt.setText("" + totalDiscount);
        txtTrainAmt.selectAll();
        
        return strTrainAmt;
    }

    private String nextSubFest() {
        String[] sp = txtCupon.getText().split("/");
        String strCuponAmt = "00/00/00";
        
        int DISC_EE = 0;
        int DISC_TT = 0;
        int DISC_DD = 0;
        if (sp.length == 3) {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = Integer.parseInt(sp[1]);
            DISC_DD = Integer.parseInt(sp[2]);            
        } else if (sp.length == 2) {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = Integer.parseInt(sp[1]);
            DISC_DD = 0;
        } else {
            DISC_EE = Integer.parseInt(sp[0]);
            DISC_TT = 0;
            DISC_DD = 0;
        }
        
        strCuponAmt = DISC_EE+"/"+DISC_TT+"/"+DISC_DD;

        double TOTAL_EE = 0.00;
        double TOTAL_DD = 0.00;
        double TOTAL_TT = 0.00;
        try {
            String sql = "select R_Normal, sum(R_Total) as R_Total "
                    + "from balance where R_Table='" + tableNo + "' "
                    + "group by R_Normal;";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            while (rs.next()) {
                String type = rs.getString("R_Normal");
                double total = rs.getDouble("R_Total");

                if (type.equals("N")) {
                    TOTAL_EE = total;
                } else {
                    TOTAL_DD = total;
                }
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        TOTAL_EE = (TOTAL_EE * DISC_EE) / 100;
        TOTAL_TT = (TOTAL_TT * DISC_TT) / 100;
        TOTAL_DD = (TOTAL_DD * DISC_DD) / 100;

        double totalDiscount = TOTAL_EE + TOTAL_TT + TOTAL_DD;

//        txtCuponAmt.setFocusable(true);
        txtCuponAmt.requestFocus();
        txtCuponAmt.setText("" + totalDiscount);
        txtCuponAmt.selectAll();
        
        return strCuponAmt;
    }

    private boolean chkInputAmt() {
        double input = Double.parseDouble(txtBahtAmt.getText());
        double amount = totalAmount;
        int DiscBathMax = posConfig.getP_DiscBathMax();
        
        double discAllow = (amount*DiscBathMax)/100;
        discAllow = NumberControl.UP_DOWN_NATURAL_BAHT(discAllow);
        if(input>discAllow){
            // warning
            JOptionPane.showMessageDialog(this, "ยอดส่วนลดมากกว่า % ที่สามารถให้ส่วนลดได้ !");
            txtBahtAmt.setText("0.00");
            txtBahtAmt.selectAll();
            txtBahtAmt.requestFocus();
            return false;
        }else{
            return true;
        }
    }

    private void input(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
