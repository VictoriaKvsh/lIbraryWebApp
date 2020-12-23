package by.grodno.vika.librarywebapp.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.domain.UserCredentials;
import by.grodno.vika.librarywebapp.exception.ResourceNotFoundException;
import by.grodno.vika.librarywebapp.exception.UserNotFoundException;
import by.grodno.vika.librarywebapp.repo.UserCredentialsRepo;
import by.grodno.vika.librarywebapp.repo.UserRepo;
import by.grodno.vika.librarywebapp.service.EmailService;
import by.grodno.vika.librarywebapp.service.UserService;

@Service
public class JPAUserService implements UserService {

	@Autowired
	private UserRepo repo;
	@Autowired
	private UserCredentialsRepo credRepo;
	@Autowired
	private EmailService emailService;

	@Override
	public List<User> getUsers() {
		return repo.findAll();
	}

	@Override
	public void saveUser(User user) {
		repo.save(user);
	}

	@Override
	public User updateUser(Integer number, User userReqest) {
		return repo.findById(number).map(user -> {
			user.setFirstName(userReqest.getFirstName());
			user.setLastName(userReqest.getLastName());
			user.setEmail(userReqest.getEmail());
			return repo.save(user);
		}).orElseThrow(() -> new ResourceNotFoundException("User Id " + number + " not found"));
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
	public Optional<User> findByEmail(String email) {
		return Optional.ofNullable(repo.findByEmail(email));
	}

	@Override
	public void activateUser(Integer id) {
		Optional<User> findById = repo.findById(id);

		findById.map(user -> {
			UserCredentials next = user.getCredentials().iterator().next();
			next.setActive(true);
			credRepo.save(next);
			return user;
		}).orElseThrow(() -> new UserNotFoundException());

	}

}
