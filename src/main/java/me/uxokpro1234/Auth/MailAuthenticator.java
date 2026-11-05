package me.uxokpro1234.Auth;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {
    
    private final String username;
    private final String password;
    
    public MailAuthenticator(String username, String password){
        if(username == null || username.isBlank()){
            throw new IllegalArgumentException("Username is required");
        }

        if(password == null || password.isBlank()){
            throw new IllegalArgumentException("Password is required");
        }

        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(username, password);
    }
}
