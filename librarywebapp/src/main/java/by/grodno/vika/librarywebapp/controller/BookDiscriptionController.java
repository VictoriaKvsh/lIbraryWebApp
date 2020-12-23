package by.grodno.vika.librarywebapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import by.grodno.vika.librarywebapp.domain.BookDiscription;
import by.grodno.vika.librarywebapp.service.BookDiscriptionService;

@Controller
public class BookDiscriptionController {

	@Autowired
	BookDiscriptionService repo;

	@PostMapping(path = "/bookszzzz", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveBook(@RequestBody BookDiscription book) {
		repo.addBook(book);
	}

	@GetMapping("/books")
	public String getAllBooks(Model model) {
		List<BookDiscription> discr = repo.getBooks();
		model.addAttribute("discr", discr);
		return "logindemo";
	}

	@PutMapping("/books/{bookId}")
	public BookDiscription updateBookDiscr(@PathVariable Integer bookId,
			@Valid @RequestBody BookDiscription bookRequest) {
		return repo.updateBook(bookId, bookRequest);
	}

	@DeleteMapping("/books/{bookId}")
	public void deleteBookDiscr(@PathVariable Integer bookId) {
		repo.deleteBook(bookId);
	}

	@GetMapping("/books/page")
	public List<BookDiscription> getUsersPage(@RequestParam("pnum") Integer pnum,
			@RequestParam("psize") Integer psize) {
		return repo.getPage(pnum, psize).getContent();
	}

	@GetMapping("/books/search")
	public List<BookDiscription> findByExample(@RequestParam(value = "autor", required = false) String autor,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "genre", required = false) String genre) {
		BookDiscription bookDiscription = new BookDiscription(null, autor, title, genre, null, null);
		return repo.findByExample(bookDiscription);
	}

}