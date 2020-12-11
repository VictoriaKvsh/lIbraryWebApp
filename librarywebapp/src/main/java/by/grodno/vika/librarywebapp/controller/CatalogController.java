package by.grodno.vika.librarywebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.grodno.vika.librarywebapp.domain.Catalog;
import by.grodno.vika.librarywebapp.service.BookDiscriptionService;
import by.grodno.vika.librarywebapp.service.CatalogService;

@RestController
public class CatalogController {

	@Autowired
	CatalogService repo;
	
	@Autowired
	BookDiscriptionService bookrepo;
	
	@GetMapping("/catalog")
	public List<Catalog> getCatalog() {
		return repo.getCatalog();
	}
	
	@PostMapping(path = "/books/{bookId}/catalog", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Catalog saveCatalog(@PathVariable (value = "postId") Integer discriptionId, @RequestBody Catalog catalog) {
		return bookrepo.findById(discriptionId).map(bookDiscription -> {
			catalog.setBookDiscription(bookDiscription);
            return catalogRepo.save(catalog);
        }).orElseThrow(() -> new ResourceNotFoundException("Discription Id " + discriptionId + " not found"));
    }
	
	
}
