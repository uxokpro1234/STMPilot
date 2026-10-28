package me.uxokpro1234;

import me.uxokpro1234.Auth.MailSender;

public class Main {
    public static String username = System.getenv("MAIL_USERNAME");
    public static String password = System.getenv("MAIL_PASSWORD");
    public static String recipient = System.getenv("MAIL_TO");

    public static void main(String[] args) {

        // Check <3
        if (username == null || password == null || recipient == null){
            System.err.println("Missing email environment variables.");
            return;
        }

        MailSender.sendMail(username, password, recipient, "My java", "Gurt: Yo");
    }
}
