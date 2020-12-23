package by.grodno.vika.librarywebapp.service;

import by.grodno.vika.librarywebapp.domain.User;

public interface EmailService {
	
	void sendUserActivationEmail(User user);
}
