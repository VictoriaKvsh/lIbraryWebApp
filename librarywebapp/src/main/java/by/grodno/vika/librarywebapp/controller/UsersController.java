package by.grodno.vika.librarywebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.grodno.vika.librarywebapp.domain.Role;
import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.service.UserService;

@RestController
public class UsersController {

	@Autowired
	private UserService repo;

	@PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody User user) {
		repo.addUser(user);
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return repo.getUsers();
	}

	@GetMapping("/users/filter")
	public List<User> findByExample(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("role") Role role, @RequestParam("login") String login) {
		User User = new User(null, firstName, lastName, null, login, role, null);
		return repo.findByExample(User);
	}


	@GetMapping("/users/{lname}")
	public List<User> getUserByLName(@PathVariable("lname") String lname) {
		return repo.findByLName(lname);
	}
}