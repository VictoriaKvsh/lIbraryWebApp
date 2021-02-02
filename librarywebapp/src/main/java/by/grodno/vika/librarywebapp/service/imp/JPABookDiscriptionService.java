package by.grodno.vika.librarywebapp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import by.grodno.vika.librarywebapp.domain.BookDiscription;
import by.grodno.vika.librarywebapp.exception.ResourceNotFoundException;
import by.grodno.vika.librarywebapp.repo.BookDiscriptionRepo;
import by.grodno.vika.librarywebapp.service.BookDiscriptionService;

@Service
public class JPABookDiscriptionService implements BookDiscriptionService {

	@Autowired
	BookDiscriptionRepo repo;

	@Override
	public Page<BookDiscription> getBooks(int pageNum, String sortField) {
		int pageSize = 8;
			if (sortField == null) {
				sortField = "autor";
			}
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(sortField).ascending());
		return repo.findAll(pageable);
	}

	@Override
	public void addBook(BookDiscription book) {
		repo.save(book);

	}

	@Override
	public BookDiscription updateBook(Integer number, BookDiscription bookRequest) {
		return repo.findById(number).map(bookDiscription -> {
			bookDiscription.setAutor(bookRequest.getAutor());
			bookDiscription.setTitle(bookRequest.getTitle());
			bookDiscription.setYear(bookRequest.getYear());
			return repo.save(bookDiscription);
		}).orElseThrow(() -> new ResourceNotFoundException("BookId " + number + " not found"));
	}

	@Override
	public void deleteBook(Integer number) {
		repo.deleteById(number);

	}

	@Override
	public Page<BookDiscription> getPage(Integer pageNum, Integer pageSize) {
		return repo.findAll(PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "autor"));
	}

	@Override
	public List<BookDiscription> findByExample(BookDiscription autor) {
		Example<BookDiscription> exp = Example.of(autor,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
		return repo.findAll(exp);
	}

}
