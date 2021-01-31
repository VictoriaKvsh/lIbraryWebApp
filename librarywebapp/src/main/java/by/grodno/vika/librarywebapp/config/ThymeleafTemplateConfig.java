package by.grodno.vika.librarywebapp.config;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


@Configuration
public class ThymeleafTemplateConfig implements InitializingBean {
	
	@Autowired
	SpringTemplateEngine t;
	
	// We need to tell Thymeleaf where the email templates are located. We do this
	// by creating and configuring the ClassLoaderTemplateResolver We can set a
	// prefix and suffix to configure where thymeleaf will search for the HTML email
	// templates.
	
    public ClassLoaderTemplateResolver htmlTemplateResolver(){
        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("/templates/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return emailTemplateResolver;
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		t.addTemplateResolver(htmlTemplateResolver());
		
	}
	
	
	
	
}