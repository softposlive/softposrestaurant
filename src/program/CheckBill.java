package program;

import printReport.PPrint;
import com.softpos.discount.DiscountBean;
import com.softpos.discount.DiscountDialog;
import com.softpos.member.MemberBean;
import database.MySQLConnect;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import soft.virtual.JTableControl;

public class CheckBill extends javax.swing.JDialog {

    private String tableNo;
    private TableFileBean tBean;
    private DefaultTableModel model;
    private DecimalFormat dec = new DecimalFormat("#,##0.00");
    private DiscountBean discBean = new DiscountBean();
    private MemberBean memberBean;

    public CheckBill(java.awt.Frame parent, boolean modal, String tableNo, MemberBean memberBean) {
        super(parent, modal);
        initComponents();

        this.memberBean = memberBean;
        this.tableNo = tableNo;
        initTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        txtTotalService = new javax.swing.JTextField();
        txtPromotion = new javax.swing.JTextField();
        txtItemDisc = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelNumber = new javax.swing.JPanel();
        btn1000 = new javax.swing.JButton();
        btn500 = new javax.swing.JButton();
        btn100 = new javax.swing.JButton();
        btn50 = new javax.swing.JButton();
        btnCLR = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnCash = new javax.swing.JButton();
        btnFit = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btnCreditAmt = new javax.swing.JButton();
        btnMember = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnGift = new javax.swing.JButton();
        btnDiscount = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnDot = new javax.swing.JButton();
        btnAR = new javax.swing.JButton();
        c_bntenter = new javax.swing.JButton();
        btnAccept = new javax.swing.JButton();
        bntPrintCheckBill = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblShowBalance = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtBillNo = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        lbAddMoney = new javax.swing.JLabel();
        txtTotalCash = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        Digital_Msg = new javax.swing.JLabel();
        txtTotalAmount = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtReturnMoneyAmount = new javax.swing.JTextField();
        btnGiftVoucher = new javax.swing.JButton();
        bntEarnest = new javax.swing.JButton();
        txtCashAmount = new javax.swing.JTextField();
        bntCash = new javax.swing.JButton();
        txtGiftVoucherAmount = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtCreditName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnCredit = new javax.swing.JButton();
        txtCreditTrackNo = new javax.swing.JTextField();
        txtCreditAmount = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCreditNo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnDiscountAll = new javax.swing.JButton();
        txtDiscountAmount = new javax.swing.JTextField();
        btnCredit1 = new javax.swing.JButton();
        txtArAmount = new javax.swing.JTextField();
        txtArCode = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbCredit = new javax.swing.JLabel();
        lbCreditMoney = new javax.swing.JLabel();
        lbCreditAmt = new javax.swing.JLabel();
        lbArName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("การรับชำระเงิน (Payment Form)");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTotalService.setEditable(false);
        txtTotalService.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTotalService.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalService.setText("0.00");
        txtTotalService.setDisabledTextColor(java.awt.Color.black);
        txtTotalService.setEnabled(false);
        txtTotalService.setRequestFocusEnabled(false);
        jPanel7.add(txtTotalService, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 25, 88, 30));

        txtPromotion.setEditable(false);
        txtPromotion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPromotion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPromotion.setText("0.00");
        txtPromotion.setDisabledTextColor(java.awt.Color.black);
        txtPromotion.setEnabled(false);
        txtPromotion.setRequestFocusEnabled(false);
        jPanel7.add(txtPromotion, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 25, 89, 30));

        txtItemDisc.setEditable(false);
        txtItemDisc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtItemDisc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtItemDisc.setText("0.00");
        txtItemDisc.setDisabledTextColor(java.awt.Color.black);
        txtItemDisc.setEnabled(false);
        txtItemDisc.setRequestFocusEnabled(false);
        jPanel7.add(txtItemDisc, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 25, 88, 30));

        txtSubTotal.setEditable(false);
        txtSubTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubTotal.setText("0.00");
        txtSubTotal.setDisabledTextColor(java.awt.Color.black);
        txtSubTotal.setEnabled(false);
        txtSubTotal.setRequestFocusEnabled(false);
        jPanel7.add(txtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 25, 88, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ส่วนลดรายการ");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 6, 88, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ส่วนลดโปรโมชั่น");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 6, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ส่วนลดท้ายบิล");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 6, 88, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ค่าบริการ");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 6, 88, -1));

        panelNumber.setRequestFocusEnabled(false);
        panelNumber.setVerifyInputWhenFocusTarget(false);
        panelNumber.setLayout(new java.awt.GridLayout(5, 5));

        btn1000.setBackground(new java.awt.Color(204, 204, 204));
        btn1000.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        btn1000.setText("1000");
        btn1000.setAlignmentX(0.5F);
        btn1000.setAutoscrolls(true);
        btn1000.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn1000.setDoubleBuffered(true);
        btn1000.setFocusable(false);
        btn1000.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn1000.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn1000.setRequestFocusEnabled(false);
        btn1000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1000ActionPerformed(evt);
            }
        });
        panelNumber.add(btn1000);

        btn500.setBackground(new java.awt.Color(204, 204, 255));
        btn500.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        btn500.setText("500");
        btn500.setAlignmentX(0.5F);
        btn500.setAutoscrolls(true);
        btn500.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn500.setDoubleBuffered(true);
        btn500.setFocusable(false);
        btn500.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn500.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn500.setRequestFocusEnabled(false);
        btn500.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn500ActionPerformed(evt);
            }
        });
        panelNumber.add(btn500);

        btn100.setBackground(new java.awt.Color(255, 204, 204));
        btn100.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        btn100.setText("100");
        btn100.setAlignmentX(0.5F);
        btn100.setAutoscrolls(true);
        btn100.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn100.setDoubleBuffered(true);
        btn100.setFocusable(false);
        btn100.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn100.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn100.setRequestFocusEnabled(false);
        btn100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn100ActionPerformed(evt);
            }
        });
        panelNumber.add(btn100);

        btn50.setBackground(new java.awt.Color(0, 153, 204));
        btn50.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        btn50.setText("50");
        btn50.setAlignmentX(0.5F);
        btn50.setAutoscrolls(true);
        btn50.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn50.setDoubleBuffered(true);
        btn50.setFocusable(false);
        btn50.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn50.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn50.setRequestFocusEnabled(false);
        btn50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn50ActionPerformed(evt);
            }
        });
        panelNumber.add(btn50);

        btnCLR.setBackground(new java.awt.Color(0, 102, 204));
        btnCLR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCLR.setForeground(new java.awt.Color(255, 255, 255));
        btnCLR.setText("CLR");
        btnCLR.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCLR.setFocusable(false);
        btnCLR.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnCLR.setRequestFocusEnabled(false);
        btnCLR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCLRActionPerformed(evt);
            }
        });
        panelNumber.add(btnCLR);

        btn7.setBackground(new java.awt.Color(255, 153, 0));
        btn7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn7.setText("7");
        btn7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn7.setFocusable(false);
        btn7.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn7.setRequestFocusEnabled(false);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        panelNumber.add(btn7);

        btn8.setBackground(new java.awt.Color(255, 153, 0));
        btn8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn8.setText("8");
        btn8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn8.setFocusable(false);
        btn8.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn8.setRequestFocusEnabled(false);
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        panelNumber.add(btn8);

        btn9.setBackground(new java.awt.Color(255, 153, 0));
        btn9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn9.setText("9");
        btn9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn9.setFocusable(false);
        btn9.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn9.setRequestFocusEnabled(false);
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        panelNumber.add(btn9);

        btnCash.setBackground(new java.awt.Color(0, 102, 204));
        btnCash.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCash.setForeground(new java.awt.Color(255, 255, 255));
        btnCash.setText("เงินสด");
        btnCash.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCash.setFocusable(false);
        btnCash.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnCash.setRequestFocusEnabled(false);
        btnCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCashActionPerformed(evt);
            }
        });
        panelNumber.add(btnCash);

        btnFit.setBackground(new java.awt.Color(0, 102, 204));
        btnFit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFit.setForeground(new java.awt.Color(255, 255, 255));
        btnFit.setText("พอดี");
        btnFit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnFit.setFocusable(false);
        btnFit.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnFit.setRequestFocusEnabled(false);
        btnFit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFitActionPerformed(evt);
            }
        });
        panelNumber.add(btnFit);

        btn4.setBackground(new java.awt.Color(255, 153, 0));
        btn4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn4.setText("4");
        btn4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn4.setFocusable(false);
        btn4.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn4.setRequestFocusEnabled(false);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        panelNumber.add(btn4);

        btn5.setBackground(new java.awt.Color(255, 153, 0));
        btn5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn5.setText("5");
        btn5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn5.setFocusable(false);
        btn5.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn5.setRequestFocusEnabled(false);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        panelNumber.add(btn5);

        btn6.setBackground(new java.awt.Color(255, 153, 0));
        btn6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn6.setText("6");
        btn6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn6.setFocusable(false);
        btn6.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn6.setRequestFocusEnabled(false);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        panelNumber.add(btn6);

        btnCreditAmt.setBackground(new java.awt.Color(0, 102, 204));
        btnCreditAmt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCreditAmt.setForeground(new java.awt.Color(255, 255, 255));
        btnCreditAmt.setText("เครดิต");
        btnCreditAmt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCreditAmt.setFocusable(false);
        btnCreditAmt.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnCreditAmt.setRequestFocusEnabled(false);
        btnCreditAmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreditAmtActionPerformed(evt);
            }
        });
        panelNumber.add(btnCreditAmt);

        btnMember.setBackground(new java.awt.Color(0, 102, 204));
        btnMember.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnMember.setForeground(new java.awt.Color(255, 255, 255));
        btnMember.setText("ลูกหนี้การค้า");
        btnMember.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnMember.setFocusable(false);
        btnMember.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnMember.setRequestFocusEnabled(false);
        btnMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMemberActionPerformed(evt);
            }
        });
        panelNumber.add(btnMember);

        btn1.setBackground(new java.awt.Color(255, 153, 0));
        btn1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn1.setText("1");
        btn1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn1.setFocusable(false);
        btn1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn1.setRequestFocusEnabled(false);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        panelNumber.add(btn1);

        btn2.setBackground(new java.awt.Color(255, 153, 0));
        btn2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn2.setText("2");
        btn2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn2.setFocusable(false);
        btn2.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn2.setRequestFocusEnabled(false);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        panelNumber.add(btn2);

        btn3.setBackground(new java.awt.Color(255, 153, 0));
        btn3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn3.setText("3");
        btn3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn3.setFocusable(false);
        btn3.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn3.setRequestFocusEnabled(false);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        panelNumber.add(btn3);

        btnGift.setBackground(new java.awt.Color(0, 102, 204));
        btnGift.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGift.setForeground(new java.awt.Color(255, 255, 255));
        btnGift.setText("บัตรกำนัล");
        btnGift.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGift.setFocusable(false);
        btnGift.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnGift.setRequestFocusEnabled(false);
        btnGift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiftActionPerformed(evt);
            }
        });
        panelNumber.add(btnGift);

        btnDiscount.setBackground(new java.awt.Color(0, 102, 204));
        btnDiscount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDiscount.setForeground(new java.awt.Color(255, 255, 255));
        btnDiscount.setText("Discount");
        btnDiscount.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDiscount.setFocusable(false);
        btnDiscount.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnDiscount.setRequestFocusEnabled(false);
        btnDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscountActionPerformed(evt);
            }
        });
        panelNumber.add(btnDiscount);

        btn0.setBackground(new java.awt.Color(255, 153, 0));
        btn0.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn0.setText("0");
        btn0.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn0.setFocusable(false);
        btn0.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btn0.setRequestFocusEnabled(false);
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });
        panelNumber.add(btn0);

        btnDot.setBackground(new java.awt.Color(255, 153, 0));
        btnDot.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnDot.setText(".");
        btnDot.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDot.setFocusable(false);
        btnDot.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnDot.setRequestFocusEnabled(false);
        btnDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDotActionPerformed(evt);
            }
        });
        panelNumber.add(btnDot);

        btnAR.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAR.setText("00");
        btnAR.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAR.setFocusable(false);
        btnAR.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAR.setRequestFocusEnabled(false);
        btnAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnARActionPerformed(evt);
            }
        });
        panelNumber.add(btnAR);

        c_bntenter.setBackground(new java.awt.Color(0, 102, 204));
        c_bntenter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        c_bntenter.setForeground(new java.awt.Color(255, 255, 255));
        c_bntenter.setAlignmentX(0.5F);
        c_bntenter.setAutoscrolls(true);
        c_bntenter.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        c_bntenter.setDoubleBuffered(true);
        c_bntenter.setFocusable(false);
        c_bntenter.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        c_bntenter.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntenter.setRequestFocusEnabled(false);
        panelNumber.add(c_bntenter);

        btnAccept.setBackground(new java.awt.Color(0, 153, 0));
        btnAccept.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAccept.setForeground(new java.awt.Color(255, 255, 255));
        btnAccept.setText("Enter");
        btnAccept.setAlignmentX(0.5F);
        btnAccept.setAutoscrolls(true);
        btnAccept.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAccept.setDoubleBuffered(true);
        btnAccept.setFocusable(false);
        btnAccept.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAccept.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAccept.setRequestFocusEnabled(false);
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });
        btnAccept.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAcceptKeyPressed(evt);
            }
        });
        panelNumber.add(btnAccept);

        bntPrintCheckBill.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntPrintCheckBill.setText("F8 พิมพ์ตรวจสอบ");
        bntPrintCheckBill.setFocusable(false);
        bntPrintCheckBill.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntPrintCheckBill.setRequestFocusEnabled(false);
        bntPrintCheckBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPrintCheckBillActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(204, 0, 51));
        btnExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("(ESC) ออก");
        btnExit.setFocusable(false);
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.setRequestFocusEnabled(false);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        tblShowBalance.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblShowBalance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Product Name", "Quantity", "Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblShowBalance.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(tblShowBalance);
        if (tblShowBalance.getColumnModel().getColumnCount() > 0) {
            tblShowBalance.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblShowBalance.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("เลขที่ใบเสร็จ");

        txtBillNo.setEditable(false);
        txtBillNo.setBackground(new java.awt.Color(255, 255, 255));
        txtBillNo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtBillNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBillNo.setFocusable(false);
        txtBillNo.setRequestFocusEnabled(false);

        jPanel22.setBackground(new java.awt.Color(0, 102, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel22.setRequestFocusEnabled(false);

        lbAddMoney.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbAddMoney.setForeground(new java.awt.Color(255, 255, 255));
        lbAddMoney.setText("มูลค่าสินค้ารวม (Total Amount)");
        lbAddMoney.setRequestFocusEnabled(false);
        lbAddMoney.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        txtTotalCash.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotalCash.setForeground(new java.awt.Color(255, 255, 0));
        txtTotalCash.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotalCash.setText("0.00");
        txtTotalCash.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        txtTotalCash.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lbAddMoney)
                .addGap(18, 18, 18)
                .addComponent(txtTotalCash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbAddMoney, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalCash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(0, 0, 0));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Digital_Msg.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Digital_Msg.setForeground(new java.awt.Color(255, 255, 255));
        Digital_Msg.setText("จำนวนเงิน :");
        Digital_Msg.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        txtTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtTotalAmount.setForeground(new java.awt.Color(255, 255, 0));
        txtTotalAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotalAmount.setText("0.00");
        txtTotalAmount.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(Digital_Msg, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(310, 310, 310)
                .addComponent(txtTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(Digital_Msg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(11, 11, 11))
            .addComponent(txtTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        txtReturnMoneyAmount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtReturnMoneyAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtReturnMoneyAmount.setText("0.00");
        txtReturnMoneyAmount.setFocusable(false);
        txtReturnMoneyAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtReturnMoneyAmountKeyPressed(evt);
            }
        });

        btnGiftVoucher.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGiftVoucher.setText("บัตรกำนัล/บัตรของขวัญ");
        btnGiftVoucher.setFocusable(false);
        btnGiftVoucher.setRequestFocusEnabled(false);
        btnGiftVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiftVoucherActionPerformed(evt);
            }
        });

        bntEarnest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntEarnest.setText("หักคืนเงินมัดจำ");
        bntEarnest.setFocusable(false);
        bntEarnest.setRequestFocusEnabled(false);
        bntEarnest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEarnestActionPerformed(evt);
            }
        });

        txtCashAmount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtCashAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCashAmount.setText("0.00");
        txtCashAmount.setFocusable(false);
        txtCashAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCashAmountKeyPressed(evt);
            }
        });

        bntCash.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntCash.setText("เงินสด (Cash)");
        bntCash.setFocusable(false);
        bntCash.setRequestFocusEnabled(false);
        bntCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCashActionPerformed(evt);
            }
        });

        txtGiftVoucherAmount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtGiftVoucherAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGiftVoucherAmount.setText("0.00");
        txtGiftVoucherAmount.setFocusable(false);
        txtGiftVoucherAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGiftVoucherAmountKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGiftVoucher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(bntEarnest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bntCash, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGiftVoucherAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .addComponent(txtReturnMoneyAmount)
                    .addComponent(txtCashAmount))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtReturnMoneyAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntEarnest, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiftVoucherAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGiftVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bntCash, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCashAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        txtCreditName.setEditable(false);
        txtCreditName.setBackground(new java.awt.Color(255, 255, 255));
        txtCreditName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCreditName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCreditName.setFocusable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Track NO");

        btnCredit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCredit.setText("บัตรเครดิต (Credit Card)");
        btnCredit.setFocusable(false);
        btnCredit.setRequestFocusEnabled(false);
        btnCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreditActionPerformed(evt);
            }
        });

        txtCreditTrackNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCreditTrackNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCreditTrackNo.setFocusable(false);
        txtCreditTrackNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCreditTrackNoKeyPressed(evt);
            }
        });

        txtCreditAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCreditAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCreditAmount.setText("0.00");
        txtCreditAmount.setFocusable(false);
        txtCreditAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCreditAmountKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Credit Card NO.");

        txtCreditNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCreditNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCreditNo.setFocusable(false);
        txtCreditNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCreditNoKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("จำนวนเงิน");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtCreditNo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(txtCreditTrackNo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCreditName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCreditAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreditName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCreditNo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreditTrackNo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCreditAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnDiscountAll.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDiscountAll.setText("ส่วนลดต่าง ๆ");
        btnDiscountAll.setFocusable(false);
        btnDiscountAll.setRequestFocusEnabled(false);
        btnDiscountAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscountAllActionPerformed(evt);
            }
        });

        txtDiscountAmount.setEditable(false);
        txtDiscountAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtDiscountAmount.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        txtDiscountAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDiscountAmount.setText("0.00");
        txtDiscountAmount.setFocusable(false);

        btnCredit1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCredit1.setText("ลูกหนี้การค้า (AR-Payment)");
        btnCredit1.setFocusable(false);
        btnCredit1.setRequestFocusEnabled(false);
        btnCredit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCredit1ActionPerformed(evt);
            }
        });

        txtArAmount.setEditable(false);
        txtArAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtArAmount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtArAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtArAmount.setText("0.00");
        txtArAmount.setFocusable(false);
        txtArAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtArAmountKeyPressed(evt);
            }
        });

        txtArCode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtArCode.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtArCode.setFocusable(false);
        txtArCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtArCodeKeyPressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ค้างชำระ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("เครดิต");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("วงเงิน");

        lbCredit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbCredit.setForeground(new java.awt.Color(255, 255, 255));
        lbCredit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCredit.setText("0");

        lbCreditMoney.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbCreditMoney.setForeground(new java.awt.Color(255, 255, 255));
        lbCreditMoney.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCreditMoney.setText("0.00");

        lbCreditAmt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbCreditAmt.setForeground(new java.awt.Color(255, 255, 255));
        lbCreditAmt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCreditAmt.setText("0.00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(lbCreditMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCreditAmt, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(lbCredit)
                    .addComponent(lbCreditMoney)
                    .addComponent(lbCreditAmt))
                .addContainerGap())
        );

        lbArName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbArName.setText("ทดสอบลูกหนี้");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bntPrintCheckBill)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnDiscountAll, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiscountAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnCredit1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtArCode, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtArAmount)
                            .addContainerGap())
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbArName))))
            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtArCode, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtArAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbArName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDiscountAll, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiscountAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bntPrintCheckBill, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCLRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCLRActionPerformed
        backspaceText();
    }//GEN-LAST:event_btnCLRActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        input("8");
    }//GEN-LAST:event_btn8ActionPerformed

    private void btnFitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFitActionPerformed
        if (txtCashAmount.hasFocus()) {
            txtCashAmount.setText("" + txtTotalAmount.getText());
        }
    }//GEN-LAST:event_btnFitActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        input("4");
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        input("5");
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        input("1");
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        input("2");
    }//GEN-LAST:event_btn2ActionPerformed

    private void btnDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscountActionPerformed
        btnDiscountAllActionPerformed(null);
    }//GEN-LAST:event_btnDiscountActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        input("0");
    }//GEN-LAST:event_btn0ActionPerformed

    private void btnDotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDotActionPerformed
        input(".");
    }//GEN-LAST:event_btnDotActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        input("9");
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        input("3");
    }//GEN-LAST:event_btn3ActionPerformed

    private void btnCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCashActionPerformed
        bntCashActionPerformed(null);
    }//GEN-LAST:event_btnCashActionPerformed

    private void btnCreditAmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreditAmtActionPerformed
        btnCreditActionPerformed(null);
    }//GEN-LAST:event_btnCreditAmtActionPerformed

    private void btnGiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiftActionPerformed
        btnGiftVoucherActionPerformed(null);
    }//GEN-LAST:event_btnGiftActionPerformed

    private void btn1000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1000ActionPerformed
        input("1000");
    }//GEN-LAST:event_btn1000ActionPerformed

    private void btn500ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn500ActionPerformed
        input("500");
    }//GEN-LAST:event_btn500ActionPerformed

    private void btn100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn100ActionPerformed
        input("100");
    }//GEN-LAST:event_btn100ActionPerformed

    private void btn50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn50ActionPerformed
        input("50");
    }//GEN-LAST:event_btn50ActionPerformed

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed

        new Thread(new Runnable() {

            @Override
            public void run() {
                if (txtCreditNo.hasFocus()) {
                    txtCreditTrackNo.setFocusable(true);
                    txtCreditTrackNo.requestFocus();
                } else if (txtCreditTrackNo.hasFocus()) {
                    trackNoExist();
                } else if (txtArCode.hasFocus()) {
                    arCodeExits();
                } else {
                    if (!PublicVar.SubTotalOK) {
                        checkBillPayment();
                    } else {
                        dispose();
                    }
                }
            }
        }).start();

    }//GEN-LAST:event_btnAcceptActionPerformed

    private void btnARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnARActionPerformed
        input("00");
    }//GEN-LAST:event_btnARActionPerformed

    private void btnDiscountAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscountAllActionPerformed
        DiscountDialog dd = new DiscountDialog(null, true, tableNo, tBean.getTAmount(), memberBean);
        dd.setVisible(true);

        if (dd.getDiscountBean() != null) {
            discBean = dd.getDiscountBean();
            txtDiscountAmount.setText("" + discBean.getTotalDiscount());
            loadTableBill();
        }
    }//GEN-LAST:event_btnDiscountAllActionPerformed

    private void bntPrintCheckBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPrintCheckBillActionPerformed
        printBillCheck();
    }//GEN-LAST:event_bntPrintCheckBillActionPerformed

    private void bntCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCashActionPerformed
        txtCashAmount.setFocusable(true);
        txtCashAmount.selectAll();
        txtCashAmount.requestFocus();
    }//GEN-LAST:event_bntCashActionPerformed

    private void txtCashAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashAmountKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAcceptActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
        }
    }//GEN-LAST:event_txtCashAmountKeyPressed

    private void bntEarnestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEarnestActionPerformed
        txtReturnMoneyAmount.setFocusable(true);
        txtReturnMoneyAmount.selectAll();
        txtReturnMoneyAmount.requestFocus();
    }//GEN-LAST:event_bntEarnestActionPerformed

    private void btnGiftVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiftVoucherActionPerformed
        GiftVoucherDialog giftDialog = new GiftVoucherDialog(null, true);
        giftDialog.setVisible(true);
        if (giftDialog.getTotalAmount() > 0) {
            txtGiftVoucherAmount.setFocusable(true);
            txtGiftVoucherAmount.setText("" + giftDialog.getTotalAmount());
            txtGiftVoucherAmount.selectAll();
            txtGiftVoucherAmount.requestFocus();
        } else {
            txtGiftVoucherAmount.setFocusable(true);
            txtGiftVoucherAmount.selectAll();
            txtGiftVoucherAmount.requestFocus();
        }
    }//GEN-LAST:event_btnGiftVoucherActionPerformed

    private void btnCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreditActionPerformed
        FindCredit findCredit = new FindCredit(this, true);
        findCredit.setVisible(true);
        if (findCredit.getCreditCode() != null) {
            if (!findCredit.getCreditCode().equals("")) {
                txtCreditName.setText(findCredit.getCreditCode());
                txtCreditNo.setFocusable(true);
                txtCreditNo.requestFocus();
            }
        }
    }//GEN-LAST:event_btnCreditActionPerformed

    private void btnCredit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCredit1ActionPerformed
        txtArCode.setFocusable(true);
        txtArCode.requestFocus();
    }//GEN-LAST:event_btnCredit1ActionPerformed

    private void txtArCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArCodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtArCode.getText().equals("")) {
                arCodeExits();
            }
        }
    }//GEN-LAST:event_txtArCodeKeyPressed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        input("7");
    }//GEN-LAST:event_btn7ActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        input("6");
    }//GEN-LAST:event_btn6ActionPerformed

    private void txtCreditNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditNoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCreditTrackNo.setFocusable(true);
            txtCreditTrackNo.requestFocus();
        }
    }//GEN-LAST:event_txtCreditNoKeyPressed

    private void txtCreditTrackNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditTrackNoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            trackNoExist();
        }
    }//GEN-LAST:event_txtCreditTrackNoKeyPressed

    private void txtCreditAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditAmountKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAcceptActionPerformed(null);
        }
    }//GEN-LAST:event_txtCreditAmountKeyPressed

    private void txtReturnMoneyAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReturnMoneyAmountKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAcceptActionPerformed(null);
        }
    }//GEN-LAST:event_txtReturnMoneyAmountKeyPressed

    private void txtGiftVoucherAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiftVoucherAmountKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAcceptActionPerformed(null);
        }
    }//GEN-LAST:event_txtGiftVoucherAmountKeyPressed

    private void txtArAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArAmountKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAcceptActionPerformed(null);
        }
    }//GEN-LAST:event_txtArAmountKeyPressed

    private void btnMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMemberActionPerformed
        btnCredit1ActionPerformed(null);
    }//GEN-LAST:event_btnMemberActionPerformed

    private void btnAcceptKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAcceptKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAcceptActionPerformed(null);
        }
    }//GEN-LAST:event_btnAcceptKeyPressed

    boolean _SubTotalOK = false;

    public void input(String Str) {
        if (txtCashAmount.hasFocus()) {
            String temp = txtCashAmount.getText();
            String data = Str;
            if (data.equals("1000") || data.equals("500") || data.equals("100") || data.equals("50")) {
                if (temp.equals("")) {
                    temp = "0.00";
                } else {
                    temp = temp.replace(",", "");
                }

                double tempD = Double.parseDouble(temp);
                double dataD = Double.parseDouble(data);
                double totalD = tempD + dataD;
                txtCashAmount.setText("" + totalD);
            } else {
                if (temp.equals("0.00")) {
                    temp = "";
                }
                txtCashAmount.setText(temp + Str);
            }
        } else if (txtReturnMoneyAmount.hasFocus()) {
            String temp = txtReturnMoneyAmount.getText().trim();
            if (temp.equals("0.00")) {
                temp = "";
            }
            String data = Str;
            if (data.equals("1000") || data.equals("500") || data.equals("100") || data.equals("50")) {
                if (temp.equals("")) {
                    temp = "0.00";
                } else {
                    temp = temp.replace(",", "");
                }
                double tempD = Double.parseDouble(temp);
                double dataD = Double.parseDouble(data);
                double totalD = tempD + dataD;
                txtReturnMoneyAmount.setText("" + totalD);
            } else {
                txtReturnMoneyAmount.setText(temp + Str);
            }
        } else if (txtGiftVoucherAmount.hasFocus()) {
            String temp = txtGiftVoucherAmount.getText().trim();
            String data = Str;
            if (data.equals("1000") || data.equals("500") || data.equals("100") || data.equals("50")) {
                if (temp.equals("")) {
                    temp = "0.00";
                } else {
                    temp = temp.replace(",", "");
                }
                double tempD = Double.parseDouble(temp);
                double dataD = Double.parseDouble(data);
                double totalD = tempD + dataD;
                txtGiftVoucherAmount.setText("" + totalD);
            } else {
                txtGiftVoucherAmount.setText(temp + Str);
            }
        } else if (txtCreditAmount.hasFocus()) {
            String temp = txtCreditAmount.getText().trim();
            String data = Str;
            if (data.equals("1000") || data.equals("500") || data.equals("100") || data.equals("50")) {
                if (temp.equals("")) {
                    temp = "0.00";
                } else {
                    temp = temp.replace(",", "");
                }
                double tempD = Double.parseDouble(temp);
                double dataD = Double.parseDouble(data);
                double totalD = tempD + dataD;
                txtCreditAmount.setText("" + totalD);
            } else {
                txtCreditAmount.setText(temp + Str);
            }
        } else if (txtCreditNo.hasFocus()) {
            String temp = txtCreditNo.getText().trim();
            txtCreditNo.setText(temp + Str);
        } else if (txtCreditTrackNo.hasFocus()) {
            String temp = txtCreditTrackNo.getText().trim();
            txtCreditTrackNo.setText(temp + Str);
        }
    }

    public static void main(String args[]) {
        new MySQLConnect();
        CheckBill cb = new CheckBill(null, true, "", null);
        cb.setVisible(true);
//        new MySQLConnect();
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                CheckBill dialog = new CheckBill(new javax.swing.JFrame(), true, "1", null);
//                dialog.setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Digital_Msg;
    private javax.swing.JButton bntCash;
    private javax.swing.JButton bntEarnest;
    private javax.swing.JButton bntPrintCheckBill;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn100;
    private javax.swing.JButton btn1000;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn50;
    private javax.swing.JButton btn500;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnAR;
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnCLR;
    private javax.swing.JButton btnCash;
    private javax.swing.JButton btnCredit;
    private javax.swing.JButton btnCredit1;
    private javax.swing.JButton btnCreditAmt;
    private javax.swing.JButton btnDiscount;
    private javax.swing.JButton btnDiscountAll;
    private javax.swing.JButton btnDot;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFit;
    private javax.swing.JButton btnGift;
    private javax.swing.JButton btnGiftVoucher;
    private javax.swing.JButton btnMember;
    private javax.swing.JButton c_bntenter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAddMoney;
    private javax.swing.JLabel lbArName;
    private javax.swing.JLabel lbCredit;
    private javax.swing.JLabel lbCreditAmt;
    private javax.swing.JLabel lbCreditMoney;
    private javax.swing.JPanel panelNumber;
    private javax.swing.JTable tblShowBalance;
    private javax.swing.JTextField txtArAmount;
    private javax.swing.JTextField txtArCode;
    private javax.swing.JTextField txtBillNo;
    private javax.swing.JTextField txtCashAmount;
    private javax.swing.JTextField txtCreditAmount;
    private javax.swing.JTextField txtCreditName;
    private javax.swing.JTextField txtCreditNo;
    private javax.swing.JTextField txtCreditTrackNo;
    private javax.swing.JTextField txtDiscountAmount;
    private javax.swing.JTextField txtGiftVoucherAmount;
    private javax.swing.JTextField txtItemDisc;
    private javax.swing.JTextField txtPromotion;
    private javax.swing.JTextField txtReturnMoneyAmount;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JLabel txtTotalAmount;
    private javax.swing.JLabel txtTotalCash;
    private javax.swing.JTextField txtTotalService;
    // End of variables declaration//GEN-END:variables

    private void initTable() {
        tblShowBalance.setShowGrid(true);
        tblShowBalance.setGridColor(Color.gray);
        tblShowBalance.setRowHeight(35);

        JTableControl.alignColumn(tblShowBalance, 2, "right");
        JTableControl.alignColumn(tblShowBalance, 3, "right");
        JTableControl.alignColumn(tblShowBalance, 4, "right");

        JTableHeader header = tblShowBalance.getTableHeader();
        header.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

        model = (DefaultTableModel) tblShowBalance.getModel();
        lbArName.setText("");
        lbCredit.setText("");
        lbCreditMoney.setText("");
        lbCreditAmt.setText("");

        loadTableBill();
    }

    private void loadTableBill() {
        TableFileControl tfCon = new TableFileControl();
        tBean = tfCon.getData(tableNo);

        txtTotalCash.setText(dec.format(tBean.getTAmount()));
        txtBillNo.setText(BillControl.getBillIDCurrent());

        double NetTotalUpDown = NumberControl.UP_DOWN_NATURAL_BAHT(tBean.getNetTotal());
        txtTotalAmount.setText(dec.format(NetTotalUpDown));

        int size = model.getRowCount();
        for (int i = 0; i < size; i++) {
            model.removeRow(0);
        }

        BalanceControl bc = new BalanceControl();
        ArrayList<BalanceBean> listBean = bc.getAllBalance(tableNo);
        for (int i = 0; i < listBean.size(); i++) {
            BalanceBean bean = (BalanceBean) listBean.get(i);
            if (!bean.getR_Void().equals("V")) {
                model.addRow(new Object[]{
                    bean.getR_PluCode(), bean.getR_PName(),
                    dec.format(bean.getR_Quan()), dec.format(bean.getR_Price()), dec.format(bean.getR_Total())
                });
            }
        }

        txtItemDisc.setText("" + dec.format(tBean.getItemDiscAmt()));
        txtPromotion.setText("" + dec.format(tBean.getProDiscAmt()));
        txtSubTotal.setText("" + dec.format(tBean.getSubDiscAmt()));
        txtTotalService.setText("" + dec.format(tBean.getServiceAmt()));

        bntCashActionPerformed(null);
    }

    private void backspaceText() {
        if (txtReturnMoneyAmount.hasFocus()) {
            txtReturnMoneyAmount.setText("0.00");
            txtReturnMoneyAmount.selectAll();
        } else if (txtCashAmount.hasFocus()) {
            txtCashAmount.setText("0.00");
            txtCashAmount.selectAll();
        } else if (txtCreditNo.hasFocus()) {
            txtCreditNo.setText("");
        } else if (txtCreditTrackNo.hasFocus()) {
            txtCreditTrackNo.setText("");
        } else if (txtCreditAmount.hasFocus()) {
            txtCreditAmount.setText("0.00");
        } else if (txtGiftVoucherAmount.hasFocus()) {
            txtGiftVoucherAmount.setText("0.00");
        }
    }

    private void checkBillPayment() {
        double netTotal;
        double tmpNetTotal;

        //temp
        double totalAmount = tBean.getTAmount();

        double totalDiscount = Double.parseDouble(txtDiscountAmount.getText());
        double totalItemDisc = Double.parseDouble(txtItemDisc.getText());
        double totalService;
        PosControl posControl = new PosControl();
        POSConfigSetup config = posControl.getData();
        if (config.getP_ServiceType().equals("N")) {
            totalService = (totalAmount - totalDiscount) * (config.getP_Service() / 100);
        } else if (config.getP_ServiceType().equals("G")) {
            totalService = totalAmount * (config.getP_Service() / 100);
        } else {
            totalService = 0;
        }

        netTotal = tBean.getNetTotal();

        tmpNetTotal = netTotal;
        netTotal = NumberControl.UP_DOWN_NATURAL_BAHT(netTotal);

        double returnMoney = Double.parseDouble(txtReturnMoneyAmount.getText());
        if (returnMoney > netTotal) {
            JOptionPane.showMessageDialog(this, "**** ยอดการหักคืนเงินมัดจำ (Earnest-Return) มากกว่ายอดที่ต้องจ่ายจริง ****");
            txtReturnMoneyAmount.selectAll();
            return;
        }
        double returnGift = Double.parseDouble(txtGiftVoucherAmount.getText());
        double returnCash = Double.parseDouble(txtCashAmount.getText());

        double saveAR = Double.parseDouble(txtArAmount.getText());
        double saveCredit = Double.parseDouble(txtCreditAmount.getText());
        if (saveAR > 0 && saveCredit > 0) {
            PublicVar.SubTotalOK = true;
        } else {
            double totalPayment = returnMoney + returnGift + returnCash;
            //CuponBean cuponBean = discBean.getCuponBean(); 22/07/2014
            if (totalPayment >= netTotal) {
                Digital_Msg.setText("เงินทอน");
                double ton = netTotal - totalPayment;
                if (ton < 0) {
                    ton *= -1;
                }
                txtTotalAmount.setForeground(Color.RED);
                txtTotalAmount.setText(dec.format(ton));

                BillNoBean billBean = new BillNoBean();

                billBean.setB_Ton(ton);

                // for AR
                billBean.setB_AccrCode(txtArCode.getText());
                billBean.setB_AccrAmt(saveAR);
                int creditDay;
                try {
                    creditDay = Integer.parseInt(lbCredit.getText());
                } catch (NumberFormatException e) {
                    creditDay = 0;
                }
                billBean.setB_AccrCr(creditDay);

                // for giftvoucher
                billBean.setB_GiftVoucher(returnGift);

                // for cash
                billBean.setB_NetDiff(tmpNetTotal - netTotal);
                billBean.setB_Total(netTotal);
                billBean.setB_Cash(returnCash);
                billBean.setB_ServiceAmt(totalService);
                billBean.setB_ItemDiscAmt(totalItemDisc);
                billBean.setB_NetTotal(tmpNetTotal);

                // for credit
                billBean.setB_CrCode1(txtCreditName.getText());
                billBean.setB_CardNo1(txtCreditNo.getText());
                billBean.setB_AppCode1(txtCreditTrackNo.getText());
                billBean.setB_CrAmt1(saveCredit);

                // for earnest
                billBean.setB_Earnest(returnMoney);

                //get from bean
                billBean.setB_CuponDiscAmt(discBean.getCuponDiscount());
                billBean.setB_FastDiscAmt(discBean.getFestDiscount());
                billBean.setB_FastDisc(discBean.getStrFestDiscount());
                billBean.setB_EmpDiscAmt(discBean.getEmpDiscount());
                billBean.setB_EmpDisc(discBean.getStrEmpDiscount());
                billBean.setB_TrainDiscAmt(discBean.getTrainDiscount());
                billBean.setB_TrainDisc(discBean.getStrTrainDiscount());
                billBean.setB_MemDiscAmt(discBean.getMemDiscount());
                billBean.setB_MemDisc(discBean.getStrMemDiscount());
                billBean.setB_SubDisc(discBean.getStrCuponDiscount());
                billBean.setB_SubDiscAmt(discBean.getCuponDiscount());
                billBean.setB_SubDiscBath(discBean.getBahtDiscount());

                BillControl billControl = new BillControl();
                billControl.saveBillNo(tableNo, billBean);

                PublicVar.SubTotalOK = true;
                lockScreen(this, false);
            } else if (saveCredit == netTotal || saveAR == netTotal || (saveCredit + returnCash) == netTotal) {
                Digital_Msg.setText("เงินทอน");
                double ton = 0;
                txtTotalAmount.setForeground(Color.RED);
                txtTotalAmount.setText(dec.format(ton));
                BillNoBean billBean = new BillNoBean();

                // set ton
                billBean.setB_Ton(ton);

                // for AR
                billBean.setB_AccrCode(txtArCode.getText());
                billBean.setB_AccrAmt(saveAR);
                int creditDay;
                try {
                    creditDay = Integer.parseInt(lbCredit.getText());
                } catch (NumberFormatException e) {
                    creditDay = 0;
                }
                billBean.setB_AccrCr(creditDay);

                // for giftvoucher
                billBean.setB_GiftVoucher(returnGift);

                // for cash                
                billBean.setB_NetDiff(tmpNetTotal - netTotal);
                billBean.setB_Total(netTotal);
                billBean.setB_Cash(returnCash);
                billBean.setB_ServiceAmt(totalService);
                billBean.setB_ItemDiscAmt(totalItemDisc);
                billBean.setB_NetTotal(tmpNetTotal);

                // for earnest
                billBean.setB_Earnest(returnMoney);

                // for credit
                billBean.setB_CrCode1(txtCreditName.getText());
                billBean.setB_CardNo1(txtCreditNo.getText());
                billBean.setB_AppCode1(txtCreditTrackNo.getText());
                billBean.setB_CrAmt1(saveCredit);

                //get from bean
                billBean.setB_CuponDiscAmt(discBean.getCuponDiscount());
                billBean.setB_FastDiscAmt(discBean.getFestDiscount());
                billBean.setB_FastDisc(discBean.getStrFestDiscount());
                billBean.setB_EmpDiscAmt(discBean.getEmpDiscount());
                billBean.setB_EmpDisc(discBean.getStrEmpDiscount());
                billBean.setB_TrainDiscAmt(discBean.getTrainDiscount());
                billBean.setB_TrainDisc(discBean.getStrTrainDiscount());
                billBean.setB_MemDiscAmt(discBean.getMemDiscount());
                billBean.setB_MemDisc(discBean.getStrMemDiscount());
                billBean.setB_SubDisc(discBean.getStrCuponDiscount());
                billBean.setB_SubDiscAmt(discBean.getCuponDiscount());

                BillControl billControl = new BillControl();
                billControl.saveBillNo(tableNo, billBean);

                PublicVar.SubTotalOK = true;
                Value.MemberAlready = false;

                lockScreen(this, false);
            } else {
                // กรณีใส่จำนวนเงินไม่ครบ
                double total = netTotal - returnCash;
                txtTotalAmount.setText(dec.format(total));
            }

            btnAccept.setEnabled(true);
            btnAccept.setFocusable(true);
            btnAccept.requestFocus();
        }
    }

    private void printBillCheck() {
        if (Value.useprint) {
            PPrint print = new PPrint();
            print.PrintCheckBill(tableNo);
        } else {
            JOptionPane.showMessageDialog(this, "ระบบไม่ได้กำหนดให้ใช้งานเครื่องพิมพ์ !!!");
        }
    }

    private void lockScreen(Container container, boolean b) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(b);
            component.setFocusable(false);
            if (component instanceof Container) {
                lockScreen((Container) component, b);
            }
        }

        btnAccept.setEnabled(true);
        btnExit.setEnabled(true);

        btnExit.requestFocus();
    }

    private void trackNoExist() {
        try {
            String strTotalAmount = txtTotalAmount.getText().replace(",", "");
            double TotalAmount = Double.parseDouble(strTotalAmount);
            txtCreditAmount.setFocusable(true);
            txtCreditAmount.setText("" + TotalAmount);
            txtCreditAmount.requestFocus();
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    private void arCodeExits() {
        try {
            String sql = "select sp_desc,sp_cr,sp_cramt "
                    + "from custfile "
                    + "where sp_code='" + txtArCode.getText() + "'";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            if (rs.next()) {
                lbArName.setText(ThaiUtil.ASCII2Unicode(rs.getString("sp_desc")));
                lbCredit.setText("" + rs.getInt("sp_cr"));
                lbCreditMoney.setText("" + rs.getDouble("sp_cramt"));

                try {
                    String sql2 = "select sum(aramount) total "
                            + "from accr "
                            + "where arcode='" + txtArCode.getText() + "' "
                            + "group by arcode";
                    ResultSet rs2 = MySQLConnect.getResultSet(sql2);
                    if (rs2.next()) {
                        lbCreditAmt.setText("" + rs2.getDouble("total"));
                    } else {
                        lbCreditAmt.setText("0.00");
                    }

                    rs2.close();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }

                txtArAmount.setFocusable(true);
                txtArAmount.setText(txtTotalAmount.getText());
                txtArAmount.requestFocus();
                txtArAmount.selectAll();
            } else {
                //แจ้งเตือนให้เพิ่มลูกค้าเพื่อเป็นหนี้ใหม่
                int confirm = JOptionPane.showConfirmDialog(this, "ไม่พบรหัสลูกหนี้ " + txtArCode.getText() + " ในแฟ้มข้อมูลลูกหนี้ .. ต้องการเพิ่มใหม่หรือไม่ ?");
                if (confirm == JOptionPane.YES_OPTION) {
                    AddNewArCustomer addNew = new AddNewArCustomer(null, true);
                    addNew.setVisible(true);
                }
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

}
