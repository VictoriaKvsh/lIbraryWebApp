package by.grodno.vika.librarywebapp.service.imp;

import by.grodno.vika.librarywebapp.config.Mail;
import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.service.UserService;
import net.bytebuddy.utility.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
	private UserService userService;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        String html = getHtmlContent(mail);
        helper.setTo(mail.getTo());
        helper.setFrom(mail.getFrom());
        helper.setSubject(mail.getSubject());
        helper.setText(html, true);

        emailSender.send(message);
    }

    private String getHtmlContent(Mail mail) {
        Context context = new Context();
        context.setVariables(mail.getHtmlTemplate().getProps());
        return templateEngine.process(mail.getHtmlTemplate().getTemplate(), context);
    }
    
     public void contextUserInfo(User user)  {
    	 String token = RandomString.make(30);
    	 userService.updateUserRequestToken(token, user.getEmail());
    	 
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("name", user.getFirstName());
		properties.put("host", "http://localhost:8080/activate_user?token="+token);

		Mail mail = Mail.builder()
				.from("contact@library.com")
				.to(user.getEmail())
				.htmlTemplate(new Mail.HtmlTemplate("email", properties))
				.subject("Activation link for Library.com")
				.build();

		try {
			sendEmail(mail);
		} catch (MessagingException | IOException e) {
			
			e.printStackTrace();
		}
	}
}