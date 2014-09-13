package program;

import database.MySQLConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import util.MSG;

public class BillControl {

    private final POSConfigSetup posConfig;

    public BillControl() {
        PosControl posControl = new PosControl();
        posConfig = posControl.getData();
    }

    public static String getLocalMacNO() {
        PropControl prop = new PropControl();
        return prop.getMacNo();
    }

    public static String getBillIDCurrent() {
        String ReceNo1 = "";
        try {
            String sql = "select ReceNo1 "
                    + "from poshwsetup "
                    + "where Terminal='" + getLocalMacNO() + "'";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            if (rs.next()) {
                ReceNo1 = rs.getString("ReceNo1");
                if (ReceNo1.length() == 1) {
                    ReceNo1 = "000000" + ReceNo1;
                } else if (ReceNo1.length() == 2) {
                    ReceNo1 = "00000" + ReceNo1;
                } else if (ReceNo1.length() == 3) {
                    ReceNo1 = "0000" + ReceNo1;
                } else if (ReceNo1.length() == 4) {
                    ReceNo1 = "000" + ReceNo1;
                } else if (ReceNo1.length() == 5) {
                    ReceNo1 = "00" + ReceNo1;
                } else if (ReceNo1.length() == 6) {
                    ReceNo1 = "0" + ReceNo1;
                }

            }
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }

        return ReceNo1;
    }

    public static String getExistCurrent() {
        String ReceNo1 = "";
        try {
            String sql = "select ReceNo1-1 ReceNo1 "
                    + "from poshwsetup "
                    + "where Terminal='" + getLocalMacNO() + "'";

            ResultSet rs = MySQLConnect.getResultSet(sql);
            if (rs.next()) {
                ReceNo1 = rs.getString("ReceNo1");
                if (ReceNo1.length() == 1) {
                    ReceNo1 = "000000" + ReceNo1;
                } else if (ReceNo1.length() == 2) {
                    ReceNo1 = "00000" + ReceNo1;
                } else if (ReceNo1.length() == 3) {
                    ReceNo1 = "0000" + ReceNo1;
                } else if (ReceNo1.length() == 4) {
                    ReceNo1 = "000" + ReceNo1;
                } else if (ReceNo1.length() == 5) {
                    ReceNo1 = "00" + ReceNo1;
                } else if (ReceNo1.length() == 6) {
                    ReceNo1 = "0" + ReceNo1;
                }

            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ReceNo1;
    }

    public static void updateNextBill() {
        try {
            String sql = "UPDATE poshwsetup "
                    + "SET receno1=receno1+1 "
                    + "WHERE terminal='" + getLocalMacNO() + "'";

            MySQLConnect.exeUpdate(sql);
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    public static void saveBillNo(BillNoBean bean) {
        try {
            String sql = "insert into billno "
                    + "(B_Refno,B_CuponDiscAmt,B_Ontime,B_LoginTime,B_OnDate,B_PostDate,"
                    + "B_Table,B_MacNo,B_Cashier,B_Cust,B_ETD,B_Total,B_Food,B_Drink,B_Product,"
                    + "B_Service,B_ServiceAmt,B_ItemDiscAmt,B_FastDisc,B_FastDiscAmt,B_EmpDisc,"
                    + "B_EmpDiscAmt,B_TrainDisc,B_TrainDiscAmt,B_MemDisc,B_MemDiscAmt,B_SubDisc,"
                    + "B_SubDiscAmt,B_SubDiscBath,B_ProDiscAmt,B_SpaDiscAmt,B_AdjAmt,B_PreDisAmt,"
                    + "B_NetTotal,B_NetFood,B_NetDrink,B_NetProduct,B_NetVat,B_NetNonVat,B_Vat,"
                    + "B_PayAmt,B_Cash,B_GiftVoucher,B_Earnest,B_Ton,B_CrCode1,B_CardNo1,B_AppCode1,"
                    + "B_CrCharge1,B_CrChargeAmt1,B_CrAmt1,B_AccrCode,B_AccrAmt,B_AccrCr,B_MemCode,"
                    + "B_MemName,B_MemBegin,B_MemEnd,B_MemCurSum,B_Void,B_VoidUser,B_VoidTime,"
                    + "B_BillCopy,B_PrnCnt,B_PrnTime1,B_PrnTime2,B_InvNo,B_InvType,B_Bran,B_BranName,"
                    + "B_Tel,B_RecTime,MStamp,MScore,CurStamp,StampRate,B_ChkBill,B_ChkBillTime,"
                    + "B_CashTime,B_WaitTime,B_SumScore,B_CrBank,B_CrCardAmt,B_CrCurPoint,"
                    + "B_CrSumPoint,B_Entertain,B_VoucherDiscAmt,B_VoucherOver,B_NetDiff,"
                    + "B_SumSetDiscAmt,B_DetailFood,B_DetailDrink,B_DetailProduct,B_KicQue,B_ROUNDCLOSE)  "
                    + "values('" + bean.getB_Refno() + "','" + bean.getB_CuponDiscAmt() + "',curtime(),curtime(),curdate(),"
                    + "'" + Value.getDateDefault() + "','" + bean.getB_Table() + "','" + bean.getB_MacNo() + "','" + bean.getB_Cashier() + "','" + bean.getB_Cust() + "',"
                    + "'" + bean.getB_ETD() + "','" + bean.getB_Total() + "','" + bean.getB_Food() + "','" + bean.getB_Drink() + "','" + bean.getB_Product() + "',"
                    + "'" + bean.getB_Service() + "','" + bean.getB_ServiceAmt() + "','" + bean.getB_ItemDiscAmt() + "','" + bean.getB_FastDisc() + "',"
                    + "'" + bean.getB_FastDiscAmt() + "','" + bean.getB_EmpDisc() + "','" + bean.getB_EmpDiscAmt() + "','" + bean.getB_TrainDisc() + "',"
                    + "'" + bean.getB_TrainDiscAmt() + "','" + bean.getB_MemDisc() + "','" + bean.getB_MemDiscAmt() + "','" + bean.getB_SubDisc() + "',"
                    + "'" + bean.getB_SubDiscAmt() + "','" + bean.getB_SubDiscBath() + "','" + bean.getB_ProDiscAmt() + "','" + bean.getB_SpaDiscAmt() + "',"
                    + "'" + bean.getB_AdjAmt() + "','" + bean.getB_PreDisAmt() + "','" + bean.getB_NetTotal() + "','" + bean.getB_NetFood() + "',"
                    + "'" + bean.getB_NetDrink() + "','" + bean.getB_NetProduct() + "','" + bean.getB_NetVat() + "','" + bean.getB_NetNonVat() + "',"
                    + "'" + bean.getB_Vat() + "','" + bean.getB_PayAmt() + "','" + bean.getB_Cash() + "','" + bean.getB_GiftVoucher() + "','" + bean.getB_Earnest() + "',"
                    + "'" + bean.getB_Ton() + "','" + bean.getB_CrCode1() + "','" + bean.getB_CardNo1() + "','" + bean.getB_AppCode1() + "','" + bean.getB_CrCharge1() + "',"
                    + "'" + bean.getB_CrChargeAmt1() + "','" + bean.getB_CrAmt1() + "','" + bean.getB_AccrCode() + "','" + bean.getB_AccrAmt() + "','" + bean.getB_AccrCr() + "',"
                    + "'" + bean.getB_MemCode() + "','" + bean.getB_MemName() + "','" + Value.getDateDefault() + "','" + Value.getDateDefault() + "','" + bean.getB_MemCurSum() + "',"
                    + "'" + bean.getB_Void() + "','" + bean.getB_VoidUser() + "','" + bean.getB_VoidTime() + "','" + bean.getB_BillCopy() + "','" + bean.getB_PrnCnt() + "',"
                    + "'" + bean.getB_PrnTime1() + "','" + bean.getB_PrnTime2() + "','" + bean.getB_InvNo() + "','" + bean.getB_InvType() + "','" + bean.getB_Bran() + "',"
                    + "'" + bean.getB_BranName() + "','" + bean.getB_Tel() + "','" + bean.getB_RecTime() + "','" + bean.getMStamp() + "','" + bean.getMScore() + "',"
                    + "'" + bean.getCurStamp() + "','" + bean.getStampRate() + "','" + bean.getB_ChkBill() + "','" + bean.getB_ChkBillTime() + "','" + bean.getB_CashTime() + "',"
                    + "'" + bean.getB_WaitTime() + "','" + bean.getB_SumScore() + "','" + bean.getB_CrBank() + "','" + bean.getB_CrCardAmt() + "','" + bean.getB_CrCurPoint() + "',"
                    + "'" + bean.getB_CrSumPoint() + "','" + bean.getB_Entertain() + "','" + bean.getB_VoucherDiscAmt() + "','" + bean.getB_VoucherOver() + "',"
                    + "'" + bean.getB_NetDiff() + "','" + bean.getB_SumSetDiscAmt() + "','" + bean.getB_DetailFood() + "','" + bean.getB_DetailDrink() + "',"
                    + "'" + bean.getB_DetailProduct() + "','" + bean.getB_KicQue() + "','" + bean.getB_ROUNDCLOSE() + "')";
            MySQLConnect.exeUpdate(sql);
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    public static String getQueUpdate() {
        String q = "";
        try {
            String sql = "select max(b_refno), max(B_KicQue) maxque from billno";
            int que;
            ResultSet rs = MySQLConnect.getResultSet(sql);
            if (rs.next()) {
                try {
                    que = Integer.parseInt(rs.getString("maxque"));
                    que += 1;
                    if (que < 10) {
                        q = "0" + que;
                    } else {
                        q = "" + que;
                    }
                } catch (SQLException e) {
                    System.out.println("Not get que from billno, n" + e.getMessage());
                    q = "01";
                } catch (NumberFormatException e) {
                    System.out.println("Not get que from billno, n" + e.getMessage());
                    q = "01";
                }
            }
            rs.close();
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }

        return q;
    }

    public static void saveTSale(TSaleBean bean) {
        //update table t_sale
        try {
            String sqlMember = "insert into t_sale "
                    + "(R_Index,R_Refno,R_Table,R_Date,R_Time,MacNo,Cashier,R_Emp,R_PluCode,R_PName,R_Unit,"
                    + "R_Group,R_Status,R_Normal,R_Discount,R_Service,R_Stock,R_Set,R_Vat,R_Type,R_ETD,"
                    + "R_Quan,R_Price,R_Total,R_PrType,R_PrCode,R_PrDisc,R_PrBath,R_PrAmt,R_PrCuType,"
                    + "R_PrCuCode,R_PrCuQuan,R_PrCuAmt,R_Redule,R_DiscBath,R_PrAdj,R_NetTotal,R_Kic,"
                    + "R_KicPrint,R_Refund,VoidMsg,R_Void,R_VoidUser,R_VoidTime,StkCode,PosStk,R_ServiceAmt,"
                    + "R_PrChkType,R_PrQuan,R_PrSubType,R_PrSubCode,R_PrSubQuan,R_PrSubDisc,R_PrSubBath,"
                    + "R_PrSubAmt,R_PrSubAdj,R_PrCuDisc,R_PrCuBath,R_PrCuAdj,R_PrChkType2,R_PrQuan2,"
                    + "R_PrType2,R_PrCode2,R_PrDisc2,R_PrBath2,R_PrAmt2,R_PrAdj2,R_PItemNo,R_PKicQue,"
                    + "R_MoveItem,R_MoveFrom,R_MoveUser,R_MoveFlag)  "
                    + "values("
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?)";

            PreparedStatement pre = MySQLConnect.getPreparedStatement(sqlMember);
            pre.setString(1, bean.getR_Index());
            pre.setString(2, bean.getR_Refno());
            pre.setString(3, bean.getR_Table());
            pre.setString(4, DateControl.F2.format(new Date()));//R_Date
            pre.setString(5, DateControl.T1.format(new Date()));//R_Time
            pre.setString(6, bean.getMacNo());
            pre.setString(7, bean.getCashier());
            pre.setString(8, bean.getR_Emp());
            pre.setString(9, bean.getR_PluCode());
            pre.setString(10, bean.getR_PName());
            pre.setString(11, bean.getR_Unit());
            pre.setString(12, bean.getR_Group());
            pre.setString(13, bean.getR_Status());
            pre.setString(14, bean.getR_Normal());
            pre.setString(15, bean.getR_Discount());
            pre.setString(16, bean.getR_Service());
            pre.setString(17, bean.getR_Stock());
            pre.setString(18, bean.getR_Set());
            pre.setString(19, bean.getR_Vat());
            pre.setString(20, bean.getR_Type());
            pre.setString(21, bean.getR_ETD());
            pre.setDouble(22, bean.getR_Quan());
            pre.setDouble(23, bean.getR_Price());
            pre.setDouble(24, bean.getR_Total());
            pre.setString(25, bean.getR_PrType());
            pre.setString(26, bean.getR_PrCode());
            pre.setDouble(27, bean.getR_PrDisc());
            pre.setDouble(28, bean.getR_PrBath());
            pre.setDouble(29, bean.getR_PrAmt());
            pre.setString(30, bean.getR_PrCuType());
            pre.setString(31, bean.getR_PrCuCode());
            pre.setDouble(32, bean.getR_PrCuQuan());
            pre.setDouble(33, bean.getR_PrCuAmt());
            pre.setDouble(34, bean.getR_Redule());
            pre.setDouble(35, bean.getR_DiscBath());
            pre.setDouble(36, bean.getR_PrAdj());
            pre.setDouble(37, bean.getR_NetTotal());
            pre.setString(38, bean.getR_Kic());
            pre.setString(39, bean.getR_KicPrint());
            pre.setString(40, bean.getR_Refund());
            pre.setString(41, bean.getVoidMsg());
            pre.setString(42, bean.getR_Void());
            pre.setString(43, bean.getR_VoidUser());
            pre.setString(44, bean.getR_VoidTime());
            pre.setString(45, bean.getStkCode());
            pre.setString(46, bean.getPosStk());
            pre.setDouble(47, bean.getR_ServiceAmt());
            pre.setString(48, bean.getR_PrChkType());
            pre.setDouble(49, bean.getR_PrQuan());
            pre.setString(50, bean.getR_PrSubType());
            pre.setString(51, bean.getR_PrSubCode());
            pre.setDouble(52, bean.getR_PrSubQuan());
            pre.setDouble(53, bean.getR_PrSubDisc());
            pre.setDouble(54, bean.getR_PrSubBath());
            pre.setDouble(55, bean.getR_PrSubAmt());
            pre.setDouble(56, bean.getR_PrSubAdj());
            pre.setDouble(57, bean.getR_PrCuDisc());
            pre.setDouble(58, bean.getR_PrCuBath());
            pre.setDouble(59, bean.getR_PrCuAdj());
            pre.setString(60, bean.getR_PrChkType2());
            pre.setDouble(61, bean.getR_PrQuan2());
            pre.setString(62, bean.getR_PrType2());
            pre.setString(63, bean.getR_PrCode2());
            pre.setDouble(64, bean.getR_PrDisc2());
            pre.setDouble(65, bean.getR_PrBath2());
            pre.setDouble(66, bean.getR_PrAmt2());
            pre.setDouble(67, bean.getR_PrAdj2());
            pre.setInt(68, bean.getR_PItemNo());
            pre.setInt(69, bean.getR_PKicQue());
            pre.setString(70, bean.getR_MoveItem());
            pre.setString(71, bean.getR_MoveFrom());
            pre.setString(72, bean.getR_MoveUser());
            pre.setString(73, bean.getR_MoveFlag());

            int i = pre.executeUpdate();

        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }
    }

    public String saveBillNo(String table, BillNoBean billBean) {
        //get data from balance, tablefile
        String username;
        String BillNo = BillControl.getBillIDCurrent();

        BalanceControl balanceControl = new BalanceControl();
        ArrayList<BalanceBean> balance = balanceControl.getAllBalance(table);

        //for T_Sale
        TSaleBean tSaleBean = new TSaleBean();
        int size = balance.size();
        double B_NetTotal, B_Food = 0.0, B_Drink = 0.0, B_Product = 0.0, B_Service = 0.0;
        String productType;

        BranchControl branchControl = new BranchControl();
        BranchBean branchBean = branchControl.getBranch();

        int MAX_Que = branchBean.getKicItemNo();
        for (int i = 0; i < size; i++) {
            BalanceBean bean = (BalanceBean) balance.get(i);
            tSaleBean.setR_Index(BillNo + bean.getR_Index());
            tSaleBean.setR_Refno(BillNo);
            tSaleBean.setMacNo(bean.getMacno());
            tSaleBean.setR_Price(bean.getR_Price());
            tSaleBean.setR_Total(bean.getR_Total());
            tSaleBean.setR_NetTotal(bean.getR_Total() - bean.getR_PrAmt());
            tSaleBean.setR_PrType(bean.getR_PrType());
            tSaleBean.setR_PrDisc(bean.getR_PrDisc());
            tSaleBean.setR_PrBath(bean.getR_PrBath());
            tSaleBean.setR_PrAmt(bean.getR_PrAmt());
            tSaleBean.setR_Table(bean.getR_Table());
            tSaleBean.setR_PluCode(bean.getR_PluCode());
            tSaleBean.setStkCode(bean.getR_Stock());
            tSaleBean.setR_Time(DateControl.F2.format(new Date()));
            tSaleBean.setR_Emp(bean.getR_Emp());
            tSaleBean.setR_PrCuType(bean.getR_PrCuType());
            tSaleBean.setR_PrCuCode(bean.getR_PrCuCode());
            tSaleBean.setR_Kic(bean.getR_Kic());
            tSaleBean.setR_KicPrint(bean.getR_KicPrint());
            tSaleBean.setVoidMsg("");
            tSaleBean.setR_Void(bean.getR_Void());
            tSaleBean.setR_VoidUser(bean.getR_VoidUser());
            tSaleBean.setR_VoidTime(bean.getR_VoidTime());
            tSaleBean.setR_Stock(bean.getR_Stock());
            tSaleBean.setR_PrChkType(bean.getR_PrChkType());
            tSaleBean.setR_PrSubType(bean.getR_PrSubType());
            tSaleBean.setR_PrSubCode(bean.getR_PrSubCode());

            tSaleBean.setR_PrChkType2("");
            tSaleBean.setR_PrType2("");
            tSaleBean.setR_PrCode2("");
            tSaleBean.setStkCode(bean.getStkCode());

            productType = bean.getR_Type();

            if (productType.equals(ProductBean.FOOD)) {
                B_Food += bean.getR_Total();
            } else if (productType.equals(ProductBean.DRINK)) {
                B_Drink += bean.getR_Total();
            } else if (productType.equals(ProductBean.PRODUCT)) {
                B_Product += bean.getR_Total();
            }

            username = bean.getCashier();

            tSaleBean.setR_PName(ThaiUtil.Unicode2ASCII(bean.getR_PName()));
            tSaleBean.setR_Unit(ThaiUtil.Unicode2ASCII(bean.getR_Unit()));
            tSaleBean.setR_Group(bean.getR_Group());
            tSaleBean.setR_Status(bean.getR_Status());
            tSaleBean.setR_Normal(bean.getR_Normal());
            tSaleBean.setR_Discount(bean.getR_Discount());
            tSaleBean.setR_Service(bean.getR_Service());
            tSaleBean.setR_Stock(bean.getR_Stock());
            tSaleBean.setR_Set(bean.getR_Set());
            tSaleBean.setR_Vat(bean.getR_Vat());
            tSaleBean.setR_Type(bean.getR_Type());
            tSaleBean.setR_ETD(bean.getR_ETD());
            tSaleBean.setR_Quan(bean.getR_Quan());
            tSaleBean.setR_PrCode(bean.getR_PrCode());

            tSaleBean.setR_LinkIndex("");
            tSaleBean.setR_Pause("");
            tSaleBean.setR_VoidPause("");
            tSaleBean.setR_SPIndex("");

            //set default cashier test
            tSaleBean.setCashier(username);

            BillControl.saveTSale(tSaleBean);

            if (!bean.getR_PrType().equals("") && !bean.getR_PrCode().equals("")) {
                //update T_Promotion
                TPromotionBean promotionBean = new TPromotionBean();
                promotionBean.setR_Index(tSaleBean.getR_Index());
                promotionBean.setR_RefNo(tSaleBean.getR_Refno());
                promotionBean.setTerminal(tSaleBean.getMacNo());
                promotionBean.setCashier(tSaleBean.getCashier());
                promotionBean.setPrCode(tSaleBean.getR_PrCode());
                promotionBean.setPrType(tSaleBean.getR_PrType());
                promotionBean.setPCode(tSaleBean.getR_PluCode());
                promotionBean.setPDisc(tSaleBean.getR_PrDisc());
                promotionBean.setPDiscBath(tSaleBean.getR_DiscBath());
                promotionBean.setPPrice(tSaleBean.getR_Price());
                promotionBean.setPQty(tSaleBean.getR_Quan());
                promotionBean.setPrAmt(tSaleBean.getR_PrAmt());
                promotionBean.setFlage("-");

                PromotionControl proControl = new PromotionControl();
                proControl.saveTPromotion(promotionBean);
            }
        }

        //for Billno
        try {
            TableFileControl tableControl = new TableFileControl();
            TableFileBean tableFile = tableControl.getData(table);

            BillNoBean billNo = new BillNoBean();
            double cashPay = billBean.getB_Cash();
            double creditPay = billBean.getB_CrAmt1();
            double totalSum = tableFile.getNetTotal();
            double B_Ton = billBean.getB_Ton();
            double B_Total = tableFile.getTAmount();
            B_NetTotal = tableFile.getNetTotal();
            
            billNo.setB_Earnest(billBean.getB_Earnest());

            //item discount
            billNo.setB_ItemDiscAmt(billBean.getB_ItemDiscAmt());

            //discount all
            billNo.setB_FastDiscAmt(billBean.getB_FastDiscAmt());
            billNo.setB_FastDisc(billBean.getB_FastDisc());
            
            billNo.setB_CuponDiscAmt(billBean.getB_CuponDiscAmt());
            billNo.setB_SubDiscBath(billBean.getB_SubDiscBath());
            
            billNo.setB_EmpDiscAmt(billBean.getB_EmpDiscAmt());
            billNo.setB_EmpDisc(billBean.getB_EmpDisc());
            
            billNo.setB_TrainDiscAmt(billBean.getB_TrainDiscAmt());
            billNo.setB_TrainDisc(billBean.getB_TrainDisc());
            
            billNo.setB_MemDiscAmt(billBean.getB_MemDiscAmt());
            billNo.setB_MemDisc(billBean.getB_MemDisc());
            
            billNo.setB_SubDisc(billBean.getB_SubDisc());
            billNo.setB_SubDiscAmt(billBean.getB_SubDiscAmt());

            //update credit bean
            billNo.setB_CrCode1(billBean.getB_CrCode1());
            billNo.setB_CardNo1(billBean.getB_CardNo1());
            billNo.setB_AppCode1(billBean.getB_AppCode1());
            billNo.setB_CrAmt1(billBean.getB_CrAmt1());

            //Add New
            billNo.setB_ProDiscAmt(tableFile.getProDiscAmt());
            billNo.setB_KicQue("" + MAX_Que);

            billNo.setB_Total(B_Total);
            billNo.setB_Food(B_Food);
            billNo.setB_Drink(B_Drink);
            billNo.setB_Product(B_Product);

            billNo.setB_Service(B_Service);
            billNo.setB_ServiceAmt(billBean.getB_ServiceAmt());
            billNo.setB_NetTotal(billBean.getB_NetTotal());
            billNo.setB_NetVat(B_Total);
            billNo.setB_MemBegin(new Date());
            billNo.setB_MemEnd(new Date());
            billNo.setB_Ton(B_Ton);

            billNo.setB_ChkBillTime(DateControl.T1.format(new Date()));
            billNo.setB_CashTime(DateControl.T1.format(new Date()));
            billNo.setB_Ontime(DateControl.T1.format(new Date()));

            billNo.setB_Table(table);
            billNo.setB_Refno(BillNo);
            billNo.setB_MacNo(Value.MACNO);
            billNo.setB_Cashier(tableFile.getCashier());
            billNo.setB_Cust(tableFile.getTCustomer());
            billNo.setB_ETD("E");
            billNo.setB_Cash(cashPay);
            billNo.setB_Total(B_Total);
            billNo.setB_MemCode(tableFile.getMemCode());

            billNo.setB_Service(POSConfigSetup.Bean().getP_Service());

            billNo.setB_PayAmt(cashPay);
            //
            if (new CompanyConfig().includeVat()) {
                billNo.setB_NetVat(totalSum);
                //set table billno
                billNo.setB_Vat(totalSum * posConfig.getP_Vat() / (100 + posConfig.getP_Vat()));
                billNo.setB_CrAmt1(creditPay);
            } else if (new CompanyConfig().excludeVat()) {
                totalSum = tableFile.getTAmount() + billNo.getB_ServiceAmt();
                billNo.setB_NetVat(totalSum);
                //set table billno
                billNo.setB_Vat(totalSum * posConfig.getP_Vat() / 100);
                billNo.setB_CrAmt1(creditPay);
            }

            // set default cashier test 
            billNo.setB_OnDate(new Date());
            billNo.setB_PostDate(new Date());
            billNo.setB_NetDiff(billBean.getB_NetDiff());
            
            // set giftvoucher
            billNo.setB_GiftVoucher(billBean.getB_GiftVoucher());
            
            // set AR
            billNo.setB_AccrCode(billBean.getB_AccrCode());
            billNo.setB_AccrAmt(billBean.getB_AccrAmt());
            billNo.setB_AccrCr(billBean.getB_AccrCr());

            billNo.setB_InvType("");
            billNo.setB_Bran("");
            billNo.setB_ChkBill("Y");
            billNo.setB_CrBank("");
            billNo.setB_MemName("");
            billNo.setB_VoidUser("");
            billNo.setB_VoidTime("");
            billNo.setB_PrnTime1("");
            billNo.setB_PrnTime2("");
            billNo.setB_InvNo("");
            billNo.setB_BranName("");
            billNo.setB_Tel("");
            billNo.setB_RecTime("");
            billNo.setMStamp("");
            billNo.setMScore("");
            billNo.setCurStamp("");
            billNo.setStampRate("");

            BillControl.saveBillNo(billNo);
            if (Value.useprint) {
                PPrint print = new PPrint();
                print.PrintSubTotalBill(BillNo, table);
            }
            //clear transaction for table (balance, tablefile)
            balanceControl.setDefaultBalance(table);
            tableControl.setDefaultTableFile(table);

            BillControl.updateNextBill();
            BranchControl.updateKicItemNo();
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
        }

        return BillNo;
    }

    public ArrayList<TSaleBean> getAllTSaleToBill(String billNo) {
        String sql = "select R_Normal, R_PluCode, R_PName, sum(R_Quan) R_Quan, sum(R_Total) R_Total, "
                + "sum(R_Price) R_Price, R_ETD "
                + "from t_sale "
                + "where r_refno='" + billNo + "' "
                + "group by R_PluCode ";
        if (POSConfigSetup.Bean().getP_PrintByItemType().equals("T")) {
            sql += "order by R_Type, R_Index";
        } else if (POSConfigSetup.Bean().getP_PrintByItemType().equals("D")) {
            sql += "order by R_Status, R_Index";
        }

        ArrayList<TSaleBean> data = new ArrayList<TSaleBean>();
        try {
            ResultSet rs = MySQLConnect.getResultSet(sql);
            while (rs.next()) {
                TSaleBean tsale = new TSaleBean();
                tsale.setR_Normal(rs.getString("R_Normal"));
                tsale.setR_PluCode(rs.getString("R_PluCode"));
                tsale.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                tsale.setR_Quan(rs.getDouble("R_Quan"));
                tsale.setR_Total(rs.getDouble("R_Total"));
                tsale.setR_ETD(rs.getString("R_ETD"));
                tsale.setR_Price(rs.getDouble("R_Price"));

                data.add(tsale);
            }
            rs.close();
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }

        return data;
    }

    public ArrayList<TSaleBean> getAllTSale(String billNo) {
        String sql = "select * from t_sale where R_Refno='" + billNo + "' order by R_Index";
        ArrayList<TSaleBean> data = new ArrayList<TSaleBean>();
        try {
            ResultSet rs = MySQLConnect.getResultSet(sql);
            while (rs.next()) {
                TSaleBean tsale = new TSaleBean();
                tsale.setR_Index(rs.getString("R_Index"));
                tsale.setR_Refno(rs.getString("R_Refno"));
                tsale.setR_Table(rs.getString("R_Table"));
                tsale.setR_Time(rs.getString("R_Time"));
                tsale.setMacNo(rs.getString("MacNo"));
                tsale.setCashier(rs.getString("Cashier"));
                tsale.setR_Emp(rs.getString("R_Emp"));
                tsale.setR_PluCode(rs.getString("R_PluCode"));
                tsale.setR_PName(ThaiUtil.ASCII2Unicode(rs.getString("R_PName")));
                tsale.setR_Unit(ThaiUtil.ASCII2Unicode(rs.getString("R_Unit")));
                tsale.setR_Group(rs.getString("R_Group"));
                tsale.setR_Status(rs.getString("R_Status"));
                tsale.setR_Normal(rs.getString("R_Normal"));
                tsale.setR_Discount(rs.getString("R_Discount"));
                tsale.setR_Service(rs.getString("R_Service"));
                tsale.setR_Stock(rs.getString("R_Stock"));
                tsale.setR_Set(rs.getString("R_Set"));
                tsale.setR_Vat(rs.getString("R_Vat"));
                tsale.setR_Type(rs.getString("R_Type"));
                tsale.setR_ETD(rs.getString("R_ETD"));
                tsale.setR_Quan(rs.getFloat("R_Quan"));
                tsale.setR_Price(rs.getFloat("R_Price"));
                tsale.setR_Total(rs.getFloat("R_Total"));
                String R_PrType = rs.getString("R_PrType");
                if (R_PrType == null) {
                    R_PrType = "";
                }
                tsale.setR_PrType(R_PrType);
                tsale.setR_PrCode(rs.getString("R_PrCode"));
                tsale.setR_PrDisc(rs.getFloat("R_PrDisc"));
                tsale.setR_PrBath(rs.getFloat("R_PrBath"));
                tsale.setR_PrAmt(rs.getFloat("R_PrAmt"));
                tsale.setR_PrCuType(rs.getString("R_PrCuType"));
                tsale.setR_PrCuCode(rs.getString("R_PrCuCode"));
                tsale.setR_PrCuQuan(rs.getFloat("R_PrCuQuan"));
                tsale.setR_PrCuAmt(rs.getFloat("R_PrCuAmt"));
                tsale.setR_Redule(rs.getFloat("R_Redule"));
                tsale.setR_DiscBath(rs.getFloat("R_DiscBath"));
                tsale.setR_PrAdj(rs.getFloat("R_PrAdj"));
                //tsale.setR_PreDisAmt(rs.getFloat("R_PreDisAmt"));
                tsale.setR_NetTotal(rs.getFloat("R_NetTotal"));
                tsale.setR_Kic(rs.getString("R_Kic"));
                tsale.setR_KicPrint(rs.getString("R_KicPrint"));
                tsale.setR_Refund(rs.getString("R_Refund"));
                tsale.setVoidMsg(rs.getString("VoidMsg"));
                tsale.setR_Void(rs.getString("R_Void"));
                tsale.setR_VoidUser(rs.getString("R_VoidUser"));
                tsale.setR_VoidTime(rs.getString("R_VoidTime"));
                tsale.setStkCode(rs.getString("StkCode"));
                tsale.setPosStk(rs.getString("PosStk"));
                tsale.setR_ServiceAmt(rs.getFloat("R_ServiceAmt"));
                tsale.setR_PrChkType(rs.getString("R_PrChkType"));
                tsale.setR_PrQuan(rs.getFloat("R_PrQuan"));
                tsale.setR_PrSubType(rs.getString("R_PrSubType"));
                tsale.setR_PrSubCode(rs.getString("R_PrSubCode"));
                tsale.setR_PrSubQuan(rs.getFloat("R_PrSubQuan"));
                tsale.setR_PrSubDisc(rs.getFloat("R_PrSubDisc"));
                tsale.setR_PrSubBath(rs.getFloat("R_PrSubBath"));
                tsale.setR_PrSubAmt(rs.getFloat("R_PrSubAmt"));
                tsale.setR_PrSubAdj(rs.getFloat("R_PrSubAdj"));
                tsale.setR_PrCuDisc(rs.getFloat("R_PrCuDisc"));
                tsale.setR_PrCuBath(rs.getFloat("R_PrCuBath"));
                tsale.setR_PrCuAdj(rs.getFloat("R_PrCuAdj"));
                tsale.setR_PrChkType2(rs.getString("R_PrChkType2"));
                tsale.setR_PrQuan2(rs.getFloat("R_PrQuan2"));
                tsale.setR_PrType2(rs.getString("R_PrType2"));
                tsale.setR_PrCode2(rs.getString("R_PrCode2"));
                tsale.setR_PrDisc2(rs.getFloat("R_PrDisc2"));
                tsale.setR_PrBath2(rs.getFloat("R_PrBath2"));
                tsale.setR_PrAmt2(rs.getFloat("R_PrAmt2"));
                tsale.setR_PrAdj2(rs.getFloat("R_PrAdj2"));
                tsale.setR_PItemNo(rs.getInt("R_PItemNo"));
                tsale.setR_PKicQue(rs.getInt("R_PKicQue"));
                tsale.setR_MoveItem(rs.getString("R_MoveItem"));
                tsale.setR_MoveFrom(rs.getString("R_MoveFrom"));
                tsale.setR_MoveUser(rs.getString("R_MoveUser"));
                tsale.setR_MoveFlag(rs.getString("R_MoveFlag"));

                data.add(tsale);
            }
            rs.close();
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }

        return data;
    }

    public BillNoBean getData(String billNo) {
        BillNoBean bean = new BillNoBean();
        try {
            String sql = "select * from billno where B_Refno='" + billNo + "'";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            if (rs.next()) {
                bean.setB_Refno(rs.getString("B_Refno"));
                bean.setB_CuponDiscAmt(rs.getFloat("B_CuponDiscAmt"));
                bean.setB_Ontime(rs.getString("B_Ontime"));
                bean.setB_LoginTime(rs.getString("B_LoginTime"));
                bean.setB_Table(rs.getString("B_Table"));
                bean.setB_MacNo(rs.getString("B_MacNo"));
                bean.setB_Cashier(rs.getString("B_Cashier"));
                bean.setB_Cust(rs.getInt("B_Cust"));
                bean.setB_ETD(rs.getString("B_ETD"));
                bean.setB_Total(rs.getFloat("B_Total"));
                bean.setB_Food(rs.getFloat("B_Food"));
                bean.setB_Drink(rs.getFloat("B_Drink"));
                bean.setB_Product(rs.getFloat("B_Product"));
                bean.setB_Service(rs.getFloat("B_Service"));
                bean.setB_ServiceAmt(rs.getFloat("B_ServiceAmt"));
                bean.setB_ItemDiscAmt(rs.getFloat("B_ItemDiscAmt"));
                bean.setB_FastDisc(rs.getString("B_FastDisc"));
                bean.setB_FastDiscAmt(rs.getFloat("B_FastDiscAmt"));
                bean.setB_EmpDisc(rs.getString("B_EmpDisc"));
                bean.setB_EmpDiscAmt(rs.getFloat("B_EmpDiscAmt"));
                bean.setB_TrainDisc(rs.getString("B_TrainDisc"));
                bean.setB_TrainDiscAmt(rs.getFloat("B_TrainDiscAmt"));
                bean.setB_MemDisc(rs.getString("B_MemDisc"));
                bean.setB_MemDiscAmt(rs.getFloat("B_MemDiscAmt"));
                bean.setB_SubDisc(rs.getString("B_SubDisc"));
                bean.setB_SubDiscAmt(rs.getFloat("B_SubDiscAmt"));
                bean.setB_SubDiscBath(rs.getFloat("B_SubDiscBath"));
                bean.setB_ProDiscAmt(rs.getFloat("B_ProDiscAmt"));
                bean.setB_SpaDiscAmt(rs.getFloat("B_SpaDiscAmt"));
                bean.setB_AdjAmt(rs.getFloat("B_AdjAmt"));
                bean.setB_NetTotal(rs.getFloat("B_NetTotal"));
                bean.setB_NetFood(rs.getFloat("B_NetFood"));
                bean.setB_NetDrink(rs.getFloat("B_NetDrink"));
                bean.setB_NetProduct(rs.getFloat("B_NetProduct"));
                bean.setB_NetVat(rs.getFloat("B_NetVat"));
                bean.setB_NetNonVat(rs.getFloat("B_NetNonVat"));
                bean.setB_Vat(rs.getFloat("B_Vat"));
                bean.setB_PayAmt(rs.getFloat("B_PayAmt"));
                bean.setB_Cash(rs.getFloat("B_Cash"));
                bean.setB_GiftVoucher(rs.getFloat("B_GiftVoucher"));
                bean.setB_Earnest(rs.getFloat("B_Earnest"));
                bean.setB_Ton(rs.getFloat("B_Ton"));
                bean.setB_CrCode1(rs.getString("B_CrCode1"));
                bean.setB_CardNo1(rs.getString("B_CardNo1"));
                bean.setB_AppCode1(rs.getString("B_AppCode1"));
                bean.setB_CrCharge1(rs.getFloat("B_CrCharge1"));
                bean.setB_CrChargeAmt1(rs.getFloat("B_CrChargeAmt1"));
                bean.setB_CrAmt1(rs.getFloat("B_CrAmt1"));
                bean.setB_AccrCode(rs.getString("B_AccrCode"));
                bean.setB_AccrAmt(rs.getFloat("B_AccrAmt"));
                bean.setB_AccrCr(rs.getInt("B_AccrCr"));
                bean.setB_MemCode(rs.getString("B_MemCode"));
                bean.setB_MemName(rs.getString("B_MemName"));
                bean.setB_MemCurSum(rs.getFloat("B_MemCurSum"));
                bean.setB_Void(rs.getString("B_Void"));
                bean.setB_VoidUser(rs.getString("B_VoidUser"));
                bean.setB_VoidTime(rs.getString("B_VoidTime"));
                bean.setB_BillCopy(rs.getInt("B_BillCopy"));
                bean.setB_PrnCnt(rs.getInt("B_PrnCnt"));
                bean.setB_PrnTime1(rs.getString("B_PrnTime1"));
                bean.setB_PrnTime2(rs.getString("B_PrnTime2"));
                bean.setB_InvNo(rs.getString("B_InvNo"));
                bean.setB_InvType(rs.getString("B_InvType"));
                bean.setB_Bran(rs.getString("B_Bran"));
                bean.setB_BranName(rs.getString("B_BranName"));
                bean.setB_Tel(rs.getString("B_Tel"));
                bean.setB_RecTime(rs.getString("B_RecTime"));
                bean.setMStamp(rs.getString("MStamp"));
                bean.setMScore(rs.getString("MScore"));
                bean.setCurStamp(rs.getString("CurStamp"));
                bean.setStampRate(rs.getString("StampRate"));
                bean.setB_ChkBill(rs.getString("B_ChkBill"));
                bean.setB_ChkBillTime(rs.getString("B_ChkBillTime"));
                bean.setB_CashTime(rs.getString("B_CashTime"));
                bean.setB_WaitTime(rs.getString("B_WaitTime"));
                bean.setB_SumScore(rs.getFloat("B_SumScore"));
                bean.setB_CrBank(rs.getString("B_CrBank"));
                bean.setB_CrCardAmt(rs.getFloat("B_CrCardAmt"));
                bean.setB_CrCurPoint(rs.getFloat("B_CrCurPoint"));
                bean.setB_CrSumPoint(rs.getFloat("B_CrSumPoint"));
                bean.setB_KicQue(rs.getString("B_KicQue"));
                bean.setB_NetDiff(rs.getFloat("B_NetDiff"));

                try {
                    bean.setB_OnDate(rs.getDate("B_OnDate"));
                    bean.setB_PostDate(rs.getDate("B_PostDate"));
                    bean.setB_MemBegin(rs.getDate("B_MemBegin"));
                    bean.setB_MemEnd(rs.getDate("B_MemEnd"));
                } catch (SQLException e) {
                    MSG.ERR(null, e.getMessage());
                }
            }

            rs.close();
        } catch (Exception e) {
            MSG.ERR(null, e.getMessage());
        }

        return bean;
    }

}
