package by.grodno.vika.librarywebapp;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import by.grodno.vika.librarywebapp.config.Mail;
import by.grodno.vika.librarywebapp.service.imp.EmailSenderService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Log4j2
class ThymeleafSptingBootApplicationTests {

	@Autowired
	EmailSenderService senderService;

	@Test
	void contextLoads() throws MessagingException, IOException {
		log.info("sending sample email");

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("name", "John Michel!");
		properties.put("location", "Sri Lanka");
		properties.put("sign", "Java Developer");

		Mail mail = Mail.builder().from("testfrom@gmail.com").to("wnsfernando95@gmail.com")
				.htmlTemplate(new Mail.HtmlTemplate("email", properties))
				.subject("This is sample email with spring boot and thymeleaf").build();

		senderService.sendEmail(mail);
	}

}