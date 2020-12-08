package by.grodno.vika.librarywebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportingServiceBook {
	
	@Autowired
	private BookDiscriptionService jdbcUserService;

	public Integer getAllBooksCount() {
		return jdbcUserService.getBooks().size();
	}

}
