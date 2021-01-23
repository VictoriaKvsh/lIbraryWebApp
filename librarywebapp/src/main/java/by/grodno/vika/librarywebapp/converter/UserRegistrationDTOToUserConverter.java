package by.grodno.vika.librarywebapp.converter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.domain.UserCredentials;
import by.grodno.vika.librarywebapp.domain.UserRole;
import by.grodno.vika.librarywebapp.dto.UserRegistrationDTO;



@Component
public class UserRegistrationDTOToUserConverter implements Converter<UserRegistrationDTO, User> {
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Override
	public User convert(UserRegistrationDTO source) {
		User user = new User();
		user.setFirstName(source.getFirstName());
		user.setLastName(source.getLastName());
		user.setEmail(source.getEmail());
		user.setRole(UserRole.READER);
		UserCredentials creds = new UserCredentials(null,  new Date(), false, passwordEncoder.encode(source.getPassword()));
		
		user.setCredentials(creds);
		
		return user;
	}

}