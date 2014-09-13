package program;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import util.MSG;

public class SendEmail {

    public static boolean SendEmailBug(String subject, String msg) {
        final String username = "softpos.app@gmail.com";
        final String password = "softpos2013";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("softpos.app@gmail.com"));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);

            MSG.NOTICE("ส่งข้อมูลการแจ้งปัญหาของท่านไปยังทีมงาน Support โปรแกรมเรียบร้อยแล้ว");
            return true;
        } catch (MessagingException e) {
            MSG.ERR(null, "พบข้อผิดพลาดในการส่งข้อมูล\n" + e.getMessage());
            return false;
        }
    }
}
