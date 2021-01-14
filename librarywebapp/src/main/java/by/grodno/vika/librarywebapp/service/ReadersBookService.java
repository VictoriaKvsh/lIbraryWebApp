package by.grodno.vika.librarywebapp.service;

import java.util.List;

import by.grodno.vika.librarywebapp.domain.ReadersBook;

public interface ReadersBookService {
	

	List<ReadersBook> getReadersBook();

	ReadersBook addReadersBook(Integer discriptionId, Integer userId, ReadersBook book);

//	ReadersBook updateReadersBook(Integer catalogId, ReadersBook bookRequest);



}
