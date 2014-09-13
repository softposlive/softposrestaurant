package com.softpos.cupon;

import database.MySQLConnect;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import util.MSG;

public class CuponControl {
    
    SimpleDateFormat simp = new SimpleDateFormat("EE", Locale.ENGLISH);
    private String EE = "";
    
    public CuponControl(){
        EE = simp.format(new Date());
    }
    
    public static void main(String[] args) {
        new MySQLConnect();
        CuponControl cc = new CuponControl();
        cc.getCupon("KB1");
    }
    
    public void updateCupontDiscount(String cuCode, String table, String pcode, String r_index){
        CuponBean bean = getCupon(cuCode);
        if(bean!=null){
            String R_PrCuType = "-C";//update prefix cupon
            
            double CuDisc = bean.getCuDisc();
            double CuDiscBath = bean.getCuDiscBath();
            ArrayList<CuponListBean> listCuponPlu = bean.getCuponList();
            
            //ใช้เป็นส่วนลด
            if(bean.getChkMember().equals("Y")){
                
                //ให้ส่วนลดได้เฉพาะรายการที่กำหนด
                if(bean.getCuType().equals("A")){
                    for(int i=0;i<listCuponPlu.size();i++){
                        CuponListBean cListBean = (CuponListBean)listCuponPlu.get(i);
                        if(cListBean.getPCode().equals(pcode)){
                            
                        }
                    }
                }
                
                //ให้ส่วนลดได้ทุกรายการที่ลดได้
                else if(bean.getCuType().equals("B")){
                    
                }
            }
            
            //ไม่ใช้เป็นส่วนลดหลัก
            else if(bean.getChkMember().equals("N")){
                
                //ให้ส่วนลดได้เฉพาะรายการที่กำหนด
                if(bean.getCuType().equals("A")){
                    for(int i=0;i<listCuponPlu.size();i++){
                        CuponListBean cListBean = (CuponListBean)listCuponPlu.get(i);
                        if(cListBean.getPCode().equals(pcode)){
                            
                        }
                    }
                }
                
                //ให้ส่วนลดได้ทุกรายการที่ลดได้
                else if(bean.getCuType().equals("B")){
                    
                }
            }
        }else{
            MSG.ERR("คูปองส่วนลดนี้ไม่สามารถนำมาใช้งานได้ กรุณาตรวจสอบ !!!");
        }
    }
    
    public ArrayList<CuponBean> listCupon(){
        ArrayList<CuponBean> listBean = new ArrayList<CuponBean>();
        try {
            String sql = "select * from cupon "
                    + "where CuEnd >=curdate() "
                    + "and CuBegin<=curdate() "
                    + "and CuStrDay like '%"+EE+"%';";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            while(rs.next()){
                CuponBean bean = new CuponBean();
                bean.setCuCode(rs.getString("CuCode"));
                bean.setCuName(rs.getString("CuName"));
                bean.setCuBegin(rs.getDate("CuBegin"));
                bean.setCuEnd(rs.getDate("CuEnd"));
                bean.setCuStrDay(rs.getString("CuStrDay"));
                bean.setCuType(rs.getString("CuType"));
                bean.setCuADisc(rs.getString("CuADisc"));
                bean.setCuADiscBath(rs.getFloat("CuADiscBath"));
                bean.setCuBDisc(rs.getString("CuBDisc"));
                bean.setCuBDiscBath(rs.getFloat("CuBDiscBath"));
                bean.setCuPLUList(rs.getString("CuPLUList"));
                bean.setCuPLU1(rs.getString("CuPLU1"));
                bean.setCuPLU2(rs.getString("CuPLU2"));
                bean.setCuPLU3(rs.getString("CuPLU3"));
                bean.setCuPLU4(rs.getString("CuPLU4"));
                bean.setCuPLU5(rs.getString("CuPLU5"));
                bean.setCuPLU6(rs.getString("CuPLU6"));
                bean.setCuPLU7(rs.getString("CuPLU7"));
                bean.setCuPLU8(rs.getString("CuPLU8"));
                bean.setCuPLU9(rs.getString("CuPLU9"));
                bean.setCuPLU10(rs.getString("CuPLU10"));
                bean.setCuDisc(rs.getFloat("CuDisc"));
                bean.setCuDiscBath(rs.getFloat("CuDiscBath"));
                bean.setChkMember(rs.getString("ChkMember"));
                bean.setCuDisc2(rs.getFloat("CuDisc2"));
                bean.setCuDiscBath2(rs.getFloat("CuDiscBath2"));
                bean.setCuDisc3(rs.getFloat("CuDisc3"));
                bean.setCuDiscBath3(rs.getFloat("CuDiscBath3"));
                
                String sql2 = "select * from cuponlist where CuCode='"+bean.getCuCode()+"'";
                ResultSet rs2 = MySQLConnect.getResultSet(sql2);
                while(rs2.next()){
                    CuponListBean clBean = new CuponListBean();
                    clBean.setCuCode(rs2.getString("CuCode"));
                    clBean.setPCode(rs2.getString("PCode"));
                    
                    bean.addCuponList(clBean);
                }
                
                rs2.close();
            }
            
            rs.close();
            
        } catch (Exception e) {
            MSG.ERR_MSG(null, e.getMessage());
        }
        
        return listBean;
    }

    public CuponBean getCupon(String cuCode) {
        CuponBean bean = new CuponBean();
        try {
            String sql = "select * from cupon "
                    + "where CuEnd >=curdate() "
                    + "and CuBegin<=curdate() "
                    + "and CuCode='"+cuCode+"' "
                    + "and CuStrDay like '%"+EE+"%';";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            while(rs.next()){
                bean.setCuCode(rs.getString("CuCode"));
                bean.setCuName(rs.getString("CuName"));
                bean.setCuBegin(rs.getDate("CuBegin"));
                bean.setCuEnd(rs.getDate("CuEnd"));
                bean.setCuStrDay(rs.getString("CuStrDay"));
                bean.setCuType(rs.getString("CuType"));
                bean.setCuADisc(rs.getString("CuADisc"));
                bean.setCuADiscBath(rs.getFloat("CuADiscBath"));
                bean.setCuBDisc(rs.getString("CuBDisc"));
                bean.setCuBDiscBath(rs.getFloat("CuBDiscBath"));
                bean.setCuPLUList(rs.getString("CuPLUList"));
                bean.setCuPLU1(rs.getString("CuPLU1"));
                bean.setCuPLU2(rs.getString("CuPLU2"));
                bean.setCuPLU3(rs.getString("CuPLU3"));
                bean.setCuPLU4(rs.getString("CuPLU4"));
                bean.setCuPLU5(rs.getString("CuPLU5"));
                bean.setCuPLU6(rs.getString("CuPLU6"));
                bean.setCuPLU7(rs.getString("CuPLU7"));
                bean.setCuPLU8(rs.getString("CuPLU8"));
                bean.setCuPLU9(rs.getString("CuPLU9"));
                bean.setCuPLU10(rs.getString("CuPLU10"));
                bean.setCuDisc(rs.getFloat("CuDisc"));
                bean.setCuDiscBath(rs.getFloat("CuDiscBath"));
                bean.setChkMember(rs.getString("ChkMember"));
                bean.setCuDisc2(rs.getFloat("CuDisc2"));
                bean.setCuDiscBath2(rs.getFloat("CuDiscBath2"));
                bean.setCuDisc3(rs.getFloat("CuDisc3"));
                bean.setCuDiscBath3(rs.getFloat("CuDiscBath3"));
                
                String sql2 = "select * from cuponlist where CuCode='"+cuCode+"'";
                ResultSet rs2 = MySQLConnect.getResultSet(sql2);
                while(rs2.next()){
                    CuponListBean clBean = new CuponListBean();
                    clBean.setCuCode(rs2.getString("CuCode"));
                    clBean.setPCode(rs2.getString("PCode"));
                    
                    bean.addCuponList(clBean);
                }
                
                rs2.close();
            }
            
            rs.close();
            
            return bean;
        } catch (Exception e) {
            MSG.ERR_MSG(null, e.getMessage());
        }
        
        return null;
    }
}
