package by.grodno.vika.librarywebapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import by.grodno.vika.librarywebapp.domain.AuthenticationProvider;
import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.service.UserService;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
		String email = oauth2User.getEmail();
		User user = userService.findByEmail(email);
		String name = oauth2User.getName();
		if (user == null) {
			userService.createNewUserAfterOAuthLoginSuccess(email, name, AuthenticationProvider.GOOGLE);
		} else {
			userService.updateUserAfterOAuthLoginSuccess(user, name, AuthenticationProvider.GOOGLE);

		}

		System.out.println("Email: " + email);
		super.onAuthenticationSuccess(request, response, authentication);
	}

}