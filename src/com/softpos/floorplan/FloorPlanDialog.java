package com.softpos.floorplan;

import com.softpos.login.FileSettingDialog;
import database.MySQLConnect;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import program.BalanceBean;
import program.BalanceControl;
import program.CheckProductNotEnough;
import program.CheckStockNow;
import program.DisplayEJ;
import program.EmployLogin;
import program.GetPassword;
import program.MainSale;
import program.POSConfigSetup;
import program.POSHWSetup;
import program.PPrint;
import program.PUtility;
import program.PublicVar;
import program.SetupButtonTable;
import program.TableFileControl;
import program.ThaiUtil;
import program.Value;
import util.MSG;

public class FloorPlanDialog extends javax.swing.JFrame {

    private POSHWSetup POSHW;
    private POSConfigSetup CONFIG;

    public FloorPlanDialog() {
        POSHW = POSHWSetup.Bean(Value.getMacno());
        CONFIG = POSConfigSetup.Bean();

        initComponents();

        Value.TableSelected = "";

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        addButton();
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        //setUndecorated(true);
        //setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        Dimension d = getMaximumSize();
        setSize(d.width, d.height);
        setLocationRelativeTo(null);

        loadImageHome();

        jMenuBar1.setVisible(false);
        jMenuItem21.setVisible(false);
        jMenuItem18.setVisible(false);
        jMenuItem19.setVisible(false);
        jMenuItem20.setVisible(false);
        jMenu5.setVisible(false);
        jMenuItem25.setVisible(false);
        jMenuItem26.setVisible(false);
        jMenuItem29.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnZone1 = new javax.swing.JPanel();
        pnZone2 = new javax.swing.JPanel();
        pnZone3 = new javax.swing.JPanel();
        pnZone4 = new javax.swing.JPanel();
        pnZone5 = new javax.swing.JPanel();
        pnZone6 = new javax.swing.JPanel();
        pnZone7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        MShowDailyEJ1 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();

        jPopupMenu1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jMenuItem1.setText("กำหนดโต๊ะ (Setup Table)");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Table  Plan System");

        jPanel1.setLayout(new java.awt.GridLayout(6, 1));

        jButton5.setBackground(new java.awt.Color(255, 255, 204));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/paid_in.png"))); // NOI18N
        jButton5.setText("นำเงินเข้า");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);

        jButton6.setBackground(new java.awt.Color(255, 255, 204));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/paid_out.png"))); // NOI18N
        jButton6.setText("นำเงินออก");
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6);

        jButton2.setBackground(new java.awt.Color(255, 255, 204));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/move.png"))); // NOI18N
        jButton2.setText("ย้ายโต๊ะ");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton1.setBackground(new java.awt.Color(255, 255, 204));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check.png"))); // NOI18N
        jButton1.setText("ตรวจสอบโต๊ะ");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton7.setBackground(new java.awt.Color(255, 255, 204));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        jButton7.setText("Refresh (F5)");
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);

        jButton3.setBackground(new java.awt.Color(255, 255, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        jButton3.setText("ยกเลิกบิล");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 204));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane1KeyPressed(evt);
            }
        });

        pnZone1.setLayout(new java.awt.GridLayout(5, 6));
        jTabbedPane1.addTab("Zone1", pnZone1);

        pnZone2.setLayout(new java.awt.GridLayout(5, 6));
        jTabbedPane1.addTab("Zone2", pnZone2);

        pnZone3.setLayout(new java.awt.GridLayout(5, 6));
        jTabbedPane1.addTab("Zone3", pnZone3);

        pnZone4.setLayout(new java.awt.GridLayout(5, 6));
        jTabbedPane1.addTab("Zone4", pnZone4);

        pnZone5.setLayout(new java.awt.GridLayout(5, 6));
        jTabbedPane1.addTab("Zone5", pnZone5);

        pnZone6.setLayout(new java.awt.GridLayout(5, 6));
        jTabbedPane1.addTab("Zone6", pnZone6);

        pnZone7.setLayout(new java.awt.GridLayout(5, 6));
        jTabbedPane1.addTab("Zone7", pnZone7);

        jPanel3.setBackground(new java.awt.Color(0, 153, 204));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton8.setBackground(new java.awt.Color(0, 102, 204));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        jButton8.setText("SOFTPOS");
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 204));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit_icon.png"))); // NOI18N
        jButton4.setText("ออกจากระบบ");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tb.png"))); // NOI18N
        jLabel2.setText("ร้านอาหาร ซอร์ฟโพส สุดแซ่บ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
                .addComponent(jButton4))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)))
        );

        jMenu1.setText("โปรแกรม (Program)");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jMenuItem4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem4.setText("กำหนดชื่อแต่ละ Tab");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator2);

        jMenuItem7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem7.setText("นำเงินเข้าระบบ (Paid In)");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem8.setText("นำเงินออกจากระบบ (Paid Out)");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem11.setText("ตรวจสอบโต๊ะ (Check Table List)");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuItem12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem12.setText("ย้ายโต๊ะ (Move Table)");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuItem10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem10.setText("ยกเลิกบิล (Refund Bill)");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);
        jMenu1.add(jSeparator1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem3.setText("จบการทำงาน");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("การขาย (Main Menu)");
        jMenu4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jMenuItem6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem6.setText("ดึงรายการสินค้าที่ยกเลิกบิลก่อนหน้า");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem30.setText("รายการสินค้าที่หมดในวันนี้");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem30);

        jMenuItem31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem31.setText("ตรวจสอบสถานะคลังสินค้า");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem31);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("รายงานต่าง ๆ (Report)");
        jMenu2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem2.setText("Dialy Report (รายงานประจำวัน)");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem5.setText("MTD Report (รายงานประจำเดือน)");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);
        jMenu2.add(jSeparator3);

        MShowDailyEJ1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MShowDailyEJ1.setText("ตรวจสอบข้อมูลม้วนสำเนา(ของวันนี้)");
        MShowDailyEJ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MShowDailyEJ1ActionPerformed(evt);
            }
        });
        jMenu2.add(MShowDailyEJ1);

        jMenuBar1.add(jMenu2);

        jMenu7.setText("กำหนดค่าไฟล์ (Setting)");
        jMenu7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jMenuItem13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem13.setText("แก้ไขข้อมูลเริ่มต้นระบบ");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem13);

        jMenuBar1.add(jMenu7);

        jMenu3.setText("เกี่ยวกับโปรแกรม (About)");
        jMenu3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jMenuItem14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem14.setText("คู่มือการใช้งาน (user_manual.pdf)");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem14);

        jMenuItem9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem9.setText("คีย์ลัดต่าง ๆ  ในระบบ");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuItem15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem15.setText("ส่งข้อความคิดเห็น / Bug / ติดต่อ Support");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem15);

        jMenuItem17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem17.setText("เว็บไซต์ http://www.softpos.co.th");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem17);
        jMenu3.add(jSeparator6);

        jMenuItem21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem21.setText("ติดตั้งปลั๊กอิน (Plug-In)");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem21);

        jMenuItem16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem16.setText("เปลี่ยนรูปภาพ Welcome");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem16);

        jMenuItem18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem18.setText("การอัพเดตโปรแกรมอัตโนมัติ");
        jMenu3.add(jMenuItem18);

        jMenuItem19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem19.setText("เกี่ยวกับโปรแกรม");
        jMenu3.add(jMenuItem19);

        jMenuItem20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem20.setText("Serial Number สำหรับใช้งานเต็มรูปแบบ");
        jMenu3.add(jMenuItem20);

        jMenu5.setText("เลือกภาษาโปรแกรม");
        jMenu5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenuItem22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/th.png"))); // NOI18N
        jMenuItem22.setText("ภาษาไทย");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem22);

        jMenuItem23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/us.png"))); // NOI18N
        jMenuItem23.setText("English / America");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem23);
        jMenu5.add(jSeparator5);

        jMenuItem24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem24.setText("ติดตั้งภาษาอื่น ๆ");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem24);

        jMenu3.add(jMenu5);

        jMenuItem25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem25.setText("Sync ข้อมูลวันและเวลาจาก Time Server");
        jMenu3.add(jMenuItem25);

        jMenuItem26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem26.setText("Sync Database อัตโนมัติ");
        jMenu3.add(jMenuItem26);

        jMenu6.setText("เลือกการพิมพ์ออก Printer");
        jMenu6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenuItem27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem27.setText("Printer Port");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem27);

        jMenuItem28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem28.setText("Printer Driver");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem28);

        jMenu3.add(jMenu6);

        jMenuItem29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem29.setText("เปลี่ยน Theme Program");
        jMenu3.add(jMenuItem29);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        SetupButtonTable setup = new SetupButtonTable(null, true, Value.BTN_FLOORPLAN);
        setup.setVisible(true);

        addButton();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTabbedPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            addButton();
        } else if (evt.getKeyCode() == KeyEvent.VK_F10) {
            jMenuBar1.setVisible(true);
        }
    }//GEN-LAST:event_jTabbedPane1KeyPressed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        logout();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        SetupFloorPlanHeader setup = new SetupFloorPlanHeader(null, true);
        setup.setVisible(true);

        loadHeaderTab();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (ChkEJPath()) {
            DailyRep frm = new DailyRep(null, true);
            frm.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (ChkEJPath()) {
            MTDRep frm = new MTDRep(null, true);
            frm.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        //MSG.ERR(this, "Comming soon...");
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        try {
            openWebpage(new URL("http://www.softpos.co.th"));
        } catch (MalformedURLException ex) {
            MSG.ERR(this, ex.getMessage());
        } catch (URISyntaxException ex) {
            MSG.ERR(this, ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        ReportBug report = new ReportBug(null, true);
        report.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        ShortKeyDialog sk = new ShortKeyDialog(null, true);
        sk.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        openDoc();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        PaidinFrm frm = new PaidinFrm(null, true);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        PaidoutFrm frm = new PaidoutFrm(null, true);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        RefundBill refund = new RefundBill(null, true);
        refund.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        ShowTable s = new ShowTable(null, true);
        s.setVisible(true);

        if (!Value.TableSelected.equals("")) {
            MainSale ms = new MainSale(this, true, Value.TableSelected);
            ms.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        MoveGroupTable move = new MoveGroupTable(null, true);
        move.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        HomeImageDialog home = new HomeImageDialog(null, true);
        home.setVisible(true);

        loadImageHome();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        if (!Value.LANG.equals("TH")) {
            Value.LANG = "TH";
        }
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        if (!Value.LANG.equals("EN")) {
            Value.LANG = "EN";
        }
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        MSG.ERR(this, "Comming soon...");
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        Value.printdriver = false;
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        Value.printdriver = true;
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void MShowDailyEJ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MShowDailyEJ1ActionPerformed
        GetPassword frm = new GetPassword(null, true);
        frm.setVisible(true);
        if (frm.ValidPassword) {
            DisplayEJ frm2 = new DisplayEJ(null, true);
            frm2.setVisible(true);
        }
    }//GEN-LAST:event_MShowDailyEJ1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        returnBill();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        CheckProductNotEnough cp = new CheckProductNotEnough(null, true);
        cp.setVisible(true);
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        CheckStockNow check = new CheckStockNow(null, true);
        check.setVisible(true);
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        logout();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        PaidinFrm frm = new PaidinFrm(null, true);
        frm.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        PaidoutFrm frm = new PaidoutFrm(null, true);
        frm.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        RefundBill refund = new RefundBill(null, true);
        refund.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ShowTable s = new ShowTable(null, true);
        s.setVisible(true);

        if (!Value.TableSelected.equals("")) {
            MainSale ms = new MainSale(this, true, Value.TableSelected);
            ms.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        addButton();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MoveGroupTable move = new MoveGroupTable(null, true);
        move.setVisible(true);

        addButton();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        jMenuBar1.setVisible(!jMenuBar1.isVisible());
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        FileSettingDialog fd = new FileSettingDialog(this, true);
        fd.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    public static void main(String args[]) {
        new MySQLConnect();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FloorPlanDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FloorPlanDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FloorPlanDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FloorPlanDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FloorPlanDialog dialog = new FloorPlanDialog();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MShowDailyEJ1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnZone1;
    private javax.swing.JPanel pnZone2;
    private javax.swing.JPanel pnZone3;
    private javax.swing.JPanel pnZone4;
    private javax.swing.JPanel pnZone5;
    private javax.swing.JPanel pnZone6;
    private javax.swing.JPanel pnZone7;
    // End of variables declaration//GEN-END:variables

    private void addButton() {
        int count;
        String strCount;
        int c = 0;
        pnZone1.removeAll();
        pnZone2.removeAll();
        pnZone3.removeAll();
        pnZone4.removeAll();
        pnZone5.removeAll();
        pnZone6.removeAll();
        pnZone7.removeAll();

        //load header for tab
        loadHeaderTab();

        Color cWhite = Color.WHITE;

        for (int i = 1; i <= 5; i++) {
            count = i;

            for (int j = 0; j < 7; j++) {

                if (count < 5) {
                    strCount = "00" + count;
                } else if (count < 50) {
                    strCount = "0" + count;
                } else {
                    strCount = "" + count;
                }
                JButton button = new JButton("");
                button.setName("T" + strCount);
                button.setFont(new Font("Tahoma", Font.PLAIN, 14));
                button.addMouseListener(new MouseClickAction(button, c));
                button.addFocusListener(new MouseFocusAction(button, c));
                pnZone1.add(button);

                button = new JButton("");
                button.setName("A" + strCount);
                button.setFont(new Font("Tahoma", Font.PLAIN, 14));
                button.addMouseListener(new MouseClickAction(button, c));
                button.addFocusListener(new MouseFocusAction(button, c));
                pnZone2.add(button);

                button = new JButton("");
                button.setName("B" + strCount);
                button.setFont(new Font("Tahoma", Font.PLAIN, 14));
                button.addMouseListener(new MouseClickAction(button, c));
                button.addFocusListener(new MouseFocusAction(button, c));
                pnZone3.add(button);

                button = new JButton("");
                button.setName("C" + strCount);
                button.setFont(new Font("Tahoma", Font.PLAIN, 14));
                button.addMouseListener(new MouseClickAction(button, c));
                button.addFocusListener(new MouseFocusAction(button, c));
                pnZone4.add(button);

                button = new JButton("");
                button.setName("D" + strCount);
                button.setFont(new Font("Tahoma", Font.PLAIN, 14));
                button.addMouseListener(new MouseClickAction(button, c));
                button.addFocusListener(new MouseFocusAction(button, c));
                pnZone5.add(button);

                button = new JButton("");
                button.setName("E" + strCount);
                button.setFont(new Font("Tahoma", Font.PLAIN, 14));
                button.addMouseListener(new MouseClickAction(button, c));
                button.addFocusListener(new MouseFocusAction(button, c));
                pnZone6.add(button);

                button = new JButton("");
                button.setName("F" + strCount);
                button.setFont(new Font("Tahoma", Font.PLAIN, 14));
                button.addMouseListener(new MouseClickAction(button, c));
                button.addFocusListener(new MouseFocusAction(button, c));
                pnZone7.add(button);

                count += 5;
                c++;
            }
        }

        ArrayList<TableSetup> list = new ArrayList<TableSetup>();

        try {
            String sql = "select code_id, t1.tcode, tcustomer, tonact,tlogintime,"
                    + "TAmount,PrintChkBill "
                    + "from tablesetup t1, tablefile t2 "
                    + "where t1.tcode=t2.tcode "
                    + "order by code_id";
            ResultSet rs = MySQLConnect.getResultSet(sql);

            while (rs.next()) {
                TableSetup bean = new TableSetup();
                bean.setTableNo(rs.getString("TCode"));
                bean.setLoginTime(rs.getString("tlogintime"));
                bean.setCustomer(rs.getInt("TCustomer"));
                bean.setIsActive(rs.getString("TOnAct").equals("Y"));
                bean.setTAmount(rs.getDouble("TAmount"));
                bean.setPrintChkBill(rs.getString("PrintChkBill"));

                String codeId = rs.getString("code_id");

                //find zone, index
                bean.setZone(codeId.substring(0, 1));
                try {
                    JButton btn = new JButton();
                    bean.setIndex(Integer.parseInt(codeId.substring(1, codeId.length())));
                    if (bean.getZone().equals("T")) {
                        btn = findButton(pnZone1, codeId);
                    }
                    if (bean.getZone().equals("A")) {
                        btn = findButton(pnZone2, codeId);
                    }
                    if (bean.getZone().equals("B")) {
                        btn = findButton(pnZone3, codeId);
                    }
                    if (bean.getZone().equals("C")) {
                        btn = findButton(pnZone4, codeId);
                    }
                    if (bean.getZone().equals("D")) {
                        btn = findButton(pnZone5, codeId);
                    }
                    if (bean.getZone().equals("E")) {
                        btn = findButton(pnZone6, codeId);
                    }
                    if (bean.getZone().equals("F")) {
                        btn = findButton(pnZone7, codeId);
                    }

                    //set value of Table No
                    if (bean.getLoginTime() != null && bean.getTAmount() > 0) {
                        btn.setText(bean.getTableNo());
                        if (bean.isIsActive()) {
                            setButtonShowTableFloorPlan(btn, Color.RED, bean.getTableNo());
                        } else {
                            if (bean.getPrintChkBill().equals("N")) {
                                setButtonShowTableFloorPlan(btn, Color.ORANGE, bean.getTableNo());
                            } else {
                                setButtonShowTableFloorPlan(btn, Color.PINK, bean.getTableNo());
                            }
                        }
                    } else {
                        setButtonShowTableFloorPlan(btn, new Color(153, 255, 153), bean.getTableNo());
                    }

                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    MSG.ERR(this, e.getMessage());
                }

                list.add(bean);
            }

            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            MSG.ERR(this, e.getMessage());
        }
    }

    private JButton findButton(JPanel pnZone, String codeId) {
        for (int i = 0; i < pnZone.getComponentCount(); i++) {
            JButton btn = (JButton) pnZone.getComponent(i);
            if (btn.getName().equals(codeId)) {
                return btn;
            }
        }

        return null;
    }

    private void loadHeaderTab() {
        try {
            String sql = "select FloorTab1,FloorTab2,FloorTab3,FloorTab4,FloorTab5,FloorTab6,FloorTab7 from company;";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            if (rs.next()) {

                for (int i = 0; i < 7; i++) {
                    JLabel lab = new JLabel(ThaiUtil.ASCII2Unicode(rs.getString("FloorTab" + (i + 1))));
                    lab.setPreferredSize(new Dimension(100, 50));
                    lab.setFont(new Font("Tahoma", Font.BOLD, 12));
                    if (lab.getText().trim().equals("")) {
                        lab.setHorizontalAlignment(SwingConstants.CENTER);
                    } else {
                        lab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zone.png")));
                    }
                    jTabbedPane1.setTabComponentAt(i, lab);
                }
            }

            rs.close();
        } catch (Exception e) {
            MSG.ERR(this, e.getMessage());
        }
    }

    private void logout() {
        if (MSG.CONF(this, "ยืนยันการออกจากระบบการขาย (Logoff User) ? ")) {
            PublicVar.P_LineCount = 1;
            PublicVar.P_LogoffOK = false;
            try {
                Statement stmt = MySQLConnect.con.createStatement();
                String QryUpdatePosuser = "update posuser set onact='N',macno=''where (username='" + PublicVar._User + "')";
                stmt.executeUpdate(QryUpdatePosuser);
                String QryUpdatePosHeSetup = "update poshwsetup set onact='N' where(terminal='" + Value.MACNO + "')";
                stmt.executeUpdate(QryUpdatePosHeSetup);
            } catch (SQLException e) {
                PUtility.showError(e.getMessage());
            }
            if (this.ChkEJPath()) {
                PPrint Prn = new PPrint();
                Prn.printLogout();
                Prn.closePrint();
            }
            if (UpdateLogout(PublicVar._RealUser)) {
                //LoadLoginForm
                clearTemp();
                MySQLConnect.close();
                System.exit(0);
            }
        }
    }

    private void clearTemp() {
        new File("softrestaurant.running").delete();
    }

    boolean UpdateLogout(String UserCode) {
        try {
            Statement stmt = MySQLConnect.con.createStatement();
            String SQLQuery = "update posuser set onact='N',macno='' where username='" + UserCode + "'";
            stmt.executeUpdate(SQLQuery);
            stmt.close();
            Value.CASHIER = "";

            return true;
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
            return false;
        }

    }

    private void loadImageHome() {
        try {
            String sql = "select IMG_HOME_PATH from branch";
            ResultSet rs = MySQLConnect.getResultSet(sql);
            if (rs.next()) {
                //lbMainImage.setIcon(new ImageIcon(rs.getString("IMG_HOME_PATH")));
            }
            rs.close();
        } catch (Exception e) {
            MSG.ERR(e.getMessage());
        }
    }

    private void returnBill() {
        String tableTemp = Value.TEMP_TABLE_REFUND;
        boolean checkExistTempRefund = false;
        try {
            String sql = "select * from sp_temp_refund;";
            ResultSet rs = MySQLConnect.getResultSet(sql);

            if (rs.next()) {
                //create temp table
                TableFileControl tfControl = new TableFileControl();
                tfControl.createNewTable(tableTemp);
            }

            BalanceControl bControl = new BalanceControl();
            int count = 0;
            rs = MySQLConnect.getResultSet(sql);
            while (rs.next()) {
                count++;
                checkExistTempRefund = true;
                BalanceBean bBean = new BalanceBean();
                bBean.setR_PluCode(rs.getString("R_PluCode"));
                bBean.setR_Quan(rs.getDouble("R_Quan"));
                bBean.setR_Price(rs.getDouble("R_Price"));
                bBean.setR_ETD(rs.getString("R_ETD"));
                bBean.setR_Opt1("");
                bBean.setR_Opt2("");
                bBean.setR_Opt3("");
                bBean.setR_Opt4("");
                bBean.setR_Opt5("");
                bBean.setR_Opt6("");
                bBean.setR_Opt7("");
                bBean.setR_Opt8("");
                bBean.setR_Opt9("");

                bBean.setR_Table(tableTemp);
                bBean.setR_Emp("");
                bBean.setCashier("");

                String runningIndex = "";
                if (count < 10) {
                    runningIndex = "00" + count;
                } else if (count < 100) {
                    runningIndex = "0" + count;
                } else {
                    runningIndex = "" + count;
                }

                bBean.setR_Index(tableTemp + "/" + runningIndex);
                bControl.saveBalance(bBean);
            }

            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        if (!checkExistTempRefund) {
            JOptionPane.showMessageDialog(this, "ไม่พบบิลรายการขายสินค้า ที่ยกเลิกก่อนหน้านี้ !");
        } else {
            try {
                //clear temp table
                MySQLConnect.exeUpdate("delete from sp_temp_refund");
                BalanceControl.updateProSerTable(tableTemp, null);
                Value.TableSelected = tableTemp;

                MainSale ms = new MainSale(this, true, tableTemp);
                ms.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    private void setButtonShowTableFloorPlan(JButton btn, Color color, String tableNo) {
        btn.setText("Table " + tableNo);
        btn.setBackground(color);
        btn.setFont(new Font("Tahoma", Font.BOLD, 18));
        btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_1.png")));
        btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    }

    class TableSetup {

        private int index;
        private String tableNo;
        private String loginTime;
        private int customer;
        private String status;
        private boolean isActive;
        private String zone;
        private double TAmount;
        private String PrintChkBill;

        public String getPrintChkBill() {
            return PrintChkBill;
        }

        public void setPrintChkBill(String PrintChkBill) {
            this.PrintChkBill = PrintChkBill;
        }

        public double getTAmount() {
            return TAmount;
        }

        public void setTAmount(double TAmount) {
            this.TAmount = TAmount;
        }

        public String getZone() {
            return zone;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }

        public boolean isIsActive() {
            return isActive;
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getTableNo() {
            return tableNo;
        }

        public void setTableNo(String tableNo) {
            this.tableNo = tableNo;
        }

        public String getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(String loginTime) {
            this.loginTime = loginTime;
        }

        public int getCustomer() {
            return customer;
        }

        public void setCustomer(int customer) {
            this.customer = customer;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }

    private class MouseFocusAction extends FocusAdapter {

        private JButton button;
        private int index;

        public MouseFocusAction(JButton button, int index) {
            this.button = button;
            this.index = index;
        }

        @Override
        public void focusGained(FocusEvent e) {
            String tableNo = button.getText().trim();
            if (!tableNo.equals("")) {
                tableNo = button.getText().substring(6, tableNo.length());
                Value.TableSelected = tableNo;
                //check table is available
                TableFileControl tfCont = new TableFileControl();
                if (tfCont.checkTableOpened(tableNo)) {
                    MSG.WAR("มีพนักงานกำลังใช้งานโต๊ะนี้อยู่ !!!");
                    Value.TableSelected = "";
                } else {

                    // check P_EmpUse
                    try {
                        String sql = "select P_EmpUse from posconfigsetup";
                        ResultSet rs = MySQLConnect.getResultSet(sql);
                        if (rs.next()) {
                            String P_EmpUse = rs.getString("P_EmpUse");
                            if (P_EmpUse.equals("Y")) {
                                // get employee password
                                EmployLogin login = new EmployLogin(null, true);
                                login.setVisible(true);

                                String sql2 = "select * from employ where Code='" + login.getLoginPWD() + "';";
                                try {
                                    ResultSet rs2 = MySQLConnect.getResultSet(sql2);
                                    if (rs2.next()) {
                                        login.setVisible(false);
                                        
                                        try {
                                            String sql9 = "update tablefile "
                                                    + "set TUser='"+login.getLoginPWD()+"' "
                                                    + "where TCode = '"+tableNo+"'";
                                            MySQLConnect.exeUpdate(sql9);
                                        } catch (Exception e9) {
                                            JOptionPane.showMessageDialog(null, e9.getMessage());
                                        }

                                        MainSale ms = new MainSale(null, true, tableNo);
                                        ms.setVisible(true);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "ท่านระบุรหัสบริกรไม่ถูกต้อง !");
                                        login.setVisible(false);                                       
                                    }

                                } catch (Exception e1) {
                                    login.setVisible(false);
                                    JOptionPane.showMessageDialog(null, e1.getMessage());
                                }
                            } else {
                                MainSale ms = new MainSale(null, true, tableNo);
                                ms.setVisible(true);
                            }
                        }
                    } catch (Exception e3) {
                        JOptionPane.showMessageDialog(null, e3.getMessage());
                    }
                }
            }
            
            addButton();
        }

        @Override
        public void focusLost(FocusEvent e) {

        }
    }

    private class MouseClickAction extends MouseAdapter {

        private JButton button;
        private int index;

        public MouseClickAction(JButton button, int index) {
            this.button = button;
            this.index = index;
        }

        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getButton() == 3) {
                System.out.println("mouseClicked");
                Value.BTN_FLOORPLAN = button.getName();
                JPopupMenu pop = jPopupMenu1;
                pop.show(button, evt.getX(), evt.getY());
            }
        }
    }

    public JPanel getPanelImage(JLabel lbCust, final JLabel lbTable, JButton btnIcon) {
        //config layout
        JPanel pnButton = new JPanel();
        pnButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbCust.setFont(new java.awt.Font("Tahoma", 1, 10));
        lbCust.setForeground(new java.awt.Color(0, 0, 204));
        lbCust.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCust.setText("C(0) T(1,500)");
        pnButton.add(lbCust, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 80, 30));

        lbTable.setFont(new java.awt.Font("Tahoma", 1, 12));
        lbTable.setForeground(new java.awt.Color(0, 0, 204));
        lbTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTable.setText("TABLE: 101");
        pnButton.add(lbTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 80, 30));

        btnIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_void.png")));

        btnIcon.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MSG.ERR(null, lbTable.getText());
            }
        });

        pnButton.add(btnIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 80));

        return pnButton;
    }

    boolean ChkEJPath() {
        if (POSHW.getEJounal().equals("Y")) {
            try {
                String TempFile = POSHW.getEJDailyPath();
                File fObject = new File(TempFile);
                if (!fObject.exists()) {
                    MSG.ERR(this, "ไม่สามารถสร้าง Log File/EJFile ตามตำแหน่งที่เก็บข้อมูล Log File/EJ ได้ กรุณาติดต่อเจ้าหน้าที่ Support...");
                    return false;
                }
                return true;
            } catch (HeadlessException e) {
                MSG.ERR(this, "ไม่สามารถสร้าง Log File/EJFile ตามตำแหน่งที่เก็บข้อมูล Log File/EJ ได้ กรุณาติดต่อเจ้าหน้าที่ Support...");
                return false;
            }
        } else {
            return true;
        }
    }

    private void openDoc() {
        Desktop desktop = Desktop.getDesktop();
        String file = "C:/softpos/user_manaual.pdf";
        try {
            File f = new File(file);
            if (f.exists()) {
                desktop.open(f);
            } else {
                MSG.ERR(this, "หาไฟล์ " + file + " ไม่เจอในระบบ กรุณาตรวจสอบ");
            }
        } catch (IOException ex) {
            MSG.ERR(this, ex.getMessage());
        }
    }

    private void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (IOException e) {
                MSG.ERR(this, e.getMessage());
            }
        }
    }

    private void openWebpage(URL url) throws URISyntaxException {
        openWebpage(url.toURI());
    }

}
