package by.grodno.vika.librarywebapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import by.grodno.vika.librarywebapp.domain.BookDiscription;
import by.grodno.vika.librarywebapp.domain.Catalog;
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

	@GetMapping("/books_list")
	public String getAllBooks(Model model) {
		model.addAttribute("books", repo.getBooks());
	//	model.addAttribute("byAutor", Comparator.comparing(BookDiscription :: getAutor));
		return "booksCatalogDiscr";
	}
	
	@PostMapping(path = "/books_list/{bookId}/catalog")
	public String saveCatalog(@PathVariable ("bookId") Integer discriptionId, Catalog catalog) {
		catalogRepo.addCatalog(discriptionId, catalog);
		
		return "redirect:/books_list";
	}	

	@PostMapping("/books_list/edit/{bookId}")
	public String updateBookDiscr(@PathVariable Integer bookId,
			@Valid @RequestBody BookDiscription bookRequest) {
		repo.updateBook(bookId, bookRequest);
		return "";
	}

	@PostMapping("/books_list/delete/{bookId}")
	public String deleteBookDiscr(@PathVariable Integer bookId) {
		repo.deleteBook(bookId);
		return "redirect:/books_list";
	}

	@GetMapping("/books_list/page")
	public List<BookDiscription> getUsersPage(@RequestParam("pnum") Integer pnum,
			@RequestParam("psize") Integer psize) {
		return repo.getPage(pnum, psize).getContent();
	}

	@GetMapping("/books_list/search")
	public List<BookDiscription> findByExample(@RequestParam(value = "autor", required = false) String autor,
			@RequestParam(value = "title", required = false) String title) {
		BookDiscription bookDiscription = new BookDiscription(null, autor, title, null, null);
		return repo.findByExample(bookDiscription);
	}

}