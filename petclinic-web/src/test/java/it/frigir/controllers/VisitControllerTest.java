package it.frigir.controllers;

import it.frigir.model.Owner;
import it.frigir.model.Pet;
import it.frigir.services.PetService;
import it.frigir.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VisitControllerTest {

	@Mock
	PetService petService;
	@Mock
	VisitService visitService;

	VisitController visitController;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		visitController = new VisitController(visitService, petService);
		mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
	}

	@Test
	void initNewVisitForm() throws Exception {
		//when
		Pet pet = Pet.builder().id(1L).owner(Owner.builder().id(1L).build()).build();

		//when
		when(petService.findById(anyLong())).thenReturn(pet);

		mockMvc.perform(get("/owners/1/pets/1/visits/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("/visits/form"))
				.andExpect(model().attributeExists("pet"))
				.andExpect(model().attributeExists("visit"));

	}


	@Test
	void crateVisit() {
	}
}