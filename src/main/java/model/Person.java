package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "person")
@NamedQuery(
			name = "Person.findAll", 
			query = "SELECT * FROM person"
		)
public class Person {

//	Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	
	private String name;
	private Integer age;
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	public boolean equals(Person p) {
		return p != null && p.getId() != null && p.getId() == this.id;
	}
	
}
