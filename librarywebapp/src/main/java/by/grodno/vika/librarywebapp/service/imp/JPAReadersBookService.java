package by.grodno.vika.librarywebapp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;

import by.grodno.vika.librarywebapp.repo.ReadersBookRepo;
import by.grodno.vika.librarywebapp.service.ReadersBookService;

public class JPAReadersBookService implements ReadersBookService{
	
	@Autowired
	ReadersBookRepo repo;
	
	

}
