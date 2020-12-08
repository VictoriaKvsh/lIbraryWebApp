package by.grodno.vika.librarywebapp.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class ReadersBook {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date year;

	@ManyToOne
	@JoinColumn(name = "reader_id")
	private User student;

	
	@OneToOne
	@JoinColumn(name = "catalog")
	private Catalog catalog;
	

	
}