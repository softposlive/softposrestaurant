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
import database.MySQLConnect;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import program.Jdi_MTDdepReport;
import program.POSHWSetup;
import printReport.PPrint;
import program.PUtility;
import program.PluRec;
import program.PublicVar;
import program.Value;
import soft.virtual.KeyBoardDialog;
import util.DateChooseDialog;
import util.MSG;

public class MTDDept extends javax.swing.JDialog {

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

    /** Creates new form MTDDept */
    public MTDDept(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtDate1.setText(DatefmtShow.format(date));
        txtDate2.setText(DatefmtShow.format(date));
        txtMacNo1.setText("");
        txtMacNo2.setText("");
        InitScreen();
        
        POSHW = POSHWSetup.Bean(Value.getMacno());
    }

    public void InitScreen() {
        
        txtDate1.requestFocus();
    }

    public void inputfrombnt(String str) {
        if (txtMacNo1.hasFocus()) {
            String tempstr = "";
            tempstr = txtMacNo1.getText();
            tempstr = tempstr + str;
            txtMacNo1.setText(tempstr);
        }
        if (txtMacNo2.hasFocus()) {
            String tempstr = "";
            tempstr = txtMacNo2.getText();
            tempstr = tempstr + str;
            txtMacNo2.setText(tempstr);
        }
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
        Action() ;
    }

    public void ProcessProc() {
        String MacNo1 = txtMacNo1.getText();
        String MacNo2 = txtMacNo2.getText();
        String TempGroup = "";
        int ArraySize = 0;
        Boolean Found = false;
        PluRec[] GArray;
        GArray = new PluRec[1];
        ArraySize = 0;

        try {
            Statement stmt =  MySQLConnect.con.createStatement();
            String SqlQuery = "select s_date,s_dept,sum(e_qty),sum(e_amt),sum(t_qty),sum(t_amt),sum(d_qty),sum(d_amt),sum(p_qty),sum(p_amt),sum(w_qty),sum(w_amt),sum(s_qty),sum(s_amt) from s_sale " +
                    "where (s_date>='" + Datefmt.format(TDate1) + "') and (s_date<='" + Datefmt.format(TDate2) + "') and (s_dept>='" + txtMacNo1.getText() + "') and (s_dept<='" + txtMacNo2.getText() + "') group by s_dept order by s_dept";

            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            TempGroup = "";
            Double SumEQty = 0.0;
            Double SumEAmt = 0.0;
            Double SumTQty = 0.0;
            Double SumTAmt = 0.0;
            Double SumDQty = 0.0;
            Double SumDAmt = 0.0;
            Double SumPQty = 0.0;
            Double SumPAmt = 0.0;
            Double SumWQty = 0.0;
            Double SumWAmt = 0.0;
            Double SumSQty = 0.0;
            Double SumSAmt = 0.0;
            if (rec.getRow() == 0) {
            } else {
                TempGroup = rec.getString("s_dept");
                do {
                    Found = true;
                    if (!TempGroup.equals(rec.getString("s_dept"))) {
                        PluRec GroupRec = new PluRec();
                        GroupRec.MacNo1 = MacNo1;
                        GroupRec.MacNo2 = MacNo2;
                        GroupRec.Cashier1 = "";
                        GroupRec.Cashier2 = "";
                        GroupRec.Group1 = txtMacNo1.getText();
                        GroupRec.Group2 = txtMacNo2.getText();
                        GroupRec.Plu1 = "";
                        GroupRec.Plu2 = "";
                        GroupRec.GroupCode = TempGroup;
                        GroupRec.GroupName = PUtility.SeekGroupName(TempGroup);
                        GroupRec.PCode = "";
                        GroupRec.PName = "";

                        GroupRec.E_Qty = GroupRec.E_Qty + SumEQty;
                        GroupRec.E_Amt = GroupRec.E_Amt + SumEAmt;


                        GroupRec.T_Qty = GroupRec.T_Qty + SumTQty;
                        GroupRec.T_Amt = GroupRec.T_Amt + SumTAmt;


                        GroupRec.D_Qty = GroupRec.D_Qty + SumDQty;
                        GroupRec.D_Amt = GroupRec.D_Amt + SumDAmt;


                        GroupRec.P_Qty = GroupRec.P_Qty + SumPQty;
                        GroupRec.P_Amt = GroupRec.P_Amt + SumPAmt;


                        GroupRec.W_Qty = GroupRec.W_Qty + SumWQty;
                        GroupRec.W_Amt = GroupRec.W_Amt + SumWAmt;

                        GroupRec.S_Qty = GroupRec.S_Qty + SumSQty;
                        GroupRec.S_Amt = GroupRec.S_Amt + SumSAmt;
                        if (ArraySize == 0) {
                            GArray[ArraySize] = GroupRec;
                            ArraySize = GArray.length;
                        } else {
                            GArray = PUtility.addPluArray(GArray);
                            ArraySize = GArray.length;
                            GArray[ArraySize - 1] = GroupRec;
                        }
                        TempGroup = rec.getString("s_dept");
                        SumEQty = 0.0;
                        SumEAmt = 0.0;
                        SumTQty = 0.0;
                        SumTAmt = 0.0;
                        SumDQty = 0.0;
                        SumDAmt = 0.0;
                        SumPQty = 0.0;
                        SumPAmt = 0.0;
                        SumWQty = 0.0;
                        SumWAmt = 0.0;
                        SumSQty = 0.0;
                        SumSAmt = 0.0;
                    }

                    SumEQty = SumEQty + rec.getDouble("sum(e_qty)");
                    SumEAmt = SumEAmt + rec.getDouble("sum(e_amt)");


                    SumTQty = SumTQty + rec.getDouble("sum(t_qty)");
                    SumTAmt = SumTAmt + rec.getDouble("sum(t_amt)");


                    SumDQty = SumDQty + rec.getDouble("sum(d_qty)");
                    SumDAmt = SumDAmt + rec.getDouble("sum(d_amt)");


                    SumPQty = SumPQty + rec.getDouble("sum(p_qty)");
                    SumPAmt = SumPAmt + rec.getDouble("sum(p_amt)");


                    SumWQty = SumWQty + rec.getDouble("sum(w_qty)");
                    SumWAmt = SumWAmt + rec.getDouble("sum(w_amt)");

                    SumSQty = SumSQty + rec.getDouble("sum(s_qty)");
                    SumSAmt = SumSAmt + rec.getDouble("sum(s_amt)");
                } while (rec.next());
                if (SumSQty > 0) {
                    PluRec GroupRec = new PluRec();
                        GroupRec.MacNo1 = MacNo1;
                        GroupRec.MacNo2 = MacNo2;
                        GroupRec.Cashier1 = "";
                        GroupRec.Cashier2 = "";
                        GroupRec.Group1 = txtMacNo1.getText();
                        GroupRec.Group2 = txtMacNo2.getText();
                        GroupRec.Plu1 = "";
                        GroupRec.Plu2 = "";
                        GroupRec.GroupCode = TempGroup;
                        GroupRec.GroupName = PUtility.SeekGroupName(TempGroup);
                        GroupRec.PCode = "";
                        GroupRec.PName = "";

                        GroupRec.E_Qty = GroupRec.E_Qty + SumEQty;
                        GroupRec.E_Amt = GroupRec.E_Amt + SumEAmt;


                        GroupRec.T_Qty = GroupRec.T_Qty + SumTQty;
                        GroupRec.T_Amt = GroupRec.T_Amt + SumTAmt;


                        GroupRec.D_Qty = GroupRec.D_Qty + SumDQty;
                        GroupRec.D_Amt = GroupRec.D_Amt + SumDAmt;


                        GroupRec.P_Qty = GroupRec.P_Qty + SumPQty;
                        GroupRec.P_Amt = GroupRec.P_Amt + SumPAmt;


                        GroupRec.W_Qty = GroupRec.W_Qty + SumWQty;
                        GroupRec.W_Amt = GroupRec.W_Amt + SumWAmt;

                        GroupRec.S_Qty = GroupRec.S_Qty + SumSQty;
                        GroupRec.S_Amt = GroupRec.S_Amt + SumSAmt;
                        if (ArraySize == 0) {
                            GArray[ArraySize] = GroupRec;
                            ArraySize = GArray.length;
                        } else {
                            GArray = PUtility.addPluArray(GArray);
                            ArraySize = GArray.length;
                            GArray[ArraySize - 1] = GroupRec;
                        }
                    
                }
            }

            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
        PrintGroup(GArray, Found);
        InitScreen();
    }

    public void PrintGroup(PluRec[] GArray, Boolean Found) {
        Double SumEQty = 0.0;
        Double SumEAmt = 0.0;
        Double SumTQty = 0.0;
        Double SumTAmt = 0.0;
        Double SumDQty = 0.0;
        Double SumDAmt = 0.0;
        Double SumPQty = 0.0;
        Double SumPAmt = 0.0;
        Double SumWQty = 0.0;
        Double SumWAmt = 0.0;
        Double SumSQty = 0.0;
        Double SumSAmt = 0.0;
        int ArraySize = GArray.length;
        if(Value.printdriver){
            
        }else if (!Value.getComPort().equals("NONE")) {
            if (prn.OpenPrint(Value.getComPort())) {
                prn.InitPrinter();
                prn.print(POSHW.getHeading1());
                prn.print(POSHW.getHeading2());
                prn.print(POSHW.getHeading3());
                prn.print(POSHW.getHeading4());
                prn.print("REG ID :" + Value.MACNO);
                prn.print("         รายงานการขายตามกลุ่มสินค้า");
                prn.print("        (MTD Department Report)");
                prn.print("ช่วงวันที่ : " + DatefmtShow.format(TDate1) + "..." + DatefmtShow.format(TDate2));
                prn.print("รหัสกลุ่มสินค้า (Dept/Group) " + txtMacNo1.getText() + "..." + txtMacNo2.getText());
                prn.print(" ");
                Date dateP = new Date();
                prn.print(DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                prn.print("----------------------------------------");
                prn.print("รายละเอียด");
                prn.print("    .....EAT IN.....   ...TAKE AWAY.....");
                prn.print("    ....DELIVERY....   .....PINTO.......");
                prn.print("    ...WHOLE SALE...   .....TOTAL.......");
                prn.print("----------------------------------------");
                if (Found) {
                    for (int i = 0; i < ArraySize; i++) {

                        prn.print(PUtility.DataFullR(GArray[i].GroupCode, 4) + "  " + GArray[i].GroupName);
                        prn.print(PUtility.DataFull(IntFmt.format(GArray[i].E_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].E_Amt), 13) + PUtility.DataFull(IntFmt.format(GArray[i].T_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].T_Amt), 13));
                        prn.print(PUtility.DataFull(IntFmt.format(GArray[i].D_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].D_Amt), 13) + PUtility.DataFull(IntFmt.format(GArray[i].P_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].P_Amt), 13));
                        prn.print(PUtility.DataFull(IntFmt.format(GArray[i].W_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].W_Amt), 13) + PUtility.DataFull(IntFmt.format(GArray[i].S_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].S_Amt), 13));

                        SumEQty = SumEQty + GArray[i].E_Qty;
                        SumEAmt = SumEAmt + GArray[i].E_Amt;
                        SumTQty = SumTQty + GArray[i].T_Qty;
                        SumTAmt = SumTAmt + GArray[i].T_Amt;
                        SumDQty = SumDQty + GArray[i].D_Qty;
                        SumDAmt = SumDAmt + GArray[i].D_Amt;
                        SumPQty = SumPQty + GArray[i].P_Qty;
                        SumPAmt = SumPAmt + GArray[i].P_Amt;
                        SumWQty = SumWQty + GArray[i].W_Qty;
                        SumWAmt = SumWAmt + GArray[i].W_Amt;
                        SumSQty = SumSQty + GArray[i].S_Qty;
                        SumSAmt = SumSAmt + GArray[i].S_Amt;
                    }
                }

                prn.print("----------------------------------------");
                prn.print(PUtility.DataFull(IntFmt.format(SumEQty), 6) + PUtility.DataFull(DecFmt.format(SumEAmt), 13) + PUtility.DataFull(IntFmt.format(SumTQty), 6) + PUtility.DataFull(DecFmt.format(SumTAmt), 13));
                prn.print(PUtility.DataFull(IntFmt.format(SumDQty), 6) + PUtility.DataFull(DecFmt.format(SumDAmt), 13) + PUtility.DataFull(IntFmt.format(SumPQty), 6) + PUtility.DataFull(DecFmt.format(SumPAmt), 13));
                prn.print(PUtility.DataFull(IntFmt.format(SumWQty), 6) + PUtility.DataFull(DecFmt.format(SumWAmt), 13) + PUtility.DataFull(IntFmt.format(SumSQty), 6) + PUtility.DataFull(DecFmt.format(SumSAmt), 13));

                prn.print("----------------------------------------");
                prn.print(" ");
                prn.print(" ");
                prn.print(" ");
                
                prn.CutPaper();
                prn.closePrint();
            }
        }
    }
    public void bntViewClick() {
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
            ProcessProc2();
            InitScreen();
        } catch (ParseException ex) {
            MSG.ERR(this, "กรุณาป้อนวันที่ให้ถูกต้อง (Format=dd/MM/yyyy EXP 01/01/2009)");
            Logger.getLogger(MTDTerminal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void ProcessProc2() {
        String MacNo1 = txtMacNo1.getText();
        String MacNo2 = txtMacNo2.getText();
        String TempGroup = "";
        int ArraySize = 0;
        Boolean Found = false;
        PluRec[] GArray;
        GArray = new PluRec[1];
        ArraySize = 0;

        try {
            Statement stmt =  MySQLConnect.con.createStatement();
            String SqlQuery = "select s_date,s_dept,sum(e_qty),sum(e_amt),sum(t_qty),sum(t_amt),sum(d_qty),sum(d_amt),sum(p_qty),sum(p_amt),sum(w_qty),sum(w_amt),sum(s_qty),sum(s_amt) from s_sale " +
                    "where (s_date>='" + Datefmt.format(TDate1) + "') and (s_date<='" + Datefmt.format(TDate2) + "') and (s_dept>='" + txtMacNo1.getText() + "') and (s_dept<='" + txtMacNo2.getText() + "') group by s_dept order by s_dept";

            ResultSet rec = stmt.executeQuery(SqlQuery);
            rec.first();
            TempGroup = "";
            Double SumEQty = 0.0;
            Double SumEAmt = 0.0;
            Double SumTQty = 0.0;
            Double SumTAmt = 0.0;
            Double SumDQty = 0.0;
            Double SumDAmt = 0.0;
            Double SumPQty = 0.0;
            Double SumPAmt = 0.0;
            Double SumWQty = 0.0;
            Double SumWAmt = 0.0;
            Double SumSQty = 0.0;
            Double SumSAmt = 0.0;
            if (rec.getRow() == 0) {
            } else {
                TempGroup = rec.getString("s_dept");
                do {
                    Found = true;
                    if (!TempGroup.equals(rec.getString("s_dept"))) {
                        PluRec GroupRec = new PluRec();
                        GroupRec.MacNo1 = MacNo1;
                        GroupRec.MacNo2 = MacNo2;
                        GroupRec.Cashier1 = "";
                        GroupRec.Cashier2 = "";
                        GroupRec.Group1 = txtMacNo1.getText();
                        GroupRec.Group2 = txtMacNo2.getText();
                        GroupRec.Plu1 = "";
                        GroupRec.Plu2 = "";
                        GroupRec.GroupCode = TempGroup;
                        GroupRec.GroupName = PUtility.SeekGroupName(TempGroup);
                        GroupRec.PCode = "";
                        GroupRec.PName = "";

                        GroupRec.E_Qty = GroupRec.E_Qty + SumEQty;
                        GroupRec.E_Amt = GroupRec.E_Amt + SumEAmt;


                        GroupRec.T_Qty = GroupRec.T_Qty + SumTQty;
                        GroupRec.T_Amt = GroupRec.T_Amt + SumTAmt;


                        GroupRec.D_Qty = GroupRec.D_Qty + SumDQty;
                        GroupRec.D_Amt = GroupRec.D_Amt + SumDAmt;


                        GroupRec.P_Qty = GroupRec.P_Qty + SumPQty;
                        GroupRec.P_Amt = GroupRec.P_Amt + SumPAmt;


                        GroupRec.W_Qty = GroupRec.W_Qty + SumWQty;
                        GroupRec.W_Amt = GroupRec.W_Amt + SumWAmt;

                        GroupRec.S_Qty = GroupRec.S_Qty + SumSQty;
                        GroupRec.S_Amt = GroupRec.S_Amt + SumSAmt;
                        if (ArraySize == 0) {
                            GArray[ArraySize] = GroupRec;
                            ArraySize = GArray.length;
                        } else {
                            GArray = PUtility.addPluArray(GArray);
                            ArraySize = GArray.length;
                            GArray[ArraySize - 1] = GroupRec;
                        }
                        TempGroup = rec.getString("s_dept");
                        SumEQty = 0.0;
                        SumEAmt = 0.0;
                        SumTQty = 0.0;
                        SumTAmt = 0.0;
                        SumDQty = 0.0;
                        SumDAmt = 0.0;
                        SumPQty = 0.0;
                        SumPAmt = 0.0;
                        SumWQty = 0.0;
                        SumWAmt = 0.0;
                        SumSQty = 0.0;
                        SumSAmt = 0.0;
                    }

                    SumEQty = SumEQty + rec.getDouble("sum(e_qty)");
                    SumEAmt = SumEAmt + rec.getDouble("sum(e_amt)");


                    SumTQty = SumTQty + rec.getDouble("sum(t_qty)");
                    SumTAmt = SumTAmt + rec.getDouble("sum(t_amt)");


                    SumDQty = SumDQty + rec.getDouble("sum(d_qty)");
                    SumDAmt = SumDAmt + rec.getDouble("sum(d_amt)");


                    SumPQty = SumPQty + rec.getDouble("sum(p_qty)");
                    SumPAmt = SumPAmt + rec.getDouble("sum(p_amt)");


                    SumWQty = SumWQty + rec.getDouble("sum(w_qty)");
                    SumWAmt = SumWAmt + rec.getDouble("sum(w_amt)");

                    SumSQty = SumSQty + rec.getDouble("sum(s_qty)");
                    SumSAmt = SumSAmt + rec.getDouble("sum(s_amt)");
                } while (rec.next());
                if (SumSQty > 0) {
                    PluRec GroupRec = new PluRec();
                        GroupRec.MacNo1 = MacNo1;
                        GroupRec.MacNo2 = MacNo2;
                        GroupRec.Cashier1 = "";
                        GroupRec.Cashier2 = "";
                        GroupRec.Group1 = txtMacNo1.getText();
                        GroupRec.Group2 = txtMacNo2.getText();
                        GroupRec.Plu1 = "";
                        GroupRec.Plu2 = "";
                        GroupRec.GroupCode = TempGroup;
                        GroupRec.GroupName = PUtility.SeekGroupName(TempGroup);
                        GroupRec.PCode = "";
                        GroupRec.PName = "";

                        GroupRec.E_Qty = GroupRec.E_Qty + SumEQty;
                        GroupRec.E_Amt = GroupRec.E_Amt + SumEAmt;


                        GroupRec.T_Qty = GroupRec.T_Qty + SumTQty;
                        GroupRec.T_Amt = GroupRec.T_Amt + SumTAmt;


                        GroupRec.D_Qty = GroupRec.D_Qty + SumDQty;
                        GroupRec.D_Amt = GroupRec.D_Amt + SumDAmt;


                        GroupRec.P_Qty = GroupRec.P_Qty + SumPQty;
                        GroupRec.P_Amt = GroupRec.P_Amt + SumPAmt;


                        GroupRec.W_Qty = GroupRec.W_Qty + SumWQty;
                        GroupRec.W_Amt = GroupRec.W_Amt + SumWAmt;

                        GroupRec.S_Qty = GroupRec.S_Qty + SumSQty;
                        GroupRec.S_Amt = GroupRec.S_Amt + SumSAmt;
                        if (ArraySize == 0) {
                            GArray[ArraySize] = GroupRec;
                            ArraySize = GArray.length;
                        } else {
                            GArray = PUtility.addPluArray(GArray);
                            ArraySize = GArray.length;
                            GArray[ArraySize - 1] = GroupRec;
                        }
                    
                }
            }

            rec.close();
            stmt.close();
        } catch (SQLException e) {
            PUtility.showError(e.getMessage());
        }
       if (Found) { 
           Jdi_MTDdepReport dept = new Jdi_MTDdepReport(new Frame(),true);
           dept.setData(GArray);
           dept.setHeaderPage(txtDate1.getText(),txtDate2.getText(),MacNo1,MacNo2);
           dept.setVisible(true); 
       } else {
            MSG.ERR(this, "ไม่พบข้อมูลการขายตามช่วงที่ต้องการ...");
       }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtMacNo1 = new javax.swing.JTextField();
        txtMacNo2 = new javax.swing.JTextField();
        bntExit = new javax.swing.JButton();
        bntOK = new javax.swing.JButton();
        bntF1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtDate1 = new javax.swing.JFormattedTextField();
        txtDate2 = new javax.swing.JFormattedTextField();
        cmdDateChoose1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmdDateChoose2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("รายงานการขายตามกลุ่มสินค้า (MTD Department Report)");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray, 2), "รหัสแผนกสินค้า (Department)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        txtMacNo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMacNo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMacNo1MouseClicked(evt);
            }
        });
        txtMacNo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMacNo1KeyPressed(evt);
            }
        });

        txtMacNo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMacNo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMacNo2MouseClicked(evt);
            }
        });
        txtMacNo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMacNo2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMacNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMacNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntExit.setText("ESC- ออก");
        bntExit.setFocusable(false);
        bntExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntExitActionPerformed(evt);
            }
        });

        bntOK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntOK.setText("F5- พิมพ์");
        bntOK.setFocusable(false);
        bntOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntOKActionPerformed(evt);
            }
        });

        bntF1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bntF1.setText("F1- จอภาพ");
        bntF1.setFocusable(false);
        bntF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntF1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ช่วงวันที่ๆต้องการ (Date)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        txtDate1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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

        txtDate2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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

        jLabel4.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel4.setText("ถึง");

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
                .addGap(9, 9, 9)
                .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(cmdDateChoose2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDateChoose1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bntF1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntOK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntF1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
    } 
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
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
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
    } 
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtDate1.requestFocus();
    }
}//GEN-LAST:event_txtMacNo2KeyPressed

private void bntExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntExitActionPerformed
    bntExitClick();
}//GEN-LAST:event_bntExitActionPerformed

private void bntOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntOKActionPerformed
    bntOKClick();
}//GEN-LAST:event_bntOKActionPerformed

private void bntF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntF1ActionPerformed
// TODO add your handling code here:
       bntViewClick() ;
}//GEN-LAST:event_bntF1ActionPerformed

private void txtDate2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate2KeyPressed
if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
    } 
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        txtMacNo1.requestFocus();
    }
}//GEN-LAST:event_txtDate2KeyPressed

private void cmdDateChoose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose1ActionPerformed
Point point = cmdDateChoose2.getLocation();    
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(),true,point);    
    dcd.setVisible(true);
   // dcd.showDialog(new LookAndFeelFrame(), true, point);
    txtDate1.setText(ShowDatefmt.format(dcd.getSelectDate().getTime()));
    txtDate1.requestFocus();
}//GEN-LAST:event_cmdDateChoose1ActionPerformed

private void cmdDateChoose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDateChoose2ActionPerformed
Point point = cmdDateChoose2.getLocation();    
    point.setLocation(point.getX(), point.getY());
    DateChooseDialog dcd = new DateChooseDialog(new Frame(),true,point);    
    dcd.setVisible(true);
   // dcd.showDialog(new LookAndFeelFrame(), true, point);
    txtDate2.setText(ShowDatefmt.format(dcd.getSelectDate().getTime()));
    txtDate2.requestFocus();
}//GEN-LAST:event_cmdDateChoose2ActionPerformed

    private void txtDate1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDate1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        bntExitClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F5) {
        bntOKClick();
    }
    if (evt.getKeyCode() == KeyEvent.VK_F1) {
        bntViewClick();
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

    private void txtMacNo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMacNo1MouseClicked
        if(evt.getClickCount()==2){
            KeyBoardDialog.get(txtMacNo1);
        }
    }//GEN-LAST:event_txtMacNo1MouseClicked

    private void txtMacNo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMacNo2MouseClicked
        if(evt.getClickCount()==2){
            KeyBoardDialog.get(txtMacNo2);
        }
    }//GEN-LAST:event_txtMacNo2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                MTDDept dialog = new MTDDept(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bntF1;
    private javax.swing.JButton bntOK;
    private javax.swing.JButton cmdDateChoose1;
    private javax.swing.JButton cmdDateChoose2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JFormattedTextField txtDate1;
    private javax.swing.JFormattedTextField txtDate2;
    private javax.swing.JTextField txtMacNo1;
    private javax.swing.JTextField txtMacNo2;
    // End of variables declaration//GEN-END:variables
}
