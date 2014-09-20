package printReport;

import printReport.PrintDriver;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.MySQLConnect;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import program.BalanceBean;
import program.BalanceControl;
import program.BillControl;
import program.BillNoBean;
import program.CreditPaymentRec;
import program.CreditRec;
import program.FinalcialRec;
import program.HourlyRec;
import program.POSConfigSetup;
import program.POSHWSetup;
import program.PUtility;
import program.PluRec;
import program.PublicVar;
import program.TSaleBean;
import program.TableFileBean;
import program.TableFileControl;
import program.TextWriter;
import program.ThaiUtil;
import program.TranRecord;
import program.Value;
import util.MSG;

public class PPrint {

    private SerialPort serialPort;
    private OutputStream outputStream;
    private Enumeration portList;
    private CommPortIdentifier portId;
    private boolean OpenStatus = false;
    private String XLine1 = "";
    private String XLine2 = "";
    private String XLine3 = "";
    private String XLine4 = "";
    SimpleDateFormat PPrint_DatefmtThai = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
    SimpleDateFormat Datefmt = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    SimpleDateFormat ShowDatefmt = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    DecimalFormat DecFmt = new DecimalFormat("##,###,##0.00");
    DecimalFormat IntFmt = new DecimalFormat("##,###,##0");
    private int LineCount = 0;
    private boolean EJPrint = false;
    private POSHWSetup POSHW;
    private POSConfigSetup CONFIG;

    public void PPrint() {
        POSHW = POSHWSetup.Bean(Value.getMacno());
        CONFIG = POSConfigSetup.Bean();
    }

    public boolean OpenPrint(String PortName) {
        System.out.println("OpenPrint");
        if (OpenStatus) {
            return OpenStatus;
        }
        POSHW = POSHWSetup.Bean(Value.getMacno());
        if (POSHW.getPRNTYPE().equals("6")) {
            OpenStatus = false;
            portList = CommPortIdentifier.getPortIdentifiers();
            while (portList.hasMoreElements()) {
                portId = (CommPortIdentifier) portList.nextElement();
                if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                    if (portId.getName().equals(PortName)) {
                        try {
                            serialPort = (SerialPort) portId.open("SimpleWriteApp", 1000);
                        } catch (PortInUseException e) {
                            PUtility.ShowMsg("Can not Open Port...1");
                        } catch (RuntimeException re) {
                            PUtility.showError("Com Port ไม่สามารถใช้งานได้ " + portId.getName());
                        }
                        try {
                            outputStream = serialPort.getOutputStream();
                        } catch (IOException e) {
                            PUtility.ShowMsg("Can not Open Port...2");
                        }
                        try {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                PUtility.ShowMsg("Can not Open Port...2");
                                Logger.getLogger(PPrint.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            serialPort.setSerialPortParams(19200, //boardrate
                                    SerialPort.DATABITS_8,
                                    SerialPort.STOPBITS_1,
                                    SerialPort.PARITY_NONE);
                            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_XONXOFF_IN | SerialPort.FLOWCONTROL_XONXOFF_OUT);
                            OpenStatus = true;
                        } catch (UnsupportedCommOperationException e) {
                            PUtility.ShowMsg("Can not Open Port...3");
                        }
                    }
                }
            }
        } else {
            OpenStatus = false;
            portList = CommPortIdentifier.getPortIdentifiers();

            while (portList.hasMoreElements()) {
                portId = (CommPortIdentifier) portList.nextElement();
                if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                    if (portId.getName().equals(PortName)) {
                        //if (portId.getName().equals("/dev/term/a")) 
                        try {
                            serialPort = (SerialPort) portId.open("SimpleWriteApp", 1000);
                        } catch (PortInUseException e) {
                            PUtility.ShowMsg("Can not Open Port...1");
                        } catch (RuntimeException re) {
                            PUtility.showError("Com Port ไม่สามารถใช้งานได้ " + portId.getName());
                        }
                        try {
                            outputStream = serialPort.getOutputStream();
                        } catch (IOException e) {
                            PUtility.ShowMsg("Can not Open Port...2");
                        }
                        try {
                            try {
                                //PUtility.ShowMsg(serialPort.toString());
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                PUtility.ShowMsg("Can not Open Port...2");
                                Logger.getLogger(PPrint.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            serialPort.setSerialPortParams(9600, //boardrate
                                    SerialPort.DATABITS_8,
                                    SerialPort.STOPBITS_1,
                                    SerialPort.PARITY_NONE);
                            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_XONXOFF_IN | SerialPort.FLOWCONTROL_XONXOFF_OUT);
                            OpenStatus = true;
                        } catch (UnsupportedCommOperationException e) {
                            PUtility.ShowMsg("Can not Open Port...3");
                        }
                    }
                }
            }
        }

        return OpenStatus;
    }

    public void OpenDrawer() {
        POSHW = POSHWSetup.Bean(Value.getMacno());
        if (!POSHW.getDRPort().equals("NONE") && POSHW.getDRType().equals("1")) {
            String TempRate = POSHW.getDRCOM().trim();
            byte Rate = 49;
            try {
                int StrRate = Integer.parseInt(TempRate);
                Rate = (byte) StrRate;
            } catch (NumberFormatException e) {
            }

            try {
                byte Str[] = {27, 112, Rate}; //init Printer
                outputStream.write(Str);
                LineCount = 0;
            } catch (IOException ex) {
                PUtility.showError(ex.getMessage());
            }
        }

    }

    public void InitPrinter() {
        try {
            byte Str[] = {27, 64, 1}; //init Printer

            outputStream.write(Str);
            byte Str2[] = {27, 33, 1}; //Set to Nmormal Fornt

            outputStream.write(Str2);
            outputStream.flush();
            LineCount = 0;
        } catch (IOException ex) {
            PUtility.showError(ex.getMessage());
        }
    }

    public void SelectStye(int Stye) {

        if (Stye == 1) {
            byte Str[] = {27, 33, 1};
            try {
                outputStream.write(Str);
                outputStream.flush();
            } catch (IOException ex) {
                PUtility.showError(ex.getMessage());
            }
        }
        if (Stye == 2) {
            byte Str[] = {27, 33, 2};
            try {
                outputStream.write(Str);
                outputStream.flush();
            } catch (IOException ex) {
                PUtility.showError(ex.getMessage());
            }
        }
        if (Stye == 3) {
            byte Str[] = {27, 33, 97};
            try {
                outputStream.write(Str);
                outputStream.flush();
            } catch (IOException ex) {
                PUtility.showError(ex.getMessage());
            }
        }
        if (Stye == 4) {
            byte Str[] = {27, 33, 98};
            try {
                outputStream.write(Str);
                outputStream.flush();
            } catch (IOException ex) {
                PUtility.showError(ex.getMessage());
            }
        }
        if (Stye == 5) {
            byte Str[] = {27, 33, 16};
            try {
                outputStream.write(Str);
                outputStream.flush();
            } catch (IOException ex) {
                PUtility.showError(ex.getMessage());
            }
        }
        if (Stye == 6) {
            byte Str[] = {27, 33, 17};
            try {
                outputStream.write(Str);
                outputStream.flush();
            } catch (IOException ex) {
                PUtility.showError(ex.getMessage());
            }
        }
        if (Stye == 7) {
            byte Str[] = {27, 33, 49};
            try {
                outputStream.write(Str);
                outputStream.flush();
            } catch (IOException ex) {
                PUtility.showError(ex.getMessage());
            }
        }
        if (Stye == 8) {
            byte Str[] = {27, 33, 48};
            try {
                outputStream.write(Str);
                outputStream.flush();
            } catch (IOException ex) {
                PUtility.showError(ex.getMessage());
            }
        }
        if (Stye == 10) {
            byte Str[] = {27, 45, 1};
            try {
                outputStream.write(Str);
                outputStream.flush();
            } catch (IOException ex) {
                PUtility.showError(ex.getMessage());
            }
        }
        if (Stye == 11) {
            byte Str[] = {27, 45, 0};
            try {
                outputStream.write(Str);
                outputStream.flush();
            } catch (IOException ex) {
                PUtility.showError(ex.getMessage());
            }
        }
        if (Stye == 12) {
            byte Str[] = {27, 114, 1};
        }
        if (Stye == 13) {
            byte Str[] = {27, 114, 0};

            try {
                outputStream.write(Str);
                outputStream.flush();
            } catch (IOException ex) {
                PUtility.showError(ex.getMessage());
            }
        }

    }

    public void Get_Line(String St) {

        int SetMode[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int list1[] = {232, 233, 234, 235, 236};
        int list2[] = {128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138,
            139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149,
            150, 151, 152, 153, 154, 155, 156, 157, 158,
            209, 212, 213, 214, 215, 219, 220, 221, 222, 231, 232,
            233, 234, 235, 236, 237, 251, 252, 253, 254
        };

        int list4[] = {216, 217};
        char ch;

        String Level1 = "";
        String Level2 = "";
        String Level3 = "";
        String Level4 = "";

        int I = 0;
        while (I <= St.length() - 1) {   // Check TIS Upper }

            ch = St.charAt(I);
            if (SearchArray((int) ch, SetMode) != -1) {
                //Level2=Level2+ch ;
            } else {
                if (SearchArray((int) ch, list1) != -1) {
                    Level1 = Level1.substring(0, Level1.length() - 1) + ch;
                } else {
                    if (SearchArray((int) ch, list2) != -1) {
                        Level2 = Level2.substring(0, Level2.length() - 1) + ch;
                    } else {
                        if (SearchArray((int) ch, list4) != -1) {
                            Level4 = Level4.substring(0, Level4.length() - 1) + ch;

                        } else {
                            Level1 = Level1 + " ";
                            Level2 = Level2 + " ";
                            Level3 = Level3 + ch;
                            Level4 = Level4 + " ";
                        }
                    }
                }
            }
            I++;
        }
        XLine1 = Level1;
        XLine2 = Level2;
        XLine3 = Level3;
        XLine4 = Level4;

    }

    public void Print_Head_EJ() {
        POSHW = POSHWSetup.Bean(Value.getMacno());
        String TempFile = POSHW.getEJDailyPath() + "/tempbill.txt";
        File fObject = new File(TempFile);
        if (fObject.canRead()) {
            fObject.delete();
        }
        int cLoop = 0;
        while ((fObject.canRead()) | (cLoop > 10)) {
            cLoop++;
            TextWriter TextWrite = new TextWriter();
            TextWrite.writeToText(TempFile, "");
        }
        TextWriter TextWrite = new TextWriter();
        TextWrite.writeToText(TempFile, POSHW.getHeading1());
        TextWrite.writeToText(TempFile, POSHW.getHeading2());
        TextWrite.writeToText(TempFile, POSHW.getHeading3());
        TextWrite.writeToText(TempFile, POSHW.getHeading4());
        TextWrite.writeToText(TempFile, "REG ID :" + POSHW.getMacNo());
        EJPrint = true;
    }

    public void print(String PrintMsg) {
        if (POSHW.getPRNTYPE().equals("6")) {
            try {
                XLine1 = PrintMsg + "\n";
                Thread.sleep(50);
                outputStream.write(XLine1.getBytes("tis-620"));
            } catch (InterruptedException ex) {
                Logger.getLogger(PPrint.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                PUtility.showError(ex.getMessage());
            }
        } else {
            Get_Line(ThaiUtil.Unicode2ASCII(PrintMsg));
            try {
                try {
                    //outputStream.flush();
                    if (XLine1.trim().length() > 0) {
                        XLine1 = ThaiUtil.ASCII2Unicode(XLine1 + "\n");
                        byte LineSpace[] = {27, 51, 15, 1};
                        byte LineSpace2[] = {27, 51, 5, 1};
                        String TempPrint = "\n";
                        if (XLine2.trim().length() > 0) {
                            outputStream.write(LineSpace2);
                        } else {
                            outputStream.write(LineSpace);
                        }
                        Thread.sleep(230);
                        outputStream.write(XLine1.getBytes("tis-620"));
                        LineCount = LineCount + 1;
                    }
                    if (XLine2.trim().length() > 0) {
                        XLine2 = ThaiUtil.ASCII2Unicode(XLine2 + "\n");
                        byte LineSpace[] = {27, 51, 17, 1};
                        outputStream.write(LineSpace);
                        Thread.sleep(230);
                        outputStream.write(XLine2.getBytes("tis-620"));
                        LineCount = LineCount + 1;
                    }
                    if (XLine3.trim().length() >= 0) {
                        XLine3 = ThaiUtil.ASCII2Unicode(XLine3 + "\n");
                        byte LineSpace[] = {27, 51, 15, 1};
                        byte LineSpace2[] = {27, 51, 18, 1};
                        String TempPrint = "\n";
                        if (XLine4.trim().length() > 0) {
                            outputStream.write(LineSpace);
                        } else {
                            outputStream.write(LineSpace2);
                        }
                        Thread.sleep(190);
                        outputStream.write(XLine3.getBytes("tis-620"));
                        LineCount = LineCount + 1;
                    }
                    if (XLine4.trim().length() > 0) {
                        XLine4 = ThaiUtil.ASCII2Unicode(XLine4 + "\n");
                        byte LineSpace4[] = {27, 51, 0};
                        outputStream.write(LineSpace4);
                        Thread.sleep(230);
                        outputStream.write(XLine4.getBytes("tis-620"));
                        LineCount = LineCount + 1;
                    }
                } catch (IOException ex) {
                    PUtility.showError(ex.getMessage());
                }
            } catch (InterruptedException ex) {
                PUtility.showError(ex.getMessage());
            }
        }
        POSHWSetup bean = POSHWSetup.Bean(Value.MACNO);
        String TempFile = bean.getEJDailyPath() + "/log" + Value.MACNO + ".gif";
        TextWriter TextWrite = new TextWriter();
        File fObject = new File(TempFile);
        if (!fObject.canRead()) {
            TextWrite.writeToText(TempFile, "");
        }
        TextWrite.writeToText(TempFile, PrintMsg);
        if (EJPrint) {
            String TempBill = POSHW.getEJDailyPath() + "/tempbill.txt";
            if (!fObject.canRead()) {
                TextWrite.writeToText(TempBill, "");
            }
            TextWrite.writeToText(TempBill, PrintMsg);
        }

    }

    public void closePrint() {
        System.out.println("Close Printer");
        if (!OpenStatus) {
            return;
        }
        if (serialPort != null) {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                PUtility.showError(e.getMessage());
            }
            serialPort.close();
        }
        OpenStatus = false;
    }

    public void closePrintExit() {

        if (serialPort != null) {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                PUtility.showError(e.getMessage());
            }
            serialPort.close();
        }
        OpenStatus = false;
    }

    public void PrintHeader() {
        if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print(POSHW.getHeading1());
                print(POSHW.getHeading2());
                print(POSHW.getHeading3());
                print(POSHW.getHeading4());
                print("REG ID :" + Value.MACNO);
                CutPaper();
                closePrint();
            }
        } else {
            PUtility.showError("Printer Header Error...");
        }
    }

    public void printLogin() {
        if (Value.useprint) {
            POSHW = POSHWSetup.Bean(Value.getMacno());
            Date dateP = new Date();

            if (Value.printdriver) {
                PrintDriver pd = new PrintDriver();
                pd.addTextLn("Log In User : " + PublicVar._User);
                pd.addTextLn("Log In Time : " + PPrint_DatefmtThai.format(dateP));

                pd.printNormal();
            } else {
                if (!Value.getComPort().equals("NONE")) {
                    if (OpenPrint(Value.getComPort())) {
                        InitPrinter();
                        print("Log In User : " + PublicVar._User);
                        print("Log In Time : " + PPrint_DatefmtThai.format(dateP));
                        print("");
                        print("");
                        print("");
                        print("");
                        print("");
                        print("");
                        CutPaper();
                        closePrint();
                    }
                }
            }
        }
    }

    public void printLogout() {
        if (Value.useprint) {
            POSHW = POSHWSetup.Bean(Value.getMacno());
            Date dateP = new Date();
            if (Value.printdriver) {
                PrintDriver pd = new PrintDriver();
                pd.addTextLn("Log Out User : " + PublicVar._User);
                pd.addTextLn("Log Out Time : " + PPrint_DatefmtThai.format(dateP));

                pd.printNormal();
            } else {
                if (!Value.getComPort().equals("NONE")) {
                    if (OpenPrint(Value.getComPort())) {
                        InitPrinter();
                        print("Log Out User : " + PublicVar._User);
                        print("Log Out Time : " + PPrint_DatefmtThai.format(dateP));
                        print("");
                        print("");
                        CutPaper();
                        closePrint();
                    }
                }
            }
        }
    }

    public void printerror(String RefNo) {
        if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print("");
                print("มีปัญหาในการบันทึกข้อมูล  Bill-No : " + RefNo);
                print("      กรุณาทำรายการใหม่");
                print("");
                closePrint();
            }
        }
    }

    public void PrintSubTotalBillDriver(String _RefNo, String tableNo) {
        String t = "";
        POSHW = POSHWSetup.Bean(Value.getMacno());
        CONFIG = POSConfigSetup.Bean();

        BillControl billC = new BillControl();
        BillNoBean bBean = billC.getData(_RefNo);

        ArrayList<TSaleBean> listTSale = billC.getAllTSale(_RefNo);

        int AmtLength = 10;
        int ItemCnt = 0;
        String VatStr;
        PublicVar.P_LineCount = 0;
        for (int i = 0; i < listTSale.size(); i++) {
            TSaleBean bean = (TSaleBean) listTSale.get(i);
            if (!bean.getR_Void().equals("V")) {
                ItemCnt = (int) (ItemCnt + bean.getR_Quan());
            }
        }
        CONFIG = POSConfigSetup.Bean();
        if (CONFIG.getP_PrintDetailOnRecp().equals("Y")) {
            Date dateP = new Date();
            t += (POSHW.getHeading1()) + "_";
            t += (POSHW.getHeading2()) + "_";
            t += (POSHW.getHeading3()) + "_";
            t += (POSHW.getHeading4()) + "_";
            t += ("REG ID :" + Value.MACNO) + "_";
            t += PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO + "_";
            t += "----------------------------------------_";

            for (int i = 0; i < listTSale.size(); i++) {
                TSaleBean bean = (TSaleBean) listTSale.get(i);
                if (bean.getR_Vat().equals("N")) {
                    VatStr = "*";
                } else {
                    VatStr = "-";
                }
                if (bean.getR_Void().equals("V")) {
                    t += ("VOID..." + "User :" + bean.getR_VoidUser()) + "_";
                    if (CONFIG.getP_CodePrn().equals("Y")) {
                        t += (bean.getR_PName()) + "_";
                        t += (bean.getR_Normal() + VatStr + bean.getR_PluCode() + TAB + IntFmt.format(-1 * bean.getR_Quan()) + TAB2 + DecFmt.format(-1 * bean.getR_Total()) + bean.getR_ETD()) + "_";
                    } else {
                        t += (bean.getR_Normal() + VatStr + bean.getR_PName() + TAB + IntFmt.format(-1 * bean.getR_Quan()) + TAB2 + DecFmt.format(-1 * bean.getR_Total()) + bean.getR_ETD()) + "_";
                    }
                } else {
                    if (bean.getR_PrAmt() == 0) {
                        if (CONFIG.getP_CodePrn().equals("Y")) {
                            t += (bean.getR_PName()) + "_";
                            t += (bean.getR_Normal() + VatStr + bean.getR_PluCode() + TAB + IntFmt.format(bean.getR_Quan()) + TAB2 + DecFmt.format(bean.getR_Total()) + bean.getR_ETD()) + "_";
                        } else {
                            t += (bean.getR_Normal() + VatStr + bean.getR_PName() + TAB + IntFmt.format(bean.getR_Quan()) + TAB2 + DecFmt.format(bean.getR_Total()) + bean.getR_ETD()) + "_";
                        }
                    } else {
                        if (CONFIG.getP_CodePrn().equals("Y")) {
                            t += (bean.getR_PName()) + "_";
                            t += (bean.getR_Normal() + VatStr + bean.getR_PluCode() + TAB + IntFmt.format(bean.getR_Quan()) + TAB2 + DecFmt.format(bean.getR_Total()) + bean.getR_ETD()) + "_";
                        } else {
                            t += (bean.getR_Normal() + VatStr + bean.getR_PName() + TAB + IntFmt.format(bean.getR_Quan()) + TAB2 + DecFmt.format(bean.getR_Total()) + bean.getR_ETD()) + "_";
                        }
                        if (bean.getR_PrType().equals("-P")) {
                            if (bean.getR_PrAmt() > 0) {
                                t += ("   **Promotion  " + bean.getR_PrCode() + " " + PUtility.SeekPromotionName(bean.getR_PrCode())) + "_";
                            }
                        }
                        if (bean.getR_PrType().equals("-I")) {
                            if (bean.getR_PrDisc() != 0) {
                                t += ("   **Item-Discount " + bean.getR_PrCode() + " " + DecFmt.format(bean.getR_PrDisc() - bean.getR_Redule()) + "%") + "_";
                            }
                        }
                    }
                }
            }
        } else {
            Date dateP = new Date();
            t += (PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO) + "_";
            t += ("----------------------------------------") + "_";
            t += ("     อาหารและเครื่องดื่ม " + DecFmt.format(bBean.getB_Total())) + "_";
        }
        t += ("----------------------------------------") + "_";
        t += ("Sub-TOTAL   " + "(" + IntFmt.format(ItemCnt) + " )" + TAB2 + DecFmt.format(bBean.getB_Total())) + "_";

        if (bBean.getB_ProDiscAmt() > 0) {
            t += ("    " + "ลด Promotion     " + DecFmt.format(bBean.getB_ProDiscAmt())) + "_";
        }
        if (bBean.getB_SpaDiscAmt() > 0) {
            t += ("     " + "Special Disc     " + DecFmt.format(bBean.getB_SpaDiscAmt())) + "_";
        }
        if (bBean.getB_MemDiscAmt() > 0) {
            t += ("     " + "ลดสมาชิก.........." + bBean.getB_MemDisc() + DecFmt.format(bBean.getB_MemDiscAmt())) + "_";
        }
        if (bBean.getB_FastDiscAmt() > 0) {
            t += ("     " + "ลดเทศกาล........." + bBean.getB_FastDisc() + DecFmt.format(bBean.getB_FastDiscAmt())) + "_";
        }
        if (bBean.getB_EmpDiscAmt() > 0) {
            t += ("     " + "ลดพนักงาน........." + bBean.getB_EmpDisc() + DecFmt.format(bBean.getB_EmpDiscAmt())) + "_";
        }
        if (bBean.getB_TrainDiscAmt() > 0) {
            t += ("     " + "ลด Trainnee......" + bBean.getB_TrainDisc() + PUtility.DataFull2(DecFmt.format(bBean.getB_TrainDiscAmt()), AmtLength)) + "_";
        }
        if (bBean.getB_CuponDiscAmt() > 0) {
            t += ("     " + "ลดคูปอง..........." + bBean.getB_SubDisc() + DecFmt.format(bBean.getB_CuponDiscAmt())) + "_";
        }
        if (bBean.getB_ItemDiscAmt() > 0) {
            t += ("     " + "ลดตามรายการ(Item)" + DecFmt.format(bBean.getB_ItemDiscAmt())) + "_";
        }
        if (bBean.getB_ServiceAmt() > 0) {
            t += ("     " + "ค่าบริการ (Service)     " + DecFmt.format(bBean.getB_ServiceAmt())) + "_";
        }
        if (bBean.getB_Earnest() > 0) {
            t += ("     " + "หักคืนเงินมัดจำ           " + DecFmt.format(bBean.getB_Earnest())) + "_";
        }
        if (CONFIG.getP_VatType().equals("I")) {
            t += ("Net-TOTAL..      " + "<font size=1>" + DecFmt.format(bBean.getB_NetTotal())) + "</font>_";
            if (CONFIG.getP_VatPrn().equals("Y")) {
                t += (TAB + TAB2 + "Vat..." + DecFmt.format(bBean.getB_Vat())) + "_";
            }
        } else {
            t += ("      Net-Amount " + DecFmt.format(bBean.getB_NetVat() + bBean.getB_NetNonVat())) + "_";
            t += ("      Vat....... " + DecFmt.format(CONFIG.getP_Vat())) + "%" + DecFmt.format(bBean.getB_Vat()) + "_";
            t += ("VAT INCLUDED") + "_";
        }
        if (bBean.getB_GiftVoucher() > 0) {
            t += ("     " + "บัตรกำนัล.............." + DecFmt.format(bBean.getB_GiftVoucher())) + "_";
            try {
                Statement stmt = MySQLConnect.con.createStatement();
                String CheckGift = "select *from t_gift where (refno='" + _RefNo + "')";
                ResultSet rec = stmt.executeQuery(CheckGift);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    do {
                        t += ("   " + rec.getString("giftbarcode") + "@" + DecFmt.format(rec.getDouble("giftamt"))) + "_";
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                PUtility.showError(e.getMessage());
            }
        }
        if (bBean.getB_PayAmt() > 0) {
            t += ("เงินสด  : " + DecFmt.format(bBean.getB_PayAmt()) + "  ทอน : " + DecFmt.format(bBean.getB_Ton())) + "_";
        }
        if (bBean.getB_CrAmt1() > 0) {
            //get credit name
            String crName = "";
            try {
                String sql = "select * from creditfile where crcode='" + bBean.getB_CrCode1() + "'";
                ResultSet rs = MySQLConnect.getResultSet(sql);
                if (rs.next()) {
                    crName = ThaiUtil.ASCII2Unicode(rs.getString("CrName"));
                }

                rs.close();
            } catch (Exception e) {
                MSG.ERR(null, e.getMessage());
            }

            t += (bBean.getB_CrCode1() + TAB + crName) + "_";
            t += ("XXXXXXXXXXX" + PUtility.Addzero(bBean.getB_CardNo1(), 16).substring(12, 16) + TAB + bBean.getB_AppCode1()) + "_";
            t += ("Credit Payment               " + DecFmt.format(bBean.getB_CrAmt1())) + "_";
        }

        if (bBean.getB_AccrAmt() > 0) {
            t += ("AR-" + bBean.getB_AccrCode() + " ลูกหนี้การค้า........" + DecFmt.format(bBean.getB_AccrAmt())) + "_";
            t += (PUtility.SeekArName(bBean.getB_AccrCode())) + "_";
        }

        if (!bBean.getB_MemCode().equals("")) {
            t += ("----------------------------------------") + "_";
            t += ("สมาชิก - " + bBean.getB_MemCode()) + "_";
            t += (ThaiUtil.ASCII2Unicode(bBean.getB_MemName())) + "_";
            t += ("   แต้มครั้งนี้ :           0 แต้ม") + "_";
            t += ("   แต้มสะสมถึง  --- -- ----   0 แต้ม") + "_";
            t += ("   บัตรหมมดอายุวันที่ : " + ShowDatefmt.format(bBean.getB_MemEnd())) + "_";
        }

        t += ("----------------------------------------") + "_";
        t += ("TABLE : " + tableNo + " " + "Receipt No: " + _RefNo) + "_";
        t += ("จำนวนลูกค้า : " + IntFmt.format(bBean.getB_Cust()) + " คน") + "_";
        t += (" ") + "_";
        if (!CONFIG.getP_PrintRecpMessage().equals("")) {
            t += (CONFIG.getP_PrintRecpMessage()) + "_";
        }

        if (!POSHW.getFootting1().equals("")) {
            t += (POSHW.getFootting1()) + "_";
        }
        if (!POSHW.getFootting2().equals("")) {
            t += (POSHW.getFootting2()) + "_";
        }

        t += (" ") + "_";
        EJPrint = false;

        //print
        PrintDriver pd = new PrintDriver();
        String[] strs = t.split("_");
        for (String data : strs) {
            if (data.indexOf("<font") == -1) {
                pd.addText("<font size=-6>" + data + "</font>");
            } else {
                pd.addText(data);
            }
        }

        pd.printHTML();
    }

    public void PrintSubTotalBill(String _RefNo, String tableNo) {
        if (Value.printdriver) {
            PrintSubTotalBillDriver(_RefNo, tableNo);
        } else {
            BillControl billC = new BillControl();
            BillNoBean bBean = billC.getData(_RefNo);

            ArrayList<TSaleBean> listTSale = billC.getAllTSale(_RefNo);

            int QtyLength = 5;
            int AmtLength = 10;
            int SubLength = 20;
            int SubLength2 = 13;
            int ItemCnt = 0;
            String VatStr;
            if (!Value.getComPort().equals("NONE")) {
                if (OpenPrint(Value.getComPort())) {
                    InitPrinter();
                    OpenDrawer();
                    InitPrinter();

                    PublicVar.P_LineCount = 0;
                    for (int i = 0; i < listTSale.size(); i++) {
                        TSaleBean bean = (TSaleBean) listTSale.get(i);
                        if (!bean.getR_Void().equals("V")) {
                            ItemCnt = (int) (ItemCnt + bean.getR_Quan());
                        }
                    }
                    CONFIG = POSConfigSetup.Bean();
                    print(POSHW.getHeading1());
                    print(POSHW.getHeading2());
                    print(POSHW.getHeading3());
                    print(POSHW.getHeading4());
                    print("REG ID :" + Value.MACNO);
                    if (CONFIG.getP_PrintDetailOnRecp().equals("Y")) {
                        Date dateP = new Date();
                        print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                        print("----------------------------------------");
                        for (int i = 0; i < listTSale.size(); i++) {
                            TSaleBean bean = (TSaleBean) listTSale.get(i);
                            if (bean.getR_Vat().equals("N")) {
                                VatStr = "*";
                            } else {
                                VatStr = "-";
                            }
                            if (bean.getR_Void().equals("V")) {
                                SelectStye(12);
                                print("VOID..." + "User :" + bean.getR_VoidUser());
                                if (CONFIG.getP_CodePrn().equals("Y")) {
                                    print(bean.getR_PName());
                                    print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(-1 * bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(-1 * bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                } else {
                                    print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(-1 * bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(-1 * bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                }
                                SelectStye(13);
                            } else {
                                if (bean.getR_PrAmt() == 0) {
                                    if (CONFIG.getP_CodePrn().equals("Y")) {
                                        print(bean.getR_PName());
                                        print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                    } else {
                                        print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                    }
                                } else {
                                    if (CONFIG.getP_CodePrn().equals("Y")) {
                                        print(bean.getR_PName());
                                        print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                    } else {
                                        print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                    }
                                    if (bean.getR_PrType().equals("-P")) {
                                        if (bean.getR_PrAmt() > 0) {
                                            print("   **Promotion  " + bean.getR_PrCode() + " " + PUtility.DataFull(PUtility.SeekPromotionName(bean.getR_PrCode()), 20));
                                        }
                                    }
                                    if (bean.getR_PrType().equals("-I")) {
                                        if (bean.getR_PrDisc() != 0) {
                                            print("   **Item-Discount " + bean.getR_PrCode() + " " + PUtility.DataFull(DecFmt.format(bean.getR_PrDisc() - bean.getR_Redule()), QtyLength) + "%");
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Date dateP = new Date();
                        print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                        print("----------------------------------------");
                        print("     อาหารและเครื่องดื่ม " + PUtility.DataFull(DecFmt.format(bBean.getB_Total()), AmtLength));
                    }
                    print("----------------------------------------");
                    print("Sub-TOTAL   " + "(Item" + PUtility.DataFull(IntFmt.format(ItemCnt), QtyLength) + " )     " + PUtility.DataFull(DecFmt.format(bBean.getB_Total()), AmtLength));

                    if (bBean.getB_ProDiscAmt() > 0) {
                        print("    " + PUtility.DataFullR("ลด Promotion     ", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_ProDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_SpaDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("Special Disc     ", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_SpaDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_MemDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดสมาชิก..........", SubLength2) + PUtility.DataFull(bBean.getB_MemDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_MemDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_FastDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดเทศกาล.........", SubLength2) + PUtility.DataFull(bBean.getB_FastDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_FastDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_EmpDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดพนักงาน.........", SubLength2) + PUtility.DataFull(bBean.getB_EmpDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_EmpDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_TrainDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลด Trainnee......", SubLength2) + PUtility.DataFull(bBean.getB_TrainDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_TrainDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_CuponDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดคูปอง...........", SubLength2) + PUtility.DataFull(bBean.getB_SubDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_CuponDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_ItemDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดตามรายการ(Item)", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_ItemDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_ServiceAmt() > 0) {
                        print("     " + PUtility.DataFullR("ค่าบริการ (Service)     ", 23) + PUtility.DataFull(DecFmt.format(bBean.getB_ServiceAmt()), 9));
                    }
                    if (bBean.getB_Earnest() > 0) {
                        print("     " + PUtility.DataFullR("หักคืนเงินมัดจำ           ", 23) + PUtility.DataFull(DecFmt.format(bBean.getB_Earnest()), 9));
                    }
                    if (CONFIG.getP_VatType().equals("I")) {
                        //Print_Str(" ");
                        SelectStye(3);
                        print("Net-TOTAL " + PUtility.DataFull(DecFmt.format(bBean.getB_NetTotal()), AmtLength));
                        SelectStye(1);
                        if (CONFIG.getP_VatPrn().equals("Y")) {
                            print(PUtility.DataFull("              Vat...", 43) + PUtility.DataFull(DecFmt.format(bBean.getB_Vat()), AmtLength));
                        }
                    } else {
                        print(PUtility.DataFull("      Net-Amount ", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_NetVat() + bBean.getB_NetNonVat()), AmtLength));
                        print(PUtility.DataFull("      Vat....... ", SubLength) + PUtility.DataFull(DecFmt.format(CONFIG.getP_Vat()), QtyLength) + "%" + PUtility.DataFull(DecFmt.format(bBean.getB_Vat()), AmtLength));
                        print("VAT INCLUDED");
                    }
                    if (bBean.getB_GiftVoucher() > 0) {
                        print("     " + PUtility.DataFullR("บัตรกำนัล..............", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_GiftVoucher()), AmtLength));
                        try {
                            Statement stmt = MySQLConnect.con.createStatement();
                            String CheckGift = "select *from t_gift where (refno='" + _RefNo + "')";
                            ResultSet rec = stmt.executeQuery(CheckGift);
                            rec.first();
                            if (rec.getRow() == 0) {
                            } else {
                                do {
                                    print("   " + PUtility.DataFull(rec.getString("giftbarcode"), 25) + "@" + PUtility.DataFull(DecFmt.format(rec.getDouble("giftamt")), AmtLength));
                                } while (rec.next());
                            }
                            rec.close();
                            stmt.close();
                        } catch (SQLException e) {
                            PUtility.showError(e.getMessage());
                        }
                    }
                    if (bBean.getB_PayAmt() > 0) {
                        print("เงินสด  : " + PUtility.DataFull(DecFmt.format(bBean.getB_PayAmt()), AmtLength) + "  ทอน : " + PUtility.DataFull(DecFmt.format(bBean.getB_Ton()), AmtLength));
                    }
                    if (bBean.getB_CrAmt1() > 0) {
                        //get credit name
                        String crName = "";
                        try {
                            String sql = "select * from creditfile where crcode='" + bBean.getB_CrCode1() + "'";
                            ResultSet rs = MySQLConnect.getResultSet(sql);
                            if (rs.next()) {
                                crName = ThaiUtil.ASCII2Unicode(rs.getString("CrName"));
                            }

                            rs.close();
                        } catch (Exception e) {
                            MSG.ERR(null, e.getMessage());
                        }

                        print(bBean.getB_CrCode1() + "  " + crName);
                        print("XXXXXXXXXXX" + PUtility.Addzero(bBean.getB_CardNo1(), 16).substring(12, 16) + "  " + bBean.getB_AppCode1());
                        print("Credit Payment               " + PUtility.DataFull(DecFmt.format(bBean.getB_CrAmt1()), AmtLength));
                    }

                    if (bBean.getB_AccrAmt() > 0) {
                        print("AR-" + bBean.getB_AccrCode() + " ลูกหนี้การค้า........" + PUtility.DataFull(DecFmt.format(bBean.getB_AccrAmt()), AmtLength));
                        print(PUtility.DataFullR(PUtility.SeekArName(bBean.getB_AccrCode()), 38));
                    }

                    if (!bBean.getB_MemCode().equals("")) {
                        print("----------------------------------------");
                        print("สมาชิก - " + bBean.getB_MemCode());
                        print(ThaiUtil.ASCII2Unicode(bBean.getB_MemName()));
                        print("   แต้มครั้งนี้ :           0 แต้ม");
                        print("   แต้มสะสมถึง  --- -- ----   0 แต้ม");
                        print("   บัตรหมมดอายุวันที่ : " + ShowDatefmt.format(bBean.getB_MemEnd()));
                    }

                    print("----------------------------------------");
                    SelectStye(5);
                    print("TABLE : " + PUtility.DataFullR(tableNo, 5) + " " + "Receipt No: " + _RefNo);
                    SelectStye(1);
                    print("จำนวนลูกค้า : " + IntFmt.format(bBean.getB_Cust()) + " คน");
                    print(" ");
                    if (!CONFIG.getP_PrintRecpMessage().equals("")) {
                        print(CONFIG.getP_PrintRecpMessage());
                    }

                    if (!POSHW.getFootting1().equals("")) {
                        print(POSHW.getFootting1());
                    }
                    if (!POSHW.getFootting2().equals("")) {
                        print(POSHW.getFootting2());
                    }

                    print(" ");
                    print(" ");
                    SelectStye(3);
                    SelectStye(1);
                    print("");
                    print(" ");
                    print(" ");
                    print(" ");
                    EJPrint = false;
                    print(" ");
                    print(" ");
                    print(" ");
                    CutPaper();
                    closePrint();
                }
            }
        }
    }

    String TAB = "               ";
    String TAB2 = "       ";
    String SPLIT = "=";

    public void printCheckBillDriver(String tableNo) {
        String t = "";
        POSHW = POSHWSetup.Bean(Value.getMacno());
        CONFIG = POSConfigSetup.Bean();

        BalanceControl bc = new BalanceControl();
        ArrayList<BalanceBean> listBean = bc.getAllBalance(tableNo);
        int ItemCnt = 0;
        String VatStr;
        CONFIG = POSConfigSetup.Bean();

        for (int i = 0; i < listBean.size(); i++) {
            BalanceBean bean = (BalanceBean) listBean.get(i);
            if (!bean.getR_Void().equals("V")) {
                ItemCnt = (int) (ItemCnt + bean.getR_Quan());
            }
        }

        t += "_";
        t += POSHW.getHeading1() + "_";
        t += POSHW.getHeading2() + "_";
        t += POSHW.getHeading3() + "_";
        t += POSHW.getHeading4() + "_";
        t += "REG ID :" + Value.MACNO + "_";
        t += " *** ใบตรวจสอบรายการ *** _";
        Date dateP = new Date();
        t += PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO + "_";
        t += "----------------------------------------_";

        for (int i = 0; i < listBean.size(); i++) {
            BalanceBean bean = (BalanceBean) listBean.get(i);
            if (bean.getR_Void().equals("V")) {
                t += "VOID..." + "User :" + bean.getR_VoidUser() + "_";

                if (bean.getR_Vat().equals("V")) {
                    VatStr = "-";
                } else {
                    VatStr = "*";
                }
                if (CONFIG.getP_CodePrn().equals("Y")) {
                    t += bean.getR_PName() + "_";
                    t += bean.getR_Normal() + VatStr + bean.getR_PluCode() + TAB + IntFmt.format(-1 * bean.getR_Quan()) + TAB2 + DecFmt.format(-1 * bean.getR_Total()) + bean.getR_ETD() + "_";
                } else {
                    t += bean.getR_Normal() + VatStr + bean.getR_PName() + IntFmt.format(-1 * bean.getR_Quan()) + TAB2 + DecFmt.format(-1 * bean.getR_Total()) + bean.getR_ETD() + "_";
                }
            } else {
                if (bean.getR_Vat().equals("V")) {
                    VatStr = "-";
                } else {
                    VatStr = "*";
                }
                if (bean.getR_PrAmt() == 0) {
                    if (CONFIG.getP_CodePrn().equals("Y")) {
                        t += bean.getR_PName() + "_";
                        t += bean.getR_Normal() + VatStr + bean.getR_PluCode() + TAB + IntFmt.format(bean.getR_Quan()) + TAB2 + DecFmt.format(bean.getR_Total()) + bean.getR_ETD() + "_";
                    } else {
                        t += bean.getR_Normal() + VatStr + bean.getR_PName() + TAB + IntFmt.format(bean.getR_Quan()) + TAB2 + DecFmt.format(bean.getR_Total()) + bean.getR_ETD() + "_";
                    }
                } else {
                    if (CONFIG.getP_CodePrn().equals("Y")) {
                        t += bean.getR_PName() + "_";
                        t += bean.getR_Normal() + VatStr + bean.getR_PluCode() + TAB + IntFmt.format(bean.getR_Quan()) + TAB2 + DecFmt.format(bean.getR_Total()) + bean.getR_ETD() + "_";
                    } else {
                        t += bean.getR_Normal() + VatStr + bean.getR_PName() + TAB + IntFmt.format(bean.getR_Quan()) + TAB2 + DecFmt.format(bean.getR_Total()) + bean.getR_ETD() + "_";
                    }
                    if (bean.getR_PrType().equals("-P")) {
                        if (bean.getR_PrAmt() > 0) {
                            t += "   **Promotion  " + bean.getR_PrCode() + TAB + PUtility.SeekPromotionName(bean.getR_PrCode()) + "_";
                        }
                    }
                    if (bean.getR_PrType().equals("-I")) {
                        if (bean.getR_PrDisc() != 0) {
                            t += "   **Item-Discount " + bean.getR_PrCode() + TAB + DecFmt.format(bean.getR_PrDisc() - bean.getR_Redule()) + "%_";
                        }
                    }
                }
            }
        }

        TableFileControl tCon = new TableFileControl();
        TableFileBean tBean = tCon.getData(tableNo);

        t += "----------------------------------------_";
        t += "Sub-TOTAL " + "(" + IntFmt.format(ItemCnt) + " )" + TAB2 + DecFmt.format(tBean.getTAmount()) + "_";

        if (tBean.getProDiscAmt() > 0) {
            t += "    " + "ลด Promotion     " + DecFmt.format(tBean.getProDiscAmt()) + "_";
        }
        if (tBean.getSpaDiscAmt() > 0) {
            t += "     " + "Special Disc     " + DecFmt.format(tBean.getSpaDiscAmt()) + "_";
        }
        if (tBean.getMemDiscAmt() > 0) {
            t += "     " + "ลดสมาชิก.........." + tBean.getMemDisc() + DecFmt.format(tBean.getMemDiscAmt()) + "_";
        }
        if (tBean.getFastDiscAmt() > 0) {
            t += "     " + "ลดเทศกาล........." + tBean.getFastDisc() + DecFmt.format(tBean.getFastDiscAmt()) + "_";
        }
        if (tBean.getEmpDiscAmt() > 0) {
            t += "     " + "ลดพนักงาน........." + tBean.getEmpDisc() + DecFmt.format(tBean.getEmpDiscAmt()) + "_";
        }
        if (tBean.getTrainDiscAmt() > 0) {
            t += "     " + "ลด Trainnee......" + tBean.getTrainDisc() + DecFmt.format(tBean.getTrainDiscAmt()) + "_";
        }
        if (tBean.getSubDiscAmt() > 0) {
            t += "     " + "ลดคูปอง..........." + tBean.getSubDisc() + DecFmt.format(tBean.getSubDiscAmt()) + "_";
        }
        if (tBean.getDiscBath() > 0) {
            t += "     " + "ลด(บาท).........." + DecFmt.format(tBean.getDiscBath()) + "_";
        }
        if (tBean.getItemDiscAmt() > 0) {
            t += "     " + "ลดตามรายการ(Item)" + DecFmt.format(tBean.getItemDiscAmt()) + "_";
        }
        if (tBean.getServiceAmt() > 0) {
            t += "     " + "ค่าบริการ (Service)     " + DecFmt.format(tBean.getServiceAmt()) + "_";
        }

        t += "----------------------------------------_";
        t += "NET-TOTAL    " + DecFmt.format(tBean.getNetTotal()) + "_";
        t += "----------------------------------------_";

        t += "TABLE  " + tableNo + "   " + "จำนวนลูกค้า : " + IntFmt.format(tBean.getTCustomer()) + " คน_";

        if (!CONFIG.getP_PrintRecpMessage().equals("")) {
            t += CONFIG.getP_PrintRecpMessage() + "_";
        }

        if (!POSHW.getFootting1().equals("")) {
            t += POSHW.getFootting1() + "_";
        }
        if (!POSHW.getFootting2().equals("")) {
            t += POSHW.getFootting2() + "_";
        }
        t += " _";

        //print
        PrintDriver pd = new PrintDriver();
        String[] strs = t.split("_");
        for (String data : strs) {
            pd.addText(data);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }

        pd.printHTML();
    }

    public void PrintCheckBill(String tableNo) {
        if (Value.printdriver) {
            printCheckBillDriver(tableNo);
        } else {
            BalanceControl bc = new BalanceControl();
            ArrayList<BalanceBean> listBean = bc.getAllBalance(tableNo);

            int QtyLength = 5;
            int AmtLength = 10;
            int SubLength = 20;
            int SubLength2 = 13;
            int ItemCnt = 0;
            String VatStr;
            CONFIG = POSConfigSetup.Bean();
            if (!Value.getComPort().equals("NONE")) {
                if (OpenPrint(Value.getComPort())) {
                    InitPrinter();
                    //OpenDrawer() ; 
                    PublicVar.P_LineCount = 0;
                    for (int i = 0; i < listBean.size(); i++) {
                        BalanceBean bean = (BalanceBean) listBean.get(i);
                        if (!bean.getR_Void().equals("V")) {
                            ItemCnt = (int) (ItemCnt + bean.getR_Quan());
                        }
                    }
                    print(POSHW.getHeading1());
                    print(POSHW.getHeading2());
                    print(POSHW.getHeading3());
                    print(POSHW.getHeading4());
                    print("REG ID :" + Value.MACNO);

                    print(" *** ใบตรวจสอบรายการไม่ใช่ใบเสร็จรับเงิน *** ");

                    print(" ");
                    Date dateP = new Date();
                    print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                    print("----------------------------------------");
                    for (int i = 0; i < listBean.size(); i++) {
                        BalanceBean bean = (BalanceBean) listBean.get(i);
                        if (bean.getR_Void().equals("V")) {
                            SelectStye(12);
                            print("VOID..." + "User :" + bean.getR_VoidUser());

                            if (bean.getR_Vat().equals("V")) {
                                VatStr = "-";
                            } else {
                                VatStr = "*";
                            }
                            if (CONFIG.getP_CodePrn().equals("Y")) {
                                print(bean.getR_PName());
                                print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(-1 * bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(-1 * bean.getR_Total()), AmtLength) + bean.getR_ETD());
                            } else {
                                print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(-1 * bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(-1 * bean.getR_Total()), AmtLength) + bean.getR_ETD());
                            }
                            SelectStye(13);
                        } else {
                            if (bean.getR_Vat().equals("V")) {
                                VatStr = "-";
                            } else {
                                VatStr = "*";
                            }
                            if (bean.getR_PrAmt() == 0) {
                                if (CONFIG.getP_CodePrn().equals("Y")) {
                                    print(bean.getR_PName());
                                    print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                } else {
                                    print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                }
                            } else {
                                if (CONFIG.getP_CodePrn().equals("Y")) {
                                    print(bean.getR_PName());
                                    print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                } else {
                                    print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                }
                                if (bean.getR_PrType().equals("-P")) {
                                    if (bean.getR_PrAmt() > 0) {
                                        print("   **Promotion  " + bean.getR_PrCode() + " " + PUtility.DataFull(PUtility.SeekPromotionName(bean.getR_PrCode()), 20));
                                    }
                                }
                                if (bean.getR_PrType().equals("-I")) {
                                    if (bean.getR_PrDisc() != 0) {
                                        print("   **Item-Discount " + bean.getR_PrCode() + " " + PUtility.DataFull(DecFmt.format(bean.getR_PrDisc() - bean.getR_Redule()), QtyLength) + "%");
                                    }
                                }
                            }
                        }
                    }

                    TableFileControl tCon = new TableFileControl();
                    TableFileBean tBean = tCon.getData(tableNo);

                    print("----------------------------------------");
                    print("Sub-TOTAL   " + "(Item" + PUtility.DataFull(IntFmt.format(ItemCnt), QtyLength) + " )     " + PUtility.DataFull(DecFmt.format(tBean.getTAmount()), AmtLength));

                    if (tBean.getProDiscAmt() > 0) {
                        print("    " + PUtility.DataFullR("ลด Promotion     ", SubLength) + PUtility.DataFull(DecFmt.format(tBean.getProDiscAmt()), AmtLength));
                    }
                    if (tBean.getSpaDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("Special Disc     ", SubLength) + PUtility.DataFull(DecFmt.format(tBean.getSpaDiscAmt()), AmtLength));
                    }
                    if (tBean.getMemDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดสมาชิก..........", SubLength2) + PUtility.DataFull(tBean.getMemDisc(), 8) + PUtility.DataFull(DecFmt.format(tBean.getMemDiscAmt()), AmtLength));
                    }
                    if (tBean.getFastDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดเทศกาล.........", SubLength2) + PUtility.DataFull(tBean.getFastDisc(), 8) + PUtility.DataFull(DecFmt.format(tBean.getFastDiscAmt()), AmtLength));
                    }
                    if (tBean.getEmpDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดพนักงาน.........", SubLength2) + PUtility.DataFull(tBean.getEmpDisc(), 8) + PUtility.DataFull(DecFmt.format(tBean.getEmpDiscAmt()), AmtLength));
                    }
                    if (tBean.getTrainDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลด Trainnee......", SubLength2) + PUtility.DataFull(tBean.getTrainDisc(), 8) + PUtility.DataFull(DecFmt.format(tBean.getTrainDiscAmt()), AmtLength));
                    }
                    if (tBean.getSubDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดคูปอง...........", SubLength2) + PUtility.DataFull(tBean.getSubDisc(), 8) + PUtility.DataFull(DecFmt.format(tBean.getSubDiscAmt()), AmtLength));
                    }
                    if (tBean.getDiscBath() > 0) {
                        print("     " + PUtility.DataFullR("ลด(บาท)..........", SubLength) + PUtility.DataFull(DecFmt.format(tBean.getDiscBath()), AmtLength));
                    }
                    if (tBean.getItemDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดตามรายการ(Item)", SubLength) + PUtility.DataFull(DecFmt.format(tBean.getItemDiscAmt()), AmtLength));
                    }
                    if (tBean.getServiceAmt() > 0) {
                        print("     " + PUtility.DataFullR("ค่าบริการ (Service)     ", 23) + PUtility.DataFull(DecFmt.format(tBean.getServiceAmt()), 9));
                    }

                    print("----------------------------------------");
                    SelectStye(3);
                    print("Net-TOTAL " + TAB + DecFmt.format(tBean.getNetTotal()));
                    SelectStye(5);
                    print("TABLE  " + PUtility.DataFullR(tableNo, 5) + "   " + "จำนวนลูกค้า : " + IntFmt.format(tBean.getTCustomer()) + " คน");

                    SelectStye(1);
                    print(" ");
                    if (!CONFIG.getP_PrintRecpMessage().equals("")) {
                        print(CONFIG.getP_PrintRecpMessage());
                    }

                    if (!POSHW.getFootting1().equals("")) {
                        print(POSHW.getFootting1());
                    }
                    if (!POSHW.getFootting2().equals("")) {
                        print(POSHW.getFootting2());
                    }
                    print(" ");
                    SelectStye(1);
                    print("");
                    print(" ");
                    print(" ");
                    print(" ");

                    CutPaper();
                    closePrint();
                }
            }
        }

    }

    public String GetChargeType(int ChargeType) {
        String TempStr = "";
        if (ChargeType == 1) {
            TempStr = "1) เลี้ยงรับรอง ";
        }
        if (ChargeType == 2) {
            TempStr = "2) สินค้าตัวอย่าง ";
        }
        if (ChargeType == 3) {
            TempStr = "3) กิจกรรมเพื่อสังคม ";
        }
        if (ChargeType == 4) {
            TempStr = "4) อบรมภายใน ";
        }
        if (ChargeType == 5) {
            TempStr = "5) อาหารพนักงาน ";
        }
        return TempStr;
    }

    public static int SearchArray(int key, int[] list) {
        int ans = -1;
        for (int i = 0; i < list.length; i++) {
            if (key == list[i]) {
                ans = i;
            }
        }
        return ans;
    }

    public void CutPaperForT70() {
        try {
            byte Str[] = {29, 86, 1}; //init Printer
            outputStream.write(Str);
        } catch (IOException ex) {
            PUtility.showError(ex.getMessage());
        }
    }

    public void CutPaper() {
        try {
            byte Str[] = {27, 105, 0}; //init Printer
            outputStream.write(Str);
        } catch (IOException ex) {
            PUtility.showError(ex.getMessage());
        }
    }

    public void PrintTableAction() {
        if (Value.printdriver) {
            PrintTableActionDriver();
        } else if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print("");
                print(POSHW.getHeading1());
                print(POSHW.getHeading2());
                print(POSHW.getHeading3());
                print(POSHW.getHeading4());
                print("");
                print("REG ID :" + Value.MACNO);
                print("      รายงานโต๊ะค้าง (ยังไม่ได้ชำระเงิน) ");
                print("               Table Check        ");
                Date dateP = new Date();
                print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);

                print("----------------------------------------");
                print("Table     Amount    Open-Time  Customer");
                print("----------------------------------------");
                Double SumTotal = 0.0;
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String ChkTable = "select r_table,sum(r_total),r_void,tlogintime,tcustomer from balance"
                            + " left join tablefile on balance.r_table=tablefile.tcode where (r_void<>'V') or (r_void is null) group by r_table";
                    ResultSet rec = stmt.executeQuery(ChkTable);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        do {
                            print(PUtility.DataFull(rec.getString("r_table"), 6)
                                    + PUtility.DataFull(DecFmt.format(rec.getDouble("sum(r_total)")), 10) + "     "
                                    + PUtility.DataFull(rec.getString("tlogintime"), 8) + "  "
                                    + PUtility.DataFull(IntFmt.format(rec.getInt("tcustomer")), 5));
                            SumTotal = SumTotal + rec.getDouble("sum(r_total)");
                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                print("-----------------------------------------");
                print("Total " + PUtility.DataFull(DecFmt.format(SumTotal), 10));
                print("-----------------------------------------");
                print("");
                print("");
                CutPaper();
                closePrint();
            }
        }
    }

    public void PrintTerminal(FinalcialRec frec, CreditRec[] CrArray) {
        Date dateP = new Date();
        if (Value.printdriver) {
            PrintTerminalDriver();
        } else if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print(POSHW.getHeading1());
                print(POSHW.getHeading2());
                print(POSHW.getHeading3());
                print(POSHW.getHeading4());
                print("REG ID :" + Value.MACNO);
                print("   รายงานยอดการเงิน (Terminal Report)");
                print("หมายเลขเครื่อง : " + Value.MACNO);

                print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                print("----------------------------------------");
                print(PUtility.DataFullR("ยอดรวมค่าอาหาร                 ", 26) + PUtility.DataFull(DecFmt.format(frec.Food), 13));
                print(PUtility.DataFullR("ยอดรวมค่าเครื่องดื่ม               ", 26) + PUtility.DataFull(DecFmt.format(frec.Drink), 13));
                print(PUtility.DataFullR("ยอดรวมค่าสินค้าทั่วไป              ", 26) + PUtility.DataFull(DecFmt.format(frec.Product), 13));
                print(PUtility.DataFullR("ยอดขายตามป้าย (Dept-Sum)       ", 26) + PUtility.DataFull(DecFmt.format(frec.Dept_Sum), 13));
                print("                       ==============");
                print(PUtility.DataFullR("ค่าบริการ Service       ", 20) + PUtility.DataFull(IntFmt.format(frec.ServiceCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Service), 13));
                print(PUtility.DataFullR("Charge บัตรเครดิต       ", 20) + PUtility.DataFull(IntFmt.format(frec.ChargeCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Charge), 13));
                print(PUtility.DataFullR("ส่วนลดสมาชิก VIP        ", 20) + PUtility.DataFull(IntFmt.format(frec.Vip_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Vip_Disc), 13));
                print(PUtility.DataFullR("ส่วนลดเทศกาล           ", 20) + PUtility.DataFull(IntFmt.format(frec.Fast_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Fast_Disc), 13));
                print(PUtility.DataFullR("ส่วนลดพนักงาน           ", 20) + PUtility.DataFull(IntFmt.format(frec.Emp_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Emp_Disc), 13));
                print(PUtility.DataFullR("ส่วนลดพนักงาน Trainee   ", 20) + PUtility.DataFull(IntFmt.format(frec.Train_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Train_Disc), 13));
                print(PUtility.DataFullR("ส่วนลดคูปอง              ", 20) + PUtility.DataFull(IntFmt.format(frec.Sub_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Sub_Disc), 13));
                print(PUtility.DataFullR("ส่วนลดบาท               ", 20) + PUtility.DataFull(IntFmt.format(frec.Gen_RefundCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Gen_Refund), 13));
                print(PUtility.DataFullR("ส่วนลด Promotion        ", 20) + PUtility.DataFull(IntFmt.format(frec.PromotionCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Promotion), 13));
                print(PUtility.DataFullR("ส่วนลดพิเศษ (Special)    ", 20) + PUtility.DataFull(IntFmt.format(frec.SpacialCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Spacial), 13));
                print(PUtility.DataFullR("ส่วนลดตามรายการ (Item)  ", 20) + PUtility.DataFull(IntFmt.format(frec.Item_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Item_Disc), 13));
                print(PUtility.DataFullR("ส่วนลดบัตรคูปอง (Coupon)  ", 20) + PUtility.DataFull(IntFmt.format(frec.Cupon_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Cupon_Disc), 13));
                print(PUtility.DataFullR("หักคืนเงินมัดจำ            ", 20) + PUtility.DataFull(IntFmt.format(frec.EarnestCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Earnest), 13));
                print("----------------------------------------");
                print(PUtility.DataFullR("ยอดขายสุทธิ (Net-Sales)           ", 26) + PUtility.DataFull(DecFmt.format(frec.Net_Sale), 13));
                print("                       ==============");
                print(PUtility.DataFullR("เงินสด Cash             ", 20) + PUtility.DataFull(IntFmt.format(frec.CashCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Cash), 13));
                print(PUtility.DataFullR("บัตรกำนัล Gift Voucher   ", 20) + PUtility.DataFull(IntFmt.format(frec.GiftCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Gift), 13));
                print(PUtility.DataFullR("ลูกหนี้การค้า              ", 20) + PUtility.DataFull(IntFmt.format(frec.ArPaymentCnt), 6) + PUtility.DataFull(DecFmt.format(frec.ArPayment), 13));

                if (frec.Credit_Card > 0) {
                    if (CrArray != null) {
                        int ArraySize = CrArray.length;
                        for (int i = 0; i < ArraySize; i++) {
                            print(PUtility.DataFullR(CrArray[i].CrName + "                     ", 20) + PUtility.DataFull(IntFmt.format(CrArray[i].CrCnt), 6) + PUtility.DataFull(DecFmt.format(CrArray[i].CrAmt), 13));
                        }
                    }
                }
                print(PUtility.DataFullR("PAID-IN                ", 20) + PUtility.DataFull(IntFmt.format(frec.Paid_InCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Paid_In), 13));
                print(PUtility.DataFullR("PAID-OUT               ", 20) + PUtility.DataFull(IntFmt.format(frec.Paid_OutCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Paid_Out), 13));
                print("----------------------------------------");
                print(PUtility.DataFullR("ยอดขายสินค้า/บริการ คิดภาษี       ", 26) + PUtility.DataFull(DecFmt.format(frec.SaleVat), 13));
                print(PUtility.DataFullR("ยอดขายไม่คิดภาษี                ", 26) + PUtility.DataFull(DecFmt.format(frec.SaleNonVat), 13));
                print(PUtility.DataFullR("ภาษีมูลค่าเพิ่ม (Vat)             ", 26) + PUtility.DataFull(DecFmt.format(frec.VatAmt), 13));
                print("----------------------------------------");
                print(PUtility.DataFullR("จำนวนลูกค้าทั้งสิ้น                ", 26) + PUtility.DataFull(IntFmt.format(frec.Customer), 8) + " คน");
                print(PUtility.DataFullR("จำนวนใบกำกับภาษีอย่างย่อ         ", 26) + PUtility.DataFull(IntFmt.format(frec.CntBill), 8) + " ใบ");
                print("    เลขที่เริ่มต้น  :" + frec.StBill + "   ถึง  : " + frec.SpBill);
                print(PUtility.DataFullR("ใบกำกับภาษีที่ยกเลิก       ", 20) + PUtility.DataFull(IntFmt.format(frec.CntBillVoid), 6) + PUtility.DataFull(DecFmt.format(frec.AmtBillVoid), 13));
                print(PUtility.DataFullR("มูลค่าสินค้าที่ทำการ Void   ", 20) + PUtility.DataFull(IntFmt.format(frec.CntVoid), 6) + PUtility.DataFull(DecFmt.format(frec.VoidValue), 13));
                print("----------------------------------------");
                print("ประเภทการขาย   บิล        ลูกค้า  จำนวนเงิน");
                print("----------------------------------------");
                print("Eat-In      " + PUtility.DataFull(IntFmt.format(frec.Eat_In_Cnt), 6) + PUtility.DataFull(IntFmt.format(frec.Eat_In_Cust), 8) + PUtility.DataFull(DecFmt.format(frec.Eat_In_Amt), 13));
                print("Take Away   " + PUtility.DataFull(IntFmt.format(frec.Take_AwayCnt), 6) + PUtility.DataFull(IntFmt.format(frec.Take_AwayCust), 8) + PUtility.DataFull(DecFmt.format(frec.Take_AwayAmt), 13));
                print("Delivery    " + PUtility.DataFull(IntFmt.format(frec.DeliveryCnt), 6) + PUtility.DataFull(IntFmt.format(frec.DeliveryCust), 8) + PUtility.DataFull(DecFmt.format(frec.DeliveryAmt), 13));
                print("Pinto       " + PUtility.DataFull(IntFmt.format(frec.PintoCnt), 6) + PUtility.DataFull(IntFmt.format(frec.PintoCust), 8) + PUtility.DataFull(DecFmt.format(frec.PintoAmt), 13));
                print("Whole Sales " + PUtility.DataFull(IntFmt.format(frec.WholeCnt), 6) + PUtility.DataFull(IntFmt.format(frec.WholeCust), 8) + PUtility.DataFull(DecFmt.format(frec.WholeAmt), 13));
                print("----------------------------------------");
                print("");
                print("");
                print("");

                Double SumAmt = 0.0;
                print("    รายงานการรับชำระจากลูกหนี้ภายนอก ");
                print("          AR Payment Report");
                print("หมายเลขเครื่อง : " + Value.MACNO);

                print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                print("----------------------------------------");
                print("AR Code    เลขที่ใบเสร็จรับเงิน/วันที่  จำนวนเงิน");
                print("----------------------------------------");
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String SqlQuery = "select *from t_ar where (fat<>'V') and (terminal='" + Value.MACNO + "')";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        do {
                            print(PUtility.DataFull(rec.getString("arcode"), 4) + "  " + rec.getString("billno") + "  " + ShowDatefmt.format(rec.getDate("billdate")) + PUtility.DataFull(DecFmt.format(rec.getDouble("amount")), 9));
                            SumAmt = SumAmt + rec.getDouble("amount");
                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                print("----------------------------------------");
                print(PUtility.DataFullR("Total Amount  ", 26) + PUtility.DataFull(DecFmt.format(SumAmt), 13));
                print("----------------------------------------");
                print("");
                Double SumCash = 0.0;
                Double SumCupon = 0.0;
                int CntBill = 0;
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String SqlQuery = "select *from billar where (fat<>'V') and (terminal='" + Value.MACNO + "')";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        do {
                            CntBill++;
                            SumCash = SumCash + rec.getDouble("cash");
                            SumCupon = SumCupon + rec.getDouble("cupon");
                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                print(PUtility.DataFullR("     เงินสด Cash              ", 26) + PUtility.DataFull(DecFmt.format(SumCash), 13));
                print(PUtility.DataFullR("     บัตรกำนัล Coupon          ", 26) + PUtility.DataFull(DecFmt.format(SumCupon), 13));
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String SqlQuery = "select *from t_crar where (fat<>'V') and (terminal='" + Value.MACNO + "')";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        do {
                            print(PUtility.DataFullR(PUtility.SeekCreditName(rec.getString("crcode") + "                "), 20) + PUtility.DataFull(IntFmt.format(rec.getInt("crcnt")), 6) + PUtility.DataFull(DecFmt.format(rec.getDouble("cramt")), 13));

                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                print("ยอดรับชำระ AR    : " + PUtility.DataFull(IntFmt.format(CntBill), 6));
                print("----------------------------------------");
                print(" ");
                print("ยอดยกเลิกรายการ การรับชำระจากลูกหนี้ภายนอก");
                print("AR Pay-No    Amount  Mac  User User Void ");
                print("----------------------------------------");
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String SqlQuery = "select *from billar where (fat='V') and (terminal='" + Value.MACNO + "')";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        do {
                            print(rec.getString("ref_no") + "  " + PUtility.DataFull(DecFmt.format(rec.getDouble("stotal")), 9) + "  " + rec.getString("terminal") + "  " + PUtility.DataFull(rec.getString("cashier"), 6) + "  " + PUtility.DataFull(rec.getString("uservoid"), 6));

                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                print("----------------------------------------");
                print("");
                print("");
                print("");
                CutPaper();
                closePrint();
            }
        }
    }

    public void PrintCashier(FinalcialRec frec, CreditRec[] CrArray) {
        if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print(POSHW.getHeading1());
                print(POSHW.getHeading2());
                print(POSHW.getHeading3());
                print(POSHW.getHeading4());
                print("REG ID :" + Value.MACNO);
                print("   รายงานพนักงานขาย (Cashier Report)");
                print("รหัสพนักงาน : " + frec.Cashier1);
                Date dateP = new Date();
                print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                print("----------------------------------------");
                print(PUtility.DataFullR("ยอดรวมค่าอาหาร                 ", 26) + PUtility.DataFull(DecFmt.format(frec.Food), 13));
                print(PUtility.DataFullR("ยอดรวมค่าเครื่องดื่ม               ", 26) + PUtility.DataFull(DecFmt.format(frec.Drink), 13));
                print(PUtility.DataFullR("ยอดรวมค่าสินค้าทั่วไป              ", 26) + PUtility.DataFull(DecFmt.format(frec.Product), 13));
                print(PUtility.DataFullR("ยอดขายตามป้าย (Dept-Sum)       ", 26) + PUtility.DataFull(DecFmt.format(frec.Dept_Sum), 13));
                print("                       ==============");
                print(PUtility.DataFullR("ค่าบริการ Service       ", 20) + PUtility.DataFull(IntFmt.format(frec.ServiceCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Service), 13));
                print(PUtility.DataFullR("Charge บัตรเครดิต       ", 20) + PUtility.DataFull(IntFmt.format(frec.ChargeCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Charge), 13));
                print(PUtility.DataFullR("ส่วนลดสมาชิก VIP        ", 20) + PUtility.DataFull(IntFmt.format(frec.Vip_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Vip_Disc), 13));
                print(PUtility.DataFullR("ส่วนลดเทศกาล           ", 20) + PUtility.DataFull(IntFmt.format(frec.Fast_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Fast_Disc), 13));
                print(PUtility.DataFullR("ส่วนลดพนักงาน           ", 20) + PUtility.DataFull(IntFmt.format(frec.Emp_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Emp_Disc), 13));
                print(PUtility.DataFullR("ส่วนลดพนักงาน Trainee   ", 20) + PUtility.DataFull(IntFmt.format(frec.Train_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Train_Disc), 13));
                print(PUtility.DataFullR("ส่วนลดคูปอง              ", 20) + PUtility.DataFull(IntFmt.format(frec.Sub_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Sub_Disc), 13));
                print(PUtility.DataFullR("ส่วนลดบาท               ", 20) + PUtility.DataFull(IntFmt.format(frec.Gen_RefundCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Gen_Refund), 13));
                print(PUtility.DataFullR("ส่วนลด Promotion        ", 20) + PUtility.DataFull(IntFmt.format(frec.PromotionCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Promotion), 13));
                print(PUtility.DataFullR("ส่วนลดพิเศษ (Special)    ", 20) + PUtility.DataFull(IntFmt.format(frec.SpacialCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Spacial), 13));
                print(PUtility.DataFullR("ส่วนลดตามรายการ (Item)  ", 20) + PUtility.DataFull(IntFmt.format(frec.Item_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Item_Disc), 13));
                print(PUtility.DataFullR("ส่วนลดบัตรคูปอง (Coupon)  ", 20) + PUtility.DataFull(IntFmt.format(frec.Cupon_DiscCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Cupon_Disc), 13));
                print(PUtility.DataFullR("หักคืนเงินมัดจำ            ", 20) + PUtility.DataFull(IntFmt.format(frec.EarnestCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Earnest), 13));
                print("----------------------------------------");
                print(PUtility.DataFullR("ยอดขายสุทธิ (Net-Sales)           ", 26) + PUtility.DataFull(DecFmt.format(frec.Net_Sale), 13));
                print("                       ==============");
                print(PUtility.DataFullR("เงินสด Cash             ", 20) + PUtility.DataFull(IntFmt.format(frec.CashCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Cash), 13));
                print(PUtility.DataFullR("บัตรกำนัล Gift Voucher   ", 20) + PUtility.DataFull(IntFmt.format(frec.GiftCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Gift), 13));
                print(PUtility.DataFullR("ลูกหนี้การค้า              ", 20) + PUtility.DataFull(IntFmt.format(frec.ArPaymentCnt), 6) + PUtility.DataFull(DecFmt.format(frec.ArPayment), 13));
                if (frec.Credit_Card > 0) {
                    if (CrArray != null) {
                        int ArraySize = CrArray.length;
                        for (int i = 0; i < ArraySize; i++) {
                            print(PUtility.DataFullR(CrArray[i].CrName + "                     ", 20) + PUtility.DataFull(IntFmt.format(CrArray[i].CrCnt), 6) + PUtility.DataFull(DecFmt.format(CrArray[i].CrAmt), 13));
                        }
                    }
                }
                print(PUtility.DataFullR("PAID-IN                ", 20) + PUtility.DataFull(IntFmt.format(frec.Paid_InCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Paid_In), 13));
                print(PUtility.DataFullR("PAID-OUT               ", 20) + PUtility.DataFull(IntFmt.format(frec.Paid_OutCnt), 6) + PUtility.DataFull(DecFmt.format(frec.Paid_Out), 13));
                print("----------------------------------------");
                print(PUtility.DataFullR("ยอดขายสินค้า/บริการ คิดภาษี       ", 26) + PUtility.DataFull(DecFmt.format(frec.SaleVat), 13));
                print(PUtility.DataFullR("ยอดขายไม่คิดภาษี                ", 26) + PUtility.DataFull(DecFmt.format(frec.SaleNonVat), 13));
                print(PUtility.DataFullR("ภาษีมูลค่าเพิ่ม (Vat)             ", 26) + PUtility.DataFull(DecFmt.format(frec.VatAmt), 13));
                print("----------------------------------------");
                print(PUtility.DataFullR("จำนวนลูกค้าทั้งสิ้น                ", 26) + PUtility.DataFull(IntFmt.format(frec.Customer), 8) + " คน");
                print(PUtility.DataFullR("จำนวนใบกำกับภาษีอย่างย่อ         ", 26) + PUtility.DataFull(IntFmt.format(frec.CntBill), 8) + " ใบ");
                print("    เลขที่เริ่มต้น  :" + frec.StBill + "   ถึง  : " + frec.SpBill);
                print(PUtility.DataFullR("ใบกำกับภาษีที่ยกเลิก       ", 20) + PUtility.DataFull(IntFmt.format(frec.CntBillVoid), 6) + PUtility.DataFull(DecFmt.format(frec.AmtBillVoid), 13));
                print(PUtility.DataFullR("มูลค่าสินค้าที่ทำการ Void   ", 20) + PUtility.DataFull(IntFmt.format(frec.CntVoid), 6) + PUtility.DataFull(DecFmt.format(frec.VoidValue), 13));
                print("----------------------------------------");
                print("ประเภทการขาย   บิล        ลูกค้า  จำนวนเงิน");
                print("----------------------------------------");
                print("Eat-In      " + PUtility.DataFull(IntFmt.format(frec.Eat_In_Cnt), 6) + PUtility.DataFull(IntFmt.format(frec.Eat_In_Cust), 8) + PUtility.DataFull(DecFmt.format(frec.Eat_In_Amt), 13));
                print("Take Away   " + PUtility.DataFull(IntFmt.format(frec.Take_AwayCnt), 6) + PUtility.DataFull(IntFmt.format(frec.Take_AwayCust), 8) + PUtility.DataFull(DecFmt.format(frec.Take_AwayAmt), 13));
                print("Delivery    " + PUtility.DataFull(IntFmt.format(frec.DeliveryCnt), 6) + PUtility.DataFull(IntFmt.format(frec.DeliveryCust), 8) + PUtility.DataFull(DecFmt.format(frec.DeliveryAmt), 13));
                print("Pinto       " + PUtility.DataFull(IntFmt.format(frec.PintoCnt), 6) + PUtility.DataFull(IntFmt.format(frec.PintoCust), 8) + PUtility.DataFull(DecFmt.format(frec.PintoAmt), 13));
                print("Whole Sales " + PUtility.DataFull(IntFmt.format(frec.WholeCnt), 6) + PUtility.DataFull(IntFmt.format(frec.WholeCust), 8) + PUtility.DataFull(DecFmt.format(frec.WholeAmt), 13));
                print("----------------------------------------");
                print("");
                print("");
                print("");

                Double SumAmt = 0.0;
                print("    รายงานการรับชำระจากลูกหนี้ภายนอก ");
                print("          AR Payment Report");
                print("รหัสพนักงานขาย : " + frec.Cashier1);
                print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                print("----------------------------------------");
                print("AR Code    เลขที่ใบเสร็จรับเงิน/วันที่  จำนวนเงิน");
                print("----------------------------------------");
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String SqlQuery = "select *from t_ar where (fat<>'V') and (cashier='" + frec.Cashier1 + "')";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        do {
                            print(PUtility.DataFull(rec.getString("arcode"), 4) + "  " + rec.getString("billno") + "  " + ShowDatefmt.format(rec.getDate("billdate")) + PUtility.DataFull(DecFmt.format(rec.getDouble("amount")), 9));
                            SumAmt = SumAmt + rec.getDouble("amount");
                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                print("----------------------------------------");
                print(PUtility.DataFullR("Total Amount  ", 26) + PUtility.DataFull(DecFmt.format(SumAmt), 13));
                print("----------------------------------------");
                print("");
                Double SumCash = 0.0;
                Double SumCupon = 0.0;
                int CntBill = 0;
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String SqlQuery = "select *from billar where (fat<>'V') and (cashier='" + frec.Cashier1 + "')";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        do {
                            CntBill++;
                            SumCash = SumCash + rec.getDouble("cash");
                            SumCupon = SumCupon + rec.getDouble("cupon");
                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                print(PUtility.DataFullR("     เงินสด Cash              ", 26) + PUtility.DataFull(DecFmt.format(SumCash), 13));
                print(PUtility.DataFullR("     บัตรกำนัล Coupon          ", 26) + PUtility.DataFull(DecFmt.format(SumCupon), 13));
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String SqlQuery = "select *from t_crar where (fat<>'V') and (cashier='" + frec.Cashier1 + "')";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        do {
                            print(PUtility.DataFullR(PUtility.SeekCreditName(rec.getString("crcode") + "                "), 20) + PUtility.DataFull(IntFmt.format(rec.getInt("crcnt")), 6) + PUtility.DataFull(DecFmt.format(rec.getDouble("cramt")), 13));

                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                print("ยอดรับชำระ AR    : " + PUtility.DataFull(IntFmt.format(CntBill), 6));
                print("----------------------------------------");
                print(" ");
                print("ยอดยกเลิกรายการ การรับชำระจากลูกหนี้ภายนอก");
                print("AR Pay-No    Amount  Mac  User User Void ");
                print("----------------------------------------");
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String SqlQuery = "select *from billar where (fat='V') and (cashier='" + frec.Cashier1 + "')";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        do {
                            print(rec.getString("ref_no") + "  " + PUtility.DataFull(DecFmt.format(rec.getDouble("stotal")), 9) + "  " + rec.getString("terminal") + "  " + PUtility.DataFull(rec.getString("cashier"), 6) + "  " + PUtility.DataFull(rec.getString("uservoid"), 6));

                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                print("----------------------------------------");
                print("");
                print("");
                print("");

                print("    รายงานการทำรายการ Void ");
                print("          Void Report");
                print("รหัสพนักงานขาย : " + frec.Cashier1);
                print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                print("----------------------------------------");
                print("Mac Cashier Table Time  User-Void T_Void");
                print("    Ref-No  PLU-Code       Qty    Amount");
                print("----------------------------------------");
                int SumVoid = 0;
                Double SumAmount = 0.0;
                try {
                    Statement stmt = MySQLConnect.con.createStatement();
                    String SqlQuery = "select *from t_sale where (r_void='V') and (cashier='" + frec.Cashier1 + "')";
                    ResultSet rec = stmt.executeQuery(SqlQuery);
                    rec.first();
                    if (rec.getRow() == 0) {
                    } else {
                        do {
                            print(rec.getString("macno") + " " + PUtility.DataFullR(rec.getString("cashier"), 6) + " " + PUtility.DataFullR(rec.getString("r_table"), 5) + " " + PUtility.DataFullR(rec.getString("r_time"), 6) + "  " + PUtility.DataFullR(rec.getString("r_voiduser"), 10) + " " + PUtility.DataFullR(rec.getString("r_voidtime"), 6));
                            print("     " + PUtility.DataFullR(rec.getString("r_pname"), 35));
                            print("     " + rec.getString("r_refno") + " " + PUtility.DataFull(rec.getString("r_plucode"), 13) + " " + PUtility.DataFull(IntFmt.format(rec.getDouble("r_quan")), 4) + " " + PUtility.DataFull(DecFmt.format(rec.getDouble("r_total")), 8));
                            SumVoid++;
                            SumAmount = SumAmount + rec.getDouble("r_total");
                        } while (rec.next());
                    }
                    rec.close();
                    stmt.close();
                } catch (SQLException e) {
                    PUtility.showError(e.getMessage());
                }
                print("----------------------------------------");
                print("จำนวน Void :" + PUtility.DataFull(IntFmt.format(SumVoid), 5) + "  จำนวนเงิน :" + PUtility.DataFull(DecFmt.format(SumAmount), 11));
                print("----------------------------------------");
                print("");
                print("");
                print("");
                CutPaper();
                closePrint();
            }
        }
    }

    public void PrintGroup(PluRec[] GArray) {
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
        if (Value.printdriver) {
            JOptionPane.showMessageDialog(null, Value.driverNotSupport);
        } else if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print(POSHW.getHeading1());
                print(POSHW.getHeading2());
                print(POSHW.getHeading3());
                print(POSHW.getHeading4());
                print("REG ID :" + Value.MACNO);
                print("         รายงานการขายตามกลุ่มสินค้า");
                print("           (Department Report)");
                print("หมายเลขเครื่อง :" + GArray[0].MacNo1 + " ..." + GArray[0].MacNo2);
                print("พนักงานขาย    :" + GArray[0].Cashier1 + "..." + GArray[0].Cashier2);
                print("รหัสกลุ่มสินค้า (Dept/Group) " + GArray[0].Group1 + "..." + GArray[0].Group2);
                print(" ");
                Date dateP = new Date();
                print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                print("----------------------------------------");
                print("รายละเอียด");
                print("    .....EAT IN.....   ...TAKE AWAY.....");
                print("    ....DELIVERY....   .....PINTO.......");
                print("    ...WHOLE SALE...   .....TOTAL.......");
                print("----------------------------------------");
                if (GArray[0].S_Qty > 0) {
                    for (int i = 0; i < ArraySize; i++) {

                        print(PUtility.DataFullR(GArray[i].GroupCode, 4) + "  " + GArray[i].GroupName);
                        print(PUtility.DataFull(IntFmt.format(GArray[i].E_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].E_Amt), 12) + PUtility.DataFull(IntFmt.format(GArray[i].T_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].T_Amt), 12));
                        print(PUtility.DataFull(IntFmt.format(GArray[i].D_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].D_Amt), 12) + PUtility.DataFull(IntFmt.format(GArray[i].P_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].P_Amt), 12));
                        print(PUtility.DataFull(IntFmt.format(GArray[i].W_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].W_Amt), 12) + PUtility.DataFull(IntFmt.format(GArray[i].S_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].S_Amt), 12));

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
                print("----------------------------------------");
                print("***Subtotal***");
                print(PUtility.DataFull(IntFmt.format(SumEQty), 6) + PUtility.DataFull(DecFmt.format(SumEAmt), 12) + PUtility.DataFull(IntFmt.format(SumTQty), 6) + PUtility.DataFull(DecFmt.format(SumTAmt), 12));
                print(PUtility.DataFull(IntFmt.format(SumDQty), 6) + PUtility.DataFull(DecFmt.format(SumDAmt), 12) + PUtility.DataFull(IntFmt.format(SumPQty), 6) + PUtility.DataFull(DecFmt.format(SumPAmt), 12));
                print(PUtility.DataFull(IntFmt.format(SumWQty), 6) + PUtility.DataFull(DecFmt.format(SumWAmt), 12) + PUtility.DataFull(IntFmt.format(SumSQty), 6) + PUtility.DataFull(DecFmt.format(SumSAmt), 12));

                print("----------------------------------------");
                print(" ");
                print(" ");
                print(" ");
                CutPaper();
                closePrint();
            }
        }
    }

    public void PrintCharge2(PluRec[] GArray) {
        Double SumEQty = 0.0;
        Double SumEAmt = 0.0;
        Double SumTQty = 0.0;
        Double SumTAmt = 0.0;
        int ArraySize = GArray.length;
        if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print(POSHW.getHeading1());
                print(POSHW.getHeading2());
                print(POSHW.getHeading3());
                print(POSHW.getHeading4());
                print("REG ID :" + Value.MACNO);
                print("      รายงานการเบิกสินค้าประจำวัน By DEPT");
                print("แสดงรวมทุกเครื่อง");
                print("รหัสกลุ่มสินค้า (Dept/Group) " + GArray[0].Group1 + "..." + GArray[0].Group2);
                print(" ");
                Date dateP = new Date();
                print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                print("----------------------------------------");
                print("DEPT");
                print(" ชาร์จหน่วยงาน           ชาร์จบุคคล  ");
                print(" รายการ/บาท)          (รายการ/บาท) ");
                print("----------------------------------------");
                if (GArray[0].S_Qty > 0) {
                    for (int i = 0; i < ArraySize; i++) {

                        print(PUtility.DataFullR(GArray[i].GroupCode, 4) + "  " + GArray[i].GroupName);
                        print(PUtility.DataFull(IntFmt.format(GArray[i].E_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].E_Amt), 12) + PUtility.DataFull(IntFmt.format(GArray[i].T_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].T_Amt), 12));

                        SumEQty = SumEQty + GArray[i].E_Qty;
                        SumEAmt = SumEAmt + GArray[i].E_Amt;
                        SumTQty = SumTQty + GArray[i].T_Qty;
                        SumTAmt = SumTAmt + GArray[i].T_Amt;

                    }
                }
                print("----------------------------------------");
                print("Sum-Total (รายการ/บาท)               ");
                print(" ชาร์จหน่วยงาน             ชาร์จบุคคล ");

                print(PUtility.DataFull(IntFmt.format(SumEQty), 6) + PUtility.DataFull(DecFmt.format(SumEAmt), 12) + PUtility.DataFull(IntFmt.format(SumTQty), 6) + PUtility.DataFull(DecFmt.format(SumTAmt), 12));

                print("----------------------------------------");
                print(" ");
                print(" ");
                print(" ");

                CutPaper();
                closePrint();
            }
        }
    }

    public void PrintMTDCharge2(PluRec[] GArray, Date TDate1, Date TDate2) {
        SimpleDateFormat DatefmtShow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Double SumEQty = 0.0;
        Double SumEAmt = 0.0;
        Double SumTQty = 0.0;
        Double SumTAmt = 0.0;
        int ArraySize = GArray.length;
        if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print(POSHW.getHeading1());
                print(POSHW.getHeading2());
                print(POSHW.getHeading3());
                print(POSHW.getHeading4());
                print("REG ID :" + Value.MACNO);
                print("      รายงานสรุปการเบิกสินค้า By DEPT");
                print("สาขา  " + PublicVar.Branch_Code + " " + PUtility.DataFull(PublicVar.Branch_Name, 25));
                print("ช่วงวันที่    " + DatefmtShow.format(TDate1) + "..." + DatefmtShow.format(TDate2));
                print("รหัสกลุ่มสินค้า (Dept/Group) " + GArray[0].Group1 + "..." + GArray[0].Group2);
                print(" ");
                Date dateP = new Date();
                print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                print("----------------------------------------");
                print("DEPT");
                print(" ชาร์จหน่วยงาน           ชาร์จบุคคล  ");
                print(" รายการ/บาท)          (รายการ/บาท) ");
                print("----------------------------------------");
                if (GArray[0].S_Qty > 0) {
                    for (int i = 0; i < ArraySize; i++) {

                        print(PUtility.DataFullR(GArray[i].GroupCode, 4) + "  " + GArray[i].GroupName);
                        print(PUtility.DataFull(IntFmt.format(GArray[i].E_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].E_Amt), 12) + PUtility.DataFull(IntFmt.format(GArray[i].T_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].T_Amt), 12));

                        SumEQty = SumEQty + GArray[i].E_Qty;
                        SumEAmt = SumEAmt + GArray[i].E_Amt;
                        SumTQty = SumTQty + GArray[i].T_Qty;
                        SumTAmt = SumTAmt + GArray[i].T_Amt;
                    }
                }
                print("----------------------------------------");
                print("Sum-Total (รายการ/บาท)               ");
                print(" ชาร์จหน่วยงาน             ชาร์จบุคคล ");

                print(PUtility.DataFull(IntFmt.format(SumEQty), 6) + PUtility.DataFull(DecFmt.format(SumEAmt), 12) + PUtility.DataFull(IntFmt.format(SumTQty), 6) + PUtility.DataFull(DecFmt.format(SumTAmt), 12));

                print("----------------------------------------");
                print(" ");
                print(" ");
                print(" ");
                CutPaper();
                closePrint();
            }
        }
    }

    public void PrintPlu(PluRec[] GArray) {
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
        if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print(POSHW.getHeading1());
                print(POSHW.getHeading2());
                print(POSHW.getHeading3());
                print(POSHW.getHeading4());
                print("REG ID :" + Value.MACNO);
                print("         รายงานการขายตามรหัสสินค้า");
                print("              (PLU Report)");
                print("หมายเลขเครื่อง :" + GArray[0].MacNo1 + " ..." + GArray[0].MacNo2);
                print("พนักงานขาย    :" + GArray[0].Cashier1 + "..." + GArray[0].Cashier2);
                print("รหัสกลุ่มสินค้า (Dept/Group) " + GArray[0].Group1 + "..." + GArray[0].Group2);
                print("รหัสสินค้า (PLU) " + GArray[0].Plu1 + "..." + GArray[0].Plu2);
                print(" ");
                Date dateP = new Date();
                print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                print("----------------------------------------");
                print("รายละเอียด");
                print("    .....EAT IN.....   ...TAKE AWAY.....");
                print("    ....DELIVERY....   .....PINTO.......");
                print("    ...WHOLE SALE...   .....TOTAL.......");
                print("----------------------------------------");
                String TempDept = "";
                if (ArraySize > 0) {
                    TempDept = "";
                }
                if (GArray[0].S_Qty > 0) {
                    for (int i = 0; i < ArraySize; i++) {
                        if (!GArray[i].GroupCode.equals(TempDept)) {
                            print(PUtility.DataFullR(GArray[i].GroupCode, 4) + "  " + GArray[i].GroupName);
                            TempDept = GArray[i].GroupCode;
                        }

                        print(PUtility.DataFullR(GArray[i].PCode + "  " + GArray[i].PName, 38));
                        print(PUtility.DataFull(IntFmt.format(GArray[i].E_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].E_Amt), 12) + PUtility.DataFull(IntFmt.format(GArray[i].T_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].T_Amt), 12));
                        print(PUtility.DataFull(IntFmt.format(GArray[i].D_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].D_Amt), 12) + PUtility.DataFull(IntFmt.format(GArray[i].P_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].P_Amt), 12));
                        print(PUtility.DataFull(IntFmt.format(GArray[i].W_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].W_Amt), 12) + PUtility.DataFull(IntFmt.format(GArray[i].S_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].S_Amt), 12));

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
                print("----------------------------------------");
                print("***Subtotal***");
                print(PUtility.DataFull(IntFmt.format(SumEQty), 6) + PUtility.DataFull(DecFmt.format(SumEAmt), 12) + PUtility.DataFull(IntFmt.format(SumTQty), 6) + PUtility.DataFull(DecFmt.format(SumTAmt), 12));
                print(PUtility.DataFull(IntFmt.format(SumDQty), 6) + PUtility.DataFull(DecFmt.format(SumDAmt), 12) + PUtility.DataFull(IntFmt.format(SumPQty), 6) + PUtility.DataFull(DecFmt.format(SumPAmt), 12));
                print(PUtility.DataFull(IntFmt.format(SumWQty), 6) + PUtility.DataFull(DecFmt.format(SumWAmt), 12) + PUtility.DataFull(IntFmt.format(SumSQty), 6) + PUtility.DataFull(DecFmt.format(SumSAmt), 12));

                print("----------------------------------------");
                print(" ");
                print(" ");
                print(" ");
                CutPaper();
                closePrint();
            }
        }
    }

    public void PrintCharge3(PluRec[] GArray) {
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
        if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print(POSHW.getHeading1());
                print(POSHW.getHeading2());
                print(POSHW.getHeading3());
                print(POSHW.getHeading4());
                print("REG ID :" + Value.MACNO);
                print("         รายงานการเบิกสินค้า By PLU");
                print("แสดงรวมทุกเครื่อง");
                print("รหัสกลุ่มสินค้า (Dept/Group) " + GArray[0].Group1 + "..." + GArray[0].Group2);
                print("รหัสสินค้า (PLU) " + GArray[0].Plu1 + "..." + GArray[0].Plu2);
                print(" ");
                Date dateP = new Date();
                print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                print("----------------------------------------");
                print("DEPT");
                print("PLU ");
                print(" ชาร์จหน่วยงาน            ชาร์จบุคคล  ");
                print(" รายการ/บาท)          (รายการ/บาท) ");
                print("----------------------------------------");
                String TempDept = "";
                if (ArraySize > 0) {
                    TempDept = "";
                }
                if (GArray[0].S_Qty > 0) {
                    for (int i = 0; i < ArraySize; i++) {
                        if (!GArray[i].GroupCode.equals(TempDept)) {
                            print(PUtility.DataFullR(GArray[i].GroupCode, 4) + "  " + GArray[i].GroupName);
                            TempDept = GArray[i].GroupCode;
                        }

                        print(PUtility.DataFullR(GArray[i].PCode + "  " + GArray[i].PName, 38));
                        print(PUtility.DataFull(IntFmt.format(GArray[i].E_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].E_Amt), 12) + PUtility.DataFull(IntFmt.format(GArray[i].T_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].T_Amt), 12));

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
                print("----------------------------------------");
                print("Sum-Total (รายการ/บาท)               ");
                print(" ชาร์จหน่วยงาน            ชาร์จบุคคล  ");

                print(PUtility.DataFull(IntFmt.format(SumEQty), 6) + PUtility.DataFull(DecFmt.format(SumEAmt), 12) + PUtility.DataFull(IntFmt.format(SumTQty), 6) + PUtility.DataFull(DecFmt.format(SumTAmt), 12));

                print("----------------------------------------");
                print(" ");
                print(" ");
                print(" ");
                CutPaper();
                closePrint();
            }
        }
    }

    public void PrintMTDCharge3(PluRec[] GArray, Date TDate1, Date TDate2) {
        SimpleDateFormat DatefmtShow = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
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
        if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print(POSHW.getHeading1());
                print(POSHW.getHeading2());
                print(POSHW.getHeading3());
                print(POSHW.getHeading4());
                print("REG ID :" + Value.MACNO);
                print("      รายงานการสรุปการเบิกสินค้า By PLU");
                print("สาขา  " + PublicVar.Branch_Code + " " + PUtility.DataFull(PublicVar.Branch_Name, 25));
                print("ช่วงวันที่    " + DatefmtShow.format(TDate1) + "..." + DatefmtShow.format(TDate2));
                print("รหัสกลุ่มสินค้า (Dept/Group) " + GArray[0].Group1 + "..." + GArray[0].Group2);
                print("รหัสสินค้า (PLU) " + GArray[0].Plu1 + "..." + GArray[0].Plu2);
                print(" ");
                Date dateP = new Date();
                print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                print("----------------------------------------");
                print("DEPT");
                print("PLU ");
                print(" ชาร์จหน่วยงาน            ชาร์จบุคคล  ");
                print(" รายการ/บาท)          (รายการ/บาท) ");
                print("----------------------------------------");
                String TempDept = "";
                if (ArraySize > 0) {
                    TempDept = "";
                }
                if (GArray[0].S_Qty > 0) {
                    for (int i = 0; i < ArraySize; i++) {
                        if (!GArray[i].GroupCode.equals(TempDept)) {
                            print("*** " + PUtility.DataFullR(GArray[i].GroupCode, 4) + "  " + GArray[i].GroupName);
                            TempDept = GArray[i].GroupCode;
                        }

                        print(PUtility.DataFullR(GArray[i].PCode + "  " + GArray[i].PName, 38));
                        print(PUtility.DataFull(IntFmt.format(GArray[i].E_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].E_Amt), 12) + PUtility.DataFull(IntFmt.format(GArray[i].T_Qty), 6) + PUtility.DataFull(DecFmt.format(GArray[i].T_Amt), 12));

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
                print("----------------------------------------");
                print("Sum-Total (รายการ/บาท)               ");
                print(" ชาร์จหน่วยงาน            ชาร์จบุคคล  ");

                print(PUtility.DataFull(IntFmt.format(SumEQty), 6) + PUtility.DataFull(DecFmt.format(SumEAmt), 12) + PUtility.DataFull(IntFmt.format(SumTQty), 6) + PUtility.DataFull(DecFmt.format(SumTAmt), 12));

                print("----------------------------------------");
                print(" ");
                print(" ");
                print(" ");
                CutPaper();
                closePrint();
            }
        }
    }

    public void PrintHourly(HourlyRec[] HArray, String MacNo1, String MacNo2) {
        Double SumB = 0.0;
        Double SumC = 0.0;
        Double SumS = 0.0;
        int ArraySize = HArray.length;
        if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print(POSHW.getHeading1());
                print(POSHW.getHeading2());
                print(POSHW.getHeading3());
                print(POSHW.getHeading4());
                print("REG ID :" + Value.MACNO);
                print("         รายงานการขายตามช่วงเวลา");
                print("            (Hourly Report)");
                print("หมายเลขเครื่อง :" + MacNo1 + " ..." + MacNo2);
                print(" ");
                Date dateP = new Date();
                print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                print("----------------------------------------");
                print("เวลา    จำนวนบิล   จำนวนลูกค้า    จำนวนเงิน");
                print("----------------------------------------");
                for (int i = 0; i < ArraySize; i++) {

                    print(HArray[i].TTime + "  " + PUtility.DataFull(IntFmt.format(HArray[i].TBill), 8) + PUtility.DataFull(IntFmt.format(HArray[i].TCust), 10) + PUtility.DataFull(DecFmt.format(HArray[i].TAmount), 13));

                    SumB = SumB + HArray[i].TBill;
                    SumC = SumC + HArray[i].TCust;
                    SumS = SumS + HArray[i].TAmount;

                }

                print("----------------------------------------");
                print("Total  " + PUtility.DataFull(IntFmt.format(SumB), 8) + PUtility.DataFull(IntFmt.format(SumC), 10) + PUtility.DataFull(DecFmt.format(SumS), 13));

                print("----------------------------------------");
                print(" ");
                print(" ");
                print(" ");
                CutPaper();
                closePrint();
            }
        }
    }

    public void PrintBillCopy(TranRecord[] MyArray1, String _RefNo, int ICopy, CreditPaymentRec[] CreditArray1) {
        BillControl bc = new BillControl();
        ArrayList<TSaleBean> listBean = bc.getAllTSale(_RefNo);

        int QtyLength = 5;
        int AmtLength = 10;
        int SubLength = 20;
        int SubLength2 = 13;
        int ItemCnt = 0;
        String VatStr;

        BillControl billControl = new BillControl();
        BillNoBean tBean = billControl.getData(_RefNo);

        if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print(POSHW.getHeading1());
                print(POSHW.getHeading2());
                print(POSHW.getHeading3());
                print(POSHW.getHeading4());
                print("REG ID :" + Value.MACNO);
                print("      ***สำเนาใบเสร็จรับเงิน " + Integer.toString(ICopy) + " ***");
                PublicVar.P_LineCount = 0;
                for (int i = 0; i < listBean.size(); i++) {
                    TSaleBean bean = (TSaleBean) listBean.get(i);
                    if (!bean.getR_Void().equals("V")) {
                        ItemCnt = (int) (ItemCnt + bean.getR_Quan());
                    }
                }
                CONFIG = POSConfigSetup.Bean();
                POSHW = POSHWSetup.Bean(Value.getMacno());
                if (CONFIG.getP_PrintDetailOnRecp().equals("Y")) {
                    Date dateP = new Date();
                    print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier" + PublicVar._User + " Mac" + Value.MACNO);
                    print("----------------------------------------");
                    for (int i = 0; i < listBean.size(); i++) {
                        TSaleBean bean = (TSaleBean) listBean.get(i);
                        if (bean.getR_Void().equals("V")) {
                            SelectStye(12);
                            print("VOID..." + "User :" + bean.getR_VoidUser());
                            if (bean.getR_Vat().equals("V")) {
                                VatStr = "-";
                            } else {
                                VatStr = "*";
                            }
                            if (CONFIG.getP_CodePrn().equals("Y")) {
                                print(bean.getR_PName());
                                print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(-1 * bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(-1 * bean.getR_Total()), AmtLength) + bean.getR_ETD());
                            } else {
                                print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(-1 * bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(-1 * bean.getR_Total()), AmtLength) + bean.getR_ETD());
                            }
                            SelectStye(13);
                        } else {
                            if (bean.getR_Vat().equals("V")) {
                                VatStr = "-";
                            } else {
                                VatStr = "*";
                            }
                            if (bean.getR_PrAmt() == 0) {
                                if (CONFIG.getP_CodePrn().equals("Y")) {
                                    print(bean.getR_PName());
                                    print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                } else {
                                    print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                }
                            } else {
                                if (CONFIG.getP_CodePrn().equals("Y")) {
                                    print(bean.getR_PName());
                                    print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                } else {
                                    print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                }
                                if (bean.getR_PrType().equals("-P")) {
                                    if (bean.getR_PrAmt() > 0) {
                                        print("   **Promotion  " + bean.getR_PrCode() + " " + PUtility.DataFull(PUtility.SeekPromotionName(bean.getR_PrCode()), 20));
                                    }
                                    if (bean.getR_PrAmt2() > 0) {
                                        print("   **Promotion  " + bean.getR_PrCode2() + " " + PUtility.DataFull(PUtility.SeekPromotionName(bean.getR_PrCode2()), 20));
                                    }
                                }
                                if (bean.getR_PrType().equals("-I")) {
                                    if (bean.getR_PrDisc() != 0) {
                                        print("   **Item-Discount " + bean.getR_PrCode() + " " + PUtility.DataFull(DecFmt.format(bean.getR_PrDisc() - bean.getR_Redule()), QtyLength) + "%");
                                    }
                                    if (bean.getR_PrAmt2() > 0) {
                                        print("   **Promotion  " + bean.getR_PrCode2() + " " + PUtility.DataFull(DecFmt.format(bean.getR_PrAmt()), AmtLength));
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Date dateP = new Date();
                    print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                    print("----------------------------------------");
                    print("     อาหารและเครื่องดื่ม " + PUtility.DataFull(DecFmt.format(tBean.getB_Total()), AmtLength));
                }

                print("----------------------------------------");
                print("Sub-TOTAL   " + "(Item" + PUtility.DataFull(IntFmt.format(ItemCnt), QtyLength) + " )     " + PUtility.DataFull(DecFmt.format(tBean.getB_Total()), AmtLength));
                if (tBean.getB_ProDiscAmt() > 0) {
                    print("    " + PUtility.DataFullR("ลด Promotion     ", SubLength) + PUtility.DataFull(DecFmt.format(tBean.getB_ProDiscAmt()), AmtLength));
                }
                if (tBean.getB_SpaDiscAmt() > 0) {
                    print("     " + PUtility.DataFullR("Special Disc     ", SubLength) + PUtility.DataFull(DecFmt.format(tBean.getB_SpaDiscAmt()), AmtLength));
                }
                if (tBean.getB_MemDiscAmt() > 0) {
                    print("     " + PUtility.DataFullR("ลดสมาชิก..........", SubLength2) + PUtility.DataFull(tBean.getB_MemDisc(), 8) + PUtility.DataFull(DecFmt.format(tBean.getB_MemDiscAmt()), AmtLength));
                }
                if (tBean.getB_FastDiscAmt() > 0) {
                    print("     " + PUtility.DataFullR("ลดเทศกาล.........", SubLength2) + PUtility.DataFull(tBean.getB_FastDisc(), 8) + PUtility.DataFull(DecFmt.format(tBean.getB_FastDiscAmt()), AmtLength));
                }
                if (tBean.getB_EmpDiscAmt() > 0) {
                    print("     " + PUtility.DataFullR("ลดพนักงาน.........", SubLength2) + PUtility.DataFull(tBean.getB_EmpDisc(), 8) + PUtility.DataFull(DecFmt.format(tBean.getB_EmpDiscAmt()), AmtLength));
                }
                if (tBean.getB_TrainDiscAmt() > 0) {
                    print("     " + PUtility.DataFullR("ลด Trainnee......", SubLength2) + PUtility.DataFull(tBean.getB_TrainDisc(), 8) + PUtility.DataFull(DecFmt.format(tBean.getB_TrainDiscAmt()), AmtLength));
                }
                if (tBean.getB_SubDiscAmt() > 0) {
                    print("     " + PUtility.DataFullR("ลดคูปอง...........", SubLength2) + PUtility.DataFull(tBean.getB_SubDisc(), 8) + PUtility.DataFull(DecFmt.format(tBean.getB_SubDiscAmt()), AmtLength));
                }
                if (tBean.getB_SubDiscBath() > 0) {
                    print("     " + PUtility.DataFullR("ลด(บาท)..........", SubLength) + PUtility.DataFull(DecFmt.format(tBean.getB_SubDiscBath()), AmtLength));
                }
                if (tBean.getB_ItemDiscAmt() > 0) {
                    print("     " + PUtility.DataFullR("ลดตามรายการ(Item)", SubLength) + PUtility.DataFull(DecFmt.format(tBean.getB_ItemDiscAmt()), AmtLength));
                }
                if (tBean.getB_CuponDiscAmt() > 0) {
                    try {
                        Statement stmt = MySQLConnect.con.createStatement();
                        String CheckCoupon = "select *from t_cupon where (r_refno='" + _RefNo + "') and (terminal='" + Value.MACNO + "') ";
                        ResultSet rec = stmt.executeQuery(CheckCoupon);
                        rec.first();
                        if (rec.getRow() == 0) {
                        } else {
                            do {
                                print("     " + PUtility.DataFullR(PUtility.SeekCuponName(rec.getString("cucode")), 20) + PUtility.DataFull(DecFmt.format(rec.getDouble("cuamt")), AmtLength));
                                String SMS_Code = rec.getString("sms_code");
                                if ((SMS_Code != null) & (!SMS_Code.equals(""))) {
                                    print("     " + "SMS CODE " + SMS_Code);
                                }
                            } while (rec.next());
                        }
                        rec.close();
                        stmt.close();
                    } catch (SQLException e) {
                        PUtility.showError(e.getMessage());
                    }
                }
                if (tBean.getB_ServiceAmt() > 0) {
                    print("     " + PUtility.DataFullR("ค่าบริการ (Service)     ", 23) + PUtility.DataFull(DecFmt.format(tBean.getB_ServiceAmt()), 9));
                }

                if (tBean.getB_Earnest() > 0) {
                    print("     " + PUtility.DataFullR("หักคืนเงินมัดจำ           ", 23) + PUtility.DataFull(DecFmt.format(tBean.getB_Earnest()), 9));
                }
                if (CONFIG.getP_VatType().equals("I")) {
                    //Print_Str(" ");
                    SelectStye(3);
                    print("Net-TOTAL " + PUtility.DataFull(DecFmt.format(tBean.getB_NetTotal()), AmtLength));
                    SelectStye(1);
                    if (CONFIG.getP_VatPrn().equals("Y")) {
                        print(PUtility.DataFull("              Vat...", 43) + PUtility.DataFull(DecFmt.format(tBean.getB_Vat()), AmtLength));
                    }
                } else {
                    print(PUtility.DataFull("      Net-Amount ", SubLength) + PUtility.DataFull(DecFmt.format(tBean.getB_NetVat() + tBean.getB_NetNonVat()), AmtLength));
                    print(PUtility.DataFull("      Vat....... ", SubLength) + PUtility.DataFull(DecFmt.format(CONFIG.getP_Vat()), QtyLength) + "%" + PUtility.DataFull(DecFmt.format(tBean.getB_Vat()), AmtLength));
                    print("VAT INCLUDED");
                }
                if (tBean.getB_GiftVoucher() > 0) {
                    print("     " + PUtility.DataFullR("บัตรกำนัล..............", SubLength) + PUtility.DataFull(DecFmt.format(tBean.getB_GiftVoucher()), AmtLength));
                    try {
                        Statement stmt = MySQLConnect.con.createStatement();
                        String CheckGift = "select *from t_gift where (refno='" + _RefNo + "')";
                        ResultSet rec = stmt.executeQuery(CheckGift);
                        rec.first();
                        if (rec.getRow() == 0) {
                        } else {
                            do {
                                print("   " + PUtility.DataFull(rec.getString("giftbarcode"), 25) + "@" + PUtility.DataFull(DecFmt.format(rec.getDouble("giftamt")), AmtLength));
                            } while (rec.next());
                        }
                        rec.close();
                        stmt.close();
                    } catch (SQLException e) {
                        PUtility.showError(e.getMessage());
                    }
                }
                if (tBean.getB_PayAmt() > 0) {
                    print("เงินสด  : " + PUtility.DataFull(DecFmt.format(tBean.getB_PayAmt()), AmtLength) + "  ทอน : " + PUtility.DataFull(DecFmt.format(tBean.getB_Ton()), AmtLength));
                }

                print("----------------------------------------");
                SelectStye(5);
                print("TABLE  " + PUtility.DataFullR(tBean.getB_Table(), 5) + "  " + "Receipt No: " + _RefNo);
                SelectStye(1);
                print("จำนวนลูกค้า : " + IntFmt.format(tBean.getB_Cust()) + " คน");
                print(" ");
                if (!CONFIG.getP_PrintRecpMessage().equals("")) {
                    print(CONFIG.getP_PrintRecpMessage());
                }

                if (!POSHW.getFootting1().equals("")) {
                    print(POSHW.getFootting1());
                }
                if (!POSHW.getFootting2().equals("")) {
                    print(POSHW.getFootting2());
                }

                print(" ");
                SelectStye(1);
                print("");
                print(" ");
                print(" ");
                print(" ");
                CutPaper();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PPrint.class.getName()).log(Level.SEVERE, null, ex);
                }
                closePrint();
            }
        }

    }

    public void PrintBillRefund(String _RefNo) {
        if (Value.printdriver) {
            PrintBillRefundDriver(_RefNo);
        } else {
            BillControl bc = new BillControl();
            ArrayList<TSaleBean> listBean = bc.getAllTSale(_RefNo);

            int QtyLength = 5;
            int AmtLength = 10;
            int SubLength = 20;
            int SubLength2 = 13;
            int ItemCnt = 0;
            String VatStr;

            BillControl bill = new BillControl();
            BillNoBean bBean = bill.getData(_RefNo);

            POSHW = POSHWSetup.Bean(Value.getMacno());
            CONFIG = POSConfigSetup.Bean();

            if (!Value.getComPort().equals("NONE")) {
                if (OpenPrint(Value.getComPort())) {
                    InitPrinter();
                    print(POSHW.getHeading1());
                    print(POSHW.getHeading2());
                    print(POSHW.getHeading3());
                    print(POSHW.getHeading4());
                    print("REG ID :" + Value.MACNO);
                    print("      ***บิลยกเลิกรายการขาย (Refund) ***");
                    print("Void User : " + PublicVar._User);
                    print("Void Date/Time : " + PUtility.CurDate());
                    SelectStye(3);
                    print("อ้างถึงใบเสร็จรับเงินเลขที่");
                    print(_RefNo);
                    SelectStye(1);
                    print("");
                    PublicVar.P_LineCount = 0;
                    for (int i = 0; i < listBean.size(); i++) {
                        TSaleBean bean = (TSaleBean) listBean.get(i);
                        if (!bean.getR_Void().equals("V")) {
                            ItemCnt = (int) (ItemCnt + bean.getR_Quan());
                        }
                    }
                    if (CONFIG.getP_PrintDetailOnRecp().equals("Y")) {
                        Date dateP = new Date();
                        print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                        print("----------------------------------------");
                        for (int i = 0; i < listBean.size(); i++) {
                            TSaleBean bean = (TSaleBean) listBean.get(i);
                            if (bean.getR_Void().equals("V")) {
                                SelectStye(12);
                                print("VOID..." + "User :" + bean.getR_VoidUser());
                                if (bean.getR_Vat().equals("V")) {
                                    VatStr = "-";
                                } else {
                                    VatStr = "*";
                                }
                                if (CONFIG.getP_CodePrn().equals("Y")) {
                                    print(bean.getR_PName());
                                    print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(-1 * bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(-1 * bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                } else {
                                    print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(-1 * bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(-1 * bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                }
                                SelectStye(13);
                            } else {
                                if (bean.getR_Vat().equals("V")) {
                                    VatStr = "-";
                                } else {
                                    VatStr = "*";
                                }
                                if (bean.getR_PrAmt() == 0) {
                                    if (CONFIG.getP_CodePrn().equals("Y")) {
                                        print(bean.getR_PName());
                                        print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                    } else {
                                        print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                    }
                                } else {
                                    if (CONFIG.getP_CodePrn().equals("Y")) {
                                        print(bean.getR_PName());
                                        print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                    } else {
                                        print(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                                    }
                                    if (bean.getR_PrType().equals("-P")) {
                                        if (bean.getR_PrAmt() > 0) {
                                            print("   **Promotion  " + bean.getR_PrCode() + " " + PUtility.DataFull(PUtility.SeekPromotionName(bean.getR_PrCode()), 20));
                                        }
                                        if (bean.getR_PrAmt2() > 0) {
                                            print("   **Promotion  " + bean.getR_PrCode2() + " " + PUtility.DataFull(PUtility.SeekPromotionName(bean.getR_PrCode2()), 20));
                                        }
                                    }
                                    if (bean.getR_PrType().equals("-I")) {
                                        if (bean.getR_PrDisc() != 0) {
                                            print("   **Item-Discount " + bean.getR_PrCode() + " " + PUtility.DataFull(DecFmt.format(bean.getR_PrDisc() - bean.getR_Redule()), QtyLength) + "%");
                                        }
                                        if (bean.getR_PrAmt2() > 0) {
                                            print("   **Promotion  " + bean.getR_PrCode2() + " " + PUtility.DataFull(DecFmt.format(bean.getR_PrAmt()), AmtLength));
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Date dateP = new Date();
                        print(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
                        print("----------------------------------------");
                        print("     อาหารและเครื่องดื่ม " + PUtility.DataFull(DecFmt.format(bBean.getB_Total()), AmtLength));
                    }
                    print("----------------------------------------");
                    print("Sub-TOTAL   " + "(Item" + PUtility.DataFull(IntFmt.format(ItemCnt), QtyLength) + " )     " + PUtility.DataFull(DecFmt.format(bBean.getB_Total()), AmtLength));
                    if (bBean.getB_ProDiscAmt() > 0) {
                        print("    " + PUtility.DataFullR("ลด Promotion     ", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_ProDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_SpaDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("Special Disc     ", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_SpaDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_MemDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดสมาชิก..........", SubLength2) + PUtility.DataFull(bBean.getB_MemDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_MemDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_FastDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดเทศกาล.........", SubLength2) + PUtility.DataFull(bBean.getB_FastDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_FastDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_EmpDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดพนักงาน.........", SubLength2) + PUtility.DataFull(bBean.getB_EmpDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_EmpDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_TrainDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลด Trainnee......", SubLength2) + PUtility.DataFull(bBean.getB_TrainDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_TrainDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_SubDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดคูปอง...........", SubLength2) + PUtility.DataFull(bBean.getB_SubDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_SubDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_SubDiscBath() > 0) {
                        print("     " + PUtility.DataFullR("ลด(บาท)..........", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_SubDiscBath()), AmtLength));
                    }
                    if (bBean.getB_ItemDiscAmt() > 0) {
                        print("     " + PUtility.DataFullR("ลดตามรายการ(Item)", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_ItemDiscAmt()), AmtLength));
                    }
                    if (bBean.getB_CuponDiscAmt() > 0) {
                        try {
                            Statement stmt = MySQLConnect.con.createStatement();
                            String CheckCoupon = "select *from t_cupon where (r_refno='" + _RefNo + "') and (terminal='" + Value.MACNO + "') ";
                            ResultSet rec = stmt.executeQuery(CheckCoupon);
                            Date dt = new Date();
                            rec.first();
                            if (rec.getRow() == 0) {
                            } else {
                                do {
                                    print("     " + PUtility.DataFullR(PUtility.SeekCuponName(rec.getString("cucode")), 20) + PUtility.DataFull(DecFmt.format(rec.getDouble("cuamt")), AmtLength));
                                    String SMS_Code = rec.getString("sms_code");
                                    if ((SMS_Code != null) & (!SMS_Code.equals(""))) {
                                        print("     " + "SMS CODE " + SMS_Code);
                                    }
                                } while (rec.next());
                            }
                            rec.close();
                            stmt.close();
                        } catch (SQLException e) {
                            PUtility.showError(e.getMessage());
                        }
                    }
                    if (bBean.getB_ServiceAmt() > 0) {
                        print("     " + PUtility.DataFullR("ค่าบริการ (Service)     ", 23) + PUtility.DataFull(DecFmt.format(bBean.getB_ServiceAmt()), 9));
                    }

                    if (bBean.getB_Earnest() > 0) {
                        print("     " + PUtility.DataFullR("หักคืนเงินมัดจำ           ", 23) + PUtility.DataFull(DecFmt.format(bBean.getB_Earnest()), 9));
                    }
                    if (CONFIG.getP_VatType().equals("I")) {
                        //Print_Str(" ");
                        SelectStye(3);
                        print("Net-TOTAL " + PUtility.DataFull(DecFmt.format(bBean.getB_NetTotal()), AmtLength));
                        SelectStye(1);
                        if (CONFIG.getP_VatPrn().equals("Y")) {
                            print(PUtility.DataFull("              Vat...", 43) + PUtility.DataFull(DecFmt.format(bBean.getB_Vat()), AmtLength));
                        }
                    } else {
                        print(PUtility.DataFull("      Net-Amount ", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_NetVat() + bBean.getB_NetNonVat()), AmtLength));
                        print(PUtility.DataFull("      Vat....... ", SubLength) + PUtility.DataFull(DecFmt.format(CONFIG.getP_Vat()), QtyLength) + "%" + PUtility.DataFull(DecFmt.format(bBean.getB_Vat()), AmtLength));
                        print("VAT INCLUDED");
                    }
                    if (bBean.getB_GiftVoucher() > 0) {
                        print("     " + PUtility.DataFullR("บัตรกำนัล..............", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_GiftVoucher()), AmtLength));
                        try {
                            Statement stmt = MySQLConnect.con.createStatement();
                            String CheckGift = "select *from t_gift where (refno='" + _RefNo + "')";
                            ResultSet rec = stmt.executeQuery(CheckGift);
                            rec.first();
                            if (rec.getRow() == 0) {
                            } else {
                                do {
                                    print("   " + PUtility.DataFull(rec.getString("giftbarcode"), 25) + "@" + PUtility.DataFull(DecFmt.format(rec.getDouble("giftamt")), AmtLength));
                                } while (rec.next());
                            }
                            rec.close();
                            stmt.close();
                        } catch (SQLException e) {
                            PUtility.showError(e.getMessage());
                        }
                    }
                    if (bBean.getB_PayAmt() > 0) {
                        print("เงินสด  : " + PUtility.DataFull(DecFmt.format(bBean.getB_PayAmt()), AmtLength) + "  ทอน : " + PUtility.DataFull(DecFmt.format(bBean.getB_Ton()), AmtLength));
                    }

                    print("----------------------------------------");
                    SelectStye(5);
                    print("TABLE  " + PUtility.DataFull(bBean.getB_Table(), 5) + "   จำนวนลูกค้า : " + IntFmt.format(bBean.getB_Cust()) + " คน");
                    SelectStye(1);

                    print(" ");
                    if (!CONFIG.getP_PrintRecpMessage().equals("")) {
                        print(CONFIG.getP_PrintRecpMessage());
                    }

                    if (!POSHW.getFootting1().equals("")) {
                        print(POSHW.getFootting1());
                    }
                    if (!POSHW.getFootting2().equals("")) {
                        print(POSHW.getFootting2());
                    }
                    print(" ");
                    SelectStye(1);
                    print("");
                    print(" ");
                    print(" ");
                    print(" ");
                    EJPrint = false;
                    CutPaper();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PPrint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    closePrint();
                }
            }
        }
    }

    private void PrintBillRefundDriver(String _RefNo) {
        PrintDriver printDriver = new PrintDriver();

        BillControl bc = new BillControl();
        ArrayList<TSaleBean> listBean = bc.getAllTSale(_RefNo);

        int QtyLength = 5;
        int AmtLength = 10;
        int SubLength = 20;
        int SubLength2 = 13;
        int ItemCnt = 0;
        String VatStr;

        BillControl bill = new BillControl();
        BillNoBean bBean = bill.getData(_RefNo);

        POSHW = POSHWSetup.Bean(Value.getMacno());
        CONFIG = POSConfigSetup.Bean();

        printDriver.addText(POSHW.getHeading1());
        printDriver.addText(POSHW.getHeading2());
        printDriver.addText(POSHW.getHeading3());
        printDriver.addText(POSHW.getHeading4());
        printDriver.addText("REG ID :" + Value.MACNO);
        printDriver.addText("***บิลยกเลิกรายการขาย (Refund)***");
        printDriver.addText("Void User : " + PublicVar._User);
        printDriver.addText("Void Date/Time : " + PUtility.CurDate());
        printDriver.addText("อ้างถึงใบเสร็จรับเงินเลขที่");
        printDriver.addText(_RefNo);
        printDriver.addText("");
        PublicVar.P_LineCount = 0;
        for (int i = 0; i < listBean.size(); i++) {
            TSaleBean bean = (TSaleBean) listBean.get(i);
            if (!bean.getR_Void().equals("V")) {
                ItemCnt = (int) (ItemCnt + bean.getR_Quan());
            }
        }
        if (CONFIG.getP_PrintDetailOnRecp().equals("Y")) {
            Date dateP = new Date();
            printDriver.addText(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
            printDriver.addText("----------------------------------------");
            for (int i = 0; i < listBean.size(); i++) {
                TSaleBean bean = (TSaleBean) listBean.get(i);
                if (bean.getR_Void().equals("V")) {
                    printDriver.addText("VOID..." + "User :" + bean.getR_VoidUser());
                    if (bean.getR_Vat().equals("V")) {
                        VatStr = "-";
                    } else {
                        VatStr = "*";
                    }
                    if (CONFIG.getP_CodePrn().equals("Y")) {
                        printDriver.addText(bean.getR_PName());
                        printDriver.addText(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(-1 * bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(-1 * bean.getR_Total()), AmtLength) + bean.getR_ETD());
                    } else {
                        printDriver.addText(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(-1 * bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(-1 * bean.getR_Total()), AmtLength) + bean.getR_ETD());
                    }
                } else {
                    if (bean.getR_Vat().equals("V")) {
                        VatStr = "-";
                    } else {
                        VatStr = "*";
                    }
                    if (bean.getR_PrAmt() == 0) {
                        if (CONFIG.getP_CodePrn().equals("Y")) {
                            printDriver.addText(bean.getR_PName());
                            printDriver.addText(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                        } else {
                            printDriver.addText(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                        }
                    } else {
                        if (CONFIG.getP_CodePrn().equals("Y")) {
                            printDriver.addText(bean.getR_PName());
                            printDriver.addText(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PluCode(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                        } else {
                            printDriver.addText(bean.getR_Normal() + VatStr + PUtility.DataFullR(bean.getR_PName(), 20) + "  " + PUtility.DataFull(IntFmt.format(bean.getR_Quan()), QtyLength) + PUtility.DataFull(DecFmt.format(bean.getR_Total()), AmtLength) + bean.getR_ETD());
                        }
                        if (bean.getR_PrType().equals("-P")) {
                            if (bean.getR_PrAmt() > 0) {
                                printDriver.addText("   **Promotion  " + bean.getR_PrCode() + " " + PUtility.DataFull(PUtility.SeekPromotionName(bean.getR_PrCode()), 20));
                            }
                            if (bean.getR_PrAmt2() > 0) {
                                printDriver.addText("   **Promotion  " + bean.getR_PrCode2() + " " + PUtility.DataFull(PUtility.SeekPromotionName(bean.getR_PrCode2()), 20));
                            }
                        }
                        if (bean.getR_PrType().equals("-I")) {
                            if (bean.getR_PrDisc() != 0) {
                                printDriver.addText("   **Item-Discount " + bean.getR_PrCode() + " " + PUtility.DataFull(DecFmt.format(bean.getR_PrDisc() - bean.getR_Redule()), QtyLength) + "%");
                            }
                            if (bean.getR_PrAmt2() > 0) {
                                printDriver.addText("   **Promotion  " + bean.getR_PrCode2() + " " + PUtility.DataFull(DecFmt.format(bean.getR_PrAmt()), AmtLength));
                            }
                        }
                    }
                }
            }
        } else {
            Date dateP = new Date();
            printDriver.addText(PPrint_DatefmtThai.format(dateP) + " " + "Cashier:" + PublicVar._User + " Mac:" + Value.MACNO);
            printDriver.addText("----------------------------------------");
            printDriver.addText("     อาหารและเครื่องดื่ม " + PUtility.DataFull(DecFmt.format(bBean.getB_Total()), AmtLength));
        }
        printDriver.addText("----------------------------------------");
        printDriver.addText("Sub-TOTAL   " + "(Item" + PUtility.DataFull(IntFmt.format(ItemCnt), QtyLength) + " )     " + PUtility.DataFull(DecFmt.format(bBean.getB_Total()), AmtLength));
        if (bBean.getB_ProDiscAmt() > 0) {
            printDriver.addText("    " + PUtility.DataFullR("ลด Promotion     ", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_ProDiscAmt()), AmtLength));
        }
        if (bBean.getB_SpaDiscAmt() > 0) {
            printDriver.addText("     " + PUtility.DataFullR("Special Disc     ", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_SpaDiscAmt()), AmtLength));
        }
        if (bBean.getB_MemDiscAmt() > 0) {
            printDriver.addText("     " + PUtility.DataFullR("ลดสมาชิก..........", SubLength2) + PUtility.DataFull(bBean.getB_MemDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_MemDiscAmt()), AmtLength));
        }
        if (bBean.getB_FastDiscAmt() > 0) {
            printDriver.addText("     " + PUtility.DataFullR("ลดเทศกาล.........", SubLength2) + PUtility.DataFull(bBean.getB_FastDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_FastDiscAmt()), AmtLength));
        }
        if (bBean.getB_EmpDiscAmt() > 0) {
            printDriver.addText("     " + PUtility.DataFullR("ลดพนักงาน.........", SubLength2) + PUtility.DataFull(bBean.getB_EmpDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_EmpDiscAmt()), AmtLength));
        }
        if (bBean.getB_TrainDiscAmt() > 0) {
            printDriver.addText("     " + PUtility.DataFullR("ลด Trainnee......", SubLength2) + PUtility.DataFull(bBean.getB_TrainDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_TrainDiscAmt()), AmtLength));
        }
        if (bBean.getB_SubDiscAmt() > 0) {
            printDriver.addText("     " + PUtility.DataFullR("ลดคูปอง...........", SubLength2) + PUtility.DataFull(bBean.getB_SubDisc(), 8) + PUtility.DataFull(DecFmt.format(bBean.getB_SubDiscAmt()), AmtLength));
        }
        if (bBean.getB_SubDiscBath() > 0) {
            printDriver.addText("     " + PUtility.DataFullR("ลด(บาท)..........", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_SubDiscBath()), AmtLength));
        }
        if (bBean.getB_ItemDiscAmt() > 0) {
            printDriver.addText("     " + PUtility.DataFullR("ลดตามรายการ(Item)", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_ItemDiscAmt()), AmtLength));
        }
        if (bBean.getB_CuponDiscAmt() > 0) {
            try {
                Statement stmt = MySQLConnect.con.createStatement();
                String CheckCoupon = "select *from t_cupon where (r_refno='" + _RefNo + "') and (terminal='" + Value.MACNO + "') ";
                ResultSet rec = stmt.executeQuery(CheckCoupon);
                Date dt = new Date();
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    do {
                        printDriver.addText("     " + PUtility.DataFullR(PUtility.SeekCuponName(rec.getString("cucode")), 20) + PUtility.DataFull(DecFmt.format(rec.getDouble("cuamt")), AmtLength));
                        String SMS_Code = rec.getString("sms_code");
                        if ((SMS_Code != null) & (!SMS_Code.equals(""))) {
                            printDriver.addText("     " + "SMS CODE " + SMS_Code);
                        }
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                PUtility.showError(e.getMessage());
            }
        }
        if (bBean.getB_ServiceAmt() > 0) {
            printDriver.addText("     " + PUtility.DataFullR("ค่าบริการ (Service)     ", 23) + PUtility.DataFull(DecFmt.format(bBean.getB_ServiceAmt()), 9));
        }

        if (bBean.getB_Earnest() > 0) {
            printDriver.addText("     " + PUtility.DataFullR("หักคืนเงินมัดจำ           ", 23) + PUtility.DataFull(DecFmt.format(bBean.getB_Earnest()), 9));
        }
        if (CONFIG.getP_VatType().equals("I")) {
            printDriver.addText("Net-TOTAL " + PUtility.DataFull(DecFmt.format(bBean.getB_NetTotal()), AmtLength));
            if (CONFIG.getP_VatPrn().equals("Y")) {
                printDriver.addText(PUtility.DataFull("              Vat...", 43) + PUtility.DataFull(DecFmt.format(bBean.getB_Vat()), AmtLength));
            }
        } else {
            printDriver.addText(PUtility.DataFull("      Net-Amount ", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_NetVat() + bBean.getB_NetNonVat()), AmtLength));
            printDriver.addText(PUtility.DataFull("      Vat....... ", SubLength) + PUtility.DataFull(DecFmt.format(CONFIG.getP_Vat()), QtyLength) + "%" + PUtility.DataFull(DecFmt.format(bBean.getB_Vat()), AmtLength));
            printDriver.addText("VAT INCLUDED");
        }
        if (bBean.getB_GiftVoucher() > 0) {
            printDriver.addText("     " + PUtility.DataFullR("บัตรกำนัล..............", SubLength) + PUtility.DataFull(DecFmt.format(bBean.getB_GiftVoucher()), AmtLength));
            try {
                Statement stmt = MySQLConnect.con.createStatement();
                String CheckGift = "select *from t_gift where (refno='" + _RefNo + "')";
                ResultSet rec = stmt.executeQuery(CheckGift);
                rec.first();
                if (rec.getRow() == 0) {
                } else {
                    do {
                        printDriver.addText("   " + PUtility.DataFull(rec.getString("giftbarcode"), 25) + "@" + PUtility.DataFull(DecFmt.format(rec.getDouble("giftamt")), AmtLength));
                    } while (rec.next());
                }
                rec.close();
                stmt.close();
            } catch (SQLException e) {
                PUtility.showError(e.getMessage());
            }
        }
        if (bBean.getB_PayAmt() > 0) {
            printDriver.addText("เงินสด  : " + PUtility.DataFull(DecFmt.format(bBean.getB_PayAmt()), AmtLength) + "  ทอน : " + PUtility.DataFull(DecFmt.format(bBean.getB_Ton()), AmtLength));
        }

        printDriver.addText("----------------------------------------");
        printDriver.addText("TABLE  " + PUtility.DataFull(bBean.getB_Table(), 5) + "   จำนวนลูกค้า : " + IntFmt.format(bBean.getB_Cust()) + " คน");

        printDriver.addText(" ");
        if (!CONFIG.getP_PrintRecpMessage().equals("")) {
            printDriver.addText(CONFIG.getP_PrintRecpMessage());
        }

        if (!POSHW.getFootting1().equals("")) {
            printDriver.addText(POSHW.getFootting1());
        }
        if (!POSHW.getFootting2().equals("")) {
            printDriver.addText(POSHW.getFootting2());
        }
        printDriver.addText(" ");
        EJPrint = false;

        printDriver.printHTML();
    }

    private void PrintTableActionDriver() {
        JOptionPane.showMessageDialog(null, Value.driverNotSupport);
    }

    public void printHeaderBill() {
        if (Value.printdriver) {
            printHeaderBillDriver();
        } else if (!Value.getComPort().equals("NONE")) {
            if (OpenPrint(Value.getComPort())) {
                InitPrinter();
                print(POSHW.getHeading1());
                print(POSHW.getHeading2());
                print(POSHW.getHeading3());
                print(POSHW.getHeading4());

                print("REG ID :" + Value.MACNO);
                CutPaper();
                closePrint();
            }
        }
    }

    private void printHeaderBillDriver() {
        JOptionPane.showMessageDialog(null, Value.driverNotSupport);
    }

    private void PrintTerminalDriver() {
        JOptionPane.showMessageDialog(null, Value.driverNotSupport);
    }

}
