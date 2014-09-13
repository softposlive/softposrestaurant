package printReport;

import database.MySQLConnect;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.print.PrintService;
import javax.swing.JOptionPane;
import program.PUtility;
import program.ThaiUtil;
import util.MSG;

public class PrintSimpleForm {

    static SimpleDateFormat simp = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);

    public String DataFullR(String Str, int Len) {
        String ReturnStr;
        String AddStr = "";
        int List1[] = {209, 212, 213, 214, 215, 216, 217, 218, 219, 231, 232, 233, 234, 235, 236, 237};
        if (Len < Str.length()) {
            ReturnStr = Str.substring(0, Len - 1);
        } else {
            for (int i = 1; i <= (Len - Str.length()); i++) {
                AddStr = AddStr + " ";
            }

            ReturnStr = Str.trim() + AddStr;
        }
        int I = 0;
        int ICnt = 0;
        char ch;
        String TempStr = ThaiUtil.Unicode2ASCII(ReturnStr);
        while (I <= TempStr.length() - 1) {   // Check TIS Upper }

            ch = TempStr.charAt(I);
            if (searchArray((int) ch, List1) != -1) {
                ICnt++;
            }
            I = I + 1;
        }

        if (ICnt > 0) {
            for (int i = 1; i <= ICnt; i++) {
                ReturnStr = ReturnStr + " ";
            }
        }
        return ReturnStr;
    }

    public int searchArray(int key, int[] list) {
        int ans = -1;
        for (int i = 0; i < list.length; i++) {
            if (key == list[i]) {
                ans = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        PrintSimpleForm psf = new PrintSimpleForm();
        psf.printTest("KIC1", "ทดสอบการส่งข้อมูลออกปริ้นเตอร Test Test");
    }

    public void KIC_FORM_1(String printerName, final String tableNo, final String[] PCode) {
        final int SpaceFront = 25;
        final int PaperMaxLength = 28;

        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
                    break;
                }
            }
            pj.setPrintService(ps[prnIndex]);

            PageFormat pf = new PageFormat();
            Paper pp = new Paper();
            pp.setSize(500, 1000);
            pp.setImageableArea(0, 0, 594, 846);
            pf.setPaper(pp);
            pj.setPrintable(new Printable() {

                //**** FORM 1 **** 
                //จะ CUT ปริ้นทีละสินค้า โดย group by จำนวนสินค้า//
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้ำลำใย            5
                //__________________
                //28/04/2014 14:15 001/
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้าตะไคร้ใบเตย       2
                //__________________
                //28/04/2014 14:15 001/
                @Override
                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
                    Graphics2D g2 = (Graphics2D) g;
                    if (index == 0) {
                        String sqlAdd = "";
                        if (PCode.length == 1) {
                            sqlAdd = "and R_PluCode='" + PCode[0] + "' ";
                        } else if (PCode.length > 1) {
                            sqlAdd = "and R_PluCode in(";
                            for (int i = 0; i < PCode.length; i++) {
                                sqlAdd += "'" + PCode[i] + "'";
                                if (i < PCode.length) {
                                    sqlAdd += ",";
                                }
                            }
                            sqlAdd += ") ";
                        }
                        try {
                            String sql = "select TUser,R_Void,R_PluCode,R_Index,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
                                    + "R_Price, b.Macno,R_Date, R_Time,"
                                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                                    + "from tablefile t,balance b "
                                    + "where t.tcode=b.r_table "
                                    + "and r_table='" + tableNo + "' "
                                    + "and R_PrintOK='Y' "
                                    + "and R_KicPrint<>'P' "
                                    + "and R_Kic<>'' "
                                    + sqlAdd
                                    + "group by R_PluCode "
                                    + "order by R_Index";
                            ResultSet rs = MySQLConnect.getResultSet(sql);
                            int line = 0;
                            while (rs.next()) {
                                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                                String ETD = rs.getString("R_ETD");
                                String macNo = rs.getString("macno");
                                String custCount = rs.getString("TCustomer");
                                int qty = rs.getInt("R_Quan");
                                String TUser = getEmpName(rs.getString("TUser"));

                                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                                ArrayList<String[]> listOpt = new ArrayList<String[]>();
                                try {
                                    String sqlOpt = "select * from balance "
                                            + "where r_table='" + tableNo + "' "
                                            + "and r_pluCode='" + rs.getString("R_PluCode") + "'";
                                    ResultSet rsOpt = MySQLConnect.getResultSet(sqlOpt);
                                    while (rsOpt.next()) {

                                        String[] OPT = new String[]{
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"))
                                        };

                                        listOpt.add(OPT);
                                    }

                                    rsOpt.close();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

                                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                                line += 25;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 30));
                                String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
                                g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
                                line += 25;

                                //print ETD
                                printG(g2, ETD, line);

                                line += 25;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                                String product = DataFullR(productName, 40);
                                g2.drawString(product, SpaceFront, line);
                                g2.drawString("" + qty, SpaceFront + 150, line);

                                //********* พิมพ์ข้อความพิเศษ *************
                                for (int x = 0; x < listOpt.size(); x++) {
                                    String[] OPT = (String[]) listOpt.get(x);
                                    for (String OPT1 : OPT) {
                                        if (OPT1 != null) {
                                            if (!OPT1.trim().equals("")) {
                                                line += 20;
                                                g2.drawString("***" + OPT1, SpaceFront + 5, line);
                                            }
                                        }
                                    }
                                }
                                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************

                                line += 20;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                                g2.drawString("-----------------------------------------", SpaceFront, line);
                                line += 20;

                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 18));
                                g2.drawString("  " + simp.format(new Date()) + "   Mac " + macNo + "/" + TUser, SpaceFront, line);

                                line += 25;

                                //add kictran data
                                String R_Que = SeekKicItemNo();
                                int TempQue = Integer.parseInt(R_Que);
                                String R_VOID = rs.getString("R_Void");
                                if (R_VOID == null) {
                                    R_VOID = "";
                                }
                                try {
                                    if (R_VOID.equals("V")) {
                                        String SQLQuery2 = "update kictran "
                                                + "set pvoid = 'V' "
                                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                                + "and ptable='" + rs.getString("R_Table") + "' "
                                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                                + "and pflage='N'";
                                        MySQLConnect.exeUpdate(SQLQuery2);
                                    } else {
                                        String sqlK = "insert into kictran "
                                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                                + "values (" + TempQue + ",curdate(),"
                                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                                        MySQLConnect.exeUpdate(sqlK);
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            rs.close();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        return PAGE_EXISTS;
                    } else {
                        return NO_SUCH_PAGE;
                    }
                }
            }, pf);
            try {
                pj.print();
            } catch (PrinterException e) {
                System.out.println(e.getMessage());
            }
        } catch (PrinterException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void KIC_FORM_2(String printerName, final String tableNo, final String[] PCode) {
        final int SpaceFront = 25;
        final int PaperMaxLength = 28;

        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
                    break;
                }
            }
            pj.setPrintService(ps[prnIndex]);

            PageFormat pf = new PageFormat();
            Paper pp = new Paper();
            pp.setSize(500, 1000);
            pp.setImageableArea(0, 0, 594, 846);
            pf.setPaper(pp);
            pj.setPrintable(new Printable() {

                //**** FORM 2 **** 
                //จะรวมจำนวนสินค้าแต่ละรายการทั้งหมด แล้วค่อย CUT กระดาษ พร้อมทั้งแสดงราคา//
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้ำลำใย            
                //จำนวน  2 ราคา 45.00
                //__________________
                //28/04/2014 14:15 001/
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้ำตะใคร้ใบเตย      
                //จำนวน  1 ราคา 45.00
                //__________________
                //28/04/2014 14:15 001/
                @Override
                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
                    Graphics2D g2 = (Graphics2D) g;
                    if (index == 0) {
                        String sqlAdd = "";
                        if (PCode.length == 1) {
                            sqlAdd = "and R_PluCode='" + PCode[0] + "' ";
                        } else if (PCode.length > 1) {
                            sqlAdd = "and R_PluCode in(";
                            for (int i = 0; i < PCode.length; i++) {
                                sqlAdd += "'" + PCode[i] + "'";
                                if (i < PCode.length) {
                                    sqlAdd += ",";
                                }
                            }
                            sqlAdd += ") ";
                        }
                        try {
                            String sql = "select TUser,R_Void,R_Index, R_PluCode,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
                                    + "R_Price, b.Macno,R_Date, R_Time,"
                                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                                    + "from tablefile t,balance b "
                                    + "where t.tcode=b.r_table "
                                    + "and r_table='" + tableNo + "' "
                                    + "and R_PrintOK='Y' "
                                    + "and R_KicPrint<>'P' "
                                    + "and R_Kic<>'' "
                                    + sqlAdd
                                    + "group by R_PluCode order by R_Index";
                            ResultSet rs = MySQLConnect.getResultSet(sql);
                            int line = 0;
                            while (rs.next()) {
                                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                                String ETD = rs.getString("R_ETD");
                                String macNo = rs.getString("macno");
                                String custCount = rs.getString("TCustomer");
                                int qty = rs.getInt("R_Quan");
                                String TUser = getEmpName(rs.getString("TUser"));

                                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                                ArrayList<String[]> listOpt = new ArrayList<String[]>();
                                try {
                                    String sqlOpt = "select * from balance "
                                            + "where r_table='" + tableNo + "' and r_pluCode='" + rs.getString("R_PluCode") + "'";
                                    ResultSet rsOpt = MySQLConnect.getResultSet(sqlOpt);
                                    while (rsOpt.next()) {

                                        String[] OPT = new String[]{
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"))
                                        };

                                        listOpt.add(OPT);
                                    }

                                    rsOpt.close();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

                                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                                line += 25;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 30));
                                String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
                                g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
                                line += 25;

                                //print ETD
                                printG(g2, ETD, line);

                                line += 25;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                                g2.drawString(productName, SpaceFront, line);
                                //********* พิมพ์ข้อความพิเศษ *************
                                for (int x = 0; x < listOpt.size(); x++) {
                                    String[] OPT = (String[]) listOpt.get(x);
                                    for (String OPT1 : OPT) {
                                        if (OPT1 != null) {
                                            if (!OPT1.trim().equals("")) {
                                                line += 20;
                                                g2.drawString("***" + OPT1, SpaceFront + 5, line);
                                            }
                                        }
                                    }
                                }
                                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************
                                line += 20;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                                g2.drawString("จำนวน    " + qty + "      ราคา " + rs.getDouble("R_Price"), SpaceFront, line);
                                line += 20;
                                g2.drawString("-----------------------------------------", SpaceFront, line);
                                line += 20;

                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 18));
                                g2.drawString("  " + simp.format(new Date()) + "   Mac " + macNo + "/" + TUser, SpaceFront, line);

                                line += 25;
                                //add kictran data
                                String R_Que = SeekKicItemNo();
                                int TempQue = Integer.parseInt(R_Que);
                                String R_VOID = rs.getString("R_Void");
                                if (R_VOID == null) {
                                    R_VOID = "";
                                }
                                try {
                                    if (R_VOID.equals("V")) {
                                        String SQLQuery2 = "update kictran "
                                                + "set pvoid = 'V' "
                                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                                + "and ptable='" + rs.getString("R_Table") + "' "
                                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                                + "and pflage='N'";
                                        MySQLConnect.exeUpdate(SQLQuery2);
                                    } else {
                                        String sqlK = "insert into kictran "
                                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                                + "values (" + TempQue + ",curdate(),"
                                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                                        MySQLConnect.exeUpdate(sqlK);
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            rs.close();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        return PAGE_EXISTS;
                    } else {
                        return NO_SUCH_PAGE;
                    }
                }
            }, pf);
            try {
                pj.print();
            } catch (PrinterException e) {
                System.out.println(e.getMessage());
            }
        } catch (PrinterException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void KIC_FORM_3(String printerName, final String tableNo) {
        final int SpaceFront = 25;
        final int PaperMaxLength = 28;

        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
                    break;
                }
            }
            pj.setPrintService(ps[prnIndex]);

            PageFormat pf = new PageFormat();
            Paper pp = new Paper();
            pp.setSize(500, 1000);
            pp.setImageableArea(0, 0, 594, 846);
            pf.setPaper(pp);
            pj.setPrintable(new Printable() {

                //**** FORM 3 **** 
                //จะปริ้นรวมเป็นแผ่นเดียว//
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้ำลำใย            3
                //น้ำตะไคร้ใบเตย       1
                //__________________
                //28/04/2014 14:15 001/
                @Override
                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
                    Graphics2D g2 = (Graphics2D) g;
                    if (index == 0) {
                        try {
                            String sql = "select TUser, R_Void,R_Index, R_PluCode,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
                                    + "R_Price, b.Macno,R_Date, R_Time,"
                                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                                    + "from tablefile t,balance b "
                                    + "where t.tcode=b.r_table "
                                    + "and r_table='" + tableNo + "' "
                                    + "and R_PrintOK='Y' "
                                    + "and R_KicPrint<>'P' "
                                    + "and R_Kic<>'' "
                                    + "group by R_PluCode order by R_Index";
                            ResultSet rs = MySQLConnect.getResultSet(sql);
                            int line = 0;
                            String macNo = "";
                            String TUser = "";
                            boolean printHeader = false;
                            while (rs.next()) {
                                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                                String ETD = rs.getString("R_ETD");
                                macNo = rs.getString("macno");
                                String custCount = rs.getString("TCustomer");
                                int qty = rs.getInt("R_Quan");
                                TUser = getEmpName(rs.getString("TUser"));

                                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                                ArrayList<String[]> listOpt = new ArrayList<String[]>();
                                try {
                                    String sqlOpt = "select * from balance "
                                            + "where r_table='" + tableNo + "' and r_pluCode='" + rs.getString("R_PluCode") + "'";
                                    ResultSet rsOpt = MySQLConnect.getResultSet(sqlOpt);
                                    while (rsOpt.next()) {

                                        String[] OPT = new String[]{
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"))
                                        };

                                        listOpt.add(OPT);
                                    }

                                    rsOpt.close();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

                                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                                if (!printHeader) {
                                    line += 25;
                                    g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 30));
                                    String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
                                    g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
                                    line += 25;

                                    //print ETD
                                    printG(g2, ETD, line);
                                    printHeader = true;
                                }
                                line += 25;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                                String product = DataFullR(productName, PaperMaxLength);

                                g2.drawString(product, SpaceFront, line);
                                g2.drawString("" + qty, SpaceFront + 155, line);

                                //********* พิมพ์ข้อความพิเศษ *************
                                for (int x = 0; x < listOpt.size(); x++) {
                                    String[] OPT = (String[]) listOpt.get(x);
                                    for (String OPT1 : OPT) {
                                        if (OPT1 != null) {
                                            if (!OPT1.trim().equals("")) {
                                                line += 20;
                                                g2.drawString("***" + OPT1, SpaceFront + 5, line);
                                            }
                                        }
                                    }
                                }
                                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************

                                //add kictran data
                                String R_Que = SeekKicItemNo();
                                int TempQue = Integer.parseInt(R_Que);
                                String R_VOID = rs.getString("R_Void");
                                if (R_VOID == null) {
                                    R_VOID = "";
                                }
                                try {
                                    if (R_VOID.equals("V")) {
                                        String SQLQuery2 = "update kictran "
                                                + "set pvoid = 'V' "
                                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                                + "and ptable='" + rs.getString("R_Table") + "' "
                                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                                + "and pflage='N'";
                                        MySQLConnect.exeUpdate(SQLQuery2);
                                    } else {
                                        String sqlK = "insert into kictran "
                                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                                + "values (" + TempQue + ",curdate(),"
                                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                                        MySQLConnect.exeUpdate(sqlK);
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            //Print header
                            line += 20;
                            g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                            g2.drawString("-----------------------------------------", SpaceFront, line);
                            line += 20;

                            g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 18));
                            g2.drawString("  " + simp.format(new Date()) + "   Mac " + macNo + "/" + TUser, SpaceFront, line);

                            rs.close();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        return PAGE_EXISTS;
                    } else {
                        return NO_SUCH_PAGE;
                    }
                }
            }, pf);
            try {
                pj.print();
            } catch (PrinterException e) {
                System.out.println(e.getMessage());
            }
        } catch (PrinterException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void KIC_FORM_4(String printerName, final String tableNo) {
        final int SpaceFront = 25;
        final int PaperMaxLength = 28;

        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
                    break;
                }
            }
            pj.setPrintService(ps[prnIndex]);

            PageFormat pf = new PageFormat();
            Paper pp = new Paper();
            pp.setSize(500, 1000);
            pp.setImageableArea(0, 0, 594, 846);
            pf.setPaper(pp);
            pj.setPrintable(new Printable() {

                //**** FORM 4 **** 
                //จะปริ้นรวมเป็นแผ่นเดียว ไม่มีการตัดรายการ แต่จะแยกด้วย __________ //
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้ำลำใย            
                //จำนวน  2 ราคา 45.00
                //__________________
                //28/04/2014 14:15 001/
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้ำตะใคร้ใบเตย      1            
                //จำนวน  1 ราคา 45.00
                //__________________
                //28/04/2014 14:15 001/
                @Override
                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
                    Graphics2D g2 = (Graphics2D) g;
                    if (index == 0) {
                        try {
                            String sql = "select TUser, R_Void,R_Index, R_PluCode,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
                                    + "R_Price, b.Macno,R_Date, R_Time,"
                                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                                    + "from tablefile t,balance b "
                                    + "where t.tcode=b.r_table "
                                    + "and r_table='" + tableNo + "' "
                                    + "and R_PrintOK='Y' "
                                    + "and R_KicPrint<>'P' "
                                    + "and R_Kic<>'' "
                                    + "group by R_PluCode order by R_Index";
                            ResultSet rs = MySQLConnect.getResultSet(sql);
                            int line = 0;
                            String macNo = "";
                            boolean printTable = false;
                            String TUser = "";
                            while (rs.next()) {
                                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                                String ETD = rs.getString("R_ETD");
                                macNo = rs.getString("macno");
                                String custCount = rs.getString("TCustomer");
                                int qty = rs.getInt("R_Quan");
                                TUser = getEmpName(rs.getString("TUser"));

                                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                                ArrayList<String[]> listOpt = new ArrayList<String[]>();
                                try {
                                    String sqlOpt = "select * from balance "
                                            + "where r_table='" + tableNo + "' and r_pluCode='" + rs.getString("R_PluCode") + "'";
                                    ResultSet rsOpt = MySQLConnect.getResultSet(sqlOpt);
                                    while (rsOpt.next()) {

                                        String[] OPT = new String[]{
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"))
                                        };

                                        listOpt.add(OPT);
                                    }

                                    rsOpt.close();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

                                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                                line += 25;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 30));
                                if (!printTable) {
                                    String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
                                    g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
                                    line += 25;

                                    printTable = true;
                                }
                                //print ETD
                                printG(g2, ETD, line);

                                line += 25;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                                g2.drawString(productName, SpaceFront, line);
                                //********* พิมพ์ข้อความพิเศษ *************
                                for (int x = 0; x < listOpt.size(); x++) {
                                    String[] OPT = (String[]) listOpt.get(x);
                                    for (String OPT1 : OPT) {
                                        if (OPT1 != null) {
                                            if (!OPT1.trim().equals("")) {
                                                line += 20;
                                                g2.drawString("***" + OPT1, SpaceFront + 5, line);
                                            }
                                        }
                                    }
                                }
                                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************
                                line += 20;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                                g2.drawString("จำนวน    " + qty + "      ราคา " + rs.getDouble("R_Price"), SpaceFront, line);
                                line += 20;
                                g2.drawString("-----------------------------------------", SpaceFront, line);

                                //add kictran data
                                String R_Que = SeekKicItemNo();
                                int TempQue = Integer.parseInt(R_Que);
                                String R_VOID = rs.getString("R_Void");
                                if (R_VOID == null) {
                                    R_VOID = "";
                                }
                                try {
                                    if (R_VOID.equals("V")) {
                                        String SQLQuery2 = "update kictran "
                                                + "set pvoid = 'V' "
                                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                                + "and ptable='" + rs.getString("R_Table") + "' "
                                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                                + "and pflage='N'";
                                        MySQLConnect.exeUpdate(SQLQuery2);
                                    } else {
                                        String sqlK = "insert into kictran "
                                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                                + "values (" + TempQue + ",curdate(),"
                                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                                        MySQLConnect.exeUpdate(sqlK);
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            line += 20;
                            g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 18));
                            g2.drawString("  " + simp.format(new Date()) + "   Mac " + macNo + "/" + TUser, SpaceFront, line);

                            rs.close();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        return PAGE_EXISTS;
                    } else {
                        return NO_SUCH_PAGE;
                    }
                }
            }, pf);
            try {
                pj.print();
            } catch (PrinterException e) {
                System.out.println(e.getMessage());
            }
        } catch (PrinterException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void KIC_FORM_5(String printerName, final String tableNo) {
        final int SpaceFront = 25;
        final int PaperMaxLength = 28;

        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
                    break;
                }
            }
            pj.setPrintService(ps[prnIndex]);

            PageFormat pf = new PageFormat();
            Paper pp = new Paper();
            pp.setSize(500, 1000);
            pp.setImageableArea(0, 0, 594, 846);
            pf.setPaper(pp);
            pj.setPrintable(new Printable() {

                //**** FORM 5 **** 
                //จะปริ้นรวมเป็นแผ่นเดียว//
                //โต๊ะ 1           C0
                //***** Eat In *****
                //--- น้ำลำใย         3
                //--- น้ำตะไคร้ใบเตย    1
                //__________________
                //28/04/2014 14:15 001/
                @Override
                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
                    Graphics2D g2 = (Graphics2D) g;
                    if (index == 0) {
                        try {
                            String sql = "select TUser,R_Void,R_Index, R_PluCode,TCode, TCustomer, R_PName,sum(R_Quan) R_Quan,"
                                    + "R_Price, b.Macno,R_Date, R_Time,"
                                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                                    + "from tablefile t,balance b "
                                    + "where t.tcode=b.r_table "
                                    + "and r_table='" + tableNo + "' "
                                    + "and R_PrintOK='Y' "
                                    + "and R_KicPrint<>'P' "
                                    + "and R_Kic<>'' "
                                    + "group by R_PluCode order by R_Index";
                            ResultSet rs = MySQLConnect.getResultSet(sql);
                            int line = 0;
                            String macNo = "";
                            String TUser = "";
                            boolean printHeader = false;
                            while (rs.next()) {
                                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                                String ETD = rs.getString("R_ETD");
                                macNo = rs.getString("macno");
                                String custCount = rs.getString("TCustomer");
                                int qty = rs.getInt("R_Quan");
                                TUser = getEmpName(rs.getString("TUser"));

                                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                                ArrayList<String[]> listOpt = new ArrayList<String[]>();
                                try {
                                    String sqlOpt = "select * from balance "
                                            + "where r_table='" + tableNo + "' and r_pluCode='" + rs.getString("R_PluCode") + "'";
                                    ResultSet rsOpt = MySQLConnect.getResultSet(sqlOpt);
                                    while (rsOpt.next()) {

                                        String[] OPT = new String[]{
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"))
                                        };

                                        listOpt.add(OPT);
                                    }

                                    rsOpt.close();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

                                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                                if (!printHeader) {
                                    line += 25;
                                    g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 30));
                                    String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
                                    g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
                                    line += 25;

                                    //print ETD
                                    printG(g2, ETD, line);
                                    printHeader = true;
                                }
                                line += 25;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                                String product = DataFullR(productName, PaperMaxLength);

                                g2.drawString("---" + product, SpaceFront, line);
                                g2.drawString("" + qty, SpaceFront + 155, line);

                                //********* พิมพ์ข้อความพิเศษ *************
                                for (int x = 0; x < listOpt.size(); x++) {
                                    String[] OPT = (String[]) listOpt.get(x);
                                    for (String OPT1 : OPT) {
                                        if (OPT1 != null) {
                                            if (!OPT1.trim().equals("")) {
                                                line += 20;
                                                g2.drawString("***" + OPT1, SpaceFront + 5, line);
                                            }
                                        }
                                    }
                                }
                                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************

                                //add kictran data
                                String R_Que = SeekKicItemNo();
                                int TempQue = Integer.parseInt(R_Que);
                                String R_VOID = rs.getString("R_Void");
                                if (R_VOID == null) {
                                    R_VOID = "";
                                }
                                try {
                                    if (R_VOID.equals("V")) {
                                        String SQLQuery2 = "update kictran "
                                                + "set pvoid = 'V' "
                                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                                + "and ptable='" + rs.getString("R_Table") + "' "
                                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                                + "and pflage='N'";
                                        MySQLConnect.exeUpdate(SQLQuery2);
                                    } else {
                                        String sqlK = "insert into kictran "
                                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                                + "values (" + TempQue + ",curdate(),"
                                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                                        MySQLConnect.exeUpdate(sqlK);
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            //Print header
                            line += 20;
                            g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                            g2.drawString("-----------------------------------------", SpaceFront, line);
                            line += 20;

                            g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                            g2.drawString("  " + simp.format(new Date()) + "   Mac " + macNo + "/" + TUser, SpaceFront, line);

                            rs.close();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        return PAGE_EXISTS;
                    } else {
                        return NO_SUCH_PAGE;
                    }
                }
            }, pf);
            try {
                pj.print();
            } catch (PrinterException e) {
                System.out.println(e.getMessage());
            }
        } catch (PrinterException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void KIC_FORM_6(String printerName, final String tableNo, final String R_Index) {
        final int SpaceFront = 25;
        final int PaperMaxLength = 28;

        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equalsIgnoreCase(printerName)) {
                    prnIndex = i;
                    break;
                }
            }
            pj.setPrintService(ps[prnIndex]);

            PageFormat pf = new PageFormat();
            Paper pp = new Paper();
            pp.setSize(500, 1000);
            pp.setImageableArea(0, 0, 594, 846);
            pf.setPaper(pp);
            pj.setPrintable(new Printable() {

                //**** FORM 6 **** 
                //จะ CUT ปริ้นทีละสินค้าตาม Order รายการในบิล และแสดงราคาสินค้าด้วย//
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้ำลำใย            
                //จำนวน  1 ราคา 45.00
                //__________________
                //28/04/2014 14:15 001/
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้ำลำใย            
                //จำนวน  1 ราคา 45.00
                //__________________
                //28/04/2014 14:15 001/
                //โต๊ะ 1           C0
                //***** Eat In *****
                //น้ำตะใคร้ใบเตย      1 
                //จำนวน  1 ราคา 45.00
                //__________________
                //28/04/2014 14:15 001/
                @Override
                public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
                    Graphics2D g2 = (Graphics2D) g;
                    if (index == 0) {
                        try {
                            String sql = "select TUser,R_Void,R_Index, R_PluCode, TCode, TCustomer, R_PName, R_Quan,"
                                    + "R_Price, b.Macno,R_Date, R_Time,"
                                    + "R_Opt1,R_Opt2,R_Opt3,R_Opt4,R_Opt5,R_Opt6,"
                                    + "R_Opt7,R_Opt8,R_Opt9,R_ETD,b.cashier,R_EMP,R_Table,R_ETD,R_Kic "
                                    + "from tablefile t,balance b "
                                    + "where t.tcode=b.r_table "
                                    + "and r_table='" + tableNo + "' "
                                    + "and R_PrintOK='Y' "
                                    + "and R_KicPrint<>'P' "
                                    + "and R_Index='" + R_Index + "' "
                                    + "and R_Kic<>'' "
                                    + "order by R_Index";
                            ResultSet rs = MySQLConnect.getResultSet(sql);
                            String TUser = "";
                            int line = 0;
                            while (rs.next()) {
                                String productName = ThaiUtil.ASCII2Unicode(rs.getString("R_PName"));
                                String ETD = rs.getString("R_ETD");
                                String macNo = rs.getString("macno");
                                String custCount = rs.getString("TCustomer");
                                int qty = rs.getInt("R_Quan");
                                TUser = getEmpName(rs.getString("TUser"));

                                //*********** เพิ่มมารองรับการพิมพ์ข้อความพิเศษ ***********
                                ArrayList<String[]> listOpt = new ArrayList<String[]>();
                                try {
                                    String sqlOpt = "select * from balance "
                                            + "where r_table='" + tableNo + "' and r_pluCode='" + rs.getString("R_PluCode") + "'";
                                    ResultSet rsOpt = MySQLConnect.getResultSet(sqlOpt);
                                    while (rsOpt.next()) {

                                        String[] OPT = new String[]{
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt1")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt2")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt3")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt4")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt5")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt6")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt7")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt8")),
                                            ThaiUtil.ASCII2Unicode(rsOpt.getString("r_opt9"))
                                        };

                                        listOpt.add(OPT);
                                    }

                                    rsOpt.close();
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

                                //*********** สิ้นสุดการตรวจสอบข้อความพิเศษ ***********
                                line += 25;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 30));
                                String tableHead = DataFullR("โต๊ะ " + rs.getString("TCode"), PaperMaxLength - 3);
                                g2.drawString(tableHead + " C " + custCount, SpaceFront, line);
                                line += 25;

                                //print ETD
                                printG(g2, ETD, line);

                                line += 25;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                                g2.drawString(productName, SpaceFront, line);
                                //********* พิมพ์ข้อความพิเศษ *************
                                for (int x = 0; x < listOpt.size(); x++) {
                                    String[] OPT = (String[]) listOpt.get(x);
                                    for (String OPT1 : OPT) {
                                        if (OPT1 != null) {
                                            if (!OPT1.trim().equals("")) {
                                                line += 20;
                                                g2.drawString("***" + OPT1, SpaceFront + 5, line);
                                            }
                                        }
                                    }
                                }
                                //********* สิ้นสุดการพิมพ์ข้อความพิเศษ *************
                                line += 20;
                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 20));
                                g2.drawString("จำนวน    " + qty + "      ราคา " + rs.getDouble("R_Price"), SpaceFront, line);
                                line += 20;
                                g2.drawString("-----------------------------------------", SpaceFront, line);
                                line += 20;

                                g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 18));
                                g2.drawString("  " + simp.format(new Date()) + "   Mac " + macNo + "/" + TUser, SpaceFront, line);

                                line += 25;

                                //add kictran data
                                String R_Que = SeekKicItemNo();
                                int TempQue = Integer.parseInt(R_Que);
                                String R_VOID = rs.getString("R_Void");
                                if (R_VOID == null) {
                                    R_VOID = "";
                                }
                                try {
                                    if (R_VOID.equals("V")) {
                                        String SQLQuery2 = "update kictran "
                                                + "set pvoid = 'V' "
                                                + "where pindex ='" + rs.getString("R_Index") + "' "
                                                + "and ptable='" + rs.getString("R_Table") + "' "
                                                + "and pcode='" + rs.getString("R_PluCode") + "' "
                                                + "and pkic='" + rs.getString("R_Kic") + "' "
                                                + "and pflage='N'";
                                        MySQLConnect.exeUpdate(SQLQuery2);
                                    } else {
                                        String sqlK = "insert into kictran "
                                                + "(pitemno,pdate,pcode,pqty,pindex,"
                                                + "macno,cashier,emp,ptable,ptimein,pvoid,petd,pkic) "
                                                + "values (" + TempQue + ",curdate(),"
                                                + "'" + rs.getString("R_PluCode") + "'," + rs.getString("R_Quan") + ","
                                                + "'" + rs.getString("R_Index") + "','" + rs.getString("Macno") + "',"
                                                + "'" + rs.getString("Cashier") + "','" + rs.getString("R_Emp") + "',"
                                                + "'" + rs.getString("R_Table") + "',curtime(),'',"
                                                + "'" + rs.getString("R_ETD") + "','" + rs.getString("R_Kic") + "')";
                                        MySQLConnect.exeUpdate(sqlK);
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            rs.close();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        return PAGE_EXISTS;
                    } else {
                        return NO_SUCH_PAGE;
                    }
                }
            }, pf);
            try {
                pj.print();
            } catch (PrinterException e) {
                System.out.println(e.getMessage());
            }
        } catch (PrinterException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void printG(Graphics2D g2, String ETD, int line) {
        final int SpaceFront = 25;
        if (ETD.equals("E")) {
            g2.drawString("***** EAT IN *****", SpaceFront, line);
        } else if (ETD.equals("T")) {
            g2.drawString("* TAKE AWAY *", SpaceFront, line);
        } else if (ETD.equals("D")) {
            g2.drawString("* DELIVERY *", SpaceFront, line);
        } else if (ETD.equals("P")) {
            g2.drawString("*** PINTO ***", SpaceFront, line);
        } else if (ETD.equals("P")) {
            g2.drawString("*** WHOLE SALE ***", SpaceFront, line);
        }
    }

    private String SeekKicItemNo() {
        DecimalFormat QtyIntFmt = new DecimalFormat("###########0");
        int KicItemNo = 0;
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SQLQuery = "Select * from branch";
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

    public void printTest(String prnName, final String data) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        PrintService[] ps = PrinterJob.lookupPrintServices();
        int prnIndex = 0;
        try {
            for (int i = 0; i < ps.length; i++) {
                String PrinterName = ps[i].getName();
                if (PrinterName.equals(prnName)) {
                    prnIndex = i;
                    break;
                }
            }
            pj.setPrintService(ps[prnIndex]);

            PageFormat pf = new PageFormat();
            Paper pp = new Paper();
            pp.setSize(500, 1000);
            pp.setImageableArea(0, 0, 594, 846);
            pf.setPaper(pp);
            pj.setPrintable(new Printable() {

                @Override
                public int print(Graphics g, PageFormat pf, int index) {
                    Graphics2D g2 = (Graphics2D) g;
                    if (index == 0) {
                        int line = 0;
                        int space = 15;
                        try {
                            //AngsanaUPC
                            //angsau
                            g2.setFont(new Font("AngsanaUPC", Font.PLAIN, 16));
                            for (int i = 0; i < 5; i++) {
                                line += 25;
                                g2.drawString(data, space, line);
                            }
                        } catch (Exception e) {
                            MSG.ERR(e.getMessage());
                        }

                        return PAGE_EXISTS;
                    } else {
                        return NO_SUCH_PAGE;
                    }
                }
            }, pf);
            try {
                pj.print();
            } catch (PrinterException e) {
                System.out.println(e.getMessage());
            }
        } catch (PrinterException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Font FontTTF() {
        try {
            //Returned font is of pt size 1
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("D:/NetbeansProjects/fonts/07-TH-Sarabun-PSK/angsau.ttf"));
            return font.deriveFont(Font.BOLD);
        } catch (FontFormatException e) {
            MSG.ERR(e.getMessage());
            return null;
        } catch (IOException e) {
            MSG.ERR(e.getMessage());
            return null;
        }
    }

    public String getEmpName(String Code) {
        try {
            String sql = "select Name from employ where code='004';";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            if (rs.next()) {
                return ThaiUtil.ASCII2Unicode(rs.getString("Name"));
            }

            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return "";
    }

}
