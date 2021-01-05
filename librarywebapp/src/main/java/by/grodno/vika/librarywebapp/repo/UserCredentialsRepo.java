package by.grodno.vika.librarywebapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import by.grodno.vika.librarywebapp.domain.UserCredentials;

public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Integer>{

}
