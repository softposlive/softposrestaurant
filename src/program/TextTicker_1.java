package program;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JTextField;

public class TextTicker_1 extends JTextField{
    private String string = "";
    private int MAX = 200;
    private String server = "";
    private String user = "fgordering";
    private String pass = "bor_ftp";
    private int port = 21;
    private String PATH = "rss/rss.txt";
    private String tmpLoad = "rss.txt";
    private JTextField txt_link=null;
    public static int SPEED_NORMAL = 100;
    public static int SPEED_FAST = 50;
    public static int SPEED_SLOW = 100;
    private int SPEED = SPEED_NORMAL;
    
    public TextTicker_1(String server, String user, String pass, int port, String PATH, JTextField txt){
        setFTP(server, user, pass, port, PATH, txt);
        //download text from server
        downloadText();
        new Thread(new Runnable() {

            public void run() {
                for(int i=0;i<1000;i++){
                    try {
                        Thread.sleep(30000);
                        string = "";
                        downloadText();
                        loadText(new File(tmpLoad));
                        txt_link.setText(string);
                        txt_link.select(0, 0);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
        //preview feed from local
        loadText(new File(tmpLoad));
        txt_link.setText(string);
        txt_link.select(0, 0);
        
        new Thread(new Runnable() {

            public void run() {
                try {
                    System.out.println(string.length());
                    for(int i=0;i<5000;i++){
                        Thread.sleep(SPEED);
                        txt_link.select(i+MAX, i+MAX);
                        if(i==string.length()-MAX){
                            txt_link.select(0, 0);
                            i=1;
                        }
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();        
    }
    
    public void setSpreed(int SPEED){
        
    }
    
    private void setFTP(String server, String user, String pass, int port, String PATH, JTextField txt_link){
        this.server=server;
        this.user=user;
        this.pass=pass;
        this.port=port;
        this.PATH=PATH;
        this.txt_link=txt_link;
        this.txt_link.setBackground(java.awt.Color.ORANGE);
        this.txt_link.setEditable(false);
        this.txt_link.setFocusable(false);
    }
    private void downloadText(){
        FTPUtility ftp = new FTPUtility();
        ftp.connect(server, user, pass, port);
        ftp.download(PATH, tmpLoad);
    }
    private void loadText(File file){
        for(int i=0;i<MAX;i++){
            string+=" ";
        }        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                string+=""+text+" ";
            }
            for(int i=0;i<MAX;i++){
                string+=" ";
            }
            reader.close();
        } catch (Exception e) {
        }
    }    
}
