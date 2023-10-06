package com.database;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {
    private static final Logger LOGGER = Logger.getLogger(Email.class.getName());

    public void sendEmailCourse(String to, String course) {
        String[] courseArray = course.split("\\(");
        String courseName = courseArray[0];
        String info = courseArray[courseArray.length - 1].split("\\)")[0];
        String scheduleTime = info.equalsIgnoreCase("evening") ? "6.00 PM to 9.00 PM" : "9.00 AM to 11.00 AM";

        final String username = "luciusthebutler@gmail.com";
        final String password = "sgmj rqfb iazf hxaa";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Information About the Registered Session");

            // Create a multipart message to include both text and attachments
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Hello, This is the Training Department. Thank you for registering to attend the course."
                    + "\nYour selected course: " + courseName
                    + "\nSince you chose to attend the " + info + " session"
                    + "\nYour schedule is from " + scheduleTime + " in the " + info);

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);

            message.setContent(multipart);

            Transport.send(message);
            LOGGER.log(Level.INFO, "Email sent successfully to: " + to);
        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, "Error sending email to: " + to, e);
        }
    }
    public void sendEmail(String to, String subject, String message) {
        final String username = "luciusthebutler@gmail.com";
        final String password = "sgmj rqfb iazf hxaa";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(username));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            emailMessage.setSubject(subject);

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(message);

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);

            emailMessage.setContent(multipart);

            Transport.send(emailMessage);
            LOGGER.log(Level.INFO, "Email sent successfully to: " + to);
        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, "Error sending email to: " + to, e);
        }
    }
}
