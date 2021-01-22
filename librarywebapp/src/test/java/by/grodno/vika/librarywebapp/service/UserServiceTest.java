package by.grodno.vika.librarywebapp.service;

import lombok.extern.log4j.Log4j2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.domain.UserCredentials;
import by.grodno.vika.librarywebapp.exception.UserNotFoundException;
import by.grodno.vika.librarywebapp.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class UserServiceTest {
	@Autowired
	UserRepo repo;
	
	@Test
	public void updatePassword() throws UserNotFoundException {
       
		log.info("setting new email");
		User user = repo.findByEmail("testing@gmail.com");
       
        	List <UserCredentials> credList = new ArrayList<UserCredentials>();
        	credList.add(0, new UserCredentials(2, null, true, "111"));
        	credList.add(0, new UserCredentials(2, null, true, null));
        	user.getCredentials().get(0).setPassword("222");
        	
        	credList.add(0, new UserCredentials());
        	
        	repo.save(user);
        
    }
	

}
