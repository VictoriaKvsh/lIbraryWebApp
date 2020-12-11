package by.grodno.vika.librarywebapp.service;

import java.util.List;

import by.grodno.vika.librarywebapp.domain.User;

public interface UserService {

	List<User> getUsers();

	void addUser(User user);

	void deleteUser(Integer number);

	List<User> findByExample(User userSample);

	List<User> findByLName(String lastName);

}