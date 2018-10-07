package it.frigir.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BasedEntity {

	@Builder
	public Pet(Long id, PetType petType, Owner owner, LocalDate birthDate, String name, Set<Visit> visits) {
		super(id);
		this.petType = petType;
		this.owner = owner;
		this.birthDate = birthDate;
		this.name = name;
		this.visits = visits;
	}

	@ManyToOne
	@JoinColumn(name = "type_id")
	private PetType petType;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "name")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
	private Set<Visit> visits = new HashSet<>();

}