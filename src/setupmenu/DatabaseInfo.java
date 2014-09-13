package setupmenu;

import database.MySQLConnect;
import java.util.List;
import java.util.Map;
import util.DatabaseUtility;

public abstract class DatabaseInfo {
    public static DatabaseUtility du;

    public DatabaseInfo() {
        if(du == null){
            du = new DatabaseUtility(MySQLConnect.con);
        }
    }
    
    public abstract List<Map<String,Object>> getAllData();
    
}
