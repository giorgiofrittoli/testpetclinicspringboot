package fritz.test.petclinic.repository;

import fritz.test.petclinic.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long> {
}
