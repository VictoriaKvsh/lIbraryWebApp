package by.grodno.vika.librarywebapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import by.grodno.vika.librarywebapp.domain.UserRole;
import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.dto.Avatar;
import by.grodno.vika.librarywebapp.dto.UserDTO;
import by.grodno.vika.librarywebapp.service.StorageService;
import by.grodno.vika.librarywebapp.service.UserService;

@Controller
//@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userService;

	@Autowired
	private StorageService imgService;
	@Autowired
	private ConversionService convertionService;

	@PostMapping(path = "/users/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody User user) {
		userService.saveUser(user);
	}

	@GetMapping("/users")
	public String getAllUsers(Model model) {

		List<User> users = userService.getUsers();
		model.addAttribute("users", users);

		return "userList";
	}

	@PutMapping("/users/{userId}")
	public User updateUser(@PathVariable Integer userId, @Valid @RequestBody User userRequest) {
		return userService.updateUser(userId, userRequest);
	}

	@DeleteMapping("/users/delete/{userId}")
	public void deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
	}

	@GetMapping("/users/filter")
	public List<User> findByExample(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("role") UserRole role,
			@RequestParam("email") String email) {
		User User = new User(null, firstName, lastName, email, null, null, null, null, null, null);
		return userService.findByExample(User);
	}

	@GetMapping("/users/findByName/{lname}")
	public List<User> getUserByLName(@PathVariable("lname") String lname) {
		return userService.findByLName(lname);
	}

	@PostMapping("/users/{id}/img")
	public String handleFileUpload(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file)
			throws IOException {
		imgService.store(id, file);
		return "redirect:/users";
	}

	@GetMapping("/users/{id}/img")
	public void getImmage(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
		Avatar file = imgService.getFile(id);

		if (file != null) {
			try (InputStream is = file.getData()) {
				IOUtils.copy(is, response.getOutputStream());
			}
		}
	}
	
	@GetMapping("/apis/v1/users")
	@ResponseBody
	public List<UserDTO> getAllUsers() {
		return userService.getUsers().stream().map(u -> convertionService.convert(u, UserDTO.class))
				.collect(Collectors.toList());
	}

}