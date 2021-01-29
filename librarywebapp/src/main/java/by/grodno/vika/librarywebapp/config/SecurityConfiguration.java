package by.grodno.vika.librarywebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import by.grodno.vika.librarywebapp.security.CustomOAuth2UserService;
import by.grodno.vika.librarywebapp.security.OAuth2LoginSuccessHandler;
import by.grodno.vika.librarywebapp.service.imp.UserAuthService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserAuthService userAuthService;
	@Autowired
	private CustomOAuth2UserService oAuth2UserService;
	@Autowired
	private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/oauth2/**", "/books/**", "/catalog/**", "/register/**", "/css/**", "/js/**",
						"/activate_user/**", "/forgot_password/**", "/reset_password/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
				.and()
				.oauth2Login()
					.loginPage("/login")
					.userInfoEndpoint().userService(oAuth2UserService)
					.and()
					.successHandler(oAuth2LoginSuccessHandler)
				.and()
				.logout().permitAll();
	}
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
}
