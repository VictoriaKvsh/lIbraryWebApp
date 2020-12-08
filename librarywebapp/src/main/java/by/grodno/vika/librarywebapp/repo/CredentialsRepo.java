package by.grodno.vika.librarywebapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import by.grodno.vika.librarywebapp.domain.BookDiscription;

public interface CredentialsRepo extends JpaRepository<BookDiscription, Integer>{

}
