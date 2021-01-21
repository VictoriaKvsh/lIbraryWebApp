package by.grodno.vika.librarywebapp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.dto.UserDTO;

public interface UserService {

	List<User> getUsers();
	
	User getUser(Integer id);

	void saveUser(User user);
	
	void updateUser(UserDTO userDTO);

	void deleteUser(Integer number);
	
	Optional<User> findByEmail(String email);
	
	void activateUser(Integer id);

	List<User> findByExample(User userSample);

	List<User> findByLName(String lastName);

	
	

	

}