package it.frigir.model;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private Set<Pet> pets = new HashSet<>();

	@Column(name = "address")
	private String address;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "city")
	private String city;

	@Builder
	public Owner(Long id, String firstName, String lastName, Set<Pet> pets, String address, String telephone, String city) {
		super(id, firstName, lastName);
		this.pets = pets;
		this.address = address;
		this.telephone = telephone;
		this.city = city;
		if (pets != null)
			this.pets = pets;
	}

	public void addPet(Pet pet) {
		this.pets.add(pet);
	}


	public Pet getPet(String name) {
		return getPet(name, false);
	}

	public Pet getPet(String name, boolean ignoreNew) {
		if (ignoreNew)
			return null;

		return this.pets.stream().filter(pet -> !pet.isNew() && pet.getName().equalsIgnoreCase(name)).findFirst().orElse(null);

	}


}
