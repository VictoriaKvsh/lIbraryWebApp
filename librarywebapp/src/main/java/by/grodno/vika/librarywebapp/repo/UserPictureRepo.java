package by.grodno.vika.librarywebapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import by.grodno.vika.librarywebapp.domain.UserPicture;


public interface UserPictureRepo extends JpaRepository<UserPicture, Integer> {

}