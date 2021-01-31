package by.grodno.vika.librarywebapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.dto.UserRegistrationDTO;
import by.grodno.vika.librarywebapp.exception.ResourceNotFoundException;
import by.grodno.vika.librarywebapp.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private UserService service;
	@Autowired
	private ConversionService convertionService;

	@GetMapping("/activate_user")
	String activation(@Param(value = "token") String token, Model model) {
		User user = service.getByUserRequestToken(token);
		if (user == null) {
	       
			throw new ResourceNotFoundException("Invalid token" );
	    } else {           
	    	service.activateUser(user.getId());
	        model.addAttribute("message", "You have successfully changed your password.");
	    }
		return "redirect:/login";
	}
	
	@PostMapping("/register/new")
	String registerPage(@Valid @ModelAttribute ("user") UserRegistrationDTO user, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("userRegistrationDTO", user);
			return "login";
		}

		service.saveUser(convertionService.convert(user, User.class));

		return "redirect:/login";
	}
}