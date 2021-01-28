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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import by.grodno.vika.librarywebapp.domain.ReadersBook;
import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.dto.Avatar;
import by.grodno.vika.librarywebapp.dto.UserDTO;
import by.grodno.vika.librarywebapp.repo.UserRepo;
import by.grodno.vika.librarywebapp.security.CustomOAuth2User;
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
	@Autowired
	UserRepo uRepo;

	@GetMapping("/users")
	public String getAllUsers(Model model) {

		List<User> users = userService.getUsers();
		model.addAttribute("users", users);

		return "usersList";
	}

	@GetMapping("/users/profile/info")
	public String getUserById(@RequestParam(value = "userId", required = false) Integer userId, Model model,
			@AuthenticationPrincipal UserDetails currentUser, Authentication authentication) {
		if (userId == null) {
			userId = uRepo.findByEmail(currentUser.getUsername()).getId();
		}
		if (userId == null) {
			CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
			String email = oauth2User.getEmail();
			User user = uRepo.findByEmail(email);
			userId = user.getId();
		}

		User user = userService.getUser(userId);
		model.addAttribute("user", user);
		return "profile";
	}

	@GetMapping("/users/profile/books")
	public String getUsersBooks(@RequestParam(value = "userId", required = false) Integer userId, Model model,
			@AuthenticationPrincipal UserDetails currentUser) {
		if (userId == null) {
			userId = uRepo.findByEmail(currentUser.getUsername()).getId();
		}

		User user = userService.getUser(userId);
		List<ReadersBook> books = uRepo.findByEmail(currentUser.getUsername()).getReadersBook();

		model.addAttribute("user", user);
		model.addAttribute("books", books);
		return "profileBookList";
	}

	@GetMapping("/users/profile/edit")

	public String editUserForm(@AuthenticationPrincipal UserDetails principal, Model model) {
		model.addAttribute("user", userService.getUser(uRepo.findByEmail(principal.getUsername()).getId()));
		return "profileEdit";
	}

	@PostMapping("/users/profile/edit")
	public String updateUser(@Valid UserDTO userDTO, BindingResult br, Model model,
			@AuthenticationPrincipal UserDetails principal,
			@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
		if (br.hasErrors()) {
			model.addAttribute("userDTO", userDTO);
			return "profileEdit";
		}
		Integer id = uRepo.findByEmail(principal.getUsername()).getId();
		userDTO.setId(id);
		userService.updateUser(userDTO, file);

		return "redirect:/users/profile/info";
	}

	@PostMapping("/users/delete/{userId}")
	public String deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
		return "redirect:/userList";
	}

	@GetMapping("/users/findByName/{lname}")
	public List<User> getUserByLName(@PathVariable("lname") String lname) {
		return userService.findByLName(lname);
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

//	@GetMapping("/apis/v1/users")
//	@ResponseBody
//	public List<UserDTO> getAllUsers() {
//		return userService.getUsers().stream().map(u -> convertionService.convert(u, UserDTO.class))
//				.collect(Collectors.toList());
//	}

}