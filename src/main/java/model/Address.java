package model;

import java.util.HashSet;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "address")
@NamedQuery(
			name = "address.findAll",
			query = "SELECT * FROM address"
		)
public class Address {

//	Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	
	private String address;
	
	@OneToMany(mappedBy = "address", orphanRemoval = true, cascade = CascadeType.ALL)
	private HashSet<Person> residents;
	
//	Methods
	public void addResident(Person resident) {
		residents.add(resident);
	}
	
	public void removeResident(Person resident) {
		residents.remove(resident);
	}
	
	@Override
	public String toString() {
		List<String> rsdnts = residents.stream()
				.map(r -> r.getId().toString())
				.sorted()
				.toList();
		return String.format("[%d, %s, %s]", id, address, rsdnts);
	}
	
	public boolean equals(Address a) {
		return a != null && a.getId() != null && a.getId() == this.id;
	}
	
}
