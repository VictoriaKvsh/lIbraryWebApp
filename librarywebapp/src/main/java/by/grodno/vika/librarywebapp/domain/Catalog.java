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
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Catalog {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(nullable = false)
	private Status status;
	
	@OneToOne(mappedBy = "catalog")
    private ReadersBook readersBook;
	
	@ManyToOne
	@JoinColumn(name = "bookDiscription_id")
	private BookDiscription bookDiscription;

}
