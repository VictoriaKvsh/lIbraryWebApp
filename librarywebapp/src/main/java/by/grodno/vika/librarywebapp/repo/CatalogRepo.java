package by.grodno.vika.librarywebapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import by.grodno.vika.librarywebapp.domain.Catalog;
import by.grodno.vika.librarywebapp.domain.Status;

public interface CatalogRepo extends JpaRepository<Catalog, Integer> {

	@Modifying
	@Query("UPDATE Catalog SET status=:status WHERE id=:id")
	void updateCatalogStatus(@Param("status") Status status, @Param("id") Integer catalogId);

	
	
	@Query("SELECT c FROM BookDiscription b JOIN b.catalog c WHERE b.autor LIKE %:keyword%" + " OR CONCAT(b.title, '') LIKE %:keyword%")
	public List<Catalog> search(@Param("keyword") String keyword);
	

}
