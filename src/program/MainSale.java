package program;

import printReport.PPrint;
import printReport.PrintSimpleForm;
import printReport.PrintDriver;
import com.softpos.floorplan.MTDRep;
import com.softpos.floorplan.DailyRep;
import com.softpos.floorplan.RefundBill;
import com.softpos.floorplan.ShowTable;
import com.softpos.floorplan.MoveGroupTable;
import com.softpos.floorplan.PaidoutFrm;
import com.softpos.floorplan.PaidinFrm;
import com.softpos.member.MemberBean;
import com.softpos.member.MemberControl;
import database.MySQLConnect;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import setupmenu.SetHeaderMenu;
import static soft.virtual.JTableControl.alignColumn;
import soft.virtual.KeyBoardDialog;
import util.Option;
import util.MSG;

public class MainSale extends javax.swing.JDialog {

    private PPrint Prn = new PPrint();
    private Timer timer;
    private DefaultTableModel model;
    private SimpleDateFormat DatefmtShow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private SimpleDateFormat Timefmt = new SimpleDateFormat("HH:mm:ss");
    private boolean TableOpenStatus;
    private DecimalFormat QtyIntFmt = new DecimalFormat("###########0");
    private String TempStatus = "";
    private POSHWSetup POSHW;
    private POSConfigSetup CONFIG;
    public static String INDEX_NAME = "";
    private ArrayList<Integer> historyBack;
    private DecimalFormat dc1 = new DecimalFormat("#,##0.00");
    private MemberBean memberBean;
    private String tableNo;

    public MainSale(java.awt.Frame parent, boolean modal, String tableNo) {
        super(parent, modal);
        initComponents();
        
        BalanceControl.updateProSerTable(tableNo, null);

        Value.MemberAlready = false;
        try {
            Image im = ImageIO.read(getClass().getResource("/images/tb.png"));
            setIconImage(im);
        } catch (IOException ex) {
            MSG.ERR(null, ex.getMessage());
        }
        LoadVariable();

        TimeOfDay time = new TimeOfDay();
        timer = new Timer(1000, time);
        timer.start();

        POSHW = POSHWSetup.Bean(Value.getMacno());
        CONFIG = POSConfigSetup.Bean();

        initScreen();
        setupMenu();
        super.setTitle(super.getTitle() + " (" + Value.USERCODE + ") เลขที่ใบเสร็จ: "+BillControl.getBillIDCurrent());
        BranchBean branchBean = BranchControl.getData();
        if (branchBean.getLocation_Area().equals("02")) {
            txtShowETD.setText("T");
        }

        this.tableNo = tableNo;
        txtTable.setText(tableNo);
        tblShowPluShow(txtTable.getText());

        historyBack = new ArrayList<Integer>();

        //setUndecorated(true);
        //setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        Dimension d = getMaximumSize();
        setSize(1024, 768);
        setLocationRelativeTo(null);

        txtPluCode.setEditable(true);
        txtPluCode.setFocusable(true);
        txtPluCode.requestFocus();
    }

    @SuppressWarnings("static-access")
    public boolean ChkEJPath() {
        if (POSHW.getEJounal().equals("Y")) {
            try {
                String TempFile = POSHW.getEJDailyPath();
                File fObject = new File(TempFile);
                if (!fObject.exists()) {
                    MSG.ERR(this, "ไม่สามารถสร้าง Log File/EJFile ตามตำแหน่งที่เก็บข้อมูล Log File/EJ ได้ กรุณาติดต่อเจ้าหน้าที่ Support...");
                    return false;
                }
                return true;
            } catch (HeadlessException e) {
                MSG.ERR(this, "ไม่สามารถสร้าง Log File/EJFile ตามตำแหน่งที่เก็บข้อมูล Log File/EJ ได้ กรุณาติดต่อเจ้าหน้าที่ Support...");
                return false;
            }
        } else {
            return true;
        }
    }

    private void LoadLoginForm() {
        this.setVisible(false);
        showTableOpen();
    }

    void showTableOpen() {
        this.setVisible(false);
    }

    public void UpdateEffProduct(String eff_date, String pcode, double price1, double price2, double price3, double price4, double price5, String promotion1, String promotion2, String promotion3) {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "update product set pprice11=?,pprice12=?,pprice13=?,pprice14=?,pprice15=?,ppromotion1=?,ppromotion2=?,ppromotion3=? "
                    + " where pcode=? ";
            PreparedStatement prm = MySQLConnect.con.prepareStatement(SqlQuery);
            prm.setDouble(1, price1);
            prm.setDouble(2, price2);
            prm.setDouble(3, price3);
            prm.setDouble(4, price4);
            prm.setDouble(5, price5);
            prm.setString(6, promotion1);
            prm.setString(7, promotion2);
            prm.setString(8, promotion3);
            prm.setString(9, pcode);
            prm.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    public void DeleteEffProduct(String eff_date, String pcode) {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "delete from effective where eff_date= '" + eff_date + "' and pcode = '" + pcode + "'";
            stmt.executeUpdate(SqlQuery);
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    private void LoadVariable() {
        try {
            Statement stmt = MySQLConnect.con.createStatement();

            String SQLQuery2 = "select *from billno order by b_ondate";
            ResultSet rec2 = stmt.executeQuery(SQLQuery2);
            rec2.first();
            if (rec2.getRow() == 0) {
                PublicVar.SaleDate = new Date();
            } else {
                PublicVar.SaleDate = rec2.getDate("b_Ondate");
            }
            rec2.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
        }
        //check cr_cardno1 max = 100;
        try {
            String sql = "show columns from invcashdoc";
            Statement stmt = MySQLConnect.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String field = rs.getString(1);
                String type = rs.getString(2);
                if (field.equalsIgnoreCase("crno")) {
                    if (!type.equalsIgnoreCase("varchar(100)")) {
                        //modify
                        String sql2 = "alter table invcashdoc "
                                + "change CrNo CrNo varchar(100) NOT NULL Default '' ";
                        Statement stmt2 = MySQLConnect.con.createStatement();
                        stmt2.executeUpdate(sql2);
                        stmt2.close();
                    }
                }
            }
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
        }

    }

    public void ShowSaleType(String SaleType) {
        txtShowETD.setText(SaleType);
    }

    private void initScreen() {
        model = (DefaultTableModel) tblShowBalance.getModel();

        tblShowBalance.setShowGrid(true);
        tblShowBalance.setGridColor(Color.gray);
        tblShowBalance.setRowHeight(35);

        JTableHeader header = tblShowBalance.getTableHeader();
        header.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));

        alignColumn(tblShowBalance, 2, "right");
        alignColumn(tblShowBalance, 3, "right");
        alignColumn(tblShowBalance, 4, "right");

        PublicVar.CheckStockOnLine = PUtility.GetStockOnLine();

        TableOpenStatus = false;
        PublicVar.ErrorColect = false;

        ShowSaleType(POSHW.getSaleType());
        PublicVar.P_LogoffOK = false;

        PublicVar.CouponCnt = 0;
        PublicVar.P_ItemCount = 0;
        PublicVar.P_TotalAmt = 0.00;
        PublicVar.P_TotService = 0.00;
        PublicVar.P_TotDiscount = 0.00;
        PublicVar.P_NetAmt = 0.00;

        txtTable.setText("");
        txtCust.setText("0");
        txtPluCode.setText("");
        txtPrice.setText("0.00");
        txtTable.setEditable(true);
        txtCust.setEditable(false);
        txtPluCode.setEditable(false);
        txtPrice.setEditable(false);

        txtTable.requestFocus();

        lbTotalAmount.setText("0.00");
        txtTotalAmount.setText("0.00");
        txtItemDisc.setText("0.00");
        txtPromotion.setText("0.00");
        txtSubTotal.setText("0.00");
        txtTotalService.setText("0.00");

        ChangeSaleType("E");

        GetTableAction();

    }

    public void GetTableAction() {
        txtTable.setEditable(true);
        txtTable.requestFocus();
    }

    private void input(String str) {
        if (txtCust.hasFocus()) {
            String tempstr;
            tempstr = txtCust.getText();
            if (!isSelected) {
                tempstr = tempstr + str;
            } else {
                tempstr = str;
                isSelected = false;
            }
            txtCust.setText(tempstr);
        } else if (txtPrice.hasFocus()) {
            String tempstr;
            tempstr = txtPrice.getText();
            tempstr = tempstr + str;
            txtPrice.setText(tempstr);
        } else {
            String tempstr;
            tempstr = txtPluCode.getText();
            tempstr = tempstr + str;
            txtPluCode.setText(tempstr);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel8 = new javax.swing.JPanel();
        txtMember = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        txtTotalService = new javax.swing.JTextField();
        txtPromotion = new javax.swing.JTextField();
        txtItemDisc = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTotalAmount = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        panelMenuFunction = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblShowBalance = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lbTotalAmount = new javax.swing.JLabel();
        txtShowTime1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtTable = new javax.swing.JTextField();
        txtCust = new javax.swing.JTextField();
        txtShowETD = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTypeDesc = new javax.swing.JTextField();
        tbpMain = new javax.swing.JTabbedPane();
        pMenu1 = new javax.swing.JPanel();
        pMenu2 = new javax.swing.JPanel();
        pMenu3 = new javax.swing.JPanel();
        pMenu4 = new javax.swing.JPanel();
        pMenu5 = new javax.swing.JPanel();
        pMenu6 = new javax.swing.JPanel();
        pMenu7 = new javax.swing.JPanel();
        pMenu8 = new javax.swing.JPanel();
        pMenu9 = new javax.swing.JPanel();
        pSubMenu1 = new javax.swing.JPanel();
        pSubMenu2 = new javax.swing.JPanel();
        pSubMenu3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        getplupanel = new javax.swing.JPanel();
        txtPluCode = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        panelNumPad = new javax.swing.JPanel();
        btnAct7 = new javax.swing.JButton();
        btnAct8 = new javax.swing.JButton();
        btnAct9 = new javax.swing.JButton();
        c_bntmulti = new javax.swing.JButton();
        c_bntESC = new javax.swing.JButton();
        btnAct4 = new javax.swing.JButton();
        btnAct5 = new javax.swing.JButton();
        btnAct6 = new javax.swing.JButton();
        c_bntsub = new javax.swing.JButton();
        c_bntbs = new javax.swing.JButton();
        btnAct1 = new javax.swing.JButton();
        btnAct2 = new javax.swing.JButton();
        btnAct3 = new javax.swing.JButton();
        btnAct0 = new javax.swing.JButton();
        c_bntenter = new javax.swing.JButton();
        c_bntenter1 = new javax.swing.JButton();
        jMenuBar11 = new javax.swing.JMenuBar();
        MMainMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MCancelArPayment2 = new javax.swing.JMenuItem();
        MCancelArPayment1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        MAddNewAccr1 = new javax.swing.JMenuItem();
        MRepArNotPayment1 = new javax.swing.JMenuItem();
        MRepArHistory1 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JSeparator();
        MAddNewMember1 = new javax.swing.JMenuItem();
        MRepMemberHistory1 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JSeparator();
        MRepInvCash1 = new javax.swing.JMenuItem();
        MRepInvAr1 = new javax.swing.JMenuItem();
        MHeaderBill1 = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JSeparator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();

        jPopupMenu1.setBackground(new java.awt.Color(102, 153, 0));
        jPopupMenu1.setForeground(new java.awt.Color(255, 255, 255));

        jMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem1.setText("แก้ไขรายการ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("โปรแกรมร้านอาหาร");

        txtMember.setEditable(false);
        txtMember.setBackground(new java.awt.Color(255, 255, 153));
        txtMember.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMember.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMember.setText("ระบบสมาชิก : <ท่านไม่ได้ใช้ระบบสมาชิก> : แต้มสะสม 0 คะแนน");
        txtMember.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMember.setFocusable(false);
        txtMember.setRequestFocusEnabled(false);

        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtTotalService.setEditable(false);
        txtTotalService.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalService.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotalService.setForeground(new java.awt.Color(0, 51, 204));
        txtTotalService.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalService.setText("0.00");
        txtTotalService.setDisabledTextColor(java.awt.Color.black);
        txtTotalService.setFocusable(false);
        txtTotalService.setRequestFocusEnabled(false);

        txtPromotion.setEditable(false);
        txtPromotion.setBackground(new java.awt.Color(255, 255, 255));
        txtPromotion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtPromotion.setForeground(new java.awt.Color(0, 51, 204));
        txtPromotion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPromotion.setText("0.00");
        txtPromotion.setDisabledTextColor(java.awt.Color.black);
        txtPromotion.setFocusable(false);
        txtPromotion.setRequestFocusEnabled(false);

        txtItemDisc.setEditable(false);
        txtItemDisc.setBackground(new java.awt.Color(255, 255, 255));
        txtItemDisc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtItemDisc.setForeground(new java.awt.Color(0, 51, 204));
        txtItemDisc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtItemDisc.setText("0.00");
        txtItemDisc.setDisabledTextColor(java.awt.Color.black);
        txtItemDisc.setFocusable(false);
        txtItemDisc.setRequestFocusEnabled(false);

        txtSubTotal.setEditable(false);
        txtSubTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtSubTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtSubTotal.setForeground(new java.awt.Color(0, 51, 204));
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubTotal.setText("0.00");
        txtSubTotal.setDisabledTextColor(java.awt.Color.black);
        txtSubTotal.setFocusable(false);
        txtSubTotal.setRequestFocusEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Discount");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Promotion");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ส่วนลดท้ายบิล");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Service");

        txtTotalAmount.setEditable(false);
        txtTotalAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotalAmount.setForeground(new java.awt.Color(0, 51, 204));
        txtTotalAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalAmount.setText("0.00");
        txtTotalAmount.setDisabledTextColor(java.awt.Color.black);
        txtTotalAmount.setFocusable(false);
        txtTotalAmount.setRequestFocusEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Amount");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtItemDisc))
                .addGap(3, 3, 3)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPromotion)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtSubTotal)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalService)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalAmount)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalService, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSubTotal)
                            .addComponent(txtPromotion)
                            .addComponent(txtItemDisc))))
                .addContainerGap())
        );

        panelMenuFunction.setRequestFocusEnabled(false);
        panelMenuFunction.setLayout(new java.awt.GridLayout(1, 6));

        jButton3.setBackground(new java.awt.Color(0, 102, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/split.png"))); // NOI18N
        jButton3.setText("แยกชำระ");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setRequestFocusEnabled(false);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panelMenuFunction.add(jButton3);

        jButton6.setBackground(new java.awt.Color(0, 102, 204));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/move_1.png"))); // NOI18N
        jButton6.setText("ย้ายรายการ");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setRequestFocusEnabled(false);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        panelMenuFunction.add(jButton6);

        jButton4.setBackground(new java.awt.Color(0, 102, 204));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/member.png"))); // NOI18N
        jButton4.setText("สมาชิก");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setRequestFocusEnabled(false);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        panelMenuFunction.add(jButton4);

        jButton5.setBackground(new java.awt.Color(0, 102, 204));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hold.png"))); // NOI18N
        jButton5.setText("พักโต๊ะ");
        jButton5.setActionCommand("พักบิล/พักโต๊ะ (F3)");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setRequestFocusEnabled(false);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        panelMenuFunction.add(jButton5);

        jButton15.setBackground(new java.awt.Color(0, 102, 204));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checkbill.png"))); // NOI18N
        jButton15.setText("ชำระเงิน");
        jButton15.setFocusable(false);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton15.setRequestFocusEnabled(false);
        jButton15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        panelMenuFunction.add(jButton15);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMember, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMenuFunction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMember, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMenuFunction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblShowBalance.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblShowBalance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Product Name", "Quantity", "Price", "Total", "Void Flag", "Type Sale", "Print to Kic", "Order Send", "Promotion", "RIndex"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblShowBalance.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblShowBalance.setFocusable(false);
        tblShowBalance.setRequestFocusEnabled(false);
        tblShowBalance.setRowHeight(25);
        tblShowBalance.setShowVerticalLines(false);
        tblShowBalance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblShowBalanceMouseClicked(evt);
            }
        });
        tblShowBalance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblShowBalanceKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblShowBalance);
        if (tblShowBalance.getColumnModel().getColumnCount() > 0) {
            tblShowBalance.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblShowBalance.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jPanel3.setBackground(new java.awt.Color(0, 153, 204));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lbTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbTotalAmount.setForeground(new java.awt.Color(255, 255, 255));
        lbTotalAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTotalAmount.setText("0.00");

        txtShowTime1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtShowTime1.setForeground(new java.awt.Color(255, 255, 0));
        txtShowTime1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtShowTime1.setText("Net Total");
        txtShowTime1.setRequestFocusEnabled(false);
        txtShowTime1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtShowTime1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(lbTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                .addComponent(txtShowTime1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(0, 204, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtTable.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        txtTable.setForeground(new java.awt.Color(255, 0, 0));
        txtTable.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtTable.setDisabledTextColor(java.awt.Color.red);
        txtTable.setFocusable(false);
        txtTable.setMargin(new java.awt.Insets(2, 10, 2, 2));
        txtTable.setRequestFocusEnabled(false);
        txtTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTableFocusGained(evt);
            }
        });
        txtTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTableKeyPressed(evt);
            }
        });

        txtCust.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        txtCust.setForeground(new java.awt.Color(255, 0, 0));
        txtCust.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCust.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCust.setDisabledTextColor(java.awt.Color.black);
        txtCust.setFocusable(false);
        txtCust.setRequestFocusEnabled(false);
        txtCust.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCustMouseClicked(evt);
            }
        });
        txtCust.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCustFocusGained(evt);
            }
        });
        txtCust.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCustKeyPressed(evt);
            }
        });

        txtShowETD.setEditable(false);
        txtShowETD.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        txtShowETD.setForeground(new java.awt.Color(255, 255, 255));
        txtShowETD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtShowETD.setText("E");
        txtShowETD.setDisabledTextColor(java.awt.Color.black);
        txtShowETD.setEnabled(false);
        txtShowETD.setFocusable(false);
        txtShowETD.setRequestFocusEnabled(false);
        txtShowETD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtShowETDMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("โต๊ะ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("ลูกค้า");

        txtTypeDesc.setEditable(false);
        txtTypeDesc.setBackground(new java.awt.Color(0, 204, 102));
        txtTypeDesc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTypeDesc.setForeground(new java.awt.Color(255, 255, 255));
        txtTypeDesc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTypeDesc.setText("Eat - In");
        txtTypeDesc.setDisabledTextColor(java.awt.Color.black);
        txtTypeDesc.setEnabled(false);
        txtTypeDesc.setFocusable(false);
        txtTypeDesc.setRequestFocusEnabled(false);
        txtTypeDesc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTypeDescMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTable, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCust, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtShowETD, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTypeDesc))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(txtShowETD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtCust, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel14)
                .addComponent(txtTable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel10)
                .addComponent(txtTypeDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
        );

        tbpMain.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tbpMain.setFocusable(false);
        tbpMain.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbpMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpMainMouseClicked(evt);
            }
        });

        pMenu1.setForeground(new java.awt.Color(255, 0, 0));
        pMenu1.setLayout(new java.awt.GridLayout(4, 6));
        tbpMain.addTab("A", pMenu1);

        pMenu2.setLayout(new java.awt.GridLayout(4, 6));
        tbpMain.addTab("B", pMenu2);

        pMenu3.setLayout(new java.awt.GridLayout(4, 6));
        tbpMain.addTab("C", pMenu3);

        pMenu4.setLayout(new java.awt.GridLayout(4, 6));
        tbpMain.addTab("D", pMenu4);

        pMenu5.setLayout(new java.awt.GridLayout(4, 6));
        tbpMain.addTab("E", pMenu5);

        pMenu6.setLayout(new java.awt.GridLayout(4, 6));
        tbpMain.addTab("F", pMenu6);

        pMenu7.setLayout(new java.awt.GridLayout(4, 6));
        tbpMain.addTab("G", pMenu7);

        pMenu8.setLayout(new java.awt.GridLayout(4, 6));
        tbpMain.addTab("H", pMenu8);

        pMenu9.setLayout(new java.awt.GridLayout(4, 6));
        tbpMain.addTab("J", pMenu9);

        pSubMenu1.setLayout(new java.awt.GridLayout(4, 6));
        tbpMain.addTab("", pSubMenu1);

        pSubMenu2.setLayout(new java.awt.GridLayout(4, 6));
        tbpMain.addTab("", pSubMenu2);

        pSubMenu3.setLayout(new java.awt.GridLayout(4, 6));
        tbpMain.addTab("", pSubMenu3);

        jPanel6.setRequestFocusEnabled(false);

        getplupanel.setRequestFocusEnabled(false);
        getplupanel.setVerifyInputWhenFocusTarget(false);

        txtPluCode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtPluCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPluCode.setMargin(new java.awt.Insets(2, 0, 2, 2));
        txtPluCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPluCodeMouseClicked(evt);
            }
        });
        txtPluCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPluCodeFocusGained(evt);
            }
        });
        txtPluCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPluCodeKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("ราคา/หน่วย");

        txtPrice.setEditable(false);
        txtPrice.setBackground(new java.awt.Color(255, 51, 51));
        txtPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtPrice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrice.setText("0.00");
        txtPrice.setFocusable(false);
        txtPrice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPrice.setRequestFocusEnabled(false);
        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPriceKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("รายการอาหาร");

        javax.swing.GroupLayout getplupanelLayout = new javax.swing.GroupLayout(getplupanel);
        getplupanel.setLayout(getplupanelLayout);
        getplupanelLayout.setHorizontalGroup(
            getplupanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, getplupanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPluCode, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        getplupanelLayout.setVerticalGroup(
            getplupanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getplupanelLayout.createSequentialGroup()
                .addGroup(getplupanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPluCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelNumPad.setFocusable(false);
        panelNumPad.setOpaque(false);
        panelNumPad.setRequestFocusEnabled(false);
        panelNumPad.setLayout(new java.awt.GridLayout(4, 4, 2, 2));

        btnAct7.setBackground(new java.awt.Color(0, 102, 255));
        btnAct7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAct7.setForeground(new java.awt.Color(255, 255, 255));
        btnAct7.setText("7");
        btnAct7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAct7.setFocusable(false);
        btnAct7.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAct7.setRequestFocusEnabled(false);
        btnAct7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAct7ActionPerformed(evt);
            }
        });
        panelNumPad.add(btnAct7);

        btnAct8.setBackground(new java.awt.Color(0, 102, 255));
        btnAct8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAct8.setForeground(new java.awt.Color(255, 255, 255));
        btnAct8.setText("8");
        btnAct8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAct8.setFocusable(false);
        btnAct8.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAct8.setRequestFocusEnabled(false);
        btnAct8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAct8ActionPerformed(evt);
            }
        });
        panelNumPad.add(btnAct8);

        btnAct9.setBackground(new java.awt.Color(0, 102, 255));
        btnAct9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAct9.setForeground(new java.awt.Color(255, 255, 255));
        btnAct9.setText("9");
        btnAct9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAct9.setFocusable(false);
        btnAct9.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAct9.setRequestFocusEnabled(false);
        btnAct9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAct9ActionPerformed(evt);
            }
        });
        panelNumPad.add(btnAct9);

        c_bntmulti.setBackground(new java.awt.Color(0, 102, 255));
        c_bntmulti.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        c_bntmulti.setForeground(new java.awt.Color(255, 255, 255));
        c_bntmulti.setText("-");
        c_bntmulti.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        c_bntmulti.setFocusable(false);
        c_bntmulti.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntmulti.setRequestFocusEnabled(false);
        c_bntmulti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntmultiActionPerformed(evt);
            }
        });
        panelNumPad.add(c_bntmulti);

        c_bntESC.setBackground(new java.awt.Color(0, 102, 255));
        c_bntESC.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        c_bntESC.setForeground(new java.awt.Color(255, 255, 255));
        c_bntESC.setText("4");
        c_bntESC.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        c_bntESC.setFocusable(false);
        c_bntESC.setIconTextGap(1);
        c_bntESC.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntESC.setRequestFocusEnabled(false);
        c_bntESC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntESCActionPerformed(evt);
            }
        });
        panelNumPad.add(c_bntESC);

        btnAct4.setBackground(new java.awt.Color(0, 102, 255));
        btnAct4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAct4.setForeground(new java.awt.Color(255, 255, 255));
        btnAct4.setText("5");
        btnAct4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAct4.setFocusable(false);
        btnAct4.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAct4.setRequestFocusEnabled(false);
        btnAct4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAct4ActionPerformed(evt);
            }
        });
        panelNumPad.add(btnAct4);

        btnAct5.setBackground(new java.awt.Color(0, 102, 255));
        btnAct5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAct5.setForeground(new java.awt.Color(255, 255, 255));
        btnAct5.setText("6");
        btnAct5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAct5.setFocusable(false);
        btnAct5.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAct5.setRequestFocusEnabled(false);
        btnAct5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAct5ActionPerformed(evt);
            }
        });
        panelNumPad.add(btnAct5);

        btnAct6.setBackground(new java.awt.Color(0, 102, 255));
        btnAct6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAct6.setForeground(new java.awt.Color(255, 255, 255));
        btnAct6.setText("+");
        btnAct6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAct6.setFocusable(false);
        btnAct6.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAct6.setRequestFocusEnabled(false);
        btnAct6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAct6ActionPerformed(evt);
            }
        });
        panelNumPad.add(btnAct6);

        c_bntsub.setBackground(new java.awt.Color(0, 102, 255));
        c_bntsub.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        c_bntsub.setForeground(new java.awt.Color(255, 255, 255));
        c_bntsub.setText("1");
        c_bntsub.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        c_bntsub.setFocusable(false);
        c_bntsub.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntsub.setRequestFocusEnabled(false);
        c_bntsub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntsubActionPerformed(evt);
            }
        });
        panelNumPad.add(c_bntsub);

        c_bntbs.setBackground(new java.awt.Color(0, 102, 255));
        c_bntbs.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        c_bntbs.setForeground(new java.awt.Color(255, 255, 255));
        c_bntbs.setText("2");
        c_bntbs.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        c_bntbs.setFocusable(false);
        c_bntbs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        c_bntbs.setIconTextGap(1);
        c_bntbs.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntbs.setRequestFocusEnabled(false);
        c_bntbs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntbsActionPerformed(evt);
            }
        });
        panelNumPad.add(c_bntbs);

        btnAct1.setBackground(new java.awt.Color(0, 102, 255));
        btnAct1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAct1.setForeground(new java.awt.Color(255, 255, 255));
        btnAct1.setText("3");
        btnAct1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAct1.setFocusable(false);
        btnAct1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAct1.setRequestFocusEnabled(false);
        btnAct1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAct1ActionPerformed(evt);
            }
        });
        panelNumPad.add(btnAct1);

        btnAct2.setBackground(new java.awt.Color(0, 102, 255));
        btnAct2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAct2.setForeground(new java.awt.Color(255, 255, 255));
        btnAct2.setText("x");
        btnAct2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAct2.setFocusable(false);
        btnAct2.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAct2.setRequestFocusEnabled(false);
        btnAct2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAct2ActionPerformed(evt);
            }
        });
        panelNumPad.add(btnAct2);

        btnAct3.setBackground(new java.awt.Color(0, 102, 255));
        btnAct3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAct3.setForeground(new java.awt.Color(255, 255, 255));
        btnAct3.setText("0");
        btnAct3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAct3.setFocusable(false);
        btnAct3.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAct3.setRequestFocusEnabled(false);
        btnAct3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAct3ActionPerformed(evt);
            }
        });
        panelNumPad.add(btnAct3);

        btnAct0.setBackground(new java.awt.Color(0, 102, 255));
        btnAct0.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAct0.setForeground(new java.awt.Color(255, 255, 255));
        btnAct0.setText(".");
        btnAct0.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAct0.setFocusable(false);
        btnAct0.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnAct0.setRequestFocusEnabled(false);
        btnAct0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAct0ActionPerformed(evt);
            }
        });
        panelNumPad.add(btnAct0);

        c_bntenter.setBackground(new java.awt.Color(0, 102, 255));
        c_bntenter.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        c_bntenter.setForeground(new java.awt.Color(255, 255, 255));
        c_bntenter.setText("CE");
        c_bntenter.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        c_bntenter.setFocusable(false);
        c_bntenter.setIconTextGap(1);
        c_bntenter.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntenter.setRequestFocusEnabled(false);
        c_bntenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntenterActionPerformed(evt);
            }
        });
        panelNumPad.add(c_bntenter);

        c_bntenter1.setBackground(new java.awt.Color(0, 102, 255));
        c_bntenter1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        c_bntenter1.setForeground(new java.awt.Color(255, 255, 255));
        c_bntenter1.setText("Enter");
        c_bntenter1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        c_bntenter1.setFocusable(false);
        c_bntenter1.setIconTextGap(1);
        c_bntenter1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        c_bntenter1.setRequestFocusEnabled(false);
        c_bntenter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntenter1ActionPerformed(evt);
            }
        });
        panelNumPad.add(c_bntenter1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNumPad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(getplupanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(getplupanel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNumPad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MMainMenu1.setText("เมนูหลักระบบ (Main Menu)");
        MMainMenu1.setDelay(100);
        MMainMenu1.setDoubleBuffered(true);
        MMainMenu1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        MMainMenu1.setRequestFocusEnabled(false);
        MMainMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MMainMenuActionPerformed(evt);
            }
        });

        jMenuItem7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem7.setText("คืนเงินมัดจำเป็นเงินสด");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        MMainMenu1.add(jMenuItem7);

        jMenuItem8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem8.setText("ยกเลิกการคืนเงินมัดจำ");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        MMainMenu1.add(jMenuItem8);
        MMainMenu1.add(jSeparator1);

        MCancelArPayment2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MCancelArPayment2.setText("รับชำระจากลูกหนี้ภายนอก");
        MCancelArPayment2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCancelArPayment2ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MCancelArPayment2);

        MCancelArPayment1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MCancelArPayment1.setText("ยกเลิกการรับชำระจากลูกหนี้ภายนอก");
        MCancelArPayment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCancelArPayment1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MCancelArPayment1);
        MMainMenu1.add(jSeparator2);

        MAddNewAccr1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MAddNewAccr1.setText("ปรับปรุงรายละเอียดลูกหนี้ภายนอก");
        MAddNewAccr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MAddNewAccr1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MAddNewAccr1);

        MRepArNotPayment1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MRepArNotPayment1.setText("รายงานลูกหนี้ภายนอกค้างชำระ");
        MRepArNotPayment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRepArNotPayment1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MRepArNotPayment1);

        MRepArHistory1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MRepArHistory1.setText("ประวัติการซื้อของลูกหนี้ภายนอก");
        MRepArHistory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRepArHistory1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MRepArHistory1);
        MMainMenu1.add(jSeparator10);

        MAddNewMember1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MAddNewMember1.setText("ปรับปรุงข้อมูลสมาชิก (Member)");
        MAddNewMember1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MAddNewMember1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MAddNewMember1);

        MRepMemberHistory1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MRepMemberHistory1.setText("ประวัติการซื้อของสมาชิก");
        MRepMemberHistory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRepMemberHistory1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MRepMemberHistory1);
        MMainMenu1.add(jSeparator11);

        MRepInvCash1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MRepInvCash1.setText("พิมพ์ใบกำกับภาษี/ใบเสร็จรับเงิน");
        MRepInvCash1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRepInvCash1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MRepInvCash1);

        MRepInvAr1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MRepInvAr1.setText("พิมพ์ใบกำกับภาษี/ใบแจ้งหนี้");
        MRepInvAr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRepInvAr1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MRepInvAr1);

        MHeaderBill1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MHeaderBill1.setText("พิมพ์หัวกระดาษ/ท้ายกระดาษ");
        MHeaderBill1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MHeaderBill1ActionPerformed(evt);
            }
        });
        MMainMenu1.add(MHeaderBill1);
        MMainMenu1.add(jSeparator12);

        jMenuItem4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem4.setText("กำหนด Header TAB");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        MMainMenu1.add(jMenuItem4);

        jMenuBar11.add(MMainMenu1);

        jMenu1.setText("ช่วยเหลือ (Help)");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jCheckBoxMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_DOWN, 0));
        jCheckBoxMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("ใช้คีย์ลัด ไม่แสดงปุ่มคำสั่ง");
        jCheckBoxMenuItem1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxMenuItem1ItemStateChanged(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem1);

        jCheckBoxMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_UP, 0));
        jCheckBoxMenuItem2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("ซ่อน Number Pad");
        jCheckBoxMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem2);

        jMenuBar11.add(jMenu1);

        setJMenuBar(jMenuBar11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbpMain, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tbpMain)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbpMain.getAccessibleContext().setAccessibleName("...");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void c_bntsubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntsubActionPerformed
        input("1");
    }//GEN-LAST:event_c_bntsubActionPerformed

    void TableOpened() {
        if (!txtTable.getText().trim().equals("")) {
            txtTableOnExit();
            showSum();
            if (CONFIG.getP_EmpUse().equals("Y")) {

            } else {
                if (PublicVar.TableRec_TCustomer == 0) {
                    txtCust.setEditable(true);
                    txtCust.requestFocus();
                    txtCust.selectAll();
                } else {
                    txtPluCode.setEditable(true);
                    txtPluCode.setBackground(Color.WHITE);
                    txtPluCode.requestFocus();
                }
            }
        } else {
            if (txtTable.getText().trim().length() > 5) {
                MSG.ERR(this, "หมายเลขโต๊ะต้องกำหนดเป็นตัวเลข 0-9 เท่านั้น และกำหนดได้ไม่เกิน 5 ตัวอักษร...");
                txtTable.selectAll();
                txtTable.requestFocus();
            }
        }
    }

private void txtTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTableKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        TableOpened();
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        txtTable.setText("");
        bntlogoffuserClick();
    } else if (evt.getKeyCode() == KeyEvent.VK_F1) {
        showFloorPlan();
    } else if (evt.getKeyCode() == KeyEvent.VK_F5) {
        showTableAvialble();
    } else if (evt.getKeyCode() == KeyEvent.VK_F8) {
        showPaidIn();
    } else if (evt.getKeyCode() == KeyEvent.VK_F9) {
        showPaidOut();
    } else if (evt.getKeyCode() == KeyEvent.VK_F7) {
        showRefundBill();
    } else if (evt.getKeyCode() == KeyEvent.VK_F12) {
        showPayAR();
    }
}//GEN-LAST:event_txtTableKeyPressed

    public TableFileBean LoadDataFromTableFile(String P_Table) {
        TableFileControl tc = new TableFileControl();
        return tc.getData(P_Table);
    }

    private void tblShowPluShow(String P_Table) {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String LoadBalance = "select * from balance "
                    + "where r_table='" + P_Table + "' "
                    + "order by r_index";
            ResultSet rs = stmt.executeQuery(LoadBalance);

            int RowCount = model.getRowCount();
            for (int i = 0; i < RowCount; i++) {
                model.removeRow(0);
            }

            while (rs.next()) {
                String voidStr = rs.getString("r_void");
                String PName = PUtility.DataFullR(ThaiUtil.ASCII2Unicode(rs.getString("r_pname")), 30);
                if (voidStr.equals("V")) {
                    PName = "<html><strike><font color=red>" + PName + "</font></strike></html>";
                }
                Object[] input = {
                    rs.getString("r_plucode"),
                    PName,
                    dc1.format(rs.getDouble("r_quan")),
                    dc1.format(rs.getDouble("r_price")),
                    dc1.format(rs.getDouble("r_total")),
                    rs.getString("r_void"),
                    rs.getString("r_etd"),
                    rs.getString("r_kicprint"),
                    rs.getString("r_kicok"),
                    rs.getString("r_prtype"),
                    rs.getString("r_index")
                };
                model.addRow(input);

                String str = "";
                for (int i = 1; i <= 9; i++) {
                    String R_Opt = rs.getString("R_Opt" + i);
                    if (R_Opt == null) {
                        R_Opt = "";
                    }
                    if (!R_Opt.equals("")) {
                        if (str.equals("")) {
                            if (voidStr.equals("V")) {
                                str += "  - ";
                            } else {
                                str += "  + ";
                            }
                        }
                        str += ThaiUtil.ASCII2Unicode(rs.getString("R_Opt" + i)) + ",";
                    }
                }
                if (!str.equals("")) {
                    if (str.indexOf("-") != -1) {
                        str = "<html><font color=red>" + str + "</font></html>";
                    }
                    model.addRow(new Object[]{"", str});
                }
            }

            RowCount = model.getRowCount();
            showCell(RowCount - 1, 0);

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }

        showSum();
    }

    public void LoadDataFromBalance(String P_Table) {
        try {
            String sql = "select * from balance "
                    + "where r_table='" + P_Table + "' "
                    + "order by r_index";
            ResultSet rs = MySQLConnect.getResultSet(sql);

            int size = model.getRowCount();
            for (int i = 0; i <= size - 1; i++) {
                model.removeRow(0);
            }

            while (rs.next()) {
                String voidStr = rs.getString("r_void");
                String PName = ThaiUtil.ASCII2Unicode(rs.getString("r_pname"));
                if (voidStr.equals("V")) {
                    PName = "<html><strike><font color=red>" + PName + "</font></strike></html>";
                }
                Object[] input = {
                    rs.getString("r_plucode"),
                    PName,
                    dc1.format(rs.getDouble("r_quan")),
                    dc1.format(rs.getDouble("r_price")),
                    dc1.format(rs.getDouble("r_total")),
                    rs.getString("r_void"),
                    rs.getString("r_etd"),
                    rs.getString("r_kicprint"),
                    rs.getString("r_kicok"),
                    rs.getString("r_prtype"),
                    rs.getString("r_index")
                };

                model.addRow(input);

                String str = "";
                for (int i = 1; i <= 9; i++) {
                    if (!rs.getString("R_Opt" + i).equals("")) {
                        if (voidStr.equals("V")) {
                            str += "  - ";
                        } else {
                            str += "  + ";
                        }
                        str += ThaiUtil.ASCII2Unicode(rs.getString("R_Opt" + i)) + ",";
                    }
                }
                if (!str.equals("")) {
                    if (str.indexOf("-") != -1) {
                        str = "<html><font color=red>" + str + "</font></html>";
                    }
                    model.addRow(new Object[]{"", str});
                }
            }
            size = model.getRowCount();
            showCell(size - 1, 0);
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            MSG.ERR(e.getMessage());
        }

        showSum();
    }

    void showSum() {
        //show sum
        TableFileControl tCon = new TableFileControl();
        TableFileBean tfBean = tCon.getData(txtTable.getText());

        lbTotalAmount.setText("" + dc1.format(tfBean.getNetTotal()));
        txtTotalAmount.setText("" + dc1.format(tfBean.getTAmount()));
        txtCust.setText("" + tfBean.getTCustomer());

        txtItemDisc.setText("" + dc1.format(tfBean.getItemDiscAmt()));
        txtPromotion.setText("" + dc1.format(tfBean.getProDiscAmt()));
        txtSubTotal.setText("" + dc1.format(tfBean.getSubDiscAmt()));
        txtTotalService.setText("" + dc1.format(tfBean.getServiceAmt()));

        // for member
        String MemberCode = tfBean.getMemCode().trim();
        String MemberNameThai = tfBean.getMemName().trim();
        if (MemberCode != null && !MemberCode.equals("")) {
            Value.MemberAlready = true;
            memberBean = MemberBean.getMember(MemberCode);
            txtMember.setText(MemberCode + " - " + MemberNameThai);
        } else {
            txtMember.setText("ระบบสมาชิก : <ท่านไม่ได้ใช้ระบบสมาชิก> : แต้มสะสม 0 คะแนน");
        }
    }

private void txtCustKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtPluCode.setEditable(true);
        txtPluCode.requestFocus();
        txtCust.setSelectionEnd(0);

        txtCustOnExit();
    }
}//GEN-LAST:event_txtCustKeyPressed

    public void txtCustOnExit() {
        if (PUtility.ChkIntValue(txtCust.getText())) {
            try {
                int TableRec_TCustomer = Integer.parseInt(txtCust.getText());
                if (TableRec_TCustomer > 999) {
                    PUtility.showError("จำนวนลูกค้าป้อนได้ไม่เกิน 999 คน...");
                    txtCust.requestFocus();
                } else {
                    updateCustomerCount(TableRec_TCustomer);
                    txtCust.setEditable(false);
                    txtPluCode.setEditable(true);
                    txtPluCode.requestFocus();
                }
            } catch (NumberFormatException e) {
                MSG.ERR(this, "จำนวนลูกค้าป้อนได้ไม่เกิน 999 คน...");
                PublicVar.TableRec_TCustomer = 0;
                txtCust.requestFocus();
            }
        } else {
            PUtility.showError("กรุณาป้อนจำนวนลูกค้า เป็นตัวเลขเท่านั้น...");
            txtCust.requestFocus();
        }
    }

private void txtPluCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPluCodeKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        pCodeEnter();
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        ProcessErrorColect();
    } else if (evt.getKeyCode() == KeyEvent.VK_F1) {
        if (txtPluCode.hasFocus()) {
            bntPluClick();
        }
    } else if (evt.getKeyCode() == KeyEvent.VK_F3) {
        showHoldTable();
    } else if (evt.getKeyCode() == KeyEvent.VK_F4) {
        showCheckBill();
    } else if (evt.getKeyCode() == KeyEvent.VK_F6) {
        showBillCheck();
    } else if (evt.getKeyCode() == KeyEvent.VK_F9) {
        showCustomerCount();
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        selectedTableBalance();
    } else if (evt.getKeyCode() == KeyEvent.VK_F11) {
        showMember();
    }

}//GEN-LAST:event_txtPluCodeKeyPressed


private void tblShowBalanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblShowBalanceKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        txtPluCode.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        int row = tblShowBalance.getSelectedRow();
        if (row != -1) {
            Object index = model.getValueAt(row, 10);
            Object voidMsg = model.getValueAt(row, 5);
            String strVoid;
            if (voidMsg != null) {
                strVoid = voidMsg.toString();
            } else {
                strVoid = "";
            }

            if (index != null && !strVoid.equalsIgnoreCase("V")) {

                selectedOptionBill();

                txtPluCode.requestFocus();
            }
        }
    }

}//GEN-LAST:event_tblShowBalanceKeyPressed

private void MMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MMainMenuActionPerformed

}//GEN-LAST:event_MMainMenuActionPerformed

private void MAddNewAccr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MAddNewAccr1ActionPerformed
    PublicVar.TempUserRec = PublicVar.TUserRec;
    if (PublicVar.TUserRec.Sale7.equals("Y")) {
        AddNewArCustomer fmt = new AddNewArCustomer(null, true);
        fmt.setVisible(true);
    } else {
        GetUserAction getuser = new GetUserAction(null, true);
        getuser.setVisible(true);

        if (!PublicVar.ReturnString.equals("")) {
            String loginname = PublicVar.ReturnString;
            UserRecord supUser = new UserRecord();
            if (supUser.GetUserAction(loginname)) {
                if (supUser.Sale7.equals("Y")) {
                    PublicVar.TUserRec = supUser;
                    AddNewArCustomer fmt = new AddNewArCustomer(null, true);
                    fmt.setVisible(true);
                } else {
                    MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                }
            } else {
                MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
            }
        }
    }
    PublicVar.TUserRec = PublicVar.TempUserRec;
}//GEN-LAST:event_MAddNewAccr1ActionPerformed

private void MRepArNotPayment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRepArNotPayment1ActionPerformed
    PublicVar.TempUserRec = PublicVar.TUserRec;
    if (PublicVar.TUserRec.Sale8.equals("Y")) {
        ArNotPay frm = new ArNotPay(null, true);
        frm.setVisible(true);
    } else {
        GetUserAction getuser = new GetUserAction(null, true);
        getuser.setVisible(true);

        if (!PublicVar.ReturnString.equals("")) {
            String loginname = PublicVar.ReturnString;
            UserRecord supUser = new UserRecord();
            if (supUser.GetUserAction(loginname)) {
                if (supUser.Sale8.equals("Y")) {
                    PublicVar.TUserRec = supUser;
                    ArNotPay frm = new ArNotPay(null, true);
                    frm.setVisible(true);
                } else {
                    MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                }
            } else {
                MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
            }
        }
    }

    PublicVar.TUserRec = PublicVar.TempUserRec;

}//GEN-LAST:event_MRepArNotPayment1ActionPerformed

private void MRepArHistory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRepArHistory1ActionPerformed
    PublicVar.TempUserRec = PublicVar.TUserRec;
    if (PublicVar.TUserRec.Sale9.equals("Y")) {
        ArHistory frm = new ArHistory(null, true);
        frm.setVisible(true);
    } else {
        GetUserAction getuser = new GetUserAction(null, true);
        getuser.setVisible(true);

        if (!PublicVar.ReturnString.equals("")) {
            String loginname = PublicVar.ReturnString;
            UserRecord supUser = new UserRecord();
            if (supUser.GetUserAction(loginname)) {
                if (supUser.Sale9.equals("Y")) {
                    PublicVar.TUserRec = supUser;
                    ArHistory frm = new ArHistory(null, true);
                    frm.setVisible(true);
                } else {
                    MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                }
            } else {
                MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
            }
        }
    }
    PublicVar.TUserRec = PublicVar.TempUserRec;
}//GEN-LAST:event_MRepArHistory1ActionPerformed

private void MAddNewMember1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MAddNewMember1ActionPerformed
    AddMemberMaster master = new AddMemberMaster(null, true);
    master.setVisible(true);
}//GEN-LAST:event_MAddNewMember1ActionPerformed

private void MRepMemberHistory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRepMemberHistory1ActionPerformed
    RepMember frm = new RepMember(null, true);
    frm.setVisible(true);
}//GEN-LAST:event_MRepMemberHistory1ActionPerformed

private void MHeaderBill1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MHeaderBill1ActionPerformed
    if (Value.useprint) {
        PPrint prn = new PPrint();
        prn.printHeaderBill();
    }
}//GEN-LAST:event_MHeaderBill1ActionPerformed

private void txtPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (!PUtility.ChkNumValue(txtPrice.getText())) {
            PUtility.showError("กรุณาป้อนราคาขายให้ถูกต้อง...");
            txtPrice.selectAll();
            txtPrice.requestFocus();
        } else {
            LoadDataFromProduct();
            tblShowPluShow(txtTable.getText());
            txtPrice.setEditable(false);
            txtPluCode.setEditable(true);
            txtPluCode.setText("");
            txtPluCode.requestFocus();
        }
    } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        txtPrice.setText(dc1.format(0));
        txtPrice.setEditable(false);
        txtPluCode.setEditable(true);
        txtPluCode.setText("");
        txtPluCode.requestFocus();
    }
}//GEN-LAST:event_txtPriceKeyPressed

private void MRepInvCash1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRepInvCash1ActionPerformed
    PrintInv1 frm = new PrintInv1(null, true);
    frm.setVisible(true);
}//GEN-LAST:event_MRepInvCash1ActionPerformed

private void MRepInvAr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRepInvAr1ActionPerformed
    PrintInv2 frm = new PrintInv2(null, true);
    frm.setVisible(true);
}//GEN-LAST:event_MRepInvAr1ActionPerformed

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    SetHeaderMenu s = new SetHeaderMenu(null, true);
    s.setVisible(true);

    loadHeaderMenu();
}//GEN-LAST:event_jMenuItem4ActionPerformed

    private void clearTable() {
        tblShowBalance.setBackground(null);
        txtTableOnEnter();
        ChangeSaleType("E");

        txtTable.setText("");
        txtTable.requestFocus();
    }

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        CancelCashBack c = new CancelCashBack(null, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        CashBackDialog c = new CashBackDialog(null, true);
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void MCancelArPayment2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCancelArPayment2ActionPerformed

    }//GEN-LAST:event_MCancelArPayment2ActionPerformed

    private void MCancelArPayment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCancelArPayment1ActionPerformed
        if (PublicVar.TUserRec.Sale7.equals("Y")) {
            if (PUtility.CheckSaleDateOK()) {
                CancelArPaymentClick();
            }
        } else {
            GetUserAction getuser = new GetUserAction(null, true);
            getuser.setVisible(true);

            if (!PublicVar.ReturnString.equals("")) {
                String loginname = PublicVar.ReturnString;
                UserRecord supUser = new UserRecord();
                if (supUser.GetUserAction(loginname)) {
                    if (supUser.Sale7.equals("Y")) {
                        PublicVar.TUserRec = supUser;
                        if (PUtility.CheckSaleDateOK()) {
                            CancelArPaymentClick();
                        }
                    } else {
                        MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                    }
                } else {
                    MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                }
            }
        }
    }//GEN-LAST:event_MCancelArPayment1ActionPerformed

    private void txtShowETDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtShowETDMouseClicked
        BranchBean branchBean = BranchControl.getData();
        if (branchBean.getLocation_Area().equals("02")) {
            txtShowETD.setText("T");
        } else {
            if (txtShowETD.getText().equals("E")) {
                txtShowETD.setText("T");
                ChangeSaleType("T");
            } else if (txtShowETD.getText().equals("T")) {
                txtShowETD.setText("D");
                ChangeSaleType("D");
            } else if (txtShowETD.getText().equals("D")) {
                txtShowETD.setText("P");
                ChangeSaleType("P");
            } else if (txtShowETD.getText().equals("P")) {
                txtShowETD.setText("W");
                ChangeSaleType("W");
            } else if (txtShowETD.getText().equals("W")) {
                txtShowETD.setText("E");
                ChangeSaleType("E");
            }
        }
    }//GEN-LAST:event_txtShowETDMouseClicked

    private void tblShowBalanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblShowBalanceMouseClicked
        if (evt.getClickCount() == 2) {
            selectedOptionBill();
        }
    }//GEN-LAST:event_tblShowBalanceMouseClicked

    private void c_bntbsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntbsActionPerformed
        input("2");
    }//GEN-LAST:event_c_bntbsActionPerformed

    private void c_bntESCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntESCActionPerformed
        input("4");
    }//GEN-LAST:event_c_bntESCActionPerformed

    private void c_bntmultiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntmultiActionPerformed
        input("-");
    }//GEN-LAST:event_c_bntmultiActionPerformed

    private void btnAct0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAct0ActionPerformed
        input(".");
    }//GEN-LAST:event_btnAct0ActionPerformed

    private void btnAct3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAct3ActionPerformed
        input("0");
    }//GEN-LAST:event_btnAct3ActionPerformed

    private void btnAct6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAct6ActionPerformed
        input("+");
    }//GEN-LAST:event_btnAct6ActionPerformed

    private void btnAct9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAct9ActionPerformed
        input("9");
    }//GEN-LAST:event_btnAct9ActionPerformed

    private void btnAct8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAct8ActionPerformed
        input("8");
    }//GEN-LAST:event_btnAct8ActionPerformed

    private void btnAct5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAct5ActionPerformed
        input("6");
    }//GEN-LAST:event_btnAct5ActionPerformed

    private void btnAct2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAct2ActionPerformed
        input("*");
    }//GEN-LAST:event_btnAct2ActionPerformed

    private void btnAct7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAct7ActionPerformed
        input("7");
    }//GEN-LAST:event_btnAct7ActionPerformed

    private void btnAct4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAct4ActionPerformed
        input("5");
    }//GEN-LAST:event_btnAct4ActionPerformed

    private void btnAct1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAct1ActionPerformed
        input("3");
    }//GEN-LAST:event_btnAct1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        showHoldTable();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        showCheckBill();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        showSplitBill();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        showMember();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCheckBoxMenuItem1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ItemStateChanged
        panelMenuFunction.setVisible(jCheckBoxMenuItem1.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItem1ItemStateChanged

    private void jCheckBoxMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ActionPerformed
        panelNumPad.setVisible(jCheckBoxMenuItem2.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItem2ActionPerformed

    private void txtTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTableFocusGained
        if (!Value.TableSelected.equals("")) {
            txtTable.setText(tableNo);
            Value.TableSelected = "";
        }
    }//GEN-LAST:event_txtTableFocusGained

    private void txtPluCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPluCodeFocusGained
        txtPluCode.setEditable(true);
        txtPluCode.requestFocus();
    }//GEN-LAST:event_txtPluCodeFocusGained

    boolean isSelected = false;

    private void txtCustFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCustFocusGained
        isSelected = true;
    }//GEN-LAST:event_txtCustFocusGained

    private void c_bntenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntenterActionPerformed
        if (txtTable.hasFocus()) {
            String tempstr;
            String tempstr2 = "";
            tempstr = txtTable.getText();
            for (int i = 0; i < tempstr.length() - 1; i++) {
                tempstr2 = tempstr2 + tempstr.charAt(i);
            }
            txtTable.setText(tempstr2);
        } else if (txtCust.hasFocus()) {
            String tempstr;
            String tempstr2 = "";
            tempstr = txtCust.getText();
            for (int i = 0; i < tempstr.length() - 1; i++) {
                tempstr2 = tempstr2 + tempstr.charAt(i);
            }
            txtCust.setText(tempstr2);
        } else if (txtPluCode.hasFocus()) {
            String tempstr;
            String tempstr2 = "";
            tempstr = txtPluCode.getText();
            for (int i = 0; i < tempstr.length() - 1; i++) {
                tempstr2 = tempstr2 + tempstr.charAt(i);
            }
            txtPluCode.setText(tempstr2);
        } else if (txtPrice.hasFocus()) {
            String tempstr;
            String tempstr2 = "";
            tempstr = txtPrice.getText();
            for (int i = 0; i < tempstr.length() - 1; i++) {
                tempstr2 = tempstr2 + tempstr.charAt(i);
            }
            txtPrice.setText(tempstr2);
        }
    }//GEN-LAST:event_c_bntenterActionPerformed

    private void c_bntenter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntenter1ActionPerformed
        if (txtTable.hasFocus()) {
            TableOpened();
        } else if (txtCust.hasFocus()) {
            txtPluCode.setEnabled(true);
            txtPluCode.requestFocus();
            txtCust.setSelectionEnd(0);

            txtCustOnExit();
        } else if (txtPrice.hasFocus()) {
            if (!PUtility.ChkNumValue(txtPrice.getText())) {
                PUtility.showError("กรุณาป้อนราคาขายให้ถูกต้อง...");
                txtPrice.selectAll();
                txtPrice.requestFocus();
            } else {
                LoadDataFromProduct();

                tblShowPluShow(txtTable.getText());
                txtPrice.setEnabled(false);
                txtPluCode.setEnabled(true);
                txtPluCode.setText("");
                txtPluCode.requestFocus();
            }
        } else if (txtPluCode.hasFocus()) {
            pCodeEnter();
        }
    }//GEN-LAST:event_c_bntenter1ActionPerformed

    private void txtPluCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPluCodeMouseClicked
        if (evt.getClickCount() == 2) {
            KeyBoardDialog.get(txtPluCode);
        }
    }//GEN-LAST:event_txtPluCodeMouseClicked

    private void txtCustMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCustMouseClicked
        CustomerCountDialog ccd = new CustomerCountDialog(null, true);
        ccd.setVisible(true);

        if (ccd.getCountCustomer() > 0) {
            txtCust.setText("" + ccd.getCountCustomer());
            txtCustOnExit();
        }
    }//GEN-LAST:event_txtCustMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if ((!txtPrice.hasFocus()) & (model.getRowCount() > 0)) {
            MoveItemDialog move = new MoveItemDialog(null, true, txtTable.getText());
            move.setVisible(true);

            if (!move.getTable2().equals("")) {
                txtTable.setText(move.getTable2());
                TableOpened();
                txtCustOnExit();
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tbpMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpMainMouseClicked
        clearHistory();
        addHistory(tbpMain.getSelectedIndex());
    }//GEN-LAST:event_tbpMainMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        MGRButtonMenu mgr = new MGRButtonMenu(null, true, buttonName, buttonIndex);
        mgr.setVisible(true);

        //if (mgr.getOK()) {
        backHistory();
        //}
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtTypeDescMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTypeDescMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTypeDescMouseClicked

    public Boolean SeekEmpOK(String EmpCode) {
        Boolean RetVal = false;
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String LoadBalance = "select *from employ where code='" + EmpCode + "'";
            ResultSet rec = stmt.executeQuery(LoadBalance);
            rec.first();
            if (rec.getRow() == 0) {
                RetVal = false;
            } else {
                RetVal = true;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
        return RetVal;
    }

    public void CancelArPaymentClick() {
        PublicVar.TempUserRec = PublicVar.TUserRec;

        if (PublicVar.TUserRec.Sale6.equals("Y")) {
            CancelArPayment frm = new CancelArPayment(null, true);
            frm.setVisible(true);
        } else {
            GetUserAction getuser = new GetUserAction(null, true);
            getuser.setVisible(true);

            if (!PublicVar.ReturnString.equals("")) {
                String loginname = PublicVar.ReturnString;
                UserRecord supUser = new UserRecord();
                if (supUser.GetUserAction(loginname)) {
                    if (supUser.Sale6.equals("Y")) {
                        PublicVar.TUserRec = supUser;
                        CancelArPayment frm = new CancelArPayment(null, true);
                        frm.setVisible(true);
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

    public void bntDailyrepClick() {
        if (!ChkEJPath()) {
            return;
        }

        DailyRep frm = new DailyRep(null, true);
        frm.setVisible(true);
        initScreen();
    }

    public void bntmtdrepClick() {
        if (!ChkEJPath()) {
            return;
        }

        MTDRep frm = new MTDRep(null, true);
        frm.setVisible(true);
    }

    public void bntcheckbillClick() {
        if (!ChkEJPath()) {
            return;
        }
        PublicVar.ErrorColect = false;
        PublicVar.ProcessType = "1"; //For Check Bill

        CheckBill frm = new CheckBill(null, true, txtTable.getText(), memberBean);
        frm.setVisible(true);

        if (PublicVar.SubTotalOK) {
            bntPullTableClick();
            clearTable();
            tbpMain.setSelectedIndex(0);

            showTableOpen();
        }
    }

    public void bntpaymentClick() {
        if (!ChkEJPath()) {
            return;
        }

        PublicVar.SubTotalOK = false;

        CheckBill frm = new CheckBill(null, true, txtTable.getText(), memberBean);
        frm.setVisible(true);

        if (PublicVar.SubTotalOK) {
            initScreen();
            clearTable();
            txtTable.setText("");
            tbpMain.setSelectedIndex(0);
            Value.TableSelected = "";
            dispose();
        }
    }

    public void bntvoidClick() {
        int row = GetSelectedRowIndex();
        if (row == -1) {
            return;
        }
        String R_Index = model.getValueAt(row, 10).toString();
        boolean isPermit = false;
        //check permission
        try {
            String sql = "select Username, Sale3 "
                    + "from posuser "
                    + "where username='" + Value.USERCODE + "' and Sale3='Y'";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            if (rs.next()) {
                isPermit = true;
            }

            rs.close();
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
        }

        if (isPermit) {
            VoidPopupDialog voidD = new VoidPopupDialog(null, true, txtTable.getText(), memberBean);
            voidD.setVisible(true);
            if (!VoidPopupDialog.VOID_MSG[0].equals("")) {
                ProcVoid(R_Index, VoidPopupDialog.VOID_MSG[1]);
                showCell(row, 0);
            }
        } else {
            if (!PublicVar.TableRec_PrintChkBill.equals("Y")) {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale3.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            VoidPopupDialog voidD = new VoidPopupDialog(null, true, txtTable.getText(), memberBean);
                            voidD.setVisible(true);
                            if (!VoidPopupDialog.VOID_MSG[0].equals("")) {
                                ProcVoid(R_Index, VoidPopupDialog.VOID_MSG[1]);
                                showCell(row, 0);
                            }
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale4.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            VoidPopupDialog voidD = new VoidPopupDialog(null, true, txtTable.getText(), memberBean);
                            voidD.setVisible(true);
                            if (!VoidPopupDialog.VOID_MSG[0].equals("")) {
                                ProcVoid(R_Index, VoidPopupDialog.VOID_MSG[1]);
                                showCell(row, 0);
                            }
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
        }
    }

    public void ProcVoid(String RIndex, String voidMsg) {
        BalanceControl bc = new BalanceControl();
        BalanceBean bean = bc.getBalanceIndex(txtTable.getText(), RIndex);

        if (bean.getR_Void().equals("V")) {
            bean.setR_Void("");
            bean.setR_VoidUser("");
            bean.setR_VoidTime("");
            bean.setR_DiscBath(0.00);

            if (PublicVar.Branch_Saveorder.equals("Y")) {
                //PrintSaveOrder(IndexRow) ;
            }

            String StkRemark;
            String DocNo;
            String StkCode = PUtility.GetStkCode();
            if (PublicVar.ChargeCode.equals("")) {
                StkRemark = "SAL";
                DocNo = "EV" + txtTable.getText() + "/" + Timefmt.format(new Date());
            } else {
                StkRemark = "FRE";
                if (PublicVar.ChargeDocNo.equals("")) {
                    DocNo = txtTable.getText() + "/" + Timefmt.format(new Date());
                    PublicVar.ChargeDocNo = DocNo;
                } else {
                    DocNo = PublicVar.ChargeDocNo;
                }
            }

            Date TDate = new Date();
            PUtility.ProcessStockOut(DocNo, StkCode, bean.getR_PluCode(), TDate,
                    StkRemark, bean.getR_Quan(), bean.getR_Total(), bean.getCashier(),
                    bean.getR_Stock(), bean.getR_Set(), bean.getR_Index(), "1");
        } else {
            bean.setR_Void("V");
            bean.setR_VoidUser(bean.getCashier());
            bean.setR_VoidTime(Timefmt.format(new Date()));
            bean.setR_DiscBath(0.00);

            String StkCode = PUtility.GetStkCode();
            String StkRemark;
            String DocNo;
            if (PublicVar.ChargeCode.equals("")) {
                StkRemark = "SAL";
                DocNo = "V" + txtTable.getText() + "/" + Timefmt.format(new Date());
            } else {
                StkRemark = "FRE";
                if (PublicVar.ChargeDocNo.equals("")) {
                    DocNo = txtTable.getText() + "/" + Timefmt.format(new Date());
                    PublicVar.ChargeDocNo = DocNo;
                } else {
                    DocNo = PublicVar.ChargeDocNo;
                }
            }

            Date TDate = new Date();
            PUtility.ProcessStockOut(DocNo, StkCode, bean.getR_PluCode(), TDate, StkRemark, -1 * bean.getR_Quan(), -1 * bean.getR_Total(),
                    PublicVar.TUserRec.UserCode, bean.getR_Stock(), bean.getR_Set(), bean.getR_Index(), "1");
        }

        //Update  Balance File For Void
        try {
            String updBalance = "update balance "
                    + "set r_void='" + bean.getR_Void() + "',"
                    + "r_voiduser='" + bean.getR_VoidUser() + "',"
                    + "r_voidtime='" + bean.getR_VoidTime() + "',"
                    + "r_discbath='" + bean.getR_DiscBath() + "',"
                    + "r_kicprint='',"
                    + "r_opt9='" + ThaiUtil.Unicode2ASCII(voidMsg) + "' "
                    + "where r_index='" + bean.getR_Index() + "' "
                    + "and r_table='" + bean.getR_Table() + "'";
            MySQLConnect.exeUpdate(updBalance);
        } catch (Exception e) {
            MSG.ERR(this, e.getMessage());
        }
        if ((bean.getR_Set().equals("Y")) && CheckPSetSelect(bean.getR_PluCode())) {
            //Update  Balance File For Void
            try {
                String updateBalance = "update balance "
                        + "set r_void='" + bean.getR_Void() + "',"
                        + "r_opt9='" + ThaiUtil.Unicode2ASCII(voidMsg) + "',"
                        + "r_kicprint='' "
                        + "where r_index='" + bean.getR_Index() + "' "
                        + "and r_table='" + bean.getR_Table() + "'";
                MySQLConnect.exeUpdate(updateBalance);
            } catch (Exception e) {
                MSG.ERR(this, e.getMessage());
            }
        }

        //update promotion, discount
        BalanceControl.updateProSerTable(txtTable.getText(), memberBean);

        PublicVar.ErrorColect = false;
        PublicVar.TableRec_DiscBath = 0.0;

        tblShowPluShow(txtTable.getText());
    }

    public void showCell(int row, int column) {
        if (row > 0) {
            Rectangle rect = tblShowBalance.getCellRect(row, column, true);
            tblShowBalance.scrollRectToVisible(rect);
            tblShowBalance.clearSelection();
            tblShowBalance.setRowSelectionInterval(row, row);
        }
    }

    public void bntoptionClick() {
        int row = tblShowBalance.getSelectedRow();
        if (row != -1) {
            String RKicPrint = model.getValueAt(row, 8).toString();
            String RVoid = model.getValueAt(row, 5).toString();
            String RIndex = model.getValueAt(row, 10).toString();
            if (!RKicPrint.equals("P")) {
                if (!RVoid.equals("V")) {
                    OptionMsg frm = new OptionMsg(null, true, txtTable.getText(), RIndex);
                    frm.setVisible(true);
                } else {
                    MSG.WAR(this, "รายการนี้ได้ยกเลิกออกจากบิลแล้ว ไม่สามารถใส่ข้อความพิเศษได้ !");
                }

            } else {
                PUtility.ShowMsg("รายการนี้ได้มีการพิมพ์ออกครัวไปแล้ว...ไม่สามารถกำหนด Option เพิ่มเติมได้...");
                txtPluCode.requestFocus();
            }
        }
    }

    public void bntItemDiscountClick() {
        BalanceControl bc = new BalanceControl();
        int row = tblShowBalance.getSelectedRow();
        if (row != -1) {
            String PCode = model.getValueAt(row, 0).toString();
            String RIndex = model.getValueAt(row, 10).toString();
            BalanceBean bean = bc.getProduct(PCode, RIndex);
            if (!bean.getR_Void().equals("V")) {
                if (bean.getR_Discount().equals("Y")) {
                    if ((bean.getR_PrType().equals("-I")) || (bean.getR_PrType().equals("")) && (bean.getR_PrCuType().equals(""))
                            && (bean.getR_PrSubType().equals(""))) {
                        PublicVar.TempIndexKey = bean.getR_Index();
                        ItemDiscount frm = new ItemDiscount(null, true, RIndex, txtTable.getText(), memberBean);
                        frm.setVisible(true);
                        tblShowPluShow(txtTable.getText());
                        showCell(row, 0);

                    } else {
                        MSG.ERR(this, "รายการนี้ได้มีการให้ส่วนลดอื่นไปแล้ว ไม่สามารถให้ส่วนลดตามรายการได้อีก ...");
                    }

                } else {
                    MSG.ERR(this, "รายการนี้ได้กำหนดค่าเริ่มต้นไว้..ไม่สามารถให้ส่วนลดได้...");
                }
            } else {
                MSG.ERR(this, "รายการนี้ได้ทำการ VOID แล้วไม่สามารถให้ส่วนลดได้ ...");
            }
        }
    }

    public int GetSelectedRowIndex() {
        int row = tblShowBalance.getSelectedRow();
        if (row != -1) {
            return row;
        } else {
            return -1;
        }
    }

    public void bntPluClick() {
        if (txtPluCode.hasFocus()) {
            FindPluCode frm = new FindPluCode(null, true);
            frm.setVisible(true);

            if (!PublicVar.ReturnString.equals("")) {
                txtPluCode.setText(QtyIntFmt.format(PublicVar.ReturnQty).trim() + "*" + PublicVar.ReturnString);
                if (seekPluCode()) {
                    if (PublicVar.P_Status.equals("S")) {
                        getPrice();
                    } else {
                        txtPluCodeOnExit();
                    }
                }
            }
        }
    }

    boolean seekPluCode() {
        String PluCode;
        String StrQty;
        String TempCode = txtPluCode.getText();
        PublicVar.P_Code = "";
        PublicVar.P_Status = "";
        PublicVar.P_Qty = 0.0;
        boolean found = false;
        double Qty;
        if (TempCode.length() > 0) {
            int index = TempCode.indexOf("*");
            if (index != -1) {
                StrQty = TempCode.substring(0, index);
                PluCode = TempCode.substring(index + 1);
                if (!PUtility.ChkNumValue(StrQty)) {
                    PUtility.showError("ป้อนจำนวนไม่ถูกต้อง..กรุณาป้อนใหม่...");
                    txtPluCode.setText("");
                    txtPluCode.requestFocus();
                }
                Qty = Double.parseDouble(StrQty);
            } else {
                Qty = 1;
                PluCode = TempCode;
            }
            if (Qty > 0) {
                if (PluCode.length() <= 3) {
                    try {
                        Statement stmt = MySQLConnect.con.createStatement();
                        String SqlQuery = "select *from menulist where menuitem=('" + PluCode + "') and (menuactive='Y')";
                        ResultSet rec = stmt.executeQuery(SqlQuery);
                        rec.first();
                        if (rec.getRow() == 0) {
                            PUtility.showError("ไม่พบรหัส Menu Items " + PluCode + " ในฐานข้อมูล !!!");
                            txtPluCode.setText("");
                            rec.close();
                            stmt.close();
                            txtPluCode.selectAll();
                            txtPluCode.requestFocus();
                            return false;
                        } else {
                            PluCode = rec.getString("plucode");
                        }
                        rec.close();
                        stmt.close();

                    } catch (SQLException e) {
                        PUtility.showError(e.getMessage());
                    }
                }
                try {

                    Statement stmt = MySQLConnect.con.createStatement();
                    String sql = "select * "
                            + "from product "
                            + "where pcode='" + PluCode + "' "
                            + "and pactive='Y'";
                    ResultSet rec = stmt.executeQuery(sql);
                    rec.first();
                    if (rec.getRow() == 0) {
                        TempStatus = "";
                        String TempCode2 = SeekBarCode(PluCode);
                        if (TempCode2.equals("")) {
                            PUtility.showError("ไม่พบรหัสสินค้า " + PluCode + " ในฐานข้อมูล หรือรหัสสินค้านี้อาจถูกยกเลิกการขายแล้ว...");
                            txtPluCode.setText("");
                        } else {
                            PluCode = TempCode2;
                            found = true;
                            PublicVar.P_Code = PluCode;
                            PublicVar.P_Status = TempStatus;
                            PublicVar.P_Qty = Qty;
                        }
                        rec.close();
                        stmt.close();

                        txtPluCode.selectAll();
                        txtPluCode.requestFocus();
                    } else {
                        found = true;
                        PublicVar.P_Code = PluCode;
                        PublicVar.P_Status = rec.getString("pstatus");
                        PublicVar.P_Qty = Qty;
                    }
                    rec.close();
                    stmt.close();

                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
            } else {
                PUtility.ShowMsg("จำนวนขายต้องมากกว่า 0...");
                txtPluCode.requestFocus();
            }
        }
        return found;
    }

    public void ChangTypeClick() {
        boolean ChangOk = false;
        int row = tblShowBalance.getSelectedRow();
        if (row != -1) {
            if (!txtTable.getText().trim().equals("")) {
                PublicVar.ChangTypeOK = false;
                changtype frm = new changtype(null, true, txtTable.getText(), txtShowETD.getText());
                frm.setVisible(true);
                if (PublicVar.ChangTypeOK) {
                    if (ChangOk) {
                        if (PublicVar.TableRec_CuponDiscAmt > 0) {
                            CancelCoupon();
                        }

                        ClearDataMem();

                        LoadDataFromBalance(txtTable.getText());
                        txtPluCode.requestFocus();
                    }
                }
            }
        }
    }

    public void CancelCoupon() {
        CouponDiscount Cufrm = new CouponDiscount();
        Cufrm.ProcessAutoClearCoupon();
    }

    public void ClearDataMem() {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_MONTH, -30);
    }

    public void deletePromotion2() {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "delete from promotion2 where (macno='" + Value.MACNO + "')";
            stmt.executeUpdate(SqlQuery);
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
    }

    public void DeletePromotion3() {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "delete from promotion3 where (macno='" + Value.MACNO + "')";
            stmt.executeUpdate(SqlQuery);
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }

    }

    public void DeletePromotion4() {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "delete from promotion4 where (macno='" + Value.MACNO + "')";
            stmt.executeUpdate(SqlQuery);
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }

    }

    public void ClearTempPromotion() {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "delete from temppromotion where (tableno='" + txtTable.getText() + "')";
            stmt.executeUpdate(SqlQuery);
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
    }

    public void ClearTempCupon() {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String LoadTableFile = "select *from tempcupon "
                    + "where (m_code='" + PublicVar.TableRec_MemCode + "') "
                    + "and (r_table='" + txtTable.getText() + "')";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                do {
                    String SMS_Code = rec.getString("sms_code");
                    String M_Code = rec.getString("m_code");
                    try {
                        Statement stmt2 = MySQLConnect.con.createStatement();
                        String SqlQuery = "update smstable "
                                + "set use_status='N',billno='',usetime='' "
                                + "where (m_code='" + M_Code + "') and (sms_code='" + SMS_Code + "') ";
                        stmt2.executeUpdate(SqlQuery);
                        stmt2.close();
                    } catch (SQLException e) {
                        PUtility.showError(e.getMessage());
                    }

                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "delete from tempcupon"
                    + " where (r_table='" + txtTable.getText() + "')";
            stmt.executeUpdate(SqlQuery);
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
    }

    public void ClearBalance() {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "delete from balance "
                    + "where (r_table='" + txtTable.getText() + "')";
            stmt.executeUpdate(SqlQuery);
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }

    }

    public boolean findPluCode() {
        String PluCode;
        String StrQty;
        String TempCode = txtPluCode.getText();
        PublicVar.P_Code = "";
        PublicVar.P_Status = "";
        PublicVar.P_Qty = 0.0;
        boolean found = false;
        double Qty;

        //check outofstock
        try {
            String sql = "select * from outstocklist "
                    + "where pcode='" + TempCode + "'";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            if (rs.next()) {
                PUtility.showError("สินค้ามีไม่ในสต๊อก หรือถูกยกเลิกการขายไปแล้ว กรุณาตรวจสอบ !!!");
                txtPluCode.setText("");
                txtPluCode.requestFocus();
                return false;
            }
        } catch (Exception e) {
            MSG.ERR(this, e.getMessage());
            return false;
        }

        if (TempCode.length() > 0) {
            int index = TempCode.indexOf("*");
            if (index != -1) {
                StrQty = TempCode.substring(0, index);
                PluCode = TempCode.substring(index + 1);
                if (!PUtility.ChkNumValue(StrQty)) {
                    PUtility.showError("ป้อนจำนวนไม่ถูกต้อง..กรุณาป้อนใหม่...");
                    txtPluCode.setText("");
                    txtPluCode.requestFocus();
                }
                Qty = Double.parseDouble(StrQty);
            } else {
                Qty = 1;
                PluCode = TempCode;
            }
            if (Qty > 0) {

                //for menuitem
                if (PluCode.length() <= 3) {
                    try {
                        Statement stmt = MySQLConnect.con.createStatement();
                        String SqlQuery = "select *from menulist "
                                + "where menuitem=('" + PluCode + "') and (menuactive='Y')";
                        ResultSet rec = stmt.executeQuery(SqlQuery);
                        rec.first();
                        if (rec.getRow() == 0) {
                            PUtility.showError("ไม่พบรหัส Menu Items " + PluCode + " ในฐานข้อมูล !!!");
                            txtPluCode.setText("");
                            rec.close();
                            stmt.close();
                            txtPluCode.selectAll();
                            txtPluCode.requestFocus();
                            return false;
                        } else {
                            PluCode = rec.getString("plucode");
                        }
                        rec.close();
                        stmt.close();

                    } catch (SQLException e) {
                        PUtility.showError(e.getMessage());
                    }
                }
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String SqlQuery = "select *from product "
                            + "where pcode='" + PluCode + "' and pactive='Y'";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                        TempStatus = "";
                        String TempCode2 = SeekBarCode(PluCode);
                        if (TempCode2.equals("")) {
                            PUtility.showError("ไม่พบรหัสสินค้า " + PluCode + " ในฐานข้อมูล หรือรหัสสินค้านี้อาจถูกยกเลิกการขายแล้ว...");
                            txtPluCode.setText("");
                        } else {
                            PluCode = TempCode2;
                            found = true;
                            PublicVar.P_Code = PluCode;
                            PublicVar.P_Status = TempStatus;
                            PublicVar.P_Qty = Qty;
                        }
                        rec.close();
                        stmt.close();

                        txtPluCode.selectAll();
                        txtPluCode.requestFocus();
                    } else {
                        found = true;
                        PublicVar.P_Code = PluCode;
                        PublicVar.P_Status = rec.getString("pstatus");
                        PublicVar.P_Qty = Qty;
                    }
                    rec.close();
                    stmt.close();

                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
            } else {
                PUtility.ShowMsg("จำนวนขายต้องมากกว่า 0...");
                txtPluCode.requestFocus();
            }
        }
        return found;
    }

    public boolean CheckPSetSelect() {
        boolean RetValue = false;
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "select *from product "
                    + "where pcode='" + PublicVar.P_Code + "' and pactive='Y'";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
                RetValue = false;
            } else {
                //if (rec.getString("pset").equals("Y") & (rec.getString("psetselect").equals("Y"))) {
                if (rec.getString("pset").equals("Y")) {
                    RetValue = true;
                } else {
                    RetValue = false;
                }
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
        return RetValue;
    }

    public boolean CheckPSetSelect(String PCode) {
        boolean RetValue = false;
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "select *from product "
                    + "where pcode='" + PCode + "' "
                    + "and pactive='Y'";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
                RetValue = false;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
        }
        return RetValue;
    }

    public String SeekBarCode(String BarCode) {
        String RetVal = "";
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "select *from product "
                    + "where pbarcode='" + BarCode + "' and pactive='Y'";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
                RetVal = "";
                TempStatus = "";
            } else {
                RetVal = rec.getString("pcode");
                TempStatus = rec.getString("pstatus");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
        return RetVal;
    }

    public void getPrice() {
        txtPluCode.setEditable(false);
        txtPrice.setText("");
        txtPrice.selectAll();
        txtPrice.setEditable(true);
        txtPrice.requestFocus();
    }

    public void LoadDataFromProduct() {
        String PCode = txtPluCode.getText();
        String emp = "";
        String etd = txtShowETD.getText();
        String[] data = Option.splitPrice(PCode);
        double R_Quan = Double.parseDouble(data[0]);
        PCode = data[1];

        ProductControl pCon = new ProductControl();
        ProductBean productBean = pCon.getData(PCode);
        BalanceBean balance = new BalanceBean();

        double Price = 0.00;
        try {
            Price = Double.parseDouble(txtPrice.getText());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        if (productBean.getPStatus().equals("S")) {
            getPrice();
        }
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "select * from product "
                    + "where pcode='" + PCode + "' "
                    + "and pactive='Y'";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
                PUtility.showError("ไม่พบรหัสสินค้า " + PCode + " ในฐานข้อมูล หรือ รหัสสินค้านี้อาจยกเลิกการขายแล้ว...");
                txtPluCode.setText("");
                rec.close();
                stmt.close();
                txtPluCode.selectAll();
                txtPluCode.requestFocus();
            } else {
                if (!PUtility.CheckStockOK(PCode, R_Quan)) {
                    txtPluCode.setText("");
                    txtPluCode.selectAll();
                    txtPluCode.requestFocus();
                } else {
                    balance.setR_Opt1(GetQty.OPTION_TEXT[0]);
                    balance.setR_Opt2(GetQty.OPTION_TEXT[1]);
                    balance.setR_Opt3(GetQty.OPTION_TEXT[2]);
                    balance.setR_Opt4(GetQty.OPTION_TEXT[3]);
                    balance.setR_Opt5(GetQty.OPTION_TEXT[4]);
                    balance.setR_Opt6(GetQty.OPTION_TEXT[5]);
                    balance.setR_Opt7(GetQty.OPTION_TEXT[6]);
                    balance.setR_Opt8(GetQty.OPTION_TEXT[7]);
                    balance.setR_Opt9(GetQty.OPTION_TEXT[8]);

                    GetQty.clear();//clear temp option

                    balance.setR_PrintOK(PublicVar.PrintOK);
                    balance.setMacno(Value.MACNO);
                    balance.setCashier(Value.USERCODE);
                    balance.setR_ETD(etd);
                    balance.setR_Quan(R_Quan);
                    balance.setR_Table(txtTable.getText());
                    balance.setR_Emp(emp);

                    balance.setR_PrCuType("");
                    balance.setR_PrCuQuan(0.00);
                    balance.setR_PrCuAmt(0.00);

                    balance.setR_PluCode(productBean.getPCode());
                    balance.setR_Group(productBean.getPGroup());
                    balance.setR_Status(productBean.getPStatus());
                    balance.setR_Normal(productBean.getPNormal());
                    balance.setR_Discount(productBean.getPDiscount());
                    balance.setR_Service(productBean.getPService());
                    balance.setR_Vat(productBean.getPVat());
                    balance.setR_Type(productBean.getPType());
                    balance.setR_Stock(productBean.getPStock());
                    balance.setR_PName(productBean.getPDesc());
                    balance.setR_Unit(productBean.getPUnit1());
                    balance.setR_Set(productBean.getPSet());

                    if (balance.getR_Status().equals("P")) {
                        if (etd.equals("E")) {
                            balance.setR_Price(productBean.getPPrice11());
                        } else if (etd.equals("T")) {
                            balance.setR_Price(productBean.getPPrice12());
                        } else if (etd.equals("D")) {
                            balance.setR_Price(productBean.getPPrice13());
                        } else if (etd.equals("P")) {
                            balance.setR_Price(productBean.getPPrice14());
                        } else if (etd.equals("W")) {
                            balance.setR_Price(productBean.getPPrice15());
                        } else {
                            txtShowETD.setText("E");
                        }
                    } else {
                        balance.setR_Price(Price);
                    }

                    balance.setR_Total(balance.getR_Quan() * balance.getR_Price());
                    balance.setR_PrChkType("");

                    BalanceControl balanceControl = new BalanceControl();
                    String R_Index = balanceControl.getIndexBalance(balance.getR_Table());
                    balance.setR_Index(R_Index);

                    // for member discount
                    if (Value.MemberAlready && balance.getR_Discount().equals("Y")) {
                        balance.setR_PrSubType("-M");
                        balance.setR_PrSubCode("MEM");
                        balance.setR_PrSubQuan(balance.getR_Quan());

                        // คำนวณหาว่าลดเท่าไหร่
                        String[] subPercent = memberBean.getMember_DiscountRate().split("/");
                        int Percent = 0;
                        if (subPercent.length == 3) {
                            if (balance.getR_Normal().equals("N")) {
                                Percent = Integer.parseInt(subPercent[0].trim());
                            } else if (balance.getR_Normal().equals("C")) {
                                Percent = Integer.parseInt(subPercent[1].trim());
                            } else if (balance.getR_Normal().equals("S")) {
                                Percent = Integer.parseInt(subPercent[2].trim());
                            }
                        }
                        balance.setR_PrSubDisc(Percent);
                        balance.setR_PrSubBath(0);
                        balance.setR_PrSubAmt((balance.getR_Total() * Percent) / 100);
                        balance.setR_QuanCanDisc(0);// if member default 0
                    } else {
                        memberBean = null;
                        // for not member
                        balance.setR_PrSubType("");
                        balance.setR_PrSubCode("");
                        balance.setR_PrSubQuan(0);// not member default 0
                        balance.setR_PrSubDisc(0);
                        balance.setR_PrSubBath(0);
                        balance.setR_PrSubAmt(0);
                        balance.setR_QuanCanDisc(balance.getR_Quan());
                    }

                    balanceControl.saveBalance(balance);

                    stmt.close();
                    //Process Stock Out
                    String StkCode = PUtility.GetStkCode();
                    String StkRemark = "SAL";
                    String DocNo = txtTable.getText() + "/" + Timefmt.format(new Date());

                    PUtility.ProcessStockOut(DocNo, StkCode, balance.getR_PluCode(), new Date(), StkRemark, balance.getR_Quan(), balance.getR_Total(),
                            balance.getCashier(), balance.getR_Stock(), balance.getR_Set(), R_Index, "1");

                    //คำนวณโปรโมชัน
                    //คำนวณคิดค่าบริการ
                    BalanceControl.updateProSerTable(txtTable.getText(), memberBean);

                    PublicVar.ErrorColect = true;
                } //end of Load Data
            }
            rec.close();
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
            txtPluCode.requestFocus();
        }
    }

    public boolean CheckPluForCharge() {
        boolean RetVal = false;
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "select *from product "
                    + "where pcode='" + PublicVar.P_Code + "' and pactive='Y'";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() == 0) {
                RetVal = false;
            } else {
                String Charge1 = rec.getString("charge1");
                String Charge2 = rec.getString("charge2");
                if (PublicVar.ChargeGroup.equals("1")) {
                    RetVal = Charge1.equals("Y");
                } else {
                    RetVal = Charge2.equals("Y");
                }
            }
            rec.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
        return RetVal;

    }

    public void UpdatePromotion2(TranRecord TranRec) {
        try {

            Statement stmt = MySQLConnect.con.createStatement();
            String SqlQuery = "select *from promotion2 "
                    + "where (macno='" + Value.MACNO + "') and (pcode='" + TranRec.R_PluCode + "')";
            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            if (rec.getRow() > 0) {
                double Tempqty = rec.getDouble("pquan");
                Tempqty = Tempqty + TranRec.R_Quan;
                String UpdatePromotion2 = "Update promotion2 set pquan=? where (macno=?) and (pcode=?)";
                PreparedStatement prm = MySQLConnect.con.prepareStatement(UpdatePromotion2);
                prm.setDouble(1, Tempqty);
                prm.setString(2, Value.MACNO);
                prm.setString(3, TranRec.R_PluCode);
                prm.executeUpdate();
            } else {
                String InsertPromotion2 = "insert into promotion2 (macno,pcode,procode,pquan,pprice) "
                        + "values (?,?,?,?,?)";
                PreparedStatement prm2 = MySQLConnect.con.prepareStatement(InsertPromotion2);
                prm2.setString(1, Value.MACNO);
                prm2.setString(2, TranRec.R_PluCode);
                prm2.setString(3, TranRec.R_PrCode);
                prm2.setDouble(4, TranRec.R_Quan);
                prm2.setDouble(5, TranRec.R_Price);
                prm2.executeUpdate();
            }
            rec.close();
            stmt.close();

        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
    }

    public void UpdatePromotion3(TranRecord TranRec) {
        try {

            Statement stmt = MySQLConnect.con.createStatement();
            String InsertPromotion3 = "insert into promotion3 (r_index,pcode,procode,pquan,pprice,macno) "
                    + "values (?,?,?,?,?,?)";
            PreparedStatement prm = MySQLConnect.con.prepareStatement(InsertPromotion3);
            prm.setString(1, TranRec.R_Index);
            prm.setString(2, TranRec.R_PluCode);
            prm.setString(3, TranRec.R_PrCode);
            prm.setDouble(4, TranRec.R_Quan);
            prm.setDouble(5, TranRec.R_Price);
            prm.setString(6, Value.MACNO);
            prm.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
    }

    public void UpdatePromotion4(TranRecord TranRec) {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String InsertPromotion4 = "insert into promotion4 (r_index,pcode,procode,pquan,pprice,macno) "
                    + "values (?,?,?,?,?,?)";
            PreparedStatement prm = MySQLConnect.con.prepareStatement(InsertPromotion4);
            prm.setString(1, TranRec.R_Index);
            prm.setString(2, TranRec.R_PluCode);
            prm.setString(3, TranRec.R_PrCode);
            prm.setDouble(4, TranRec.R_Quan);
            prm.setDouble(5, TranRec.R_Price);
            prm.setString(6, Value.MACNO);
            prm.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
    }

    public void KichenPrint() {
        PrintSimpleForm psf = new PrintSimpleForm();

        try {
            String printerName;
            String[] kicMaster = BranchControl.getKicData20();

            String sqlShowKic = "select r_kic from balance "
                    + "where r_table='" + txtTable.getText() + "' "
                    + "and R_PrintOK='Y' "
                    + "and R_KicPrint<>'P' "
                    + "and R_Kic<>'' "
                    + "group by r_kic "
                    + "order by r_kic;";
            try {
                ResultSet rsKic = MySQLConnect.getResultSet(sqlShowKic);
                while (rsKic.next()) {
                    String rKic = rsKic.getString("r_kic");
                    if (!rKic.equals("")) {
                        try {
                            int iKic = Integer.parseInt(rKic);
                            if (iKic - 1 < 0) {
                                //ถ้าเป็น iKic=0 จะเป็นการไม่กำหนดให้ปริ้นออกครัว
                            } else {
                                if (kicMaster[iKic - 1].equals("N")) {
                                    //NOT PRINT or Print already
                                } else {
                                    printerName = "KIC" + rKic;
                                    String printerForm = BranchControl.getForm(rKic);
                                    if (printerForm.equals("1") || printerForm.equals("2")) {
                                        String sql1 = "select * from balance "
                                                + "where r_table='" + txtTable.getText() + "' "
                                                + "and R_PrintOK='Y' "
                                                + "and R_KicPrint<>'P' "
                                                + "and R_Kic<>'' "
                                                + "and R_Void <> 'V' "
                                                + "group by r_plucode";
                                        ResultSet rs1 = MySQLConnect.getResultSet(sql1);
                                        while (rs1.next()) {
                                            String PCode = rs1.getString("R_PluCode");
                                            if (printerForm.equals("1")) {
                                                if (Value.printkic) {
                                                    psf.KIC_FORM_1(printerName, txtTable.getText(), new String[]{PCode});
                                                }
                                            } else if (printerForm.equals("2")) {
                                                if (Value.printkic) {
                                                    psf.KIC_FORM_2(printerName, txtTable.getText(), new String[]{PCode});

                                                }
                                            }
                                        }

                                        rs1.close();
                                    } else if (printerForm.equals("6")) {
                                        String sql2 = "select * from balance "
                                                + "where r_table='" + txtTable.getText() + "' "
                                                + "and R_PrintOK='Y' "
                                                + "and R_KicPrint<>'P' "
                                                + "and R_Kic<>'' "
                                                + "and R_Void<>'V' "
                                                + "order by r_index";
                                        ResultSet rs2 = MySQLConnect.getResultSet(sql2);
                                        while (rs2.next()) {
                                            if (Value.printkic) {
                                                String R_Index = rs2.getString("R_Index");
                                                psf.KIC_FORM_6(printerName, txtTable.getText(), R_Index);
                                            }
                                        }

                                        rs2.close();
                                    } else if (printerForm.equals("3") || printerForm.equals("4") || printerForm.equals("5")) {
                                        if (printerForm.equals("3")) {
                                            if (Value.printkic) {
                                                psf.KIC_FORM_3(printerName, txtTable.getText());
                                            }
                                        } else if (printerForm.equals("4")) {
                                            if (Value.printkic) {
                                                psf.KIC_FORM_4(printerName, txtTable.getText());
                                            }
                                        } else if (printerForm.equals("5")) {
                                            if (Value.printkic) {
                                                psf.KIC_FORM_5(printerName, txtTable.getText());
                                            }
                                        }
                                    } else {
                                        MSG.ERR(this, "ไม่พบฟอร์มปริ้นเตอร์ในระบบที่สามารใช้งานได้ !!!");
                                    }
                                }
                            }
                        } catch (Exception e) {
                            MSG.ERR(this, e.getMessage());
                        }
                    }
                }

                rsKic.close();

                //update r_kicprint
                try {
                    String sql = "update balance "
                            + "set r_kicprint='P' "
                            + "where r_table='" + txtTable.getText() + "' "
                            + "and r_kicprint<>'P' "
                            + "and r_printOk='Y' "
                            + "and r_kic<>'';";
                    MySQLConnect.exeUpdate(sql);
                } catch (Exception e) {
                    MSG.ERR(this, e.getMessage());
                }
            } catch (Exception e) {
                MSG.ERR(null, e.getMessage());
            }
        } catch (HeadlessException e) {
            MSG.ERR(null, e.getMessage());
        }

        //print to kictran
        PrintDriver pd = new PrintDriver();
        pd.printVoid(txtTable.getText());

    }

    public String SeekKicItemNo() {
        int KicItemNo = 0;
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SQLQuery = "Select *from branch";
            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                KicItemNo = rec.getInt("kicitemno") + 1;
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SQLQuery = "update branch set kicitemno =" + KicItemNo;
            stmt.executeUpdate(SQLQuery);
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }

        return QtyIntFmt.format(KicItemNo);
    }

    public void ChangeSaleType(String SaleType) {
        // description
        if (SaleType.equals("E")) {
            txtTypeDesc.setText("Eat-In");
        } else if (SaleType.equals("T")) {
            txtTypeDesc.setText("TakeAway");
        } else if (SaleType.equals("D")) {
            txtTypeDesc.setText("Delivery");
        } else if (SaleType.equals("P")) {
            txtTypeDesc.setText("Pinto");
        } else {
            txtTypeDesc.setText("WholeSale");
        }

        String oldType = txtShowETD.getText();
        if (SaleType.equals("E")) {
            if (oldType.equals("E") || oldType.equals("T") || model.getRowCount() == 0) {
                txtShowETD.setText("E");
            } else {
                MSG.ERR(this, "คุณกำหนดประเภทการขาย (Sale Type)ไม่ถูกต้อง !!!");
            }
        } else if (SaleType.equals("T")) {
            if (((oldType.equals("E")) | (oldType.equals("T")) | (model.getRowCount() == 0)) & !PublicVar.ChargeGroup.equals("2")) {
                txtShowETD.setText("T");
            } else {
                MSG.ERR(this, "คุณกำหนดประเภทการขาย (Sale Type)ไม่ถูกต้อง !!!");
            }
        } else if (SaleType.equals("D")) {
            if (((oldType.equals("D")) | (model.getRowCount() == 0)) & (PublicVar.ChargeCode.length() == 0)) {
                txtShowETD.setText("D");
            } else {
                MSG.ERR(this, "คุณกำหนดประเภทการขาย (Sale Type)ไม่ถูกต้อง !!!");
            }
        } else if (SaleType.equals("P")) {
            if (((oldType.equals("P")) | (model.getRowCount() == 0)) & (PublicVar.ChargeCode.length() == 0)) {
                txtShowETD.setText("P");
            } else {
                MSG.ERR(this, "คุณกำหนดประเภทการขาย (Sale Type)ไม่ถูกต้อง !!!");
            }
        } else if (SaleType.equals("W")) {
            if (((oldType.equals("W")) | (model.getRowCount() == 0)) & (PublicVar.ChargeCode.length() == 0)) {
                txtShowETD.setText("W");
            } else {
                MSG.ERR(this, "คุณกำหนดประเภทการขาย (Sale Type)ไม่ถูกต้อง !!!");
            }
        }
    }

    public void txtTableOnEnter() {
        txtCust.setEditable(false);
        txtPrice.setEditable(false);

        int RowCount = model.getRowCount();
        for (int i = 0; i <= RowCount - 1; i++) {
            model.removeRow(0);
        }

        txtMember.setText("ระบบสมาชิก : <ท่านไม่ได้ใช้ระบบสมาชิก> : แต้มสะสม 0 คะแนน");
    }

    public void txtTableOnExit() {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String sql = "select * from tablefile "
                    + "where tcode='" + txtTable.getText().trim() + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                if (rs.getString("tonact").equals("Y") && rs.getDouble("TAmount") > 0) {
                    MSG.WAR("มีพนักงานกำลังป้อนรายการโต๊ะนี้อยู่...");
                    TableOpenStatus = false;
                    txtTable.setText("");
                    txtTable.setEditable(true);
                    txtTable.requestFocus();
                } else {
                    //load data from tablefile
                    txtTable.setEditable(false);
                    txtCust.setText(rs.getString("TCustomer"));
                    txtCust.setEditable(false);
                    try {
                        String UpdateTable = "update tablefile set "
                                + "tonact='Y',"
                                + "macno='" + Value.MACNO + "',"
                                + "cashier='" + Value.USERCODE + "',"
                                + "EmpDisc='0',"
                                + "FastDisc='0',"
                                + "TrainDisc='0',"
                                + "PrintTime1='',"
                                + "TUser='',"
                                + "tlogindate=curdate(),"
                                + "tlogintime=curtime(),"
                                + "tcurtime=curtime() "
                                + "where tcode='" + txtTable.getText().trim() + "'";
                        MySQLConnect.exeUpdate(UpdateTable);
                        tbpMain.setSelectedIndex(0);

                        //load data to table
                        LoadDataFromBalance(txtTable.getText());
                        TableOpenStatus = true;
                    } catch (Exception ex) {
                        MSG.ERR(this, ex.getMessage());
                        TableOpenStatus = false;
                        txtTable.setText("");
                        txtTable.setEditable(true);
                        txtTable.requestFocus();
                        txtTable.setText("");
                    }
                }
            } else {
                MSG.ERR(this, "หมายเลขนี้ไม่ได้มีการกำหนดไว้ในการทำงานโต๊ะหลัก !!!");
                TableOpenStatus = false;
                txtTable.setText("");
                txtTable.setEditable(true);
                txtTable.requestFocus();
                txtTable.setText("");
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            MSG.ERR(e.getMessage());
            TableOpenStatus = false;
            txtTable.setText("");
            txtTable.setEditable(true);
            txtTable.requestFocus();
            txtTable.setText("");
        }

    }

    public void txtPluCodeOnEnter() {
        txtPluCode.setText("");
        txtPrice.setText("0.00");
        tblShowBalance.setEnabled(true);
    }

    public void txtPluCodeOnExit() {
        long s = System.currentTimeMillis();
        LoadDataFromProduct();
        tblShowPluShow(txtTable.getText());
        long e = System.currentTimeMillis();
        System.out.println("Time Estimate: " + (e - s));
        txtPluCode.setText("");
        txtPluCode.requestFocus();
    }

    public void bntPullTableClick() {
        if (txtTable.getText().length() > 0) {
            KichenPrint();
            PullTableAndSave();
            PublicVar.ErrorColect = false;
            initScreen();
        }
    }

    private void updateCustomerCount(int custCount) {
        try {
            String sql = "update tablefile "
                    + "set tcustomer='" + custCount + "' "
                    + "where tcode='" + txtTable.getText() + "'";
            MySQLConnect.exeUpdate(sql);
        } catch (Exception e) {
            MSG.ERR(this, e.getMessage());
        }
    }

    public void PullTableAndSave() {
        try {
            String UpdateTableFile = "update tablefile "
                    + "set tonact='N',"
                    + "TPause='Y' "
                    + "where tcode='" + txtTable.getText() + "'";
            MySQLConnect.exeUpdate(UpdateTableFile);
        } catch (Exception e) {
            PUtility.showError(e.getMessage());
        }

        showSum();
    }

    public void bntlogoffuserClick() {
        if (MSG.CONF(this, "ยืนยันการออกจากระบบการขาย (Logoff User) ? ")) {
            if (model.getRowCount() == 0) {
                PublicVar.P_LineCount = 1;
                PublicVar.P_LogoffOK = false;
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String QryUpdatePosuser = "update posuser set onact='N',macno=''where (username='" + PublicVar._User + "')";
                    stmt.executeUpdate(QryUpdatePosuser);
                    String QryUpdatePosHeSetup = "update poshwsetup set onact='N' where(terminal='" + Value.MACNO + "')";
                    stmt.executeUpdate(QryUpdatePosHeSetup);
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                if (this.ChkEJPath()) {
                    Prn.printLogout();
                }
                if (UpdateLogout(PublicVar._RealUser)) {
                    LoadLoginForm();
                }
            } else {
                MSG.WAR(this, "มีรายการขายค้างอยู่ไม่สามารถ Logoff ออกจากระบบได้...");
                if (TableOpenStatus) {
                    txtPluCode.requestFocus();
                } else {
                    txtTable.requestFocus();
                }
            }
        } else {
            if (TableOpenStatus) {
                txtPluCode.requestFocus();
            } else {
                txtTable.requestFocus();
            }
        }

    }

    public boolean UpdateLogout(String UserCode) {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SQLQuery = "update posuser set onact='N',macno='' where username='" + UserCode + "'";
            stmt.executeUpdate(SQLQuery);
            stmt.close();
            Value.CASHIER = "";

            return true;
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
            return false;
        }

    }

    public void bntpaidinClick() {
        if (!ChkEJPath()) {
            return;
        }
        if (model.getRowCount() == 0) {
            PaidinFrm frm = new PaidinFrm(null, true);
            frm.setVisible(true);
        }
    }

    public void bntpaidoutClick() {
        if (!ChkEJPath()) {
            return;
        }
        if (model.getRowCount() == 0) {
            PaidoutFrm frm = new PaidoutFrm(null, true);
            frm.setVisible(true);
        }
    }

    public void showTableAvialble() {
        if (!PUtility.CheckCashierClose(PublicVar._User)) {
            if (txtTable.getText().trim().equals("")) {
                ShowTable frm = new ShowTable(null, true);
                frm.setVisible(true);
                if (!PublicVar.ReturnString.equals("")) {
                    txtTable.setText(PublicVar.ReturnString);
                    if (txtTable.getText().trim().length() > 0) {
                        txtTable.setEditable(false);
                        txtTableOnExit();
                        //ShowSum();
                        if (CONFIG.getP_EmpUse().equals("Y")) {

                        } else {
                            if (PublicVar.TableRec_TCustomer == 0) {
                                txtCust.setEditable(true);
                                txtCust.requestFocus();
                                txtCust.selectAll();
                            } else {
                                txtPluCode.setEditable(true);
                                txtPluCode.requestFocus();
                                txtCust.setSelectionEnd(0);
                                txtCust.setEditable(false);
                            }
                        }
                    }
                }
            } else {
                PUtility.ShowWaring("มีการกำหนดหมายเลขโต๊ะไว้แล้ว...");
            }
        } else {
            txtTable.setText("");
            txtTable.requestFocus();
        }
    }

    public void showRefundBill() {
        if (!ChkEJPath()) {
            return;
        }
        PublicVar.TempUserRec = PublicVar.TUserRec;
        if (model.getRowCount() == 0) {
            if (PublicVar.TUserRec.Sale2.equals("Y")) {
                RefundBill frm = new RefundBill(null, true);
                frm.setVisible(true);
                PublicVar.P_ItemCount = 0;
                initScreen();
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale2.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            RefundBill frm = new RefundBill(null, true);
                            frm.setVisible(true);
                            PublicVar.P_ItemCount = 0;
                            initScreen();
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
        }
        PublicVar.TUserRec = PublicVar.TempUserRec;
    }

    public void bntarpaymentClick() {
        if (!ChkEJPath()) {
            return;
        }
        if (!PUtility.CheckSaleDateOK()) {
            return;
        }
        PublicVar.TempUserRec = PublicVar.TUserRec;
        if (model.getRowCount() == 0) {
            if (PublicVar.TUserRec.Sale5.equals("Y")) {
                ARPayment frm = new ARPayment(null, true);
                frm.setVisible(true);
            } else {
                GetUserAction getuser = new GetUserAction(null, true);
                getuser.setVisible(true);

                if (!PublicVar.ReturnString.equals("")) {
                    String loginname = PublicVar.ReturnString;
                    UserRecord supUser = new UserRecord();
                    if (supUser.GetUserAction(loginname)) {
                        if (supUser.Sale5.equals("Y")) {
                            PublicVar.TUserRec = supUser;
                            ARPayment frm = new ARPayment(null, true);
                            frm.setVisible(true);
                        } else {
                            MSG.ERR(this, "รหัสพนักงานนี้ไม่สามารถเข้าใช้งาน...รายการนี้ได้...!!!");
                        }
                    } else {
                        MSG.ERR(this, "ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    }
                }
            }
        }
        PublicVar.TUserRec = PublicVar.TempUserRec;
    }

    public void ProcessErrorColect() {
        if (PublicVar.ErrorColect) {
            String StkCode = PUtility.GetStkCode();
            String StkRemark;
            String DocNo;
            if (PublicVar.ChargeCode.equals("")) {
                StkRemark = "SAL";
                DocNo = "E" + txtTable.getText() + "/" + Timefmt.format(new Date());
            } else {
                StkRemark = "FRE";
                if (PublicVar.ChargeDocNo.equals("")) {
                    DocNo = txtTable.getText() + "/" + Timefmt.format(new Date());
                    PublicVar.ChargeDocNo = DocNo;
                } else {
                    DocNo = PublicVar.ChargeDocNo;
                }
            }
            Date TDate = new Date();
            BalanceControl bc = new BalanceControl();
            String R_Index_Last = bc.getLastIndex(txtTable.getText());
            BalanceBean bean = bc.getBalanceIndex(txtTable.getText(), R_Index_Last);

            PUtility.ProcessStockOut(DocNo, StkCode, bean.getR_PluCode(), TDate, StkRemark, -1 * bean.getR_Quan(),
                    -1 * bean.getR_Total(), bean.getCashier(), bean.getR_Stock(), bean.getR_Set(), bean.getR_Index(), "1");
            try {
                String sql = "delete from balance "
                        + "where r_index='" + bean.getR_Index() + "' "
                        + "and  r_table='" + bean.getR_Table() + "' "
                        + "and macno='" + bean.getMacno() + "'";
                MySQLConnect.exeUpdate(sql);
            } catch (Exception e) {
                MSG.ERR(this, e.getMessage());
            }

            if (CheckPSetSelect(bean.getR_PluCode())) {
                try {
                    String sql = "delete from balance "
                            + "where r_index='" + bean.getR_Index() + "' "
                            + "and r_table='" + bean.getR_Table() + "'";
                    MySQLConnect.exeUpdate(sql);
                } catch (Exception e) {
                    MSG.ERR(this, e.getMessage());
                }
            }
            LoadDataFromBalance(txtTable.getText());
            BalanceControl.updateProSerTable(txtTable.getText(), memberBean);
            showSum();
            PublicVar.ErrorColect = false;
            txtPluCode.requestFocus();
        }
    }

    public static void main(String args[]) {
        new MySQLConnect();
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

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainSale(null, false, "1").setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MAddNewAccr1;
    private javax.swing.JMenuItem MAddNewMember1;
    private javax.swing.JMenuItem MCancelArPayment1;
    private javax.swing.JMenuItem MCancelArPayment2;
    private javax.swing.JMenuItem MHeaderBill1;
    private javax.swing.JMenu MMainMenu1;
    private javax.swing.JMenuItem MRepArHistory1;
    private javax.swing.JMenuItem MRepArNotPayment1;
    private javax.swing.JMenuItem MRepInvAr1;
    private javax.swing.JMenuItem MRepInvCash1;
    private javax.swing.JMenuItem MRepMemberHistory1;
    private javax.swing.JButton btnAct0;
    private javax.swing.JButton btnAct1;
    private javax.swing.JButton btnAct2;
    private javax.swing.JButton btnAct3;
    private javax.swing.JButton btnAct4;
    private javax.swing.JButton btnAct5;
    private javax.swing.JButton btnAct6;
    private javax.swing.JButton btnAct7;
    private javax.swing.JButton btnAct8;
    private javax.swing.JButton btnAct9;
    private javax.swing.JButton c_bntESC;
    private javax.swing.JButton c_bntbs;
    private javax.swing.JButton c_bntenter;
    private javax.swing.JButton c_bntenter1;
    private javax.swing.JButton c_bntmulti;
    private javax.swing.JButton c_bntsub;
    private javax.swing.JPanel getplupanel;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar11;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel lbTotalAmount;
    private javax.swing.JPanel pMenu1;
    private javax.swing.JPanel pMenu2;
    private javax.swing.JPanel pMenu3;
    private javax.swing.JPanel pMenu4;
    private javax.swing.JPanel pMenu5;
    private javax.swing.JPanel pMenu6;
    private javax.swing.JPanel pMenu7;
    private javax.swing.JPanel pMenu8;
    private javax.swing.JPanel pMenu9;
    private javax.swing.JPanel pSubMenu1;
    private javax.swing.JPanel pSubMenu2;
    private javax.swing.JPanel pSubMenu3;
    private javax.swing.JPanel panelMenuFunction;
    private javax.swing.JPanel panelNumPad;
    private javax.swing.JTable tblShowBalance;
    private javax.swing.JTabbedPane tbpMain;
    private javax.swing.JTextField txtCust;
    private javax.swing.JTextField txtItemDisc;
    private javax.swing.JTextField txtMember;
    private javax.swing.JTextField txtPluCode;
    private javax.swing.JFormattedTextField txtPrice;
    private javax.swing.JTextField txtPromotion;
    private javax.swing.JTextField txtShowETD;
    private javax.swing.JLabel txtShowTime1;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTable;
    private javax.swing.JTextField txtTotalAmount;
    private javax.swing.JTextField txtTotalService;
    private javax.swing.JTextField txtTypeDesc;
    // End of variables declaration//GEN-END:variables

    private void openDoc() {
        Desktop desktop = Desktop.getDesktop();
        String file = "user_manaual.pdf";
        try {
            File f = new File(file);
            if (f.exists()) {
                desktop.open(f);
            } else {
                MSG.ERR(this, "หาไฟล์ " + file + " ไม่เจอในระบบ กรุณาตรวจสอบ");
            }
        } catch (IOException ex) {
            MSG.ERR(this, ex.getMessage());
        }
    }

    private void showFloorPlan() {
        if (txtTable.hasFocus() && txtTable.getText().trim().equals("") && txtTable.getText().endsWith("")) {
            dispose();
        } else {
            MSG.ERR(this, "กรุณาพักโต๊ะก่อนการใช้งาน Floor Plan");
        }
    }

    private void showCustomerCount() {
        if (txtPluCode.hasFocus()) {
            txtCust.setEditable(true);
            txtCust.selectAll();
            txtCust.requestFocus();
        }
    }

    private void showSplitBill() {
        if (txtPluCode.hasFocus() && model.getRowCount() > 0) {
            SplitBillPayment sp = new SplitBillPayment(null, true, txtTable.getText());
            sp.setVisible(true);

            if (!sp.getTable2().equals("")) {
                this.setVisible(true);
                txtTable.setText(sp.getTable2());
                TableOpened();
                txtCustOnExit();
            }
        }
    }

    private void showMember() {
        if (Value.MemberAlready == true) {
            int confirm = JOptionPane.showConfirmDialog(this, "มีการป้อนรหัสสมาชิกไว้แล้วต้องการเปลี่ยนใหม่หรือไม่...?");
            if (confirm == JOptionPane.YES_OPTION) {
                Value.MemberAlready = false;
                try {
                    String sql = "update tablefile set "
                            + "MemDisc='', "
                            + "MemDiscAmt='0.00', "
                            + "MemCode='', "
                            + "MemCurAmt='0.00', "
                            + "MemName='' "
                            + "where TCode='" + txtTable.getText() + "'";
                    MySQLConnect.exeUpdate(sql);
                    showMember();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
        } else {
            MemberDialog frm = new MemberDialog(null, true);
            frm.setVisible(true);

            if (frm.getMemCode() != null) {
                Value.MemberAlready = true;
                memberBean = MemberBean.getMember(frm.getMemCode());
                String memDisc = memberBean.getMember_DiscountRate();
                txtMember.setText(memberBean.getMember_Code() + " - " + memberBean.getMember_NameThai());

                // update member in tablefile
                SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                try {
                    String sql = "update tablefile set "
                            + "MemDisc='" + memberBean.getMember_DiscountRate() + "', "
                            + "MemDiscAmt='0.00', "
                            + "MemCode='" + memberBean.getMember_Code() + "', "
                            + "MemCurAmt='" + memberBean.getMember_TotalScore() + "', "
                            + "MemName='" + ThaiUtil.Unicode2ASCII(memberBean.getMember_NameThai()) + "', "
                            + "MemBegin='" + simp.format(memberBean.getMember_Brithday()) + "', "
                            + "MemEnd='" + simp.format(memberBean.getMember_ExpiredDate()) + "' "
                            + "where TCode='" + txtTable.getText() + "'";
                    MySQLConnect.exeUpdate(sql);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }

                // update old order
                MemberControl mc = new MemberControl();
                mc.updateMemberAllBalance(txtTable.getText(), memberBean);

                // update all discount and promotion
                BalanceControl.updateProSerTable(txtTable.getText(), memberBean);
            }
        }

        showSum();
    }

    private void showHoldTable() {
        if (!txtPrice.hasFocus()) {
            tbpMain.setSelectedIndex(0);
            bntPullTableClick();
            txtTable.setText("");
            txtCust.setText("0");

            clearTable();
            showTableOpen();
        }

        clearHistory();
        Value.TableSelected = "";
    }

    private void showPaidIn() {
        if (PUtility.CheckSaleDateOK()) {
            bntpaidinClick();
        }
    }

    private void showPaidOut() {
        if (PUtility.CheckSaleDateOK()) {
            bntpaidoutClick();
        }
    }

    private void showBillCheck() {
        if ((!txtPrice.hasFocus()) & (model.getRowCount() > 0)) {
            KichenPrint();
            bntcheckbillClick();
        }
    }

    private void showMoveTable() {
        MoveGroupTable move = new MoveGroupTable(null, true);
        move.setVisible(true);
    }

    private void showPayAR() {
        if (PUtility.CheckSaleDateOK()) {
            bntarpaymentClick();
        }
    }

    private void showCheckBill() {
        if (!txtPrice.hasFocus() && model.getRowCount() > 0) {
            KichenPrint();
            bntpaymentClick();
        }
    }

    private void selectedTableBalance() {
        if (tblShowBalance.getRowCount() > 0) {
            tblShowBalance.requestFocus();
            Rectangle rect = tblShowBalance.getCellRect(0, 0, true);
            tblShowBalance.scrollRectToVisible(rect);
            tblShowBalance.clearSelection();
            tblShowBalance.setRowSelectionInterval(0, 0);
        }
    }

    private void selectedOptionBill() {
        int row = tblShowBalance.getSelectedRow();
        String chk = tblShowBalance.getValueAt(row, 0).toString();
        if (chk.equals("")) {
            return;
        }
        if (row != -1) {
            PopupItemJDialog popup = new PopupItemJDialog(null, true);
            popup.setVisible(true);

            String typeIndex = popup.getTypeIndex();

            if (!typeIndex.equals("none")) {
                String PCode = model.getValueAt(row, 0).toString();
                String PVoid = model.getValueAt(row, 5).toString();
                String RIndex = model.getValueAt(row, 10).toString();

                if (typeIndex.equals("ItemOption")) {
                    bntoptionClick();
                    tblShowPluShow(txtTable.getText());
                } else if (typeIndex.equals("TypeSale")) {
                    if (!txtPrice.hasFocus() && model.getRowCount() > 0) {

                        if (!PCode.equals("") && !PVoid.equals("V")) {
                            ChangTypeClick();
                        } else {
                            PUtility.ShowMsg("รายการนี้ได้มีการพิมพ์ออกครัวไปแล้ว...\nไม่สามารถเปลี่ยนประเภทการขายได้...");
                        }
                    }
                } else if (typeIndex.equals("ItemDiscount")) {

                    // ตรวจสอบว่าสามารถให้ส่วนลดได้อีกหรือไม่ ?
                    if (checkCanDisc(RIndex)) {
                        if (!txtPrice.hasFocus() && model.getRowCount() > 0) {
                            if (!PCode.equals("") && !PVoid.equals("V")) {
                                ItemDiscount i = new ItemDiscount(null, true, txtTable.getText(), RIndex, memberBean);
                                i.setVisible(true);

                                tblShowPluShow(txtTable.getText());
                                txtPluCode.setText("");
                                txtPluCode.requestFocus();
                            }
                        }
                    } // หากไม่สามารถให้ส่วนลดรายการได้
                    else {
                        JOptionPane.showMessageDialog(this, "รายการสินค้านี้มีการให้ส่วนลดอื่นไปแล้วไม่สามารถให้ส่วนลดได้อีก");
                    }

                } else if (typeIndex.equals("ItemVoid")) {
                    if (!PCode.equals("") && !PVoid.equals("V")) {
                        bntvoidClick();
                    }
                } else if (typeIndex.equals("ItemMove")) {
                    MoveItemDialog m = new MoveItemDialog(null, true, txtTable.getText());
                    m.setVisible(true);
                }
            }

            //load data from balance
            LoadDataFromBalance(txtTable.getText());
        }
    }

    private void pCodeEnter() {
        String pluCode = txtPluCode.getText().trim();
        String chkOpt = "";
        if (!pluCode.equals("")) {
            chkOpt = pluCode.substring(pluCode.length() - 1, pluCode.length());
        }

        if (chkOpt.equals("+")) {
            //สามารถเลือกจำนวนได้เลย

            pluCode = pluCode.substring(0, pluCode.length() - 1);

            int qtySet = 0;
            if (Value.autoqty) {
                GetQty frm = new GetQty(null, true, pluCode);
                frm.setVisible(true);

                qtySet = frm.ReturnQty;
            } else {
                qtySet = 1;
            }
            if (!pluCode.equals("")) {
                if (qtySet > 0) {
                    txtPluCode.setText(QtyIntFmt.format(qtySet).trim() + "*" + pluCode);
                    if (seekPluCode()) {
                        if (PublicVar.P_Status.equals("S")) {
                            getPrice();
                        } else {
                            txtPluCodeOnExit();
                        }
                    }
                } else {
                    txtPluCode.setText("");
                    txtPluCode.requestFocus();
                }
            }
        } else {
            if (findPluCode()) {
                if (PublicVar.P_Status.equals("S")) {
                    getPrice();
                } else {
                    txtPluCodeOnExit();
                }
            }
        }
    }

    private void clearHistory() {
        int size = historyBack.size();
        for (int i = 0; i < size; i++) {
            historyBack.remove(0);
        }
    }

    private String buttonName;
    private int buttonIndex;

    private void addMouseEvent(final JButton btn1, final int index) {
        btn1.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    buttonName = btn1.getName();
                    buttonIndex = index;

                    jPopupMenu1.show(btn1, e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void clearPanelMenu() {
        pMenu1.removeAll();
        pMenu2.removeAll();
        pMenu3.removeAll();
        pMenu4.removeAll();
        pMenu5.removeAll();
        pMenu6.removeAll();
        pMenu7.removeAll();
        pMenu8.removeAll();
        pMenu9.removeAll();
    }

    private boolean checkCanDisc(String RIndex) {
        try {
            String sql = "select R_QuanCanDisc from balance "
                    + "where R_Index='" + RIndex + "' "
                    + "and R_QuanCanDisc>0";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    class TimeOfDay implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleDateFormat tf = new SimpleDateFormat("(HH:mm:ss)", Locale.ENGLISH);
            String St = tf.format(new Date());
            //txtShowTime.setText(St);
        }
    }

    public class TableTestFormatRenderer extends DefaultTableCellRenderer {

        private Format formatter;

        public TableTestFormatRenderer(Format formatter) {
            if (formatter == null) {
                throw new NullPointerException();
            }
            this.formatter = formatter;
        }

        @Override
        protected void setValue(Object obj) {
            setText(obj == null ? "" : formatter.format(obj));
        }
    }

    //----------SetupMenu-----------------------
    private void setupMenu() {
        clearPanelMenu();
        final MGRButtonMenu mgr = new MGRButtonMenu();
        POSHW = POSHWSetup.Bean(Value.getMacno());
        loadHeaderMenu();
        String[] menuAt = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "J"};
        final JPanel[] panel = new JPanel[]{pMenu1, pMenu2, pMenu3, pMenu4, pMenu5, pMenu6, pMenu7, pMenu8, pMenu9};
        for (int a = 0; a < menuAt.length; a++) {
            for (int b = 0; b < 19; b++) {
                String MCode;
                if ((b + 1) < 10) {
                    MCode = menuAt[a] + "0" + (b + 1);
                } else {
                    MCode = menuAt[a] + (b + 1);
                }

                final JButton btn1 = mgr.getButtonLayout(MCode, b);
                btn1.setFocusable(false);
                addMouseEvent(btn1, b);
                btn1.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (btn1.getText() == null) {
                            return;
                        }
                        String[] data = checkGroup(btn1.getName());

                        if (data[0].equals("0")) {
                            addHistory(tbpMain.getSelectedIndex());
                            pSubMenu1.removeAll();

                            String MCode2;
                            for (int c = 0; c < 19; c++) {
                                if ((c + 1) < 10) {
                                    MCode2 = btn1.getName() + "0" + (c + 1);
                                } else {
                                    MCode2 = btn1.getName() + (c + 1);
                                }

                                final JButton btn2 = mgr.getButtonLayout(MCode2, c);
                                btn2.setFocusable(false);
                                addMouseEvent(btn2, c);
                                btn2.setName(MCode2);
                                pSubMenu1.add(btn2);
                                tbpMain.setSelectedIndex(9);

                                btn2.addActionListener(new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (btn2.getText() == null) {
                                            return;
                                        }
                                        String[] data = checkGroup(btn2.getName());

                                        if (data[0].equals("0")) {
                                            addHistory(9);
                                            pSubMenu2.removeAll();

                                            String MCode3;
                                            for (int d = 0; d < 19; d++) {
                                                if ((d + 1) < 10) {
                                                    MCode3 = btn2.getName() + "0" + (d + 1);
                                                } else {
                                                    MCode3 = btn2.getName() + (d + 1);
                                                }

                                                final JButton btn3 = mgr.getButtonLayout(MCode3, d);
                                                btn3.setFocusable(false);
                                                addMouseEvent(btn3, d);
                                                btn3.setName(MCode3);
                                                pSubMenu2.add(btn3);
                                                tbpMain.setSelectedIndex(10);

                                                btn3.addActionListener(new ActionListener() {

                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        if (btn3.getText() == null) {
                                                            return;
                                                        }
                                                        String[] data = checkGroup(btn3.getName());
                                                        if (data[0].equals("0")) {
                                                            addHistory(10);
                                                            pSubMenu3.removeAll();

                                                            String MCode4;
                                                            for (int f = 0; f < 19; f++) {
                                                                if ((f + 1) < 10) {
                                                                    MCode4 = btn3.getName() + "0" + (f + 1);
                                                                } else {
                                                                    MCode4 = btn3.getName() + (f + 1);
                                                                }

                                                                final JButton btn4 = mgr.getButtonLayout(MCode4, f);
                                                                btn4.setFocusable(false);
                                                                addMouseEvent(btn4, f);
                                                                btn4.setName(MCode4);
                                                                btn4.addActionListener(new ActionListener() {

                                                                    @Override
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        if (btn4.getText() == null) {
                                                                            return;
                                                                        }
                                                                        String[] data = checkGroup(btn4.getName());
                                                                        if (data[0].equals("1") && !data[1].equals("")) {
                                                                            //add Product
                                                                            String PCode = data[1];
                                                                            addProductFromButtonMenu(PCode);
                                                                        }
                                                                    }
                                                                });
                                                                pSubMenu3.add(btn4);
                                                                tbpMain.setSelectedIndex(11);
                                                            }

                                                            JButton btnBack = new JButton("กลับ");
                                                            btnBack.setFocusable(false);
                                                            btnBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
                                                            btnBack.setBackground(Color.RED);
                                                            btnBack.addActionListener(new ActionListener() {

                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    backHistory();
                                                                }
                                                            });
                                                            pSubMenu3.add(btnBack);
                                                        } else {
                                                            // add product
                                                            if (data[0].equals("1")) {
                                                                String PCode = data[1];
                                                                addProductFromButtonMenu(PCode);
                                                            }
                                                        }
                                                    }
                                                });
                                            }

                                            JButton btnBack = new JButton("กลับ");
                                            btnBack.setFocusable(false);
                                            btnBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
                                            btnBack.setBackground(Color.RED);
                                            btnBack.addActionListener(new ActionListener() {

                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    backHistory();
                                                }
                                            });
                                            pSubMenu2.add(btnBack);
                                        } else {
                                            //add product
                                            if (data[0].equals("1")) {
                                                String PCode = data[1];
                                                addProductFromButtonMenu(PCode);
                                            }
                                        }
                                    }
                                });
                            }

                            JButton btnBack = new JButton("กลับ");
                            btnBack.setFocusable(false);
                            btnBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
                            btnBack.setBackground(Color.RED);
                            btnBack.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    backHistory();
                                }
                            });
                            pSubMenu1.add(btnBack);

                        } else {
                            //add product
                            if (data[0].equals("1")) {
                                String PCode = data[1];
                                addProductFromButtonMenu(PCode);
                            }
                        }
                    }

                    private String[] checkGroup(String MenuCode) {
                        String[] data = new String[]{"", ""};
                        try {
                            String sql = "select MenuType, PCode "
                                    + "from soft_menusetup where MenuCode='" + MenuCode + "'";
                            ResultSet rs = MySQLConnect.getResultSet(sql);
                            if (rs.next()) {
                                String MenuType = rs.getString("MenuType");
                                String PCode = rs.getString("PCode");
                                data[0] = MenuType;
                                data[1] = PCode;
                            }

                            rs.close();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        return data;
                    }

                    private void addProductFromButtonMenu(String PCode) {
                        txtPluCode.setText(txtPluCode.getText().trim() + PCode);
                        if (findPluCode()) {
                            if (PublicVar.P_Status.equals("S")) {
                                getPrice();
                            } else {
                                //สามารถเลือกจำนวนได้เลย
                                double qtySet = 0;
                                if (Value.autoqty) {
                                    GetQty frm = new GetQty(null, true, txtPluCode.getText());
                                    frm.setVisible(true);

                                    qtySet = frm.ReturnQty;
                                } else {
                                    qtySet = PublicVar.P_Qty;
                                }

                                if (!txtPluCode.getText().trim().equals("")) {
                                    if (qtySet > 0) {
                                        txtPluCode.setText("" + qtySet + "*" + PCode);
                                        if (seekPluCode()) {
                                            if (PublicVar.P_Status.equals("S")) {
                                                getPrice();
                                            } else {
                                                txtPluCodeOnExit();
                                            }
                                        }
                                    } else {
                                        txtPluCode.setText("");
                                        txtPluCode.requestFocus();
                                    }
                                }
                            }
                        }
                    }
                });
                panel[a].add(btn1);
            }
            JButton btnBack = new JButton("กลับ");
            btnBack.setFocusable(false);
            btnBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
            btnBack.setBackground(Color.RED);
            btnBack.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    backHistory();
                }
            });
            panel[a].add(btnBack);
        }
    }

    private void addHistory(int index) {
        int size = historyBack.size();
        boolean isExists = false;
        for (int i = 0; i < size; i++) {
            int a = (Integer) historyBack.get(i);
            if (a == index) {
                isExists = true;
                break;
            }
        }
        if (!isExists) {
            historyBack.add(index);
        }
    }

    private void backHistory() {
        int size = historyBack.size();
        if (size > 0) {
            tbpMain.setSelectedIndex(historyBack.get(size - 1));
            historyBack.remove(size - 1);
        } else {
            clearHistory();
            tbpMain.setSelectedIndex(0);
        }
    }

    private void loadHeaderMenu() {
        try {
            String sql = "SELECT * FROM company";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            if (rs.next()) {
                for (int i = 0; i < 9; i++) {
                    String h = ThaiUtil.ASCII2Unicode(rs.getString("head" + (i + 1)));
                    tbpMain.setTitleAt(i, h);
                }
            }

            rs.close();
        } catch (Exception e) {
            MSG.ERR(this, e.getMessage());
        }

    }

    private void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (IOException e) {
                MSG.ERR(this, e.getMessage());
            }
        }
    }

    private void openWebpage(URL url) throws URISyntaxException {
        openWebpage(url.toURI());
    }
}
