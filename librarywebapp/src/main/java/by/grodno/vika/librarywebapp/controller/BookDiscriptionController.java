package by.grodno.vika.librarywebapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
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
	BookDiscriptionService repo;

	@Autowired
	CatalogService catalogRepo;

	@PostMapping(path = "/books_list/new")
	public String saveBook(BookDiscription bookDiscription) {
		repo.addBook(bookDiscription);
		return "redirect:/books";
	}

	@GetMapping("/books_list/{pageNum}")
	public String getAllBooks(Model model, @PathVariable(name = "pageNum") int pageNum,
			@RequestParam(required = false, name = "sortField") String sortField) {
		if (sortField == null) {
			sortField = "autor";
		}
		Page<BookDiscription> page = repo.getBooks(pageNum, sortField);

		List<BookDiscription> books = page.getContent();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("sortField", sortField);
		model.addAttribute("books", books);

		return "booksCatalogDiscr";
	}

	@PostMapping(path = "/books_list/{bookId}/catalog")
	public String saveCatalog(@PathVariable("bookId") Integer discriptionId, Catalog catalog) {
		catalogRepo.addCatalog(discriptionId, catalog);

		return "redirect:/books_list";
	}

	@PostMapping("/books_list/edit/{bookId}")
	public String updateBookDiscr(@PathVariable Integer bookId, @Valid @RequestBody BookDiscription bookRequest) {
		repo.updateBook(bookId, bookRequest);
		return "";
	}

	@PostMapping("/books_list/delete/{bookId}")
	public String deleteBookDiscr(@PathVariable Integer bookId) {
		repo.deleteBook(bookId);
		return "redirect:/books_list";
	}

	@GetMapping("/books_list/search")
	public List<BookDiscription> findByExample(@RequestParam(value = "autor", required = false) String autor,
			@RequestParam(value = "title", required = false) String title) {
		BookDiscription bookDiscription = new BookDiscription(null, autor, title, null, null);
		return repo.findByExample(bookDiscription);
	}

}