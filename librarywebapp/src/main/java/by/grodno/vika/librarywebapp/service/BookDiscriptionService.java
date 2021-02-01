package by.grodno.vika.librarywebapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

import by.grodno.vika.librarywebapp.domain.BookDiscription;

public interface BookDiscriptionService {

	Page<BookDiscription> getBooks(int pageNum, String sortField);
	
	void addBook(BookDiscription book);
	
	BookDiscription updateBook (Integer number, BookDiscription book);

	void deleteBook(Integer number);
	
	Page<BookDiscription> getPage(Integer pageNum, Integer pageSize);

	List<BookDiscription> findByExample(BookDiscription autor);

	


}
