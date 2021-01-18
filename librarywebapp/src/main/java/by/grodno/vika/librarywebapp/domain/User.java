package by.grodno.vika.librarywebapp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	//@NotEmpty(message = "Name should not be empty")
	private String firstName;

	//@NotEmpty(message = "Last name should not be empty")
	private String lastName;

	@Email(message = "Email should be valid")
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, updatable = false)
	private UserRole role;
	
	@OneToOne(mappedBy = "userPic", fetch = FetchType.EAGER)
	private UserPicture picture;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			joinColumns = @JoinColumn(name = "u1"), 
			inverseJoinColumns = @JoinColumn(name = "u2"))
	private List<UserCredentials> credentials;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ownerUser")
	private List<ReadersBook> readersBook;

	
	
	@Override
	public String toString() {
		return "User: [" + firstName + ", " + lastName + "]";
	}

}