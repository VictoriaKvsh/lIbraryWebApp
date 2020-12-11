package by.grodno.vika.librarywebapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import by.grodno.vika.librarywebapp.domain.BookDiscription;

public interface BookDiscriptionService {

	List<BookDiscription> getBooks();

	void addBook(BookDiscription book);

	void deleteBook(Integer number);
	
	Page<BookDiscription> getPage(Integer pageNum, Integer pageSize);

	List<BookDiscription> findByExample(BookDiscription autor);


}
