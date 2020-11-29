package by.grodno.vika.librarywebapp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Credentials {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Boolean active;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User ownerUser;

}
