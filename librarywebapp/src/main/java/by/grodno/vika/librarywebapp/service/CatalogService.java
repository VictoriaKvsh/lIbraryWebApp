package by.grodno.vika.librarywebapp.service;

import java.util.List;


import by.grodno.vika.librarywebapp.domain.Catalog;

public interface CatalogService {

	
	List<Catalog> getCatalog();
	
	Catalog addCatalog(Integer discriptionId, Catalog catalog);
	
	Catalog updateCatalog(Integer catalogId, Catalog catalogRequest);

	void deleteCatalog(Integer number);


	
}
