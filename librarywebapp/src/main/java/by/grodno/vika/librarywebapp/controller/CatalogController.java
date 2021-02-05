package by.grodno.vika.librarywebapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import by.grodno.vika.librarywebapp.domain.BookDiscription;
import by.grodno.vika.librarywebapp.domain.Catalog;
import by.grodno.vika.librarywebapp.domain.Status;
import by.grodno.vika.librarywebapp.service.CatalogService;

@Controller
public class CatalogController {

	@Autowired
	CatalogService service;

	@GetMapping("/catalog")
	public String getCatalog(Model model, @RequestParam(required = false, name = "pageNum") Integer pageNum,
			@RequestParam(required = false, name = "sortField") String sortField) {
		if (pageNum == null) {
			pageNum = 1;
		}
		Page<Catalog> page = service.getCatalog(pageNum, sortField);
		List<Catalog> catalog = page.getContent();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("catalog", catalog);

		return "catalog";

	}

//	@GetMapping("/catalog/search")
//	public List<BookDiscription> findByExample(@RequestParam(value = "autor", required = false) String autor,
//			@RequestParam(value = "title", required = false) String title) {
//		BookDiscription bookDiscription = new BookDiscription(null, autor, title, null, null);
//		return service.findByExample(bookDiscription);
//	}

	@PostMapping("/catalog/{catalogId}/status_update")
	public String updateStatus(@PathVariable("catalogId") Integer catalogId, Status status,
			HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		service.updateCatalogStatus(status, catalogId);
		return "redirect:" + referer;
	}

	@PostMapping("/catalog/delete/{catalogId}")
	public String deleteCatalog(@PathVariable("catalogId") Integer catalogId) {
		service.deleteCatalog(catalogId);
		return "redirect:/catalog";
	}

	@GetMapping("/catalog/search")
	public String findByExample(Model model, @Param(value = "keyword") String keyword) {

		List<Catalog> books = service.findAllBooks(keyword);
		model.addAttribute("books", books);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", 1);
		model.addAttribute("totalPages", 1);
		return "catalog";
	}
}
