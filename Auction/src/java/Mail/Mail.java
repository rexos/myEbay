/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import database.DbManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import javax.mail.internet.*;
import javax.mail.*;

/**
 *
 * @author alex
 */
public class Mail {
    
    private DbManager manager;
    
    public Mail(DbManager manager) {
        this.manager = manager;
    }

    public void message(String mailAddress, int itemId, String mess, int userid) throws AddressException, MessagingException, SQLException {
        String stdMessage;
        String stdSubject = "News from AuctionHouse";
        String name = manager.getUserName(userid);
        if (mess == null){
            stdMessage = "Hey "+name+" ! \n"+"Someone has placed a bet higher than yours on AuctionHouse, on Item number : ";
            
        }
        else {
            stdMessage = "Hey "+name+" ! \n"+mess;
        }
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "mail.unitn.it");
        props.put("mail.debug", "true");
        Session session = Session.getDefaultInstance(props);
        Message message = new MimeMessage(session);
        InternetAddress from = new InternetAddress("auction@unitn.it");
        InternetAddress to[] = InternetAddress.parse(mailAddress);
        message.setFrom(from);
        message.setRecipients(Message.RecipientType.TO, to);
        message.setSubject(stdSubject);
        message.setSentDate(new Date());
        message.setText(stdMessage+itemId);
        Transport.send(message);
    }
}
