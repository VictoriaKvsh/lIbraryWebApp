package by.grodno.vika.librarywebapp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.grodno.vika.librarywebapp.domain.ReadersBook;
import by.grodno.vika.librarywebapp.exception.ResourceNotFoundException;
import by.grodno.vika.librarywebapp.repo.CatalogRepo;
import by.grodno.vika.librarywebapp.repo.ReadersBookRepo;
import by.grodno.vika.librarywebapp.repo.UserRepo;
import by.grodno.vika.librarywebapp.service.ReadersBookService;
import by.grodno.vika.librarywebapp.domain.Status;

@Service
public class JPAReadersBookService implements ReadersBookService{
	
	@Autowired
	ReadersBookRepo readersRepo;
	@Autowired
	CatalogRepo catalogRepo;
	@Autowired
	UserRepo userRepo;
	
	@Override
	public List<ReadersBook> getReadersBook() {
		return readersRepo.findAll();
	}

	@Override
	public ReadersBook addReadersBook(Integer catalogId, Integer userId, ReadersBook book) {
		
		
		return catalogRepo.findById(catalogId).map(catalog -> {
			book.setCatalog(catalog);
			book.setOwnerUser(userRepo.findById(userId).get());
			catalog.setStatus(Status.BOOKED);
            return readersRepo.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("Catalog Id " + catalogId + " not found"));
		
		
	}

	@Override
	public ReadersBook updateReadersBook(Integer catalogId, ReadersBook bookRequest) {


		
		return null;
	}

	@Override
	public void deleteCatalog(Integer number) {
		readersRepo.deleteById(number);
		
	}
	
	

}
