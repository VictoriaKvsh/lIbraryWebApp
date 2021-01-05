package by.grodno.vika.librarywebapp.service;

import java.util.List;


import by.grodno.vika.librarywebapp.domain.Catalog;
import by.grodno.vika.librarywebapp.domain.Status;

public interface CatalogService {

	
	List<Catalog> getCatalog();
	
	Catalog addCatalog(Integer discriptionId, Catalog catalog);
		
	void updateCatalogStatus(Status status, Integer catalogId);

	void deleteCatalog(Integer number);


	
}
