package by.grodno.vika.librarywebapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import by.grodno.vika.librarywebapp.domain.User;
import by.grodno.vika.librarywebapp.repo.UserRepo;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepo repo;
	@Test
	public void testRepoPresent() {
		assertNotNull(repo);
	}

	private static final String EMAIL = "admin@admin.admin";
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail(EMAIL);
		user.setFirstName("fName");
		user.setLastName("lName");

		repo.save(user);
		
		assertNotNull(user);
		assertTrue(user.getId() > 0);
		
	}
}