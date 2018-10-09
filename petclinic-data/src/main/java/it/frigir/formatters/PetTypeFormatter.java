package it.frigir.formatters;

import it.frigir.model.PetType;
import it.frigir.repository.PetTypeRepository;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;


@Component
public class PetTypeFormatter implements Formatter<PetType> {

	private final PetTypeRepository petTypeRepository;

	public PetTypeFormatter(PetTypeRepository petTypeRepository) {
		this.petTypeRepository = petTypeRepository;
	}


	@Override
	public String print(PetType petType, Locale locale) {
		return petType.getName();
	}

	@Override
	public PetType parse(String text, Locale locale) throws ParseException {


		Iterable<PetType> findPetTypes = petTypeRepository.findAll();
		for (PetType type : findPetTypes) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}

