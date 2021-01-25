package by.grodno.vika.librarywebapp.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.grodno.vika.librarywebapp.domain.AuthenticationProvider;
import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.domain.UserCredentials;
import by.grodno.vika.librarywebapp.domain.UserRole;
import by.grodno.vika.librarywebapp.dto.UserDTO;
import by.grodno.vika.librarywebapp.exception.ResourceNotFoundException;
import by.grodno.vika.librarywebapp.exception.UserNotFoundException;
import by.grodno.vika.librarywebapp.repo.UserCredentialsRepo;
import by.grodno.vika.librarywebapp.repo.UserRepo;
import by.grodno.vika.librarywebapp.service.UserService;

@Service
public class JPAUserService implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserCredentialsRepo credRepo;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@Transactional
	@Override
	public void saveUser(User user) {
		userRepo.save(user);
		if (user.getCredentials().getActive() == false) {
			emailSenderService.contextUserInfo(user);
		}

	}

	@Override
	public void updateUser(UserDTO userDTO) {
		User findById = userRepo.findById(userDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		findById.setFirstName(userDTO.getFirstName());
		findById.setLastName(userDTO.getLastName());
		userRepo.save(findById);
	}

	@Override
	public void deleteUser(Integer number) {
		userRepo.deleteById(number);
	}

	@Override
	public List<User> findByExample(User userSample) {
		Example<User> exp = Example.of(userSample,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
		return userRepo.findAll(exp);
	}

	@Override
	public List<User> findByLName(String lastName) {
		return userRepo.findByLastName(lastName);
	}

	@Override
	public User getUser(Integer id) {
		return userRepo.getOne(id);
	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	@Transactional
	public void activateUser(Integer id) {
		Optional<User> findById = userRepo.findById(id);

		findById.map(user -> {
			UserCredentials cred = user.getCredentials();
			cred.setActive(true);
			user.setUserRequestToken(null);
			credRepo.save(cred);
			return user;
		}).orElseThrow(() -> new UserNotFoundException());

	}

	@Override
	public void updateUserRequestToken(String token, String email) throws UserNotFoundException {
		User user = userRepo.findByEmail(email);
		if (user != null) {
			user.setUserRequestToken(token);
			userRepo.save(user);
		} else {
			throw new UserNotFoundException("Could not find any user with the email " + email);
		}
	}

	@Override
	public User getByUserRequestToken(String token) {
		return userRepo.findByUserRequestToken(token);
	}

	@Override
	@Transactional
	public void updatePassword(User user, String newPassword) {
		user.getCredentials().setPassword(passwordEncoder.encode(newPassword));
		user.setUserRequestToken(null);
		userRepo.save(user);

	}

	@Override
	public void createNewUserAfterOAuthLoginSuccess(String email, String name, AuthenticationProvider provider) {

		User user = new User();
		user.setEmail(email);
		user.setLastName(name);
		user.setRole(UserRole.READER);
		user.setAuthProvader(provider);
		UserCredentials creds = new UserCredentials(null,  new Date(), true, null);
		user.setCredentials(creds);
		
		userRepo.save(user);
	}

	@Override
	public void updateUserAfterOAuthLoginSuccess(User user, String name, AuthenticationProvider provider) {

		user.setLastName(name);
		user.setAuthProvader(provider);
		
		userRepo.save(user);
	}

}
