package it.frigir.services.sdjpa;

import it.frigir.model.Speciality;
import it.frigir.repository.SpecialityRepository;
import it.frigir.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("sdjpa")
public class SpecialitySDJpaService implements SpecialtyService {

	private final SpecialityRepository specialityRepository;

	public SpecialitySDJpaService(SpecialityRepository specialityRepository) {
		this.specialityRepository = specialityRepository;
	}

	@Override
	public Set<Speciality> findAll() {
		Set<Speciality> specialties = new HashSet<>();
		specialityRepository.findAll().forEach(specialties::add);
		return specialties;
	}

	@Override
	public Speciality findById(Long aLong) {
		return specialityRepository.findById(aLong).orElse(null);
	}

	@Override
	public Speciality save(Speciality specialty) {
		return specialityRepository.save(specialty);
	}

	@Override
	public void delete(Speciality specialty) {
		specialityRepository.delete(specialty);
	}

	@Override
	public void deleteById(Long aLong) {
		specialityRepository.deleteById(aLong);
	}
}
