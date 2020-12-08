package by.grodno.vika.librarywebapp.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void addBook(List<BookDiscription> book) {
		repo.saveAll(book);

	}

	@Override
	public void deleteBook(Integer number) {
		repo.deleteById(number);

	}

	@Override
	public Page<BookDiscription> getPage(Integer pageNum, Integer pageSize) {
		return repo.findAll(PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "autor"));
	}

}
