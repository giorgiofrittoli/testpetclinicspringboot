package it.frigir.controllers;

import it.frigir.model.Owner;
import it.frigir.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/owners")
public class OwnerController {

	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@InitBinder
	public void setDisallowedField(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping({"/findform"})
	public String initFindForm(Model model) {
		model.addAttribute("owner", new Owner());
		return "/owners/find";
	}

	@GetMapping
	public String findOwners(Owner owner, BindingResult result, Model model) {
		if (owner.getLastName() == null)
			owner.setLastName("");

		Set<Owner> findResults = ownerService.findAllByLastName("%" + owner.getLastName() + "%");

		if (findResults.isEmpty()) {
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/find";
		} else if (findResults.size() == 1) {
			Owner foundOwner = findResults.iterator().next();
			model.addAttribute("owner", foundOwner);
			return "/owners/show";
		} else {
			model.addAttribute("owners", findResults);
			return "/owners/list";
		}

	}

	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable Long ownerId) {
		ModelAndView modelAndView = new ModelAndView("/owners/show");
		modelAndView.addObject(ownerService.findById(ownerId));
		return modelAndView;
	}

	@GetMapping("/new")
	public String initNewOwnerForm(Model model) {
		model.addAttribute("owner", new Owner());
		return "/owners/form";
	}

	@PostMapping("/new")
	public String saveOwner(@Valid Owner owner, BindingResult result) {
		if (result.hasErrors())
			return "owners/form";
		Owner savedOwner = ownerService.save(owner);
		return "redirect:/owners/" + savedOwner.getId();
	}

	@GetMapping("/{ownerId}/edit")
	public String showOwner(@PathVariable Long ownerId, Model model) {
		Owner owner = ownerService.findById(ownerId);
		if (owner == null)
			throw new RuntimeException("Owner not found");

		model.addAttribute("owner", owner);
		return "/owners/form";
	}

	@PostMapping("/{ownerId}/edit")
	public String updateOwner(@Valid Owner owner, BindingResult result,@PathVariable Long ownerId) {
		if (result.hasErrors())
			return "owners/form";
		owner.setId(ownerId);
		Owner savedOwner = ownerService.save(owner);
		return "redirect:/owners/" + savedOwner.getId();
	}

}
