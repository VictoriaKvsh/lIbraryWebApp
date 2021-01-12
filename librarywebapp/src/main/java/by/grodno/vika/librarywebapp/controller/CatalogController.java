package by.grodno.vika.librarywebapp.controller;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	CatalogService repo;
	
	
	@GetMapping("/catalog")
	public String getCatalog(Model model) throws NoSuchFieldException, SecurityException {
		 List<Catalog> catalog = repo.getCatalog();
		 model.addAttribute("catalog", catalog);
		 return "catalog";
	}
	
	@PostMapping(path = "/books/{bookId}/catalog")
	public String saveCatalog(@PathVariable ("bookId") Integer discriptionId, Catalog catalog) {
		repo.addCatalog(discriptionId, catalog);
		
		return "redirect:/books";
	}	
	
	
	@PostMapping("/catalog/{catalogId}/status_update")
	public String updateStatus(@PathVariable ("catalogId") Integer catalogId, Status status) {
		repo.updateCatalogStatus(status, catalogId);
		return "redirect:/catalog";
	}
	
	@PostMapping("/catalog/delete/{catalogId}")
	public String deleteCatalog(@PathVariable ("catalogId") Integer catalogId) {
		repo.deleteCatalog(catalogId);
		return "redirect:/catalog";
	}
}
