package by.grodno.vika.librarywebapp.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.dto.UserDTO;



@Component
public class UserDomainToDTOConverter implements Converter<User, UserDTO> {

	@Override
	public UserDTO convert(User source) {

		UserDTO u = new UserDTO();
		u.setId(source.getId());
		u.setFirstName(source.getFirstName());
		u.setLastName(source.getLastName());
		return u;
	}

}