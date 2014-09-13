package program;

import java.awt.event.KeyEvent;
import database.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import util.MSG;

public class GetSupervisor extends javax.swing.JDialog {
    Boolean GetPasswordOK ;

    /** Creates new form GetSupervisor */
    public GetSupervisor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       
        initComponents();
        c_loginname.setText("") ;
        c_loginpassword.setText("") ;
        c_loginname.requestFocus();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginpanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        c_bntlogincancel = new javax.swing.JButton();
        userloginpanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        c_loginname = new javax.swing.JTextField();
        c_loginpassword = new javax.swing.JPasswordField();
        c_bntloginok = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("กรุณาป้อนรหัสพนักงานที่มีสิทธิ์ทำรายการนี้...");

        loginpanel.setBackground(new java.awt.Color(204, 204, 204));
        loginpanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        loginpanel.setRequestFocusEnabled(false);
        loginpanel.setVerifyInputWhenFocusTarget(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("กรุณาป้อนรหัสผู้ใช้งาน (User Name) และ รหัสผ่าน (Password) ");

        c_bntlogincancel.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        c_bntlogincancel.setText("Cancel");
        c_bntlogincancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntlogincancelActionPerformed(evt);
            }
        });

        userloginpanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel3.setText("Username");

        jLabel4.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        jLabel4.setText("Password");

        c_loginname.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        c_loginname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                c_loginnameKeyPressed(evt);
            }
        });

        c_loginpassword.setFont(new java.awt.Font("Norasi", 0, 16)); // NOI18N
        c_loginpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                c_loginpasswordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout userloginpanelLayout = new javax.swing.GroupLayout(userloginpanel);
        userloginpanel.setLayout(userloginpanelLayout);
        userloginpanelLayout.setHorizontalGroup(
            userloginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userloginpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userloginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(userloginpanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(17, 17, 17))
                    .addGroup(userloginpanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)))
                .addGroup(userloginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c_loginpassword)
                    .addComponent(c_loginname, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        userloginpanelLayout.setVerticalGroup(
            userloginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userloginpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userloginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c_loginname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userloginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c_loginpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        c_bntloginok.setFont(new java.awt.Font("Norasi", 0, 14)); // NOI18N
        c_bntloginok.setText("OK");
        c_bntloginok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c_bntloginokMouseClicked(evt);
            }
        });
        c_bntloginok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_bntloginokActionPerformed(evt);
            }
        });
        c_bntloginok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                c_bntloginokKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout loginpanelLayout = new javax.swing.GroupLayout(loginpanel);
        loginpanel.setLayout(loginpanelLayout);
        loginpanelLayout.setHorizontalGroup(
            loginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginpanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(userloginpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c_bntloginok, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c_bntlogincancel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        loginpanelLayout.setVerticalGroup(
            loginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(loginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginpanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1))
                    .addGroup(loginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginpanelLayout.createSequentialGroup()
                            .addComponent(c_bntloginok, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(c_bntlogincancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(userloginpanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(loginpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(470, 208));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void c_bntlogincancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntlogincancelActionPerformed
    GetPasswordOK = false ;
    this.dispose();
}//GEN-LAST:event_c_bntlogincancelActionPerformed

private void c_loginnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_loginnameKeyPressed
keyboardcheck(evt,"c_loginname") ;
}//GEN-LAST:event_c_loginnameKeyPressed

private void c_loginpasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_loginpasswordKeyPressed
keyboardcheck(evt,"c_loginpassword") ;
}//GEN-LAST:event_c_loginpasswordKeyPressed

private void c_bntloginokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c_bntloginokMouseClicked
checkuserlogin() ;
}//GEN-LAST:event_c_bntloginokMouseClicked

private void c_bntloginokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_bntloginokActionPerformed
checkuserlogin() ;
}//GEN-LAST:event_c_bntloginokActionPerformed

private void c_bntloginokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_bntloginokKeyPressed
//System.out.println(evt) ;
        if (evt.getKeyCode()  == KeyEvent.VK_ENTER ){
            checkuserlogin() ;
        }
}//GEN-LAST:event_c_bntloginokKeyPressed


public void inputfrombnt(String str) {
        
         if (c_loginname.hasFocus()) {
                 String tempstr = "" ;
                tempstr = c_loginname.getText() ;
                tempstr = tempstr+str ;
                c_loginname.setText(tempstr);
        }
        if (c_loginpassword.hasFocus()){
                char[] pass=c_loginpassword.getPassword() ;
                String password = "" ;
                for (int i=0; i<pass.length; i++){
                     password=password+pass[i] ;
                }
                password = password+str ;
                c_loginpassword.setText(password);
        } 
    
}

 public void clearlogin() {
        c_loginname.setText("");
        c_loginpassword.setText("");
        c_loginname.requestFocus();
    }
 
 public void checkuserlogin() {
        
        String loginname = c_loginname.getText();
        char[] pass = c_loginpassword.getPassword();
        String password = "";
        for (int i = 0; i < pass.length; i++) {
            password = password + pass[i];
        }
        if ((loginname.length() == 0) || (password.length() == 0)) {
            MSG.ERR(this, "กรุณาป้อนรหัสผู้ใช้งาน(Username)/รหัสผ่าน(Password)");
            clearlogin();
        }

        try {
            Statement stmt =  MySQLConnect.con.createStatement();
            String SQLQuery = "select  *from posuser Where(username= '" + loginname + "') and (password='" + password + "')";

            ResultSet rec = stmt.executeQuery(SQLQuery);
            rec.first();
            if (rec.getRow() == 0) {
                MSG.ERR(this, "รหัสผู้ใช้งาน (Username) และรหัสผ่าน (Password) ไม่ถูกต้อง !!!");
                clearlogin();
            } else {
                PublicVar._User = loginname;
                PublicVar._UserName = ThaiUtil.ASCII2Unicode(rec.getString("name"));
                rec.close();
                UserRecord TUserRec = new UserRecord() ;
                if (TUserRec.GetUserAction(loginname)) {
                    PublicVar.TSupervisorRec = TUserRec ;
                    GetPasswordOK = true ;
                    this.dispose();
                } else {
                    PUtility.showError("ไม่สามารถ Load สิทธิ์การใช้งานของผู้ใช้งานคนนี้ได้ ...");
                    clearlogin() ;
                }
            }
        } catch (SQLException e) {
            MSG.ERR(this, e.getMessage());
            clearlogin();
        }

    }

    public void keyboardcheck(java.awt.event.KeyEvent evt, String cpname) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cpname.equals("c_loginname")) {
                c_loginpassword.requestFocus();
            } else if (cpname.equals("c_loginpassword")) {
                c_bntloginok.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                GetPasswordOK = false ;
                this.dispose();
            }
        }
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GetSupervisor dialog = new GetSupervisor(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton c_bntlogincancel;
    private javax.swing.JButton c_bntloginok;
    private javax.swing.JTextField c_loginname;
    private javax.swing.JPasswordField c_loginpassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel loginpanel;
    private javax.swing.JPanel userloginpanel;
    // End of variables declaration//GEN-END:variables

}
