package by.grodno.vika.librarywebapp.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


@Configuration
public class ThymeleafTemplateConfig {
	
	// In order to process our templates, we will configure a SpringTemplateEngine
	// which specially configured for email processing, in our Spring boot email
	// template configuration.
	@Bean
    public SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }

	// We need to tell Thymeleaf where the email templates are located. We do this
	// by creating and configuring the ClassLoaderTemplateResolver We can set a
	// prefix and suffix to configure where thymeleaf will search for the HTML email
	// templates.
	@Bean
    public ClassLoaderTemplateResolver htmlTemplateResolver(){
        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("/templates/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return emailTemplateResolver;
    }
	
	
	
	
}