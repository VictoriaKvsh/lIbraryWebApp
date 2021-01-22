package by.grodno.vika.librarywebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/", "/home", "/register", "/books/**", "/catalog/**", "/register/**", "/css/**", "/js/**","/activate/**", "/forgot_password/**", "/reset_password/**").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/users")
			.and()
		.logout()
			.permitAll();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder noop() {
		return NoOpPasswordEncoder.getInstance();
	}

}