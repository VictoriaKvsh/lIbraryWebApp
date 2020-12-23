package by.grodno.vika.librarywebapp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;

import by.grodno.vika.librarywebapp.repo.UserCredentialsRepo;
import by.grodno.vika.librarywebapp.service.CredentialsService;

public class JPACredentialsService implements CredentialsService{
	
	@Autowired
	UserCredentialsRepo repo;
	

}
