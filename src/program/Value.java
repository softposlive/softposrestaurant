package program;

import database.MySQLConnect;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import util.MSG;

public class Value {
    
    public static String []OPT = new String[]{"","","","","","","","",""};
    public static final double PRICE_BY_USER = -9999999.99;
    public static int countConnectDB = 0;
    public static String MACNO = "";
    public static String FILE_CONFIG = "C:/softpos/connect.ini";
    public static String FILE_LOG = "log.txt";
    public static String FILE_BG = "img/bg.jpg";
    public static String DATABASE = "";
    public static String CopyRight = "SoftRestaurant by SOFTPOS ©2014";
    public static String TABLE;
    public static String LANG = "";
    public static String CASHIER;
    public static String BTN_FLOORPLAN;
    public static String USERCODE;
    public static String TableSelected = "";
    public static String TEMP_TABLE_REFUND = "999";
    public static String db_member = "";    
    public static boolean printdriver = true;
    public static String printerDriverName = "SoftPrint";
    public static boolean useprint = false;
    public static boolean printkic = false;
    public static boolean autoqty = false;
    public static boolean MemberAlready = false;
    
    public static String driverNotSupport = "ยังไม่ Support การพิมพ์ผ่าน Driver !";
    
    public static String getDateDefault(){
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.set(2000, 1, 1);
        
        return simp.format(c.getTime());
    }
    
    public static void ClearOPT(){
        OPT = new String[]{"","","","","","","","",""};
    }
    
    public static String getComPort(){
        return POSHWSetup.Bean(getMacno()).getPRNPort();
    }
    
    public static String getMacno(){
        String macno;
        try {
            FileInputStream fs = new FileInputStream(FILE_CONFIG);
            DataInputStream ds = new DataInputStream(fs);
            BufferedReader br = new BufferedReader(new InputStreamReader(ds));
            macno = "";
            String tmp;
            while ((tmp = br.readLine()) != null)   {
                String []data = tmp.split(",", tmp.length());
                if(data[0].equalsIgnoreCase("macno")){
                    Value.MACNO = data[1];
                    macno = data[1];
                    break;
                }
            }
            br.close();
            ds.close();
            fs.close();
        } catch (IOException e) {
            MSG.ERR(e.getMessage());
            macno = "";
        }
        
        if(!macno.equals("")){
            //check macno in database
            try {
                String sql = "select * "
                        + "from poshwsetup "
                        + "where OnAct='N' "
                        + "and Terminal='"+macno+"'";
                ResultSet rs = MySQLConnect.getResultSet(sql);
                if(rs.next()){
                    macno = rs.getString("Terminal");
                }else{
                    macno = "Mano not available!";
                }
            } catch (Exception e) {
                MSG.ERR(e.getMessage());
                macno = "";
            }
        }
        
        return macno;
    }
    
}
