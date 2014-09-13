package setupmenu;

import database.MySQLConnect;
import program.ThaiUtil;
import setupmenu.SubButtonBean;
import util.MSG;


public class StoreSub {

    public boolean store(SubButtonBean bean) {
        String sql = "";
        try {
            sql = "INSERT INTO menusetup (code_id,code_type,pcode,shortname,ppathname,pcolor)"
                + " VALUES ('"+bean.getButtonName()+"','"+bean.getButtonType().toCharArray()[0]+"',"
                    + "'"+ThaiUtil.Unicode2ASCII(bean.getShortDesc())+"','','','"+bean.getPcolor()+"')";
            return MySQLConnect.exeUpdate(sql)>0;
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage()+"\n"+sql);
            return false;
        }
    }

    public boolean storeUpdate(SubButtonBean bean) {
        String sql = "";
        try {
            sql = "UPDATE menusetup SET "
                    + "code_type = '"+bean.getButtonType().toCharArray()[0]+"',"
                    + "pcode = '',"
                    + "shortname = '"+ThaiUtil.Unicode2ASCII(bean.getShortDesc())+"',"
                    + "ppathname = '"+bean.getPicture()+"',"
                    + "pcolor='"+bean.getPcolor()+"' "
                    + "WHERE code_id = '"+bean.getButtonName()+"'";
            return MySQLConnect.exeUpdate(sql)>0;
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage()+"\n"+sql);
            return false;
        }
    }
}
