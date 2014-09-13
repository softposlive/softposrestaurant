package com.softpos.member;

import database.MySQLConnect;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import program.BalanceBean;

public class MemberControl {
    
    public MemberControl(){
    }
    
    public void updateMemberDiscount(String table, MemberBean memberBean){
        String strDisc = "";
        if(memberBean!=null){
            if(!memberBean.getMember_DiscountRate().equals("")){
                strDisc = memberBean.getMember_DiscountRate();
            }
        }
        try {
            String sql = "select sum(R_PrSubAmt) as MemDiscount "
                    + "from balance where r_table='"+table+"' "
                    + "order by R_Index;";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            if(rs.next()){
                String upd = "update tablefile set "
                        + "MemDiscAmt='"+rs.getDouble("MemDiscount")+"',"
                        + "MemDisc='"+strDisc+"' "
                        + "where tcode='"+table+"'";
                MySQLConnect.exeUpdate(upd);
            }
            
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void updateMemberAllBalance(String table, MemberBean memberBean){
        try {
            /*
            R_PrSubType = -M
            R_PrSubCode = MEM
            R_PrSubQuan = 1
            R_PrSubDisc = 5 (เปอร์เซ็นต์การลด)
            R_PrSubBath = 0
            R_PrSubAmt = 4.75 (5% ของราคาสินค้า)
            R_QuanCanDisc = 0
            */
            String sql = "select * from balance "
                    + "where R_Table='"+table+"' "
                    + "and R_Discount='Y' "
                    + "order by R_Index;";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            while(rs.next()){
                BalanceBean balance = new BalanceBean();

                // คำนวณหาว่าลดเท่าไหร่
                String []subPercent = memberBean.getMember_DiscountRate().split("/");
                int Percent = 0;
                
                if (subPercent.length == 3) {
                    String R_Normal = rs.getString("R_Normal");
                    if (R_Normal == null) {
                        R_Normal = "";
                    }
                    if (R_Normal.equals("N")) {
                        Percent = Integer.parseInt(subPercent[0].trim());
                    } else if (R_Normal.equals("C")) {
                        Percent = Integer.parseInt(subPercent[1].trim());
                    } else if (R_Normal.equals("S")) {
                        Percent = Integer.parseInt(subPercent[2].trim());
                    }
                }
                
                balance.setR_PrSubAmt((rs.getDouble("R_Total")*Percent)/100);
                balance.setR_QuanCanDisc(0);// if member default 0
                
                String sqlUpd = "update balance set "
                        + "R_PrSubType='-M',"
                        + "R_PrSubCode='MEM',"
                        + "R_PrSubQuan='"+rs.getDouble("R_Quan")+"',"
                        + "R_PrSubDisc='"+Percent+"',"
                        + "R_PrSubBath='0',"
                        + "R_PrSubAmt='"+((rs.getDouble("R_Total")*Percent)/100)+"',"
                        + "R_QuanCanDisc='0' "
                        + "where R_Index='"+rs.getString("R_Index")+"' "
                        + "and R_Table='"+rs.getString("R_Table")+"'";
               MySQLConnect.exeUpdate(sqlUpd);
            }
            
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
