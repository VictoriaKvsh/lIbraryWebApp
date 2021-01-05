package by.grodno.vika.librarywebapp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.grodno.vika.librarywebapp.repo.UserCredentialsRepo;
import by.grodno.vika.librarywebapp.service.CredentialsService;

@Service
public class JPACredentialsService implements CredentialsService{
	
	@Autowired
	UserCredentialsRepo repo;
	

}
