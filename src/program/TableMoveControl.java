package program;

import com.softpos.member.MemberBean;
import java.util.ArrayList;

public class TableMoveControl {

    private static MemberBean memberBean;

    public TableMoveControl(MemberBean memberBean) {
        this.memberBean = memberBean;
    }

    public static void moveTable(String tableFrom, String tableDest) {

        // Check table are same
        if (tableFrom.equals(tableDest)) {
            //keyMoveTable.put("Table invalid move !", false);
        } else {

            // Check table form exist data
            BalanceControl bControl = new BalanceControl();
            ArrayList<BalanceBean> dataBalanceFrom = bControl.getAllBalance(tableFrom);
            ArrayList<BalanceBean> dataBalanceDest = bControl.getAllBalance(tableDest);

            // Table from no data
            if (dataBalanceFrom.isEmpty()) {
                //keyMoveTable.put("Table "+tableFrom+" no data to move", false);
            } else {
                // Table destination no data
                if (dataBalanceDest.isEmpty()) {
                    //keyMoveTable.put("Move success", true);
                    for (int i = 0; i < dataBalanceFrom.size(); i++) {
                        BalanceBean bean = (BalanceBean) dataBalanceFrom.get(i);
                        String R_Index = bean.getR_Index();
                        bControl.moveBalanceAll(tableDest, bean);
                        bControl.deleteBalance(tableFrom, bean.getR_PluCode(), R_Index);
                    }

                    // Clear table destination
                    TableFileControl tbFile = new TableFileControl();
                    tbFile.setDefaultTableFile(tableFrom);
                } else {
                    //keyMoveTable.put("Table "+tableDest+" exist data already , Confirm to Move table ?", true);
                    for (int i = 0; i < dataBalanceFrom.size(); i++) {
                        BalanceBean bean = (BalanceBean) dataBalanceFrom.get(i);
                        String R_Index = bean.getR_Index();
                        bControl.moveBalanceAll(tableDest, bean);
                        bControl.deleteBalance(tableFrom, bean.getR_PluCode(), R_Index);
                    }

                    // Clear table destination
                    TableFileControl tbFile = new TableFileControl();
                    tbFile.setDefaultTableFile(tableFrom);
                }
            }
        }
    }

    public static void moveProduct(String table1, String table2, String R_Index) {
        BalanceControl bControl = new BalanceControl();
        ArrayList<BalanceBean> dataBalanceFrom = bControl.getBalanceIndex(R_Index);

        for (int i = 0; i < dataBalanceFrom.size(); i++) {
            BalanceBean bean = (BalanceBean) dataBalanceFrom.get(i);
            bControl.moveBalanceAll(table2, bean);
            bControl.deleteBalance(table1, bean.getR_PluCode(), R_Index);
        }

        BalanceControl.updateProSerTable(table1, memberBean);
        BalanceControl.updateProSerTable(table2, memberBean);
    }
}
