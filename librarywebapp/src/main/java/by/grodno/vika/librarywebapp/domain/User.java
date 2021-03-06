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
import org.hibernate.validator.constraints.Length;

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
	
	@Length(min = 3, max = 20)
	private String firstName;
	
//	@Length(min = 3, max = 20)
	private String lastName;

	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, updatable = false)
	private UserRole role;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UserPicture picture;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "cred_user", joinColumns = {
			@JoinColumn(name = "u1") }, inverseJoinColumns = {
					@JoinColumn(name = "u2",  unique = true) })
	private UserCredentials credentials;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ownerUser")
	private List<ReadersBook> readersBook;

	
	
	@Length(min=10,max=13)
	private String phoneNumber;
	
	private String address;
	
	private String userRequestToken;

	@Override
	public String toString() {
		return "User: [" + firstName + ", " + lastName + "]";
	}

}