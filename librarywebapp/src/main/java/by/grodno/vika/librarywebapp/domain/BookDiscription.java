package by.grodno.vika.librarywebapp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_discription_table")
public class BookDiscription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
//	@Column(nullable = false)
	private String autor;
	
//	@Column(nullable = false)
	private String name;
	
//	@Column(nullable = false)
	private String genre;
	
	private Integer year;
	
	//@OneToMany(cascade = CascadeType.MERGE, mappedBy = "bookDiscription")
	//private List<Catalog> catalog;
	
	
	
}
