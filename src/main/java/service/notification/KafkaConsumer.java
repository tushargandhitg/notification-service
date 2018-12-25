package service.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import service.notification.email.EmailSender;

@Component
public class KafkaConsumer {
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
 
	@Autowired
	private EmailSender emailSender;
	
	@KafkaListener(topics="${jsa.kafka.topic}")
    public void processMessage(String content) {
		log.info("received content = '{}'", content);
		
		Gson json = new Gson();
		KafkaMessage message = json.fromJson(content, KafkaMessage.class);
		
		emailSender.sendMail(message.getEmailId(), 
				String.valueOf(message.getCurrentCredits()), message.getOperation());
    }
}