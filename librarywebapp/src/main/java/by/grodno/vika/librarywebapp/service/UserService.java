package by.grodno.vika.librarywebapp.service;

import java.util.List;
import by.grodno.vika.librarywebapp.domain.AuthenticationProvider;
import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.dto.UserDTO;
import by.grodno.vika.librarywebapp.exception.UserNotFoundException;

public interface UserService {

	List<User> getUsers();
	
	User getUser(Integer id);

	void saveUser(User user);
	
	void updateUser(UserDTO userDTO);

	void deleteUser(Integer number);
	
	User findByEmail(String email);
	
	void activateUser(Integer id);

	List<User> findByExample(User userSample);

	List<User> findByLName(String lastName);

	void updateUserRequestToken(String token, String email) throws UserNotFoundException;

	User getByUserRequestToken(String token);

	void updatePassword(User user, String newPassword) throws UserNotFoundException;

	void createNewUserAfterOAuthLoginSuccess(String email, String name, AuthenticationProvider provider);

	void updateUserAfterOAuthLoginSuccess(User user, String name, AuthenticationProvider provider);

}