package by.grodno.vika.librarywebapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import by.grodno.vika.librarywebapp.domain.ReadersBook;
import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.dto.Avatar;
import by.grodno.vika.librarywebapp.dto.UserDTO;
import by.grodno.vika.librarywebapp.service.StorageService;
import by.grodno.vika.librarywebapp.service.UserService;

@Controller
public class UsersController {

	@Autowired
	private UserService userService;
	@Autowired
	private StorageService imgService;
	

	
	@GetMapping("/users/{pageNum}")
	public String getAllUsers(Model model,
			@PathVariable(name = "pageNum") int pageNum,
			@RequestParam(required = false, name = "sortField") String sortField) {
		if (sortField == null) {
			sortField = "lastName";
		}
	    Page<User> page = userService.getUsers(pageNum, sortField);
	     
	    List<User> users = page.getContent();
	     
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("sortField", sortField);
	
	    model.addAttribute("users", users);
	     
	    return "usersList";
	}
	
	     
	  
	   
	
	
	

	@GetMapping("/users/profile/info")
	public String getUserById(@RequestParam(value = "userId", required = false) Integer userId, Model model,
			Authentication authentication) {
		if (userId == null) {
			userId = userService.findByEmail(authentication.getName()).getId();
			}
		User user = userService.getUser(userId);
		
		model.addAttribute("user", user);
		return "profile";
	}

	@GetMapping("/users/profile/books")
	public String getUsersBooks(@RequestParam(value = "userId", required = false) Integer userId, Model model,
			Authentication authentication) {
		if (userId == null) {
			userId = userService.findByEmail(authentication.getName()).getId();
		}

		User user = userService.getUser(userId);
		List<ReadersBook> books = user.getReadersBook();

		model.addAttribute("user", user);
		model.addAttribute("books", books);
		return "profileBookList";
	}

	@GetMapping("/users/profile/edit")

	public String editUserForm(Authentication authentication, Model model) {
		User user = userService.getUser(userService.findByEmail(authentication.getName()).getId());
		
		model.addAttribute("user", user);
		return "profileEdit";
	}

	@PostMapping("/users/profile/edit")
	public String updateUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult,  @RequestParam(value = "file", required = false) MultipartFile file,
			 Model model, Authentication authentication)
			throws IOException {
		if (bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			return "profileEdit";
		}
		Integer id = userService.findByEmail(authentication.getName()).getId();
		user.setId(id);
		userService.updateUser(user, file);

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


}