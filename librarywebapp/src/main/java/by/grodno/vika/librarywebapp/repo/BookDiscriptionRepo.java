package by.grodno.vika.librarywebapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import by.grodno.vika.librarywebapp.domain.BookDiscription;

public interface BookDiscriptionRepo extends JpaRepository<BookDiscription, Integer> {

	List<BookDiscription> findByAutor(String autor);

	@Query("SELECT b FROM BookDiscription b WHERE LOWER (CONCAT(b.autor, ' ', b.title)) LIKE %:keyword%")
	public List<BookDiscription> search(@Param("keyword") String keyword);

	
}
