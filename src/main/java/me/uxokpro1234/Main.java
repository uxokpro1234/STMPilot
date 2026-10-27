package me.uxokpro1234;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import me.uxokpro1234.Auth.MailAuthenticator;

import java.util.Properties;

public class Main {
    static String username = System.getenv("MAIL_USERNAME");
    static String password = System.getenv("MAIL_PASSWORD");
    static String recipient = System.getenv("MAIL_TO");

    public static void main(String[] args) {
        // Check
        if (username == null || password == null || recipient == null){
            System.err.println("Missing email environment variables.");
            return;
        }
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

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient, false)
            );
            message.setSubject("My first Java email");
            message.setText("Hello! This email was sent using Java.");

            Transport.send(message);

            System.out.println("Email accepted by the SMTP server!");

        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}