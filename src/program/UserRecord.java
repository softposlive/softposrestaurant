package program;

import java.sql.ResultSet;
import java.sql.SQLException;
import database.MySQLConnect;
import java.sql.Statement;

public class UserRecord {
    //User Record
    public String UserCode = "";
    public String UserName = "";
    public String UserGroup = "" ;
    public String LoginTime = "" ;
    public String Cont0 = "N" ;
    public String Cont1 = "N" ;
    public String Cont2 = "N" ;
    public String Cont3 = "N" ;
    public String Cont4 = "N" ;
    public String Cont5 = "N" ;
    public String Cont6 = "N" ;
    public String Cont7 = "N" ;
    public String Cont8 = "N" ;
    public String Cont9 = "N" ;
    public String Cont10 = "N" ;
    public String Cont11 = "N" ;
    public String Cont12 = "N" ;
    public String Cont13 = "N" ;
    public String Cont14 = "N" ;
    public String Cont15 = "N" ;
    public String Sale1 = "N" ;
    public String Sale2 = "N" ;
    public String Sale3 = "N" ;
    public String Sale4 = "N" ;
    public String Sale5 = "N" ;
    public String Sale6 = "N" ;
    public String Sale7 = "N" ;
    public String Sale8 = "N" ;
    public String Sale9 = "N" ;
    public String Sale10 = "N" ;
    public String Sale11 = "N" ;
    public String Sale12 = "N" ;
    public String Sale13 = "N" ;
    public String Sale14 = "N" ;
    public String Sale15 = "N" ;
    public String Sale16 = "N" ;
    public String Sale17 = "N" ;
    public String Sale18 = "N" ;
    public String Sale19 = "N" ;
    public String Sale20 = "N" ;
    public String Sale21 = "N" ;
    public String Sale22 = "N" ;
    public String Sale23 = "N" ;
    public String Sale24 = "N" ;
    public String Sale25 = "N" ;
    public String Sale26 = "N" ;
    public String Sale27 = "N" ;
    public String Sale28 = "N" ;
    public String Sale29 = "N" ;
    public String Sale30 = "N" ;
    public String Sale31 = "N" ;
    public String Sale32 = "N" ;
    public String Sale33 = "N" ;
    public String Sale34 = "N" ;
    public String Sale35 = "N" ;
    public String Sale36 = "N" ;
    public String Stock0 = "N" ;
    public String Stock1 = "N" ;
    public String Stock2 = "N" ;
    public String Stock3 = "N" ;
    public String Stock4 = "N" ;
    public String Stock5 = "N" ;
    public String Stock6 = "N" ;
    public String Stock7 = "N" ;
    public String Stock8 = "N" ;
    public String Stock9 = "N" ;
    public String Stock10 = "N" ;
    public String Stock11 = "N" ;
    public String Stock12 = "N" ;
    public String Stock13 = "N" ;
    public String Stock14 = "N" ;
    public String Stock15 = "N" ;
    public String Stock16 = "N" ;
    public String Stock17 = "N" ;
    public String Stock18 = "N" ;
    public String Stock19 = "N" ;
    public String Stock20 = "N" ;
    public String Stock21 = "N" ;
    public String Stock22 = "N" ;
    public String Stock23 = "N" ;
    public String Stock24 = "N" ;
    public String Stock25 = "N" ;
    public String Stock26 = "N" ;
    public String Stock27 = "N" ;
    public String Stock28 = "N" ;
    public String Stock29 = "N" ;
    public String Stock30 = "N" ;
    public String Stock31 = "N" ;
    public String Stock32 = "N" ;
    public String Stock33 = "N" ;
    public String Stock34 = "N" ;
    public String Stock35 = "N" ;
    public String Stock36 = "N" ;
    public String Stock37 = "N" ;
    public String Stock38 = "N" ;
    public String Stock39 = "N" ;
    public String Stock40 = "N" ;
    public String Stock41 = "N" ;
    public String Stock42 = "N" ;
    public String Stock43 = "N" ;
    public String Stock44 = "N" ;
    public String Stock45 = "N" ;
    
    public boolean  GetUserAction(String XUserCode) {
        try {
            String SQLQuery = "select  *from posuser Where(username= '" + XUserCode + "')";
            ResultSet rec = MySQLConnect.getResultSet(SQLQuery);//stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
                PUtility.showError("รหัสผู้ใช้งาน (Username) และรหัสผ่าน (Password) ไม่ถูกต้อง !!! ");
                return false ;
            } else {
                UserCode = rec.getString("username") ;
                UserName = rec.getString("name") ;
                UserGroup = rec.getString("usergroup") ;
                Cont0 = rec.getString("cont0") ;
                Cont1 = rec.getString("cont1") ;
                Cont2 = rec.getString("cont2") ;
                Cont3 = rec.getString("cont3") ;
                Cont4 = rec.getString("cont4") ;
                Cont5 = rec.getString("cont5") ;
                Cont6 = rec.getString("cont6") ;
                Cont7 = rec.getString("cont7") ;
                Cont8 = rec.getString("cont8") ;
                Cont9 = rec.getString("cont9") ;
                Cont10 = rec.getString("cont10") ;
                Cont11 = rec.getString("cont11") ;
                Cont12 = rec.getString("cont12") ;
                Cont13 = rec.getString("cont13") ;
                Cont14 = rec.getString("cont14") ;
                Cont15 = rec.getString("cont15") ;
                Sale1 = rec.getString("sale1") ;
                Sale2 = rec.getString("sale2") ;
                Sale3 = rec.getString("sale3") ;
                Sale4 = rec.getString("sale4") ;
                Sale5 = rec.getString("sale5") ;
                Sale6 = rec.getString("sale6") ;
                Sale7 = rec.getString("sale7") ;
                Sale8 = rec.getString("sale8") ;
                Sale9 = rec.getString("sale9") ;
                Sale10 = rec.getString("sale10") ;
                Sale11 = rec.getString("sale11") ;
                Sale12 = rec.getString("sale12") ;
                Sale13 = rec.getString("sale13") ;
                Sale14 = rec.getString("sale14") ;
                Sale15 = rec.getString("sale15") ;
                Sale16 = rec.getString("sale16") ;
                Sale17 = rec.getString("sale17") ;
                Sale18 = rec.getString("sale18") ;
                Sale19 = rec.getString("sale19") ;
                Sale20 = rec.getString("sale20") ;
                Sale21 = rec.getString("sale21") ;
                Sale22 = rec.getString("sale22") ;
                Sale23 = rec.getString("sale23") ;
                Sale24 = rec.getString("sale24") ;
                Sale25 = rec.getString("sale25") ;
                Sale26 = rec.getString("sale26") ;
                Sale27 = rec.getString("sale27") ;
                Sale28 = rec.getString("sale28") ;
                Sale29 = rec.getString("sale29") ;
                Sale30 = rec.getString("sale30") ;
                Sale31 = rec.getString("sale31") ;
                Sale32 = rec.getString("sale32") ;
                Sale33 = rec.getString("sale33") ;
                Sale34 = rec.getString("sale34") ;
                Sale35 = rec.getString("sale35") ;
                Sale36 = rec.getString("sale36") ;
                Stock0 = rec.getString("Stock0") ;
                Stock1 = rec.getString("Stock1") ;
                Stock2 = rec.getString("Stock2") ;
                Stock3 = rec.getString("Stock3") ;
                Stock4 = rec.getString("Stock4") ;
                Stock5 = rec.getString("Stock5") ;
                Stock6 = rec.getString("Stock6") ;
                Stock7 = rec.getString("Stock7") ;
                Stock8 = rec.getString("Stock8") ;
                Stock9 = rec.getString("Stock9") ;
                Stock10 = rec.getString("Stock10") ;
                Stock11 = rec.getString("Stock11") ;
                Stock12 = rec.getString("Stock12") ;
                Stock13 = rec.getString("Stock13") ;
                Stock14 = rec.getString("Stock14") ;
                Stock15 = rec.getString("Stock15") ;
                Stock16 = rec.getString("Stock16") ;
                Stock17 = rec.getString("Stock17") ;
                Stock18 = rec.getString("Stock18") ;
                Stock19 = rec.getString("Stock19") ;
                Stock20 = rec.getString("Stock20") ;
                Stock21 = rec.getString("Stock21") ;
                Stock22 = rec.getString("Stock22") ;
                Stock23 = rec.getString("Stock23") ;
                Stock24 = rec.getString("Stock24") ;
                Stock25 = rec.getString("Stock25") ;
                Stock26 = rec.getString("Stock26") ;
                Stock27 = rec.getString("Stock27") ;
                Stock28 = rec.getString("Stock28") ;
                Stock29 = rec.getString("Stock29") ;
                Stock30 = rec.getString("Stock30") ;
                Stock31 = rec.getString("Stock31") ;
                Stock32 = rec.getString("Stock32") ;
                Stock33 = rec.getString("Stock33") ;
                Stock34 = rec.getString("Stock34") ;
                Stock35 = rec.getString("Stock35") ;
                Stock36 = rec.getString("Stock36") ;
                Stock37 = rec.getString("Stock37") ;
                Stock38 = rec.getString("Stock38") ;
                Stock39 = rec.getString("Stock39") ;
                Stock40 = rec.getString("Stock40") ;
                Stock41 = rec.getString("Stock41") ;
                Stock42 = rec.getString("Stock42") ;
                Stock43 = rec.getString("Stock43") ;
                Stock44 = rec.getString("Stock44") ;
                Stock45 = rec.getString("Stock45") ;
                return true ;
            }
        } catch (Exception e) {
            PUtility.showError(e.getMessage());
            return false ;
        }
        
    }
}

