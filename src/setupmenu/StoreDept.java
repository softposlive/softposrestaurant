package setupmenu;

import database.MySQLConnect;
import program.ThaiUtil;
import setupmenu.DeptButtonBean;
import util.MSG;

public class StoreDept {

    public boolean store(DeptButtonBean bean) {
        String sql = "";
        try {
            sql = "INSERT INTO menusetup (code_id,code_type,pcode,shortname,ppathname,pcolor)"
                    + " VALUES ('" + bean.getButtonName() + "','" + bean.getButtonType().toCharArray()[0] + "',"
                    + "'" + ThaiUtil.Unicode2ASCII(bean.getShortDesc()) + "','','','"+bean.getPcolor()+"')";
            return MySQLConnect.exeUpdate(sql) > 0;
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage() + "\n" + sql);
            return false;
        }
    }

    public boolean storeUpdate(DeptButtonBean bean) {
        String sql = "";
        try {
            sql = "UPDATE menusetup SET "
                    + "code_type = '" + bean.getButtonType().toCharArray()[0] + "',"
                    + "pcode = '" + bean.getGroupcode() + "',"
                    + "shortname = '" + ThaiUtil.Unicode2ASCII(bean.getShortDesc()) + "',"
                    + "ppathname = '',"
                    + "pcolor='"+bean.getPcolor()+"' "
                    + "WHERE code_id = '" + bean.getButtonName() + "'";
            return MySQLConnect.exeUpdate(sql) > 0;
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage() + "\n" + sql);
            return false;
        }
    }
}
