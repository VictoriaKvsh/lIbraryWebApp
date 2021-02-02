package by.grodno.vika.librarywebapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import by.grodno.vika.librarywebapp.domain.BookDiscription;
import by.grodno.vika.librarywebapp.domain.Catalog;
import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.service.BookDiscriptionService;
import by.grodno.vika.librarywebapp.service.CatalogService;

@Controller
public class BookDiscriptionController {

	@Autowired
	BookDiscriptionService bookService;

	@Autowired
	CatalogService catalogService;

	@PostMapping(path = "/books_list/new")
	public String saveBook(BookDiscription bookDiscription) {
		bookService.addBook(bookDiscription);
		return "redirect:/books";
	}

	@GetMapping("/books_list/{pageNum}")
	public String getAllBooks(Model model, @PathVariable(name = "pageNum") int pageNum,
			@RequestParam(required = false, name = "sortField") String sortField) {
		
		Page<BookDiscription> page = bookService.getBooks(pageNum, sortField);

		List<BookDiscription> books = page.getContent();
		
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("sortField", sortField);
		model.addAttribute("books", books);

		return "booksCatalogDiscr";
	}

	@PostMapping(path = "/books_list/{bookId}/catalog")
	public String saveCatalog(@PathVariable("bookId") Integer discriptionId, Catalog catalog) {
		catalogService.addCatalog(discriptionId, catalog);

		return "redirect:/books_list";
	}

	@PostMapping("/books_list/edit/{bookId}")
	public String updateBookDiscr(@PathVariable Integer bookId, @Valid @RequestBody BookDiscription bookRequest) {
		bookService.updateBook(bookId, bookRequest);
		return "";
	}

	@PostMapping("/books_list/delete/{bookId}")
	public String deleteBookDiscr(@PathVariable Integer bookId) {
		bookService.deleteBook(bookId);
		return "redirect:/books_list";
	}

	@GetMapping("/books_list/1/search")
	public String findByExample(Model model, @Param(value = "autor") String autor) {
		
		BookDiscription bookDiscription = new BookDiscription(null, autor, null, null, null);
		
		List<BookDiscription> books = bookService.findByExample(bookDiscription);

		model.addAttribute("currentPage", 1);
		model.addAttribute("totalPages", 1);
		model.addAttribute("books", books);

		return "booksCatalogDiscr";
	}

}