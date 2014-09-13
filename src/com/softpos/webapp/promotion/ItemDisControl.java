package com.softpos.webapp.promotion;

import com.softpos.member.MemberBean;
import com.softpos.webapp.service.ServiceControl;
import database.MySQLConnect;
import java.sql.ResultSet;
import program.BalanceBean;
import program.BalanceControl;
import program.NumberControl;
import program.POSConfigSetup;
import util.MSG;

public class ItemDisControl {

    public ItemDisControl() {
    }
    
    public void saveBalanceItemDiscount(String PCode, String Table, String Index, double prDisc, double prBaht, MemberBean memberBean){
        BalanceControl bCon = new BalanceControl();
        BalanceBean bean = bCon.getProduct(PCode, Index);

        double prAmt = 0, R_Total = 0, addServiceAmt = 0, Service = 0, ItemDiscAmt = 0, Net_Total = 0;
        Service = POSConfigSetup.Bean().getP_Service();
        
        /* สำหรับการคำนวณยอด GROSS */
        if (POSConfigSetup.Bean().getP_ServiceType().equalsIgnoreCase("G")) {//ยอด Gross
            if (prDisc > 0) {
                //คำนวณแบบ %
                prAmt = prDisc / 100 * bean.getR_Total();
                ItemDiscAmt = prAmt;                
                Net_Total = bean.getR_Total() + ItemDiscAmt;                
                R_Total = bean.getR_Price() - prAmt;                
                bean.setR_PrDisc(prDisc);
                bean.setR_PrBath(0);
                bean.setR_PrAmt(prAmt);
            } else {
                //คำนวณแบบเงินบาท
                R_Total = bean.getR_Total() - prBaht;
                bean.setR_PrDisc(0);
                bean.setR_PrBath(0);
                bean.setR_PrAmt(prBaht);
            }
            
            //ให้คิดค่าบริการด้วย
            if (bean.getR_Service().equalsIgnoreCase("Y")) {                
                addServiceAmt = Service / 100 * bean.getR_Total();
                
                //คิดการปัดเศษ
                addServiceAmt = ServiceControl.getDouble(addServiceAmt);
                
                R_Total = bean.getR_Total() + addServiceAmt;
            }
        } 
        
        /* สำหรับการคำนวณยอด NET */
        else if (POSConfigSetup.Bean().getP_ServiceType().equalsIgnoreCase("N")) {//ยอด Net
            if (prDisc > 0) {
                //คำนวณแบบ %
                double n1,n2,n3;
                n1 = prDisc / 100 * bean.getR_Total();//จำนวนเงินบาทที่ลด
                n2 = bean.getR_Total() - n1;//คงเหลือหลังจากหักส่วนลด
                n3 = Service / 100 * n2;//ค่า Service เป็นเงินบาท
                Net_Total = n2 + n3;
                
                prAmt = prDisc / 100 * bean.getR_Total();
                ItemDiscAmt = prAmt;
                R_Total = bean.getR_Total() - prAmt;
                
                bean.setR_PrDisc(prDisc);
                bean.setR_PrBath(0);
                bean.setR_PrAmt(prAmt);
                
            } else {
                //คำนวณแบบเงินบาท
                R_Total = bean.getR_Total() - prBaht;      
                
                bean.setR_PrDisc(0);
                bean.setR_PrBath(0);
                bean.setR_PrAmt(prBaht);
            }
            
            //ให้คิดค่าบริการด้วย
            if (bean.getR_Service().equalsIgnoreCase("Y")) {
                Service = POSConfigSetup.Bean().getP_Service();
                addServiceAmt = Service / 100 * R_Total;
                
                if(POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("U")){
                    addServiceAmt = NumberControl.UP_BAHT(addServiceAmt);
                }else if(POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("D")){
                    addServiceAmt = NumberControl.DOWN_BAHT(addServiceAmt);
                }else if(POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("O")){
                    //ไม่มีการปัด
                }else if(POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("N")){
                    addServiceAmt = NumberControl.UP_DOWN_NATURAL_BAHT(addServiceAmt);
                }else if(POSConfigSetup.Bean().getP_ServiceRound().equalsIgnoreCase("F")){
                    addServiceAmt = NumberControl.UP_DOWN_25(addServiceAmt);
                }
                
                R_Total = bean.getR_Total() + addServiceAmt;
            }
        }

        bean.setR_PrType("-I");
        bean.setR_PrCode("");
        bean.setR_QuanCanDisc(0);
        bean.setR_Total(R_Total);
        
        /* SAve data to database */
        try {
            String sql = "UPDATE balance set "
                    + "R_PrType = '"+bean.getR_PrType()+"',"
                    + "R_PrCode='"+bean.getR_PrCode()+"',"
                    + "R_PrDisc='"+bean.getR_PrDisc()+"',"
                    + "R_PrBath='"+bean.getR_PrBath()+"',"
                    + "R_PrAmt='"+bean.getR_PrAmt()+"',"
                    + "R_QuanCanDisc='0',"
                    + "R_PrQuan='"+bean.getR_Quan()+"' "
                    + "WHERE R_Index='"+bean.getR_Index()+"' "
                    + "and R_Table='"+bean.getR_Table()+"' "
                    + "and R_PluCode='"+bean.getR_PluCode()+"'";
            MySQLConnect.exeUpdate(sql);
            
            double itemDiscountAll = 0.00;
            try {
                String sqlItemDisc = "select (select sum(R_PrAmt) from balance where r_table=tcode) ItemDiscount "
                        + "from tablefile "
                        + "where tcode='"+Table+"'";
                ResultSet rs = MySQLConnect.getResultSet(sqlItemDisc);
                if(rs.next()){
                    itemDiscountAll = rs.getDouble("ItemDiscount");
                }
                
                rs.close();
            } catch (Exception e) {
                MSG.ERR(null, e.getMessage());
            }
            
            //update tablefile
            sql = "Update tablefile set "
                    + "ItemDiscAmt='"+itemDiscountAll+"',"
                    + "NetTotal='"+Net_Total+"',"
                    + "ServiceAmt='"+addServiceAmt+"' "
                    + "where Tcode='"+Table+"'";
            MySQLConnect.exeUpdate(sql);
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }
        
        BalanceControl.updateProSerTable(Table, memberBean);
    }
}
