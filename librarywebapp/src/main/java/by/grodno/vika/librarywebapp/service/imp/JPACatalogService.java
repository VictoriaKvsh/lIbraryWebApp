package by.grodno.vika.librarywebapp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.grodno.vika.librarywebapp.domain.Catalog;
import by.grodno.vika.librarywebapp.repo.CatalogRepo;
import by.grodno.vika.librarywebapp.service.CatalogService;

@Service
public class JPACatalogService implements CatalogService{
	
	@Autowired
	CatalogRepo repo;

	@Override
	public List<Catalog> getCatalog() {
		
		return repo.findAll();
	}
	
	
	
	

}
