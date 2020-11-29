package by.grodno.vika.librarywebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.grodno.vika.librarywebapp.domain.OldUser;
import by.grodno.vika.librarywebapp.service.UserRepository;

@RestController
public class UsersController {

	@Autowired
	private UserRepository repo;

	@PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody List<OldUser> user) {
		repo.addUser(user);
	}

	@GetMapping("/users")
	public List<OldUser> getAllUsers() {
		return repo.getUsers();
	}
}