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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import by.grodno.vika.librarywebapp.domain.BookDiscription;
import by.grodno.vika.librarywebapp.domain.Catalog;
import by.grodno.vika.librarywebapp.service.CatalogService;

@Controller
public class CatalogController {

	@Autowired
	CatalogService repo;
	
	@GetMapping("/catalog")
	public String getCatalog(Model model) {
		 List<Catalog> catalog = repo.getCatalog();
		 model.addAttribute("catalog", catalog);
		 return "catalog";
	}
	
	@PostMapping(path = "/books/{bookId}/catalog")
	public String saveCatalog(@PathVariable ("bookId") Integer discriptionId, Catalog catalog) {
		repo.addCatalog(discriptionId, catalog);
		
		return "redirect:/books";
	}
	
	
	@PutMapping("/catalog/{catalogId}")
    public Catalog updateCatalog(@PathVariable Integer catalogId, @Valid @RequestBody Catalog catalogRequest) {
        return repo.updateCatalog(catalogId, catalogRequest);
    }
	
	
	@DeleteMapping("/catalog/{catalogId}")
	public void deleteCatalog(@PathVariable Integer catalogId) {
		 repo.deleteCatalog(catalogId);
	}
}
