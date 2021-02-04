package by.grodno.vika.librarywebapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.domain.UserPicture;
import by.grodno.vika.librarywebapp.dto.UserRegistrationDTO;
import by.grodno.vika.librarywebapp.service.UserService;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
		String email = oauth2User.getName();
		User user = userService.findByEmail(email);

		if (user == null) {
			UserRegistrationDTO userDTO = new UserRegistrationDTO();
			userDTO.setFirstName(oauth2User.getFirstName());
			userDTO.setLastName(oauth2User.getLastName());
			userDTO.setEmail(oauth2User.getName());

			userService.createNewUserAfterOAuthLoginSuccess(userDTO);
		} else {
			user.setFirstName(oauth2User.getFirstName());
			user.setLastName(oauth2User.getLastName());
			UserPicture pic = new UserPicture();
			pic.setFileLocation(oauth2User.getPicture());
			pic.setFileName("name" + Math.random());
			user.setPicture(pic);
			userService.saveUser(user);
		}

		super.onAuthenticationSuccess(request, response, authentication);
	}

}