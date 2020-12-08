package by.grodno.vika.librarywebapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import by.grodno.vika.librarywebapp.domain.Catalog;

public interface CatalogRepo extends JpaRepository<Catalog, Integer>{
	
	

}
