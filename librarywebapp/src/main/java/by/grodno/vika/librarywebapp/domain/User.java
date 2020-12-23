package by.grodno.vika.librarywebapp.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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

	@NotEmpty(message = "Name should not be empty")
	private String firstName;

	@NotEmpty(message = "Last name should not be empty")
	private String lastName;

	@Email(message = "Email should be valid")
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, updatable = false)
	private UserRole role;
	
	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
	private UserPicture picture;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ownerUser")
	private UserDetails details;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ownerUser")
	private UserCredentials credentials;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ownerUser")
	private Subscription subscription;

	private String avatarFileName;
	
	@Override
	public String toString() {
		return "User: [" + firstName + ", " + lastName + "]";
	}

}