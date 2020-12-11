package by.grodno.vika.librarywebapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import by.grodno.vika.librarywebapp.domain.BookDiscription;

public interface BookDiscriptionRepo extends JpaRepository<BookDiscription, Integer>{ // Int - primary key type

	List<BookDiscription> findByAutor(String autor);
	
	Integer findbyId (Integer numb);
}
