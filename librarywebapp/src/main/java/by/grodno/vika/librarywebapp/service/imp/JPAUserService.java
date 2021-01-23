package by.grodno.vika.librarywebapp.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.domain.UserCredentials;
import by.grodno.vika.librarywebapp.dto.UserDTO;
import by.grodno.vika.librarywebapp.exception.ResourceNotFoundException;
import by.grodno.vika.librarywebapp.exception.UserNotFoundException;
import by.grodno.vika.librarywebapp.repo.UserCredentialsRepo;
import by.grodno.vika.librarywebapp.repo.UserRepo;
import by.grodno.vika.librarywebapp.service.UserService;

@Service
public class JPAUserService implements UserService {

	@Autowired
	private UserRepo repo;
	@Autowired
	private UserCredentialsRepo credRepo;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public List<User> getUsers() {
		return repo.findAll();
	}

	@Transactional
	@Override
	public void saveUser(User user) {
		repo.save(user);
		if (user.getCredentials().getActive() == false) {
			emailSenderService.contextUserInfo(user);
		}

	}

	@Override
	public void updateUser(UserDTO userDTO) {
		User findById = repo.findById(userDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		findById.setFirstName(userDTO.getFirstName());
		findById.setLastName(userDTO.getLastName());
		repo.save(findById);
	}

	@Override
	public void deleteUser(Integer number) {
		repo.deleteById(number);
	}

	@Override
	public List<User> findByExample(User userSample) {
		Example<User> exp = Example.of(userSample,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
		return repo.findAll(exp);
	}

	@Override
	public List<User> findByLName(String lastName) {
		return repo.findByLastName(lastName);
	}

	@Override
	public User getUser(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public User findByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	@Transactional
	public void activateUser(Integer id) {
		Optional<User> findById = repo.findById(id);

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
		User user = repo.findByEmail(email);
		if (user != null) {
			user.setUserRequestToken(token);
			repo.save(user);
		} else {
			throw new UserNotFoundException("Could not find any user with the email " + email);
		}
	}

	@Override
	public User getByUserRequestToken(String token) {
		return repo.findByUserRequestToken(token);
	}

	@Override
	@Transactional
	public void updatePassword(User user, String newPassword) {
		user.getCredentials().setPassword(passwordEncoder.encode(newPassword));
		user.setUserRequestToken(null);
		repo.save(user);

	}

}
