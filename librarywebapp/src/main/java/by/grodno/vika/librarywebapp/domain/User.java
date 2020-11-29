package by.grodno.vika.librarywebapp.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.ToString;


@Data
@Entity
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String firstName;
	private String lastName;

	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@Length(min = 6, max = 20)
	@Column(nullable = false, unique = true, updatable = false)
	private String login;


	@Column(nullable = false)
	private Role role;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ownerUser")
	private List<Credentials> credentials;

	@OneToMany(mappedBy = "student")
	private List<ReadersBook> readersBook;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="subscription_id")
    private Subscription subscription;
	

	@Override
	public String toString() {
		return "User: [" + firstName + ", " + lastName + "]";}
		
	

}