package by.grodno.vika.librarywebapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import by.grodno.vika.librarywebapp.domain.BookDiscription;
import by.grodno.vika.librarywebapp.domain.Catalog;
import by.grodno.vika.librarywebapp.domain.Status;
import by.grodno.vika.librarywebapp.repo.CatalogRepo;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CatalogRepoTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	CatalogRepo repo;

	@Test
	public void testCreateCatalog() {
		BookDiscription book = entityManager.persist(new BookDiscription());
		
		book.setAutor("Puskin");
		book.setTitle("Rybak");
		book.setYear(1897);
		
		Catalog catalog = new Catalog();
		catalog.setBookDiscription(book);
		catalog.setStatus(Status.AVAILABLE);
		repo.save(catalog);
		
		assertThat(catalog).isNotNull();
		assertThat((catalog.getId()>0));
	}
	
//	@Test
//	public void testInsertCatalog() {
//		
//		entityManager.find(Catalog.class, primaryKey)
//	}
//	
}
