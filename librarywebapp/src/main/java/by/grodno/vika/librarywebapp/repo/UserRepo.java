package by.grodno.vika.librarywebapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import by.grodno.vika.librarywebapp.domain.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	List<User> findByLastName(String lastName);

	

	User findByEmail(String email);

}