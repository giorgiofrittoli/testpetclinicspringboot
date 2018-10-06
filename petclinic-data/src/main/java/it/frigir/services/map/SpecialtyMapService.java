package it.frigir.services.map;

import it.frigir.model.Speciality;
import it.frigir.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"map","default"})
public class SpecialtyMapService extends AbstractMapService<Speciality, Long> implements SpecialtyService {
	@Override
	public Set<Speciality> findAll() {
		return super.findAll();
	}

	@Override
	public Speciality findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Speciality save(Speciality specialty) {
		return super.save(specialty);
	}

	@Override
	public void delete(Speciality specialty) {
		super.delete(specialty);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
}
