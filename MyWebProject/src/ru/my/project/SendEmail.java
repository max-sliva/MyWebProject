package ru.my.project;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
//import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public static void sendEmail(String email, String fio, String login, String pass) {
	      // Recipient's email ID needs to be mentioned.
	      String to = email;
	 
	      // Sender's email ID needs to be mentioned
	      String from = "fitimstudent@inbox.ru";
	 
	      // Assuming you are sending email from inbox
	      String host = "smtp.inbox.ru";
	 
	      // Get system properties
	      Properties properties = System.getProperties();
	 
	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.auth", "true");
	      Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("fitimstudent", "nvsu_fitim");
				}
			  }; 
	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties, auth);
	      
	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);
	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));
	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	         // Set Subject: header field
	         message.setSubject("New user "+fio);
	         // Now set the actual message
	         message.setText("Your login: "+login+"\nYour password: "+pass);
	         // Send message
	         Transport.send(message);
	         System.out.println("Sent email for "+to);
	      }catch (Exception  mex) {
	         mex.printStackTrace();
	      }
	}
}
