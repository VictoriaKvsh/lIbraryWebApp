package by.grodno.vika.librarywebapp.controller;

import static org.junit.Assert.fail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import by.grodno.vika.librarywebapp.domain.OldUser;
import by.grodno.vika.librarywebapp.service.OldUserService;

@RestController
public class OldUsersController {

	@Autowired
	private OldUserService repo;

	@PostMapping(path = "/oldusers", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody List<OldUser> user) {
		repo.addUser(user);
	}

	@GetMapping("/oldusers")
	public List<OldUser> getAllUsers() {
		return repo.getUsers();
	}

	@GetMapping("/oldusers/filter")
	public List<OldUser> findByExample(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam(value = "male", required = false) 
	          Boolean male) {
		OldUser oldUser = new OldUser(null, firstName, lastName, null, male);
		return repo.findByExample(oldUser);
	}

	@GetMapping("/oldusers/page")
	public List<OldUser> getUsersPage(@RequestParam("pnum") Integer pnum, @RequestParam("psize") Integer psize) {
		return repo.getPage(pnum, psize).getContent();
	}

	@GetMapping("/oldusers/{fname}")
	public List<OldUser> getUserByFName(@PathVariable("fname") String fname) {
		return repo.findByFName(fname);
	}
}