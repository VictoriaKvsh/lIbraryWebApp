package by.grodno.vika.librarywebapp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import by.grodno.vika.librarywebapp.domain.BookDiscription;
import by.grodno.vika.librarywebapp.repo.BookDiscriptionRepo;
import by.grodno.vika.librarywebapp.service.BookDiscriptionService;

@Service
public class JPABookDiscriptionService implements BookDiscriptionService {

	@Autowired
	BookDiscriptionRepo repo;

	@Override
	public List<BookDiscription> getBooks() {
		return repo.findAll();
	}

	@Override
	public void addBook(BookDiscription book) {
		repo.save(book);

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

	@Override
	public Integer findById(Integer discriptionId) {
		
		return repo.findbyId(discriptionId);
	}

}
