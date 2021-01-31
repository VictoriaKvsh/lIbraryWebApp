package by.grodno.vika.librarywebapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
	
	
	
	
	@PostMapping("/catalog/{catalogId}/status_update")
	public String updateStatus(@PathVariable ("catalogId") Integer catalogId, Status status, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		repo.updateCatalogStatus(status, catalogId);
		return "redirect:" + referer;
	}
	
	@PostMapping("/catalog/delete/{catalogId}")
	public String deleteCatalog(@PathVariable ("catalogId") Integer catalogId) {
		repo.deleteCatalog(catalogId);
		return "redirect:/catalog";
	}
}
