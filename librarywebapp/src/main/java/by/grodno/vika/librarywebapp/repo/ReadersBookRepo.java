package by.grodno.vika.librarywebapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import by.grodno.vika.librarywebapp.domain.BookDiscription;
import by.grodno.vika.librarywebapp.domain.ReadersBook;

public interface ReadersBookRepo extends JpaRepository<ReadersBook, Integer>{

	
	
}
