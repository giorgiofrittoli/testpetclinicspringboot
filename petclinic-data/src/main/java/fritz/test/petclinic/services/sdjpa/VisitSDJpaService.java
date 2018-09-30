package fritz.test.petclinic.services.sdjpa;

import fritz.test.petclinic.model.Visit;
import fritz.test.petclinic.repository.VisitRepository;
import fritz.test.petclinic.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class VisitSDJpaService implements VisitService {

	private final VisitRepository visitRepository;

	public VisitSDJpaService(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	@Override
	public Set<Visit> findAll() {
		Set<Visit> visits = new HashSet<>();
		visitRepository.findAll().forEach(visits::add);
		return visits;
	}

	@Override
	public Visit findById(Long aLong) {
		return visitRepository.findById(aLong).orElse(null);
	}

	@Override
	public Visit save(Visit visit) {
		return visitRepository.save(visit);
	}

	@Override
	public void delete(Visit visit) {
		visitRepository.delete(visit);
	}

	@Override
	public void deleteById(Long aLong) {
		visitRepository.deleteById(aLong);
	}
}
