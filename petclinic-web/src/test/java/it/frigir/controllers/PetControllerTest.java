package it.frigir.controllers;

import it.frigir.model.Owner;
import it.frigir.model.Pet;
import it.frigir.model.PetType;
import it.frigir.services.OwnerService;
import it.frigir.services.PetService;
import it.frigir.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class PetControllerTest {

	@Mock
	OwnerService ownerService;
	@Mock
	PetTypeService petTypeService;
	@Mock
	PetService petService;

	PetController petController;

	MockMvc mockMvc;


	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		petController = new PetController(petService, ownerService, petTypeService);
		mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
	}

	@Test
	void defaultAttributesExistTest() throws Exception {

		//given
		Owner owner = new Owner();
		owner.setId(1L);
		Set<PetType> petTypeSet = new HashSet<>();

		//when
		when(ownerService.findById(anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypeSet);

		//then
		//mockMvc.perform(get("/owners/1/pets/new")).andExpect(model().attributeExists("owner"))
		//		.andExpect(model().attributeExists("petTypes"))
		//		.andExpect(model().attributeExists("pet"));
	}

	@Test
	void initNewForm() throws Exception {

		//given
		Owner owner = Owner.builder().id(1L).build();
		Set<PetType> petTypeSet = new HashSet<>();

		//when
		when(ownerService.findById(anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypeSet);

		mockMvc.perform(get("/owners/1/pets/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("/pets/form"))
				.andExpect(model().attributeExists("pet"));
	}

	@Test
	void initUpdForm() throws Exception {


		//given
		Pet pet = Pet.builder().id(1L).build();
		Owner owner = Owner.builder().id(1L).build();
		Set<PetType> petTypeSet = new HashSet<>();

		//when
		when(ownerService.findById(anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypeSet);

		//when
		when(petService.findById(anyLong())).thenReturn(pet);

		mockMvc.perform(get("/owners/1/pets/1/edit"))
				.andExpect(status().isOk())
				.andExpect(view().name("/pets/form"))
				.andExpect(model().attributeExists("pet"));
	}

	@Test
	void petSaveTest() throws Exception {
//		mockMvc.perform(post("/owners/1/pets")).andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/owners/1"));

//		verify(ownerService.save(any()));
	}


}