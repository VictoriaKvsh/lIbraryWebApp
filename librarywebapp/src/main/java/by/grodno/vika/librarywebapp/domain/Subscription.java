package by.grodno.vika.librarywebapp.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscription_table")

public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date activationDate;
	
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	@OneToOne
	@JoinColumn(name = "user_id")
    private User ownerUser;
	
	@OneToMany(mappedBy = "subscription") 
	 private List<ReadersBook> readersBook;

}
