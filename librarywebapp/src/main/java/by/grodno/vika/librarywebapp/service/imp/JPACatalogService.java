package by.grodno.vika.librarywebapp.service.imp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import by.grodno.vika.librarywebapp.domain.Catalog;
import by.grodno.vika.librarywebapp.domain.Status;
import by.grodno.vika.librarywebapp.exception.ResourceNotFoundException;
import by.grodno.vika.librarywebapp.repo.BookDiscriptionRepo;
import by.grodno.vika.librarywebapp.repo.CatalogRepo;
import by.grodno.vika.librarywebapp.repo.ReadersBookRepo;
import by.grodno.vika.librarywebapp.service.CatalogService;

@Service
public class JPACatalogService implements CatalogService {

	@Autowired
	CatalogRepo catalogRepo;
	@Autowired
	BookDiscriptionRepo bookRepo;
	@Autowired
	ReadersBookRepo readersRepo;

	@Override
	public Page<Catalog> getCatalog(int pageNum) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(pageNum, pageSize);
	     
	    return catalogRepo.findAll(pageable);
	}
		
		

	@Override
	public Catalog addCatalog(Integer discriptionId, Catalog catalog) {

		return bookRepo.findById(discriptionId).map(bookDiscription -> {
			catalog.setBookDiscription(bookDiscription);
			catalog.setStatus(Status.AVAILABLE);
			return catalogRepo.save(catalog);
		}).orElseThrow(() -> new ResourceNotFoundException("Discription Id " + discriptionId + " not found"));
	}

	@Override
	public void deleteCatalog(Integer number) {
		catalogRepo.deleteById(number);

	}

	@Override
	@Transactional
	public void updateCatalogStatus(Status status, Integer catalogId) {
		catalogRepo.updateCatalogStatus(status, catalogId);
		if (status.equals(Status.DUE_TO)) {
			catalogRepo.findById(catalogId).get().getReadersBook().setDueDate(addDate());
		} else if (status.equals(Status.AVAILABLE)) {
			readersRepo.deleteById(catalogRepo.findById(catalogId).get().getReadersBook().getId());
		}
	}

	public static Date addDate() {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DATE, 5);
		Date modifiedDate = cal.getTime();
		return modifiedDate;
	}

}
