package it.frigir.repository;

import it.frigir.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

	Owner findByLastName(String lastName);

	Set<Owner> findAllByLastNameLike(String lastName);

}