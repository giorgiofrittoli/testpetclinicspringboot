package fritz.test.petclinic.services.map;

import fritz.test.petclinic.model.Speciality;
import fritz.test.petclinic.model.Vet;
import fritz.test.petclinic.services.SpecialtyService;
import fritz.test.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"map","default"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

	private final SpecialtyService specialtyService;

	public VetMapService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Vet save(Vet vet) {
		vet.getSpecialities().forEach(specialty -> {
			if (specialty.getId() == null) {
				Speciality savedSpecialty = specialtyService.save(specialty);
				specialty.setId(savedSpecialty.getId());
			}

		});
		return super.save(vet);
	}

	@Override
	public void delete(Vet vet) {
		super.delete(vet);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
}