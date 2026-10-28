package me.uxokpro1234.Auth;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailSender {
    public static void sendMail(String username, String password, String recipient, String object, String text){

        // Properties 1st
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        // Auth 2nd
        Authenticator authenticator = new MailAuthenticator(username, password);
        Session session = Session.getInstance(props, authenticator);
        // Try to send :)
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));
            message.setSubject(object);
            message.setText(text);

            Transport.send(message);

            System.out.println("Email accepted by the SMTP server!");

        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
