package gameshop.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

/**
 * EmailUtils is a utility class for sending emails via SMTP using Gmail's mail
 * service. It provides a static method to send an email to a recipient with a
 * specified subject and content.
 *
 * @author CE171450 - Nguyen Hai Nam
 */
public class EmailUtils {

    /**
     * Sends an email to the specified recipient with the given subject and
     * content.
     *
     * @param recipient the email address of the recipient
     * @param subject the subject of the email
     * @param content the body content of the email
     */
    public static void sendEmail(String recipient, String subject, String content) {
        final String senderEmail = "namnhce171450@fpt.edu.vn";
        final String senderPassword = "bgms lpfz zawb cnce";  // (Không nên để mật khẩu trong code!)

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
