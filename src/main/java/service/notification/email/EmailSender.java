package service.notification.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

	@Autowired
	private JavaMailSender sender;
	
	private String textLine1 = "Greetings ";
	private String newLine = "\n";
	private String textLine2 = "A recent activity was tracked on your account and the credit score has been updated.";
	private String textLine3 = "Updated Credit Values ::: ";
	private String textLine4 = "Operation Performed ::: ";
	private String textLine5 = "Thanks!!!";
	private String textLine6 = "Team Calculation";
	
	private Logger logger = LoggerFactory.getLogger(EmailSender.class);

	public boolean sendMail(String email, String credit, String operation) {
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try {
			
			helper.setTo(email);
			helper.setText(textLine1 +newLine + textLine2 + newLine + textLine3 + credit 
					+ newLine + textLine4 + operation + newLine + textLine5 + newLine + textLine6 );

			helper.setSubject("Credit Update");
			
		} catch (MessagingException e) {
			logger.error("error in sending email", e);
			return false;
		}
		
		sender.send(message);
		
		return true;
	}
	
	
}
