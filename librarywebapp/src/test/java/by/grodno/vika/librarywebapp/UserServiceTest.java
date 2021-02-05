package by.grodno.vika.librarywebapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.repo.UserRepo;
import by.grodno.vika.librarywebapp.service.imp.JPAUserService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	@MockBean
	private UserRepo repo;
	
	@InjectMocks
	private JPAUserService service;
	
	
	@Test
	public void testTokenWhenActivatingNewUser(){
		String token = "fgddf";
		String userEmail = "vika@vika@vika";
		Integer id = null;
		
		User user = new User();
		user.setEmail(userEmail);
		user.setUserRequestToken(token);
		Mockito.when(repo.findByEmail(userEmail)).thenReturn(user);
		Mockito.when(repo.findByUserRequestToken(token)).thenReturn(user);
		
		service.updateUserRequestToken(token, userEmail);
		
	}
}
