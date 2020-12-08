package by.grodno.vika.librarywebapp.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import by.grodno.vika.librarywebapp.domain.OldUser;


public interface OldUserRepo extends JpaRepository<OldUser, Integer> {
	
	List<OldUser> findByFirstName(String firstName);

}