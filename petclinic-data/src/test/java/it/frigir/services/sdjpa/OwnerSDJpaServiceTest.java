package it.frigir.services.sdjpa;

import it.frigir.model.Owner;
import it.frigir.repository.OwnerRepository;
import it.frigir.repository.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

	@Mock
	OwnerRepository ownerRepository;
	@Mock
	PetTypeRepository petTypeRepository;

	@InjectMocks
	OwnerSDJpaService ownerSDJpaService;

	final String LAST_NAME = "Smith";

	Long ownerId = 1L;

	Owner owner;

	@BeforeEach
	void setUp() {

		owner = Owner.builder().id(ownerId).lastName(LAST_NAME).build();
	}

	@Test
	void findByLastName() {

		when(ownerRepository.findByLastName(any())).thenReturn(owner);

		Owner smith = ownerSDJpaService.findByLastName(LAST_NAME);

		assertEquals(LAST_NAME, smith.getLastName());

		verify(ownerRepository).findByLastName(any());

	}

	@Test
	void findAll() {
		Set<Owner> ownerSet = new HashSet<>();
		ownerSet.add(Owner.builder().id(1L).build());
		ownerSet.add(Owner.builder().id(2L).build());

		when(ownerRepository.findAll()).thenReturn(ownerSet);

		Set<Owner> findOwners = ownerSDJpaService.findAll();

		assertNotNull(findOwners);
		assertEquals(2, findOwners.size());
	}

	@Test
	void findById() {
		when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(owner));

		Owner findOwner = ownerSDJpaService.findById(ownerId);

		assertNotNull(findOwner);

	}

	@Test
	void save() {
	}

	@Test
	void delete() {
	}

	@Test
	void deleteById() {
	}
}