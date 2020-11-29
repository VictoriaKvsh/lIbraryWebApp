package by.grodno.vika.librarywebapp.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
public class BookDiscription {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(nullable = false)
	private String autor;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String genre;
	
	@Length(max = 4)
	@Column(nullable = false)
	private Integer year;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "bookDiscription")
	private List<Catalog> catalog;
	
	
	
}
