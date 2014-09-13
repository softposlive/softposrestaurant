package program;

import database.MySQLConnect;
import java.sql.ResultSet;
import util.MSG;

public class CompanyConfig {

    public CompanyConfig() {
    }

    public boolean includeVat() {
        String vatType = "";
        try {
            ResultSet rs = MySQLConnect.getResultSet("select P_VatType from posconfigsetup");
            if (rs.next()) {
                vatType = rs.getString(1);
            }
            rs.close();
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
        }

        return vatType.equals("I");
    }
    
    public boolean excludeVat() {
        String vatType = "";
        try {
            ResultSet rs = MySQLConnect.getResultSet("select P_VatType from posconfigsetup");
            if (rs.next()) {
                vatType = rs.getString(1);
            }
            rs.close();
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
        }

        return vatType.equals("E");
    }
}
