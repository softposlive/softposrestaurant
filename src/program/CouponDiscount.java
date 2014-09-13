package program;

import com.softpos.cupon.CuponBean;
import com.softpos.cupon.CuponControl;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import database.MySQLConnect;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class CouponDiscount extends javax.swing.JDialog {

    private DefaultTableModel model2;
    private SimpleDateFormat Datefmtshow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    private SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private SimpleDateFormat Timefmt = new SimpleDateFormat("HH:mm");
    private Date date = new Date();
    private POSConfigSetup CONFIG;
    private String tableNo;
    private double cuponDiscAmt = 0;
    private CuponBean cuponBean = null;

    /**
     * Creates new form CouponDiscount
     * @param parent
     * @param modal
     * @param tableNo
     */
    public CouponDiscount(java.awt.Frame parent, boolean modal, String tableNo) {
        super(parent, modal);
        initComponents();

        this.tableNo = tableNo;

        CONFIG = POSConfigSetup.Bean();

        initComponents();
        PublicVar.ReturnString = "";
        model2 = (DefaultTableModel) ShowTable.getModel();
        ShowTable.setShowGrid(true);
        ShowTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ShowTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ShowTable.setRowSelectionAllowed(true);
        ShowTable.setGridColor(Color.gray);

        JTableHeader header = ShowTable.getTableHeader();
        //header.setBackground(Color.yellow);
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 16));

        int[] ColSize = {40, 80, 260, 60};
        for (int i = 0; i < 4; i++) {
            //int vColIndex = 0;
            TableColumn col = ShowTable.getColumnModel().getColumn(i);
            col.setPreferredWidth(ColSize[i]);
        }

        DecimalFormat IntegerFmt = new DecimalFormat("##,###,##0");

        TableColumnModel tcm = ShowTable.getColumnModel();

        TableTestFormatRenderer r = new TableTestFormatRenderer(IntegerFmt);

        r.setHorizontalAlignment(SwingConstants.CENTER);
        tcm.getColumn(3).setCellRenderer(r);
        LoadDataToGrid();

        ShowTable.requestFocus();

    }
    
    public CuponBean getCuponBean(){
        return cuponBean;
    }

    public double getCuponDiscount() {
        return cuponDiscAmt;
    }

    public CouponDiscount() {
        initComponents();
        PublicVar.ReturnString = "";
        model2 = (DefaultTableModel) ShowTable.getModel();
        ShowTable.setShowGrid(true);
        ShowTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ShowTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ShowTable.setRowSelectionAllowed(true);
        ShowTable.setGridColor(Color.gray);

        JTableHeader header = ShowTable.getTableHeader();
        //header.setBackground(Color.yellow);
        header.setFont(new java.awt.Font("Norasi", java.awt.Font.PLAIN, 16));

        int[] ColSize = {40, 80, 260, 60};
        for (int i = 0; i < 4; i++) {
            //int vColIndex = 0;
            TableColumn col = ShowTable.getColumnModel().getColumn(i);
            col.setPreferredWidth(ColSize[i]);
        }
        DecimalFormat DoubleFmt = new DecimalFormat("##,###,##0.00");
        DecimalFormat IntegerFmt = new DecimalFormat("##,###,##0");
        DecimalFormat PersentFmt = new DecimalFormat("#,##0.00%");

        TableColumnModel tcm = ShowTable.getColumnModel();

        TableTestFormatRenderer r = new TableTestFormatRenderer(IntegerFmt);

        r.setHorizontalAlignment(SwingConstants.CENTER);
        tcm.getColumn(3).setCellRenderer(r);
        LoadDataToGrid2();
        txtCucode.setText("");

    }

    public void ProcessAutoCoupon(String CuCode) {
        bntOKClick();
    }

    public void ProcessAutoClearCoupon() {
        bntOKClick();
    }

    public void ProcessAutoClearCouponSMS() {
    }

    public void SMS_CodeCancel(String SMS_Code, String MemCode) {
        String Cu_Code = "";
        if (!SMS_Code.equals("")) {
            try {
                Statement stmt2 = MySQLConnect.con.createStatement();
                String SqlQuery = "update smstable set use_status='N',billno='',usetime='' "
                        + "where (m_code='" + MemCode + "') and (sms_code='" + SMS_Code + "') ";
                stmt2.executeUpdate(SqlQuery);
                stmt2.close();
            } catch (SQLException e) {
                PUtility.showError(e.getMessage());
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bntOK = new javax.swing.JButton();
        bntExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShowTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCuQty = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCucode = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cupon List");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntOK.setText("F5 ตกลง (OK)");
        bntOK.setRequestFocusEnabled(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntExit.setText("ESC ออก(Exit)");
        bntExit.setRequestFocusEnabled(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        ShowTable.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        ShowTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Code", "Description/รายการ", "จำนวน"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ShowTable.setRowHeight(35);
        ShowTable.setUpdateSelectionOnSort(false);
        ShowTable.setVerifyInputWhenFocusTarget(false);
        ShowTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowTableMouseClicked(evt);
            }
        });
        ShowTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ShowTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(ShowTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("จำนวนคูปอง");

        txtCuQty.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCuQty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuQty.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        txtCuQty.setRequestFocusEnabled(false);
        txtCuQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCuQtyKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("รหัสคูปอง");

        txtCucode.setFont(new java.awt.Font("Norasi", 1, 16)); // NOI18N
        txtCucode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCucodeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCucode, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCuQty, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(bntOK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntExit)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCuQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtCucode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(563, 370));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    cuponBean = null;
    dispose();
}//GEN-LAST:event_bntExitActionPerformed

private void txtCuQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuQtyKeyPressed
// TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        SelectRow();
        txtCuQty.setText("");
        txtCuQty.requestFocus(false);
        txtCucode.requestFocus(true);
        //ShowTable.requestFocus();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        txtCuQty.setText("");
        txtCuQty.requestFocus(false);
        txtCucode.requestFocus(true);
        //ShowTable.requestFocus();
    }
}//GEN-LAST:event_txtCuQtyKeyPressed

private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
// TODO add your handling code here:
    //ShowTable.requestFocus();
    txtCucode.setText("");
    txtCucode.requestFocus(true);
    txtCuQty.setText("0");
}//GEN-LAST:event_formWindowActivated

private void ShowTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ShowTableKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
}//GEN-LAST:event_ShowTableKeyPressed

private void txtCucodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCucodeKeyPressed
    // TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        if (txtCucode.getText().length() > 0) {
            gotocupon();
        }
    }
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
}//GEN-LAST:event_txtCucodeKeyPressed

    private void ShowTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowTableMouseClicked
        int row = ShowTable.getSelectedRow();
        if (row != -1) {
            txtCucode.setText(ShowTable.getValueAt(row, 1).toString());
            txtCuQty.requestFocus();
        }
    }//GEN-LAST:event_ShowTableMouseClicked

    private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
        cuponDiscAmt = 0;
        for (int i = 0; i < model2.getRowCount(); i++) {
            double total = Double.parseDouble(model2.getValueAt(i, 3).toString());
            cuponDiscAmt += total;
        }
        
        cuponBean = new CuponBean();
        cuponBean.setCuCode(txtCucode.getText());
        cuponBean.setCuQTY(Integer.parseInt(txtCuQty.getText()));

        dispose();
    }//GEN-LAST:event_bntOKActionPerformed
    public void bntExitClick() {
        this.dispose();
    }

    private void LoadDataToGrid() {
        int Cnt = 0;
        String CuDate = Datefmt.format(date);
        SimpleDateFormat DayFormat = new SimpleDateFormat("EEE", Locale.ENGLISH);
        String CurDay = DayFormat.format(date);

        try {
            String sql = "select * from cupon "
                    + "where ('" + CuDate + "'>=cubegin) "
                    + "and ('" + CuDate + "'<=cuend) "
                    + "and (cutype is not null) "
                    + "and (cucode is not null) "
                    + "order by cutype,cucode";
            ResultSet rec = MySQLConnect.getResultSet(sql);
            while(rec.next()){
                CouponRec CuRec = new CouponRec();
                if ((rec.getString("custrday").indexOf(CurDay) >= 0)) {
                    String fixbranch = "";
                    if ((fixbranch.length() == 0) || (fixbranch.indexOf(PublicVar.Branch_Code) >= 0)) {
                        CuRec.CuType = rec.getString("cutype");
                        CuRec.CuCode = rec.getString("cucode");
                        CuRec.CuName = ThaiUtil.ASCII2Unicode(rec.getString("cuname"));
                        CuRec.CuDisc = rec.getDouble("cudisc");
                        CuRec.CuDiscBath = rec.getDouble("cudiscbath");
                        CuRec.ChkMember = rec.getString("chkmember");
                        CuRec.CuQty = SeekTempCoupon(rec.getString("cucode"));
                        CuRec.SMS_Code = SeekSMS_Code(rec.getString("cucode"));
                        CuRec.M_Code = SeekM_Code(rec.getString("cucode"));
                        
                        model2.addRow(new Object[]{
                            CuRec.CuType,
                            CuRec.CuCode,
                            CuRec.CuName,
                            CuRec.CuQty
                        });
                        if (CuRec.ChkMember.equals("Y")) {
                            if (!PublicVar.TableRec_MemCode.equals("")) {
                                Cnt++;
                            }
                        } else {
                            Cnt++;
                        }
                    }
                }
            }
            rec.close();
        } catch (Exception e) {
            PUtility.showError(e.getMessage());
        }

    }

    public void AssignGrid(CouponRec[] CuArray) {
        int RowCount = model2.getRowCount();
        for (int i = 0; i <= RowCount - 1; i++) {
            model2.removeRow(0);
        }
        int CuponSize = CuArray.length;
        for (int i = 0; i < CuponSize; i++) {
            Object[] input = {
                "",
                "",
                CuArray[i].CuName,
                CuArray[i].CuQty
            };
            model2.addRow(input);
        }
        showCell(0, 0);
        ShowTable.setRequestFocusEnabled(true);
        txtCucode.requestFocus(true);
    }

    private void LoadDataToGrid2() {
        int CuponSize = 0;
        String CuDate = Datefmt.format(date);
        SimpleDateFormat DayFormat = new SimpleDateFormat("EEE", Locale.ENGLISH);
        String CurDay = DayFormat.format(date);
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String LoadTableFile = "select *from cupon "
                    + "where ('" + CuDate + "'>=cubegin) and ('" + CuDate + "'<=cuend) and (cutype is not null) and (cucode is not null) "
                    + "order by cutype,cucode";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            int Cnt = 0;
            if (rec.getRow() == 0) {
            } else {
                do {
                    CouponRec CuRec = new CouponRec();
                    if ((rec.getString("custrday").indexOf(CurDay) >= 0)) {

                        CuRec.CuType = rec.getString("cutype");
                        CuRec.CuCode = rec.getString("cucode");
                        CuRec.CuName = rec.getString("cuname");
                        CuRec.CuDisc = rec.getDouble("cudisc");
                        CuRec.CuDiscBath = rec.getDouble("cudiscbath");
                        CuRec.ChkMember = rec.getString("chkmember");
                        CuRec.CuQty = SeekTempCoupon(rec.getString("cucode"));
                        CuRec.SMS_Code = SeekSMS_Code(rec.getString("cucode"));
                        CuRec.M_Code = SeekM_Code(rec.getString("cucode"));
//                        CuRec.CuSaleType = rec.getString("custrtype");
//                        CuRec.SaleMin = rec.getDouble("cuminsale");
                        if (CuRec.ChkMember.equals("Y")) {
                            if (!PublicVar.TableRec_MemCode.equals("")) {
                                Cnt++;
                            }
                        } else {
                            Cnt++;
                        }
                    }

                } while (rec.next());
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }

    }

    public void AssignGrid2(CouponRec[] CuArray) {
        int RowCount = model2.getRowCount();
        for (int i = 0; i <= RowCount - 1; i++) {
            model2.removeRow(0);
        }
        int CuponSize = CuArray.length;
        for (int i = 0; i < CuponSize; i++) {
            Object[] input = {
                "",
                "",
                CuArray[i].CuName,
                CuArray[i].CuQty
            };
            model2.addRow(input);
        }
        
        showCell(0, 0);
    }

    public int SeekTempCoupon(String CuCode) {
        int RetValue = 0;
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String LoadTableFile = "select *from tempcupon where (r_table='" + tableNo + "') and (cucode='" + CuCode + "')";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                RetValue = rec.getInt("cuquan");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
        return RetValue;
    }

    public String SeekSMS_Code(String CuCode) {
        String RetValue = "";
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String LoadTableFile = "select *from tempcupon where (r_table='" + tableNo + "') and (cucode='" + CuCode + "')";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                RetValue = rec.getString("sms_code");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
        if (RetValue == null) {
            RetValue = "";
        }
        return RetValue;
    }

    public String SeekM_Code(String CuCode) {
        String RetValue = "";
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String LoadTableFile = "select *from tempcupon where (r_table='" + tableNo + "') and (cucode='" + CuCode + "')";
            ResultSet rec = stmt.executeQuery(LoadTableFile);
            rec.first();
            if (rec.getRow() == 0) {
            } else {
                RetValue = rec.getString("m_code");
            }
            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
        if (RetValue == null) {
            RetValue = "";
        }
        return RetValue;
    }

    public void showCell(int row, int column) {
        if (row > 0) {
            Rectangle rect = ShowTable.getCellRect(row, column, true);
            ShowTable.scrollRectToVisible(rect);
            ShowTable.clearSelection();
            ShowTable.setRowSelectionInterval(row, row);
        }
    }

    public int GetSelectedRowQty() {
        int maxrow;
        int currow;
        int SelectedQty = 0;
        maxrow = ShowTable.getRowCount();
        if (maxrow > 0) {
            for (int i = 0; i < maxrow; i++) {
                if (ShowTable.isRowSelected(i)) {
                    currow = i;
                    SelectedQty = (Integer) ShowTable.getValueAt(currow, 3);
                }
            }
        } else {
        }
        return SelectedQty;
    }

    public void SelectRow() {
    }

    public void gotocupon() {

    }

    public void txtCuQtyExit() {
        txtCuQty.setText("");
        txtCuQty.requestFocus(false);
        txtCucode.requestFocus(true);
    }

    public boolean ChkSaleTypeOK(String SaleType, String StrSaleType) {
        return StrSaleType.indexOf(SaleType) >= 0;
    }

    public void bntOKClick() {
        Boolean ProcessOK = true;

        CuponControl cCon = new CuponControl();

        BalanceControl bCon = new BalanceControl();
        ArrayList<BalanceBean> listBalance = bCon.getAllBalancePromotion(tableNo);

        //Check Cupon OK
        Double TotalSumAmt = 0.0;
        Double TotalDisc;
        Double BCuponDisc;
        Double BCuponAmt;
        Double SumDiscBath = 0.0;
        for (int i = 0; i < model2.getRowCount(); i++) {
            int CuCount;
            int CuQty = Integer.parseInt(model2.getValueAt(i, 3).toString());
            String cuCode = model2.getValueAt(i, 1).toString();

            if (CuQty > 0) {
                Double DiscPer;
                Double DiscBath;
                CuCount = CuQty;

                CuponBean cuponBean = cCon.getCupon(cuCode);

                if (cuponBean.getChkMember().equals("Y")) { //Check For Member 
                    if (cuponBean.getCuType().equals("A")) { //Check Coupon Type A
                        BCuponDisc = 0.0;
                        BCuponAmt = 0.0;
                        DiscPer = cuponBean.getCuDisc();
                        DiscBath = cuponBean.getCuDiscBath();
                        if (DiscPer != 0) {
                            for (int j = 0; j < listBalance.size(); j++) {
                                BalanceBean bBean = (BalanceBean) listBalance.get(j);

                                Double NumCanDisc = bBean.getR_Quan();
                                Double NumDisc;
                                if (NumCanDisc >= CuCount) {
                                    NumDisc = (double) CuCount;
                                } else {
                                    NumDisc = NumCanDisc;
                                }

                                TotalSumAmt = bBean.getR_Price() * NumDisc;

                                bBean.setR_PrCuType("-C");
                                bBean.setR_PrCuCode(cuponBean.getCuCode());
                                bBean.setR_PrCuQuan(NumDisc);
                                bBean.setR_PrCuAmt((bBean.getR_Price() * NumDisc * DiscPer) / 100);
                                bBean.setR_PrCuDisc((bBean.getR_PrCuAmt() / TotalSumAmt) * 100);
                                bBean.setR_Total(TotalSumAmt);

                                if (NumDisc <= bBean.getR_QuanCanDisc()) {
                                    bBean.setR_QuanCanDisc(bBean.getR_QuanCanDisc() - NumDisc);
                                } else {
                                    if (NumDisc <= bBean.getR_QuanCanDisc() + bBean.getR_PrSubQuan()) {
                                        double R_QuanCanDisc = bBean.getR_QuanCanDisc() + bBean.getR_PrSubQuan() - NumDisc;
                                        bBean.setR_QuanCanDisc(R_QuanCanDisc);

                                        bBean.setR_PrSubType("");
                                        bBean.setR_PrSubCode("");
                                        bBean.setR_PrSubQuan(0.00);
                                        bBean.setR_PrSubDisc(0.00);
                                        bBean.setR_PrSubAmt(0.00);
                                    } else {
                                        double R_QuanCanDisc = bBean.getR_QuanCanDisc() + bBean.getR_PrSubQuan() + bBean.getR_PrQuan() - NumDisc;
                                        bBean.setR_QuanCanDisc(R_QuanCanDisc);

                                        bBean.setR_PrSubType("");
                                        bBean.setR_PrSubCode("");
                                        bBean.setR_PrSubQuan(0.00);
                                        bBean.setR_PrSubDisc(0.00);
                                        bBean.setR_PrSubAmt(0.00);

                                        bBean.setR_PrQuan(0.00);
                                        bBean.setR_PrDisc(0.00);
                                        bBean.setR_PrAmt(0.00);
                                    }
                                }
                                CuCount = (int) (CuCount - NumDisc);
                                BCuponDisc = BCuponDisc + ((bBean.getR_Price() * NumDisc * DiscPer) / 100);
                                BCuponAmt = BCuponAmt + (bBean.getR_Price() * NumDisc);

                                UpdateBalance(bBean);
                            }
                        } else { //Process Disc Bath

                            if (DiscBath > 0) {
                                for (int j = 0; j < listBalance.size(); j++) {
                                    BalanceBean bBean = (BalanceBean) listBalance.get(j);

                                    Double NumCanDisc = bBean.getR_Quan();
                                    Double NumDisc;
                                    if (NumCanDisc >= CuCount) {
                                        NumDisc = (double) CuCount;
                                    } else {
                                        NumDisc = NumCanDisc;
                                    }
                                    TotalSumAmt = bBean.getR_Price() * NumDisc;
                                    bBean.setR_PrCuType("-C");
                                    bBean.setR_PrCuCode(cuponBean.getCuCode());
                                    bBean.setR_PrCuQuan(NumDisc);
                                    bBean.setR_PrCuAmt(NumDisc * DiscBath);
                                    bBean.setR_PrCuDisc((bBean.getR_PrCuAmt() / TotalSumAmt) * 100);
                                    bBean.setR_Total(NumDisc * bBean.getR_Price());

                                    if (NumDisc <= bBean.getR_QuanCanDisc()) {
                                        double R_QuanCanDisc = bBean.getR_QuanCanDisc() - NumDisc;
                                        bBean.setR_QuanCanDisc(R_QuanCanDisc);
                                    } else {
                                        if (NumDisc <= bBean.getR_QuanCanDisc() + bBean.getR_PrSubQuan()) {
                                            double R_QuanCanDisc = bBean.getR_QuanCanDisc() + bBean.getR_PrSubQuan() - NumDisc;
                                            bBean.setR_QuanCanDisc(R_QuanCanDisc);

                                            bBean.setR_PrSubType("");
                                            bBean.setR_PrSubCode("");
                                            bBean.setR_PrSubQuan(0.00);
                                            bBean.setR_PrSubDisc(0.00);
                                            bBean.setR_PrSubAmt(0.00);
                                        } else {
                                            double R_QuanCanDisc = bBean.getR_QuanCanDisc() + bBean.getR_PrSubQuan() + bBean.getR_PrQuan() - NumDisc;
                                            bBean.setR_QuanCanDisc(R_QuanCanDisc);
                                            bBean.setR_PrSubType("");
                                            bBean.setR_PrSubCode("");
                                            bBean.setR_PrSubQuan(0.00);
                                            bBean.setR_PrSubDisc(0.00);
                                            bBean.setR_PrSubAmt(0.00);

                                            bBean.setR_PrQuan(0.00);
                                            bBean.setR_PrDisc(0.00);
                                            bBean.setR_PrAmt(0.00);
                                        }
                                    }
                                    CuCount = (int) (CuCount - NumDisc);
                                    BCuponDisc = BCuponDisc + (NumDisc * DiscBath);
                                    BCuponAmt = BCuponAmt + (bBean.getR_Price() * NumDisc);

                                    UpdateBalance(bBean);
                                }

                            }
                        }
                    } else { //For Cupon Type B

                        BCuponDisc = 0.0;
                        BCuponAmt = 0.0;
                        DiscPer = cuponBean.getCuDisc();
                        DiscBath = cuponBean.getCuDiscBath();
                        if (DiscPer != 0) {
                            for (int j = 0; j < listBalance.size(); j++) {
                                BalanceBean bBean = (BalanceBean) listBalance.get(j);
                                Double NumCanDisc = bBean.getR_Quan();
                                Double NumDisc = NumCanDisc;
                                TotalSumAmt = bBean.getR_Price() * NumDisc;

                                bBean.setR_PrCuType("-C");
                                bBean.setR_PrCuCode(cuponBean.getCuCode());
                                bBean.setR_PrCuQuan(NumDisc);
                                bBean.setR_PrCuAmt((bBean.getR_Price() * NumDisc * DiscPer) / 100);
                                bBean.setR_PrCuDisc((bBean.getR_PrCuAmt() / TotalSumAmt) * 100);
                                bBean.setR_Total(TotalSumAmt);
                                //End Of Add Detail

                                if (NumDisc <= bBean.getR_QuanCanDisc()) {
                                    bBean.setR_QuanCanDisc(bBean.getR_QuanCanDisc() - NumDisc);
                                } else {
                                    if (NumDisc <= bBean.getR_QuanCanDisc() + bBean.getR_PrSubQuan()) {
                                        double R_QuanCanDisc = bBean.getR_QuanCanDisc() + bBean.getR_PrSubQuan() - NumDisc;
                                        bBean.setR_QuanCanDisc(R_QuanCanDisc);
                                        bBean.setR_PrSubType("");
                                        bBean.setR_PrSubCode("");
                                        bBean.setR_PrSubQuan(0.00);
                                        bBean.setR_PrSubDisc(0.00);
                                        bBean.setR_PrSubAmt(0.00);
                                    } else {
                                        double R_QuanCanDisc = bBean.getR_QuanCanDisc() + bBean.getR_PrSubQuan() + bBean.getR_PrQuan() - NumDisc;
                                        bBean.setR_QuanCanDisc(R_QuanCanDisc);
                                        bBean.setR_PrSubType("");
                                        bBean.setR_PrSubCode("");
                                        bBean.setR_PrSubQuan(0.00);
                                        bBean.setR_PrSubDisc(0.00);
                                        bBean.setR_PrSubAmt(0.00);

                                        bBean.setR_PrQuan(0.00);
                                        bBean.setR_PrDisc(0.00);
                                        bBean.setR_PrAmt(0.00);
                                    }
                                }
                                BCuponDisc = BCuponDisc + ((bBean.getR_Price() * NumDisc * DiscPer) / 100);
                                BCuponAmt = BCuponAmt + (bBean.getR_Price() * NumDisc);

                                UpdateBalance(bBean);
                            }
                        } else { //Process Disc Bath

                            if (DiscBath > 0) {
                                SumDiscBath = SumDiscBath + DiscBath;
                                TotalSumAmt = 0.0;
                                TotalDisc = 0.0;
                                for (int j = 0; j < listBalance.size(); j++) {
                                    BalanceBean bBean = (BalanceBean) listBalance.get(j);
                                    if ((!bBean.getR_Void().equals("V")) & (bBean.getR_Total() > 0)) {
                                        TotalSumAmt = TotalSumAmt + (bBean.getR_Total());
                                        TotalDisc = bBean.getR_PrAmt() + bBean.getR_PrSubAmt() + bBean.getR_PrCuAmt() + bBean.getR_PrCuBath() + bBean.getR_PrCuAdj();
                                    }
                                }
                                if (TotalSumAmt + PublicVar.TableRec_ServiceAmt - TotalDisc >= SumDiscBath) {
                                    for (int j = 0; j < listBalance.size(); j++) {
                                        BalanceBean bBean = (BalanceBean) listBalance.get(j);

                                        if ((!bBean.getR_Void().equals("V")) & (bBean.getR_Total() > 0)) {
                                            bBean.setR_PrCuType("-C");
                                            bBean.setR_PrCuBath(bBean.getR_PrCuBath() + ((bBean.getR_Total() / TotalSumAmt) * DiscBath));
                                            BCuponDisc = BCuponDisc + ((bBean.getR_Total() / TotalSumAmt) * DiscBath);
                                            BCuponAmt = 0.0;
                                        }

                                        UpdateBalance(bBean);
                                    }
                                }
                            }
                        }

                    }

                } else {
                    if (cuponBean.getCuType().equals("A")) {
                        BCuponDisc = 0.0;
                        BCuponAmt = 0.0;
                        DiscPer = cuponBean.getCuDisc();
                        DiscBath = cuponBean.getCuDiscBath();
                        if (DiscPer != 0) {
                            for (int j = 0; j < listBalance.size(); j++) {
                                BalanceBean bBean = (BalanceBean) listBalance.get(j);

                                Double NumCanDisc = bBean.getR_QuanCanDisc();
                                Double NumDisc;
                                if (NumCanDisc >= CuCount) {
                                    NumDisc = (double) CuCount;
                                } else {
                                    NumDisc = NumCanDisc;
                                }
                                TotalSumAmt = bBean.getR_Price() * (bBean.getR_PrCuQuan() + NumDisc);
                                bBean.setR_PrCuType("-C");
                                bBean.setR_PrCuCode(cuponBean.getCuCode());
                                bBean.setR_PrCuQuan(bBean.getR_PrCuQuan() + NumDisc);
                                bBean.setR_PrCuAmt(bBean.getR_PrCuAmt() + ((bBean.getR_Price() * NumDisc * DiscPer) / 100));
                                bBean.setR_PrCuDisc((bBean.getR_PrCuAmt() / TotalSumAmt) * 100);
                                bBean.setR_QuanCanDisc(bBean.getR_QuanCanDisc() - NumDisc);
                                bBean.setR_Total(TotalSumAmt);

                                CuCount = (int) (CuCount - NumDisc);
                                BCuponDisc = BCuponDisc + ((bBean.getR_Price() * NumDisc * DiscPer) / 100);
                                BCuponAmt = BCuponAmt + (bBean.getR_Price() * NumDisc);

                                UpdateBalance(bBean);
                            }
                        } else {
                            if (DiscBath > 0) {
                                for (int j = 0; j < listBalance.size(); j++) {
                                    BalanceBean bBean = (BalanceBean) listBalance.get(j);

                                    Double NumCanDisc = bBean.getR_QuanCanDisc();
                                    Double NumDisc;
                                    if (NumCanDisc >= CuCount) {
                                        NumDisc = (double) CuCount;
                                    } else {
                                        NumDisc = NumCanDisc;
                                    }
                                    TotalSumAmt = bBean.getR_Price() * (bBean.getR_PrCuQuan() + NumDisc);
                                    bBean.setR_PrCuType("-C");
                                    bBean.setR_PrCuCode(cuponBean.getCuCode());
                                    bBean.setR_PrCuQuan(bBean.getR_PrCuQuan() + NumDisc);
                                    bBean.setR_PrCuAmt(bBean.getR_PrCuAmt() + (NumDisc * DiscBath));
                                    bBean.setR_PrCuDisc((bBean.getR_PrCuAmt() / TotalSumAmt) * 100);
                                    bBean.setR_QuanCanDisc(bBean.getR_QuanCanDisc() - NumDisc);
                                    bBean.setR_Total(TotalSumAmt);

                                    //End Of Add Detail
                                    CuCount = (int) (CuCount - NumDisc);
                                    BCuponDisc = BCuponDisc + (NumDisc * DiscBath);
                                    BCuponAmt = BCuponAmt + (NumDisc * bBean.getR_Price());

                                    UpdateBalance(bBean);
                                }
                            }
                        }
                    } else {
                        BCuponDisc = 0.0;
                        BCuponAmt = 0.0;
                        DiscBath = cuponBean.getCuDiscBath();
                        DiscPer = cuponBean.getCuDisc();
                        if (DiscBath > 0) {
                            DiscBath = DiscBath * CuCount;
                        }
                        if (DiscPer != 0) {
                            for (int j = 0; j < listBalance.size(); j++) {
                                BalanceBean bBean = (BalanceBean) listBalance.get(j);
                                Double NumCanDisc = bBean.getR_QuanCanDisc();
                                Double NumDisc = NumCanDisc;
                                TotalSumAmt = TotalSumAmt + (bBean.getR_Price() * (bBean.getR_PrCuQuan() + NumDisc));
                                bBean.setR_PrCuType("-C");
                                bBean.setR_PrCuCode(cuponBean.getCuCode());
                                bBean.setR_PrCuQuan(bBean.getR_PrCuQuan() + NumDisc);
                                bBean.setR_PrCuAmt(bBean.getR_PrCuAmt() + ((bBean.getR_Price() * NumDisc * DiscPer) / 100));
                                bBean.setR_PrCuDisc((bBean.getR_PrCuAmt() / TotalSumAmt) * 100);
                                bBean.setR_QuanCanDisc(bBean.getR_QuanCanDisc() - NumDisc);
                                bBean.setR_Total(TotalSumAmt);

                                BCuponDisc = BCuponDisc + ((bBean.getR_Price() * NumDisc * DiscPer) / 100);
                                BCuponAmt = BCuponAmt + (bBean.getR_Price() * NumDisc);

                                UpdateBalance(bBean);
                            }

                        } else {
                            if (DiscBath > 0) {
                                SumDiscBath = SumDiscBath + DiscBath;
                                TotalSumAmt = 0.0;
                                TotalDisc = 0.0;
                                for (int j = 0; j < listBalance.size(); j++) {
                                    BalanceBean bBean = (BalanceBean) listBalance.get(j);
                                    if ((!bBean.getR_Void().equals("V")) & (bBean.getR_Total() > 0)) {
                                        TotalSumAmt = TotalSumAmt + (bBean.getR_Total());
                                        TotalDisc = TotalDisc + (bBean.getR_PrAmt() + bBean.getR_PrCuAmt() + bBean.getR_PrSubAmt());
                                    }

                                    UpdateBalance(bBean);
                                }
                                if (TotalSumAmt + PublicVar.TableRec_ServiceAmt - TotalDisc >= SumDiscBath) {
                                    for (int j = 0; j < listBalance.size(); j++) {
                                        BalanceBean bBean = (BalanceBean) listBalance.get(j);

                                        bBean.setR_PrCuType("-C");
                                        bBean.setR_PrCuBath(bBean.getR_PrCuBath() + ((bBean.getR_Total() / TotalSumAmt) * DiscBath));
                                        BCuponDisc = BCuponDisc + ((bBean.getR_Total() / TotalSumAmt) * DiscBath);
                                        BCuponAmt = 0.0;

                                        bBean.setR_PrCuCode(cuponBean.getCuCode());
                                        bBean.setR_PrCuQuan(0.00);
                                        bBean.setR_Total(bBean.getR_Quan() * bBean.getR_Price());
                                        bBean.setR_PrCuAmt(0.00);
                                        bBean.setR_PrCuDisc(0.00);

                                        UpdateBalance(bBean);
                                    }
                                }
                            }

                        }
                    }
                }

                double CuTotal = 0.00;
                double CuPerDisc = 0.00;
                double CuAmt = 0.00;

                if (cuponBean.getCuType().equals("B")) {
                    if (BCuponDisc == 0) {
                        PUtility.ShowMsg("คูปอง " + cuponBean.getCuCode() + " " + cuponBean.getCuName() + " ไม่สามารถให้ส่วนลดได้...");
                        ProcessOK = false;
                        txtCucode.requestFocus(true);

                    } else {
                        if ((BCuponAmt > 0) & (BCuponDisc > 0)) {
                            CuTotal = BCuponAmt;
                            CuPerDisc = ((BCuponDisc / BCuponAmt) * 100);
                        } else {
                            CuTotal = 0.0;
                            CuPerDisc = 0.0;
                        }

                        DecimalFormat DecFmtCu = new DecimalFormat("########0.0000");
                        String TempCu = DecFmtCu.format(BCuponDisc);
                        BCuponDisc = Double.parseDouble(TempCu);
                        CuAmt = PUtility.RoundDecimal(BCuponDisc, CONFIG.getP_DiscRound());
                    }

                } else { //Process Cupon A

                    if (CuCount > 0) {
                        PUtility.ShowMsg("คูปอง " + cuponBean.getCuCode() + " " + cuponBean.getCuName() + " จำนวนบัตรคูปองพิเศษ มากกว่ารายการที่สามารถลดได้...");
                        ProcessOK = false;
                        txtCucode.requestFocus(true);
                        //ShowTable.requestFocus();
                    } else {
                        if ((BCuponAmt > 0) & (BCuponDisc > 0)) {
                            CuTotal = BCuponAmt;
                            CuPerDisc = ((BCuponDisc / BCuponAmt) * 100);
                        } else {
                            CuTotal = 0.0;
                            CuPerDisc = 0.0;
                        }
                        DecimalFormat DecFmtCu = new DecimalFormat("########0.0000");
                        String TempCu = DecFmtCu.format(BCuponDisc);
                        BCuponDisc = Double.parseDouble(TempCu);
                        CuAmt = PUtility.RoundDecimal(BCuponDisc, CONFIG.getP_DiscRound());

                    }
                }
            }

            //Save Coupon Array To File
            if (ProcessOK) {
                this.dispose();
            }
        }
    }

    public Boolean ChkPluOK(String PluCode, String CuCode) {
        Boolean RetVal = false;
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SeekCuList = "select *from cuponlist where (cucode='" + CuCode + "') and (pcode='" + PluCode + "')";

            ResultSet rec = stmt.executeQuery(SeekCuList);
            rec.first();
            int Cnt = 0;
            RetVal = rec.getRow() != 0;

            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }

        return RetVal;
    }

    public void ClearTempCupon(String PTable) {

        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SQLQuery = "delete from tempcupon where (r_table='" + PTable + "')";
            stmt.executeUpdate(SQLQuery);
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
    }

    public void ClearTempCuponDetail(String PTable) {
    }

    public void UpdateBalance(BalanceBean bBean) {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SQLQuery = "update balance set "
                    + "r_prtype='" + bBean.getR_PrType() + "',"
                    + "r_prcode='" + bBean.getR_PrCode() + "',r_prquan=" + bBean.getR_PrQuan() + ","
                    + "r_prdisc=" + bBean.getR_PrDisc() + ",r_pramt=" + bBean.getR_PrAmt() + ","
                    + "r_prsubtype='" + bBean.getR_PrSubType() + "',r_prsubcode='" + bBean.getR_PrSubCode() + "',"
                    + "r_prsubdisc=" + bBean.getR_PrSubDisc() + ",r_prsubamt=" + bBean.getR_PrSubAmt() + ","
                    + "r_prsubquan=" + bBean.getR_PrSubQuan() + ","
                    + "r_prcutype='" + bBean.getR_PrCuType() + "',r_prcucode='" + bBean.getR_PrCuCode() + "',"
                    + "r_prcuquan=" + bBean.getR_PrCuQuan() + ",r_prcudisc=" + bBean.getR_PrCuDisc() + ","
                    + "r_prcubath=" + bBean.getR_PrCuBath() + ",r_prcuamt=" + bBean.getR_PrCuAmt() + ","
                    + "r_quancandisc=" + bBean.getR_QuanCanDisc() + " where r_index='" + bBean.getR_Index() + "'";
            stmt.executeUpdate(SQLQuery);
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
    }

    public static void main(String args[]) {
        new MySQLConnect();
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                CouponDiscount dialog = new CouponDiscount(new javax.swing.JFrame(), true, "");
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
    private javax.swing.JTable ShowTable;
    private javax.swing.JButton bntExit;
    private javax.swing.JButton bntOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField txtCuQty;
    private javax.swing.JTextField txtCucode;
    // End of variables declaration//GEN-END:variables

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
}
