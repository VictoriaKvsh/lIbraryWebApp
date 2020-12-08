package by.grodno.vika.librarywebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportingService {

	@Autowired
	private OldUserService jdbcUserService;

	public Integer getAllUsersCount() {
		return jdbcUserService.getUsers().size();
	}

}