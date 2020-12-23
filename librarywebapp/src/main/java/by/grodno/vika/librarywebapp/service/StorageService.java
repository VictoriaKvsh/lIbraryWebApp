package by.grodno.vika.librarywebapp.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import by.grodno.vika.librarywebapp.dto.Avatar;



public interface StorageService {

	void store(Integer id, MultipartFile file) throws IOException;

	Avatar getFile(Integer id);
}