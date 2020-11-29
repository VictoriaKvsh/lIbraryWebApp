package by.grodno.vika.librarywebapp.service;

import java.util.List;

import by.grodno.vika.librarywebapp.domain.OldUser;

public interface UserRepository {

	List<OldUser> getUsers();

	void addUser(List<OldUser> user);

	void deleteUser(Integer number);

}