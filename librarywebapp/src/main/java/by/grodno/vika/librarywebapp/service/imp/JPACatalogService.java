package by.grodno.vika.librarywebapp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.grodno.vika.librarywebapp.domain.Catalog;
import by.grodno.vika.librarywebapp.exception.ResourceNotFoundException;
import by.grodno.vika.librarywebapp.repo.BookDiscriptionRepo;
import by.grodno.vika.librarywebapp.repo.CatalogRepo;
import by.grodno.vika.librarywebapp.service.CatalogService;

@Service
public class JPACatalogService implements CatalogService {

	@Autowired
	CatalogRepo catalogRepo;
	@Autowired
	BookDiscriptionRepo bookRepo;

	@Override
	public List<Catalog> getCatalog() {

		return catalogRepo.findAll();
	}

	@Override
	public Catalog addCatalog(Integer discriptionId, Catalog catalog) {
		
		return bookRepo.findById(discriptionId).map(bookDiscription -> {
			catalog.setBookDiscription(bookDiscription);
            return catalogRepo.save(catalog);
        }).orElseThrow(() -> new ResourceNotFoundException("Discription Id " + discriptionId + " not found"));
    }

	@Override
	public Catalog updateCatalog(Integer catalogId, Catalog catalogRequest) {
		return catalogRepo.findById(catalogId).map(catalog -> {
        	catalog.setStatus(catalogRequest.getStatus());
        	catalog.setBookDiscription(catalogRequest.getBookDiscription());
        	
            return catalogRepo.save(catalog);
        }).orElseThrow(() -> new ResourceNotFoundException("Catalog Id " + catalogId + " not found"));
    }
	
	@Override
	public void deleteCatalog(Integer number) {
		catalogRepo.deleteById(number);

	}
	

}
