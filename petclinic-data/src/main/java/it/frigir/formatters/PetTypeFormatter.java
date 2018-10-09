package it.frigir.formatters;

import it.frigir.model.PetType;
import it.frigir.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;


@Component
public class PetTypeFormatter implements Formatter<PetType> {

	private final PetTypeService petTypeService;

	public PetTypeFormatter(PetTypeService petTypeService) {
		this.petTypeService = petTypeService;
	}


	@Override
	public String print(PetType petType, Locale locale) {
		return petType.getName();
	}

	@Override
	public PetType parse(String text, Locale locale) {

		return petTypeService.findAll().stream()
				.filter(petType -> petType.getName().equals(text))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Error parsing petType"));
	}

}

