package test;

import database.MySQLConnect;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import util.MSG;

public class TestJasper {

    public static void main(String[] args) {
        new MySQLConnect();
        TestJasper tj = new TestJasper();
        tj.print();
    }

    public void print() {
        try {
            Map param = new HashMap();
            JasperReport jasperReport = null;
            try {
                jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("MayPrintLogo.jasper"));
            } catch (JRException e) {
                MSG.ERR(e.getMessage());
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, MySQLConnect.con);
            int pageSize = jasperPrint.getPages().size();

            if (pageSize > 0) {
                JasperPrintManager.printReport(jasperPrint, false);
            }
        } catch (HeadlessException e) {
            MSG.ERR(e.getMessage());
        } catch (JRException e) {
            MSG.ERR(e.getMessage());
        } catch (Exception e){
            MSG.ERR(e.getMessage());
        }
    }
}
