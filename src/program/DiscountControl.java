package program;

import database.MySQLConnect;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class DiscountControl {
    
    public static double getDouble(double db){
         if(POSConfigSetup.Bean().getP_DiscRound().equals("F")){
             return NumberControl.UP_DOWN_25(db);
         }else{
             return db;
         }
    }
    
    public void updateDiscount(String tableNo){
        try {
            String sql = "select sum(R_PrAmt+R_DiscBath) R_PrAmt "
                    + "from balance "
                    + "where R_Void<>'V' "
                    + "and R_Discount='Y' "
                    + "group by R_Table "
                    + "order by R_Index;";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            if(rs.next()){
                String sqlUpd = "update tablefile set ItemDiscAmt='"+rs.getDouble("R_PrAmt")+"'";
                MySQLConnect.exeUpdate(sqlUpd);
            }
            
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
