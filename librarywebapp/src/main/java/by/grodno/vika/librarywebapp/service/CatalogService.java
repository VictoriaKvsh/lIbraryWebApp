package by.grodno.vika.librarywebapp.service;

import org.springframework.data.domain.Page;

import by.grodno.vika.librarywebapp.domain.Catalog;
import by.grodno.vika.librarywebapp.domain.Status;

public interface CatalogService {

	Page<Catalog> getCatalog(Integer pageNum, String sortField);
	
	Catalog addCatalog(Integer discriptionId, Catalog catalog);
		
	void updateCatalogStatus(Status status, Integer catalogId);

	void deleteCatalog(Integer number);

	

	

	


	
}
