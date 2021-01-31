package by.grodno.vika.librarywebapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import by.grodno.vika.librarywebapp.exception.ResourceNotFoundException;


@ControllerAdvice
public class ErrorsController {

	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public String handleUserNotFoundException(ResourceNotFoundException ex, Model model) {
		model.addAttribute("message", ex.getMessage());
		return "error/resourceNotFoundExeption";
	}
}
