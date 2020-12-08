package by.grodno.vika.librarywebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import by.grodno.vika.librarywebapp.domain.Catalog;
import by.grodno.vika.librarywebapp.service.CatalogService;

@RestController
public class CatalogController {

	@Autowired
	CatalogService repo;
	
	@GetMapping("/catalog")
	public List<Catalog> getCatalog() {
		return repo.getCatalog();
	}
}
