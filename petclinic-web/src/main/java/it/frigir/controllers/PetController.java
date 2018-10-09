package it.frigir.controllers;

import it.frigir.model.Owner;
import it.frigir.model.Pet;
import it.frigir.model.PetType;
import it.frigir.services.OwnerService;
import it.frigir.services.PetService;
import it.frigir.services.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

	private final PetService petService;
	private final OwnerService ownerService;
	private final PetTypeService petTypeService;

	public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
		this.petService = petService;
		this.ownerService = ownerService;
		this.petTypeService = petTypeService;
	}

	@InitBinder
	public void setDisallowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	//add owner attribute and the set of pettype every time the controller is called

	@ModelAttribute("owner")
	public Owner addOwnerAttribute(@PathVariable Long ownerId) {
		return ownerService.findById(ownerId);
	}

	@ModelAttribute("petTypes")
	public Set<PetType> addPetTypeAttribute() {
		return petTypeService.findAll();
	}

	@GetMapping("/pets/new")
	public String initNewPetForm(Owner owner, Model model) {
		Pet pet = new Pet();
		pet.setOwner(owner);
		model.addAttribute("pet", pet);
		return "/pets/form";
	}

	@PostMapping("/pets/new")
	public String savePet(Owner owner, @Valid Pet pet, BindingResult result, Model model) {

		if (!StringUtils.isEmpty(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
			result.rejectValue("name", "duplicate", "already exist");
		}
		if (result.hasErrors()) {
			model.addAttribute("pet", pet);
			return "/pets/form";
		} else {
			pet.setOwner(owner);
			Pet savedPet = petService.save(pet);
			log.debug("Pet crated id " + savedPet.getId());
			owner.addPet(savedPet);
			return "redirect:/owners/" + owner.getId();
		}

	}

	@GetMapping("/pets/{id}/edit")
	public String initEditPetForm(@PathVariable Long id, Model model) {
		model.addAttribute("pet", petService.findById(id));
		return "/pets/form";
	}

	@PostMapping("/pets/{id}/edit")
	public String updatePet(Owner owner, @Valid Pet pet, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("pet", pet);
			return "/pets/form";
		} else {
			Pet savedPet = petService.save(pet);
			log.debug("Pet crated id " + savedPet.getId());
			owner.addPet(pet);
			return "redirect:/owners/" + owner.getId();
		}

	}

}
