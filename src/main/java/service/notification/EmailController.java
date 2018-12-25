package service.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.notification.email.EmailSender;

@RestController
public class EmailController {
	
	@Autowired
	private EmailSender emailSender;
	
	@RequestMapping("/api/v1/sendemail")
	public boolean sendEmail(
			@RequestParam(value="email") String email
			) {
		return emailSender.sendMail(email, "53", "subtract");
	}
}
