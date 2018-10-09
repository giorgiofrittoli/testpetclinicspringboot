package it.frigir.controllers;


import it.frigir.model.Pet;
import it.frigir.model.Visit;
import it.frigir.services.PetService;
import it.frigir.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/owners/{ownerId}/pets/{petId}")
@Controller
public class VisitController {

	private final VisitService visitService;
	private final PetService petService;

	public VisitController(VisitService visitService, PetService petService) {
		this.visitService = visitService;
		this.petService = petService;
	}

	@InitBinder
	public void setDisallowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	//add pet attribute and the set of pettype every time the controller is called
	@ModelAttribute("pet")
	public Pet addPetAttribute(@PathVariable Long petId) {
		return petService.findById(petId);
	}


	@GetMapping("/visits/new")
	public String initNewVisitForm(Pet pet, Model model) {
		Visit visit = new Visit();
		visit.setPet(pet);
		model.addAttribute("visit", visit);
		return "/visits/form";
	}

	@PostMapping("/visits/new")
	public String crateVisit(Pet pet, @Valid Visit visit, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("visit", visit);
			return "/visits/form";
		} else {
			visit.setPet(pet);
			pet.addVisit(visit);
			visitService.save(visit);
			return "redirect:/owners/" + pet.getOwner().getId();
		}
	}

}

