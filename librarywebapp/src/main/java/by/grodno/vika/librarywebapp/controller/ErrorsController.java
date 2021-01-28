package by.grodno.vika.librarywebapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import by.grodno.vika.librarywebapp.exception.ResourceNotFoundException;
import by.grodno.vika.librarywebapp.exception.UserNotFoundException;


@ControllerAdvice
public class ErrorsController {

	@ExceptionHandler(UserNotFoundException.class)
	public String handleUserNotFoundException(UserNotFoundException ex, Model model) {
		model.addAttribute("userId", ex.getUserInfo());
		return "error/userNotFoundException";
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public String handleUserNotFoundException(ResourceNotFoundException ex, Model model) {
		model.addAttribute("message", ex.getMessage());
		return "errors/resourceNotFoundExeption";
	}
}
