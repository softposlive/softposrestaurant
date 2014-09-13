package program;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JTextField;
import database.MySQLConnect;

public class TextTicker extends JTextField {
    private String string = "";
    private String string2 = "";
    //MAX หมายถึงค่าว่างที่เพิ่มก่อนจะแสดงข้อความ
    //จะทำให้ข้อความอยู่ทางขวามือ เหมือนข้อความค่อยๆ โผล่ออกมา
    private int MAX = 200;
    private String PATH_ON_FROMCENTER = "C:/spapplication/fromcenter/"+PublicVar.Branch_Group+"_"+"message.zip";
    private JTextField txt_link = null;
    //กำนหดช่วงความเร็วในการแสดงผลข้อความบนหน้าจอ
    public static int SPEED_NORMAL = 100;
    public static int SPEED_FAST = 50;
    public static int SPEED_SLOW = 200;
    private int SPEED = SPEED_SLOW;
    private int ONE_MINUTE = 1000*60;//1000*60 = 1 นาที
    //status สำหรับแสดงสีสันของข้อความ
    public static int Status = 0;
    //ข้อมูลในการเชื่อมต่อ FTP server
    private String server = "localhost";
    private String user = "b111";
    private String pass = "bor_ftp";
    private int port = 21;
    private String PATH_ON_SERVER = "C:/spapplication/posmasterfile/"+PublicVar.Branch_Group+"_"+"message.zip";
    //สำหรับ convert char set from tis620--->utf8
    private String FileCharset = "TIS-620";
    private int TIME_STOP = 30 * ONE_MINUTE;//30 นาที
    private int TIME_LOADTEXT = 5 * ONE_MINUTE;//5 นาที
    private String TEMP_DOWNLOAD_PATH = "C:/tmp/"+PublicVar.Branch_Group+"_"+"message.zip";
    private String PATH_RSS = "C:/spapplication/fromcenter/rss.txt";

    public TextTicker(String server, String user, String pass, int port, String PATH, JTextField txt) {
        setFTP(server, user, pass, port, PATH, txt);
        this.txt_link = txt;
        //เพิ่ม Event ให้กับข้อความวิ่งในกรณี่ที่ผู้ใช้งานต้องการดูข้อความทั้งหมด
        //หากไม่ต้องการให้คลิ๊กได้ ให้ comment ไว้
        setAction();

        //LOAD TIME
        checkTime();

        //LOAD TEXT
        new Thread(new Runnable() {

            @Override
            public void run() {
                do {
                    try {
                        copyText();
                        loadText(new File(PATH_RSS));
                        txt_link.setText(string);
                        txt_link.select(0, 0);
                        //หน่วงเวลา 5 นาทีสำหรับโหลดข้อความใหม่จาก server
                        Thread.sleep(TIME_LOADTEXT);
                        string = "";
                        string2 = "";
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } while (true);
            }
        }).start();
        //preview feed from local
        loadText(new File(PATH_RSS));
        txt_link.setText(string);
        txt_link.select(0, 0);

        //DISPLAY TEXT
        //แสดงข้อความวิ่ง
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5000; i++) {
                        Thread.sleep(SPEED);
                        if (i == string.length() - MAX) {
                            txt_link.select(0, 0);
                            i = 1;
                        }
                        txt_link.select(i + MAX, i + MAX);
                        //หากไม่พบไฟล์ให้ค่า i เริ่มต้นที่ 0 ใหม่อีกครั้ง
                        //เมื่อมีการโหลดข้อมูลใหม่ ข้อความจะแสดงเหมือนตอนเปิดเข้าสู่หน้าจอครั้งแรก
                        if (!new File(PATH_ON_FROMCENTER).exists()) {
                            string2 = "";
                            i = 0;
                        }
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    private void setFTP(String server, String user, String pass, int port, String PATH, JTextField txt_link) {
        this.txt_link = txt_link;
        this.txt_link.setBackground(java.awt.Color.ORANGE);
        this.txt_link.setEditable(false);
        this.txt_link.setFocusable(false);
        loadLocalServer();
    }

    private void ConverCharSet(String inFile, String inCharsetName, String outFile, String outCharsetName) {
        DirectoryUtility dirUtil = new DirectoryUtility();
        try {
            dirUtil.getFileAndCreateDir(outFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            InputStreamReader in = new InputStreamReader(
                    new FileInputStream(inFile), inCharsetName);
            OutputStreamWriter out = new OutputStreamWriter(
                    new FileOutputStream(outFile), outCharsetName);
            int c = in.read();
            int n = 0;
            while (c != -1) {
                out.write(c);
                n++;
                c = in.read();
            }
            in.close();
            out.close();
        } catch (IOException e) {
            util.MSG.ERR(null, e.getMessage());
        }
    }

    private void downloadText() {
        FTPUtility ftp = new FTPUtility();
        ftp.connect(server, user, pass, port);
        if (ftp.download(PATH_ON_SERVER, TEMP_DOWNLOAD_PATH)) {
            System.out.println("download file success");
            try {
                if (compareByte()) {
                    //copy file จาก temp ---> /sapplication/fromcenter
                    copyfile();
                }
                //หน่วงเวลา 2.5 วินาที สำหรับลบไฟล์ และแสดงข้อความวิ่งอีกครั้ง
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            System.err.println("download fail");
        }
    }

    private void copyText() {
        if (copyfile(PATH_ON_SERVER, TEMP_DOWNLOAD_PATH)) {
            System.out.println("download file success");
            try {
                if (compareByte()) {
                    //copy file จาก temp ---> /sapplication/fromcenter
                    copyfile();
                }
                //หน่วงเวลา 2.5 วินาที สำหรับลบไฟล์ และแสดงข้อความวิ่งอีกครั้ง
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                
            }
        } else {
            System.err.println("download fail");
        }
    }

    private boolean compareByte() {
        long sizeFileFromTemp = new File(TEMP_DOWNLOAD_PATH).getUsableSpace();
        long sizeFileFromCenter = new File(PATH_ON_FROMCENTER).getUsableSpace();

        return sizeFileFromTemp != sizeFileFromCenter;
    }

    public void copyfile() {
        String srFile = TEMP_DOWNLOAD_PATH;
        String dtFile = PATH_ON_FROMCENTER;
        try {
            File f1 = new File(srFile);
            File f2 = new File(dtFile);
            InputStream in = new FileInputStream(f1);
            OutputStream out = new FileOutputStream(f2);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public boolean copyfile(String path_server, String temp_download) {
        String srFile = path_server;
        String dtFile = temp_download;
        boolean isSuccess = false;
        try {
            File f1 = new File(srFile);
            File f2 = new File(dtFile);
            InputStream in = new FileInputStream(f1);
            OutputStream out = new FileOutputStream(f2);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            isSuccess = true;
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return isSuccess;
    }

    private void loadText(File file) {
        //convertCharset Example !
        //ในกรณีที่แปลงข้อมูลจาก window มา
        //ตอนนี้ยังไม่ได้ใช้
        if (!FileCharset.equals("UTF-8")) {
            String inFile = TEMP_DOWNLOAD_PATH;
            String inCharset = FileCharset;
            String outFile = PATH_RSS;
            String outCharset = "UTF-8";
            ConverCharSet(inFile, inCharset, outFile, outCharset);
        }
        //------------------------------------------------------------->
        for (int i = 0; i < MAX; i++) {
            string += " ";
        }
        if (file.exists()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String text = null;
                // repeat until all lines is read
                while ((text = reader.readLine()) != null) {
                    if (text.indexOf("STATUS:1") != -1) {
                        Status = 1;
                        this.txt_link.setDisabledTextColor(new Color(22,35,95));
                    } else {
                        if (text.indexOf("STATUS:2") != -1) {
                            Status = 2;
                            this.txt_link.setDisabledTextColor(new Color(11,134,58));
                        } else {
                            if (text.indexOf("STATUS:3") != -1) {
                                Status = 3;
                                this.txt_link.setDisabledTextColor(new Color(180,21,6));
                            }
                        }
                    }
                    string += "" + text + " ";
                    string2 += "" + text + " \n";
                }

                reader.close();
            } catch (Exception e) {
            }
        }
        for (int i = 0; i < MAX; i++) {
            string += " ";
            string2 += " ";
        }
    }

    public String getBTYPE(){
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            ResultSet rs = stmt.executeQuery("select btype from branch");
            if(rs.next()){
                return rs.getString(1);
            }else{
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    private void checkTime() {
        new Thread(new Runnable() {

            int timeCount = 0;

            @Override
            public void run() {
                try {
                    for (int i = 0; i < TIME_STOP; i++) {
                        Thread.sleep(1000);
                        if (!new File(PATH_ON_FROMCENTER).exists()) {
                            timeCount++;
                        } else {
                            timeCount++;
                        }

                        if (i == TIME_STOP - 1) {
                            new File(PATH_ON_FROMCENTER).delete();
                            i = 0;
                            timeCount = 0;
                        }
                    }
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }).start();
    }

    private void setAction() {
        txt_link.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                //เมื่อ double click ที่ข้อความวิ่ง
                if (me.getClickCount() == 2) {
                    MSG msg = new MSG(null, true, string2);
                    msg.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }
    //ประกาศไว้สำหรับ double click ที่ข้อความวิ่งจะแสดงข้อความทั้งหมด

    class MSG extends javax.swing.JDialog {

        public MSG(java.awt.Frame parent, boolean modal, String string) {
            super(parent, modal);
            initComponents();

            jTextArea1.setText(string);
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() {

            jScrollPane1 = new javax.swing.JScrollPane();
            jTextArea1 = new javax.swing.JTextArea();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("แสดงข้อความทั้งหมดจากศูนย์ข้อความ");

            jTextArea1.setColumns(20);
            jTextArea1.setEditable(false);
            jTextArea1.setFont(new java.awt.Font("Norasi", 1, 14)); // NOI18N
            jTextArea1.setLineWrap(true);
            jTextArea1.setRows(5);
            jTextArea1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            jScrollPane1.setViewportView(jTextArea1);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE));
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE));

            java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            setBounds((screenSize.width - 1000) / 2, (screenSize.height - 518) / 2, 1000, 518);
        }// </editor-fold>
        // Variables declaration - do not modify
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextArea jTextArea1;
        // End of variables declaration
    }
    private void loadLocalServer(){
//        prop = new Properties();
//        try {
//            FileInputStream input = new FileInputStream("C:/spapplication/dbconfig/ftp_local.ini");
//            prop.load(input);
//            this.server = prop.getProperty("host1");
//            this.user = prop.getProperty("usersend1");
//            this.pass = prop.getProperty("passsend1");
//            this.port = Integer.parseInt(prop.getProperty("port1"));
//            //this.PATH_ON_SERVER = prop.getProperty("user")+PublicVar.Branch_BranchType+"_"+"message.zip";
//
//            input.close();
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//            MSG.ERR(this, "File Not Found : "+"C:/spapplication/dbconfig/ftp_local.ini");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            MSG.ERR(this, "Error with file : "+"C:/spapplication/dbconfig/ftp_local.ini");
//        }
    }
    
    Properties prop = new Properties();
    boolean isCheckServer = false;

    void loadDefaultRadio() {
        String file1 = "C:/spapplication/dbconfig/ftp_local.ini";
        String file2 = "C:/spapplication/dbconfig/ftp_main.ini";
        String file3 = "C:/spapplication/dbconfig/ftp_secondary.ini";
        Properties pp = new Properties();
        try {
            //config1-----------------------------------------------
            FileInputStream input = new FileInputStream(file1);
            pp.load(input);
            if (pp.getProperty("default").equalsIgnoreCase("true")) {
                FileInputStream input2 = new FileInputStream(file1);
                prop = new Properties();
                prop.load(input2);
                System.out.println(prop.getProperty("host1"));
                isCheckServer = false;
            }
//            input.close();
            //config2-----------------------------------------------
            input = new FileInputStream(file2);
            pp.load(input);
            if (pp.getProperty("default").equalsIgnoreCase("true")) {
                FileInputStream input2 = new FileInputStream(file2);
                prop = new Properties();
                prop.load(input2);
                System.out.println(prop.getProperty("host1"));
                isCheckServer = true;
            }
//            input.close();
            //config3-----------------------------------------------
            input = new FileInputStream(file3);
            pp.load(input);
            if (pp.getProperty("default").equalsIgnoreCase("true")) {
                FileInputStream input2 = new FileInputStream(file3);
                prop = new Properties();
                prop.load(input2);
                System.out.println(prop.getProperty("host1"));
                isCheckServer = true;
            }
//            input.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
