package by.grodno.vika.librarywebapp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.repo.UserRepo;
import by.grodno.vika.librarywebapp.service.UserService;

@Service
public class JPAUserService implements UserService {

	@Autowired
	UserRepo repo;

	@Override
	public List<User> getUsers() {
		return repo.findAll();
	}

	@Override
	public void addUser(List<User> users) {
		repo.saveAll(users);

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

}
