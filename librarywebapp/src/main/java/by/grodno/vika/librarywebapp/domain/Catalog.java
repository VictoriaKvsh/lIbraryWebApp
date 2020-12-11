package by.grodno.vika.librarywebapp.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "catalog_table")
public class Catalog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private Status status;

	@ManyToOne(optional = true)
	@JoinColumn(name = "bookDiscription_id")
	private BookDiscription bookDiscription;

	
//	@OneToOne(mappedBy = "catalog")
//	private ReadersBook readersBook;

}
