package by.grodno.vika.librarywebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.grodno.vika.librarywebapp.domain.BookDiscription;
import by.grodno.vika.librarywebapp.service.BookDiscriptionService;

@RestController
public class BookDiscriptionController {
	
	@Autowired
	BookDiscriptionService repo;
	
	
	@PostMapping(path = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveBook(@RequestBody List<BookDiscription> book) {
		repo.addBook(book);;
	}

	@GetMapping("/books")
	public List<BookDiscription> getAllBooks() {
		return repo.getBooks();
	}

	@GetMapping("/books/page")
	public List<BookDiscription> getUsersPage(@RequestParam("pnum") Integer pnum, @RequestParam("psize") Integer psize) {
		return repo.getPage(pnum, psize).getContent();
}
}