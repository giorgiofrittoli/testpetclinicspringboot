package fritz.test.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

	@Builder
	public Owner(String firstName, String lastName, Set<Pet> pets, String address, String telephone, String city) {
		super(firstName, lastName);
		this.pets = pets;
		this.address = address;
		this.telephone = telephone;
		this.city = city;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private Set<Pet> pets = new HashSet<>();

	@Column(name = "address")
	private String address;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "city")
	private String city;

}
