package by.grodno.vika.librarywebapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.multipart.MultipartFile;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.domain.UserPicture;
import by.grodno.vika.librarywebapp.dto.UserDTO;
import by.grodno.vika.librarywebapp.dto.UserRegistrationDTO;

public interface UserService {

	Page<User> getUsers(int pageNum, String sortField);
	
	User getUser(Integer id);

	void saveUser(User user);
	
	void updateUser(UserDTO userDTO, MultipartFile file);

	void deleteUser(Integer number);
	
	User findByEmail(String email);
	
	void activateUser(Integer id);

	List<User> findByExample(User userSample);

	List<User> findByLName(String lastName);

	void updateUserRequestToken(String token, String email);

	User getByUserRequestToken(String token);

	void updatePassword(User user, String newPassword);

	void createNewUserAfterOAuthLoginSuccess(UserRegistrationDTO userDTO, UserPicture picture);

	

	

	

	

}