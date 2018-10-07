package it.frigir.controllers;

import it.frigir.model.Owner;
import it.frigir.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
	@Mock
	OwnerService ownerService;

	@InjectMocks
	OwnerController ownerController;

	Set<Owner> owners;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());

		mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

	}


	@Test
	void findOwners() throws Exception {
		mockMvc.perform(get("/owners/findform"))
				.andExpect(view().name("owners/find"));

	}


	@Test
	void getOwnerTest() throws Exception {

		//given
		Owner owner = Owner.builder().id(1L).build();

		//when
		when(ownerService.findById(anyLong())).thenReturn(owner);

		//then
		mockMvc.perform(get("/owners/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/show"));

	}

	@Test
	void initFindFormTest() throws Exception {
		mockMvc.perform(get("/owners/findform"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/find"));
	}

	@Test
	void finAllOwnersOneTest() throws Exception {
		//given
		Set<Owner> owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		when(ownerService.findAllByLastName(anyString())).thenReturn(owners);

		mockMvc.perform(get("/owners"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/show"))
				.andExpect(model().attributeExists("owner"));
	}

	@Test
	void finAllOwnersManyTest() throws Exception {
		//given
		Set<Owner> owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());
		when(ownerService.findAllByLastName(anyString())).thenReturn(owners);

		mockMvc.perform(get("/owners"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/list"))
				.andExpect(model().attribute("owners", hasSize(2)));
	}

}