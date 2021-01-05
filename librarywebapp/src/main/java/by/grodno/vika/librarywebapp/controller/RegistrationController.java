package by.grodno.vika.librarywebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.dto.UserRegistrationDTO;
import by.grodno.vika.librarywebapp.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private UserService service;
	@Autowired
	private ConversionService convertionService;

	@GetMapping("/activate/{id}")
	String activation(@PathVariable Integer id) {

		service.activateUser(id);

		return "redirect:/login";
	}

	@PostMapping("/register/new")
	String registerPage(UserRegistrationDTO newUserDTO) {

		service.saveUser(convertionService.convert(newUserDTO, User.class));
		return "redirect:/login";
	}
}