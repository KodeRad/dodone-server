package com.dodone.dodone.service;

import java.util.Properties;

import com.dodone.dodone.repository.TodoRepository;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// TODO: FIX THE ERROR THROWS
@Service
public class EmailService {

    public static void sendMail(String recipient) throws MessagingException {
        System.out.println("Preparing the send an email");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccEmail = "dodone.enterprise@gmail.com";
        // TODO: HIDE YOUR PASSWORD SOMEHOW
        String password = "looj einb emgw zkhh";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccEmail, password);
            }
        });

        // TODO: CHECK IMPORT(S)
        Message message = prepareMessage(session, myAccEmail, recipient);

        Transport.send(message);
        System.out.println("Message sent successfully");

    }

    private static Message prepareMessage(Session session, String myAccEmail, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("These are the task you have for today.");
            message.setText("Hello there \n Lorem ipsum Task Task Task \n \n Best Regards \n DoDone Team");
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}