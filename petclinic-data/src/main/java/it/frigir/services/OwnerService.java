package it.frigir.services;

import it.frigir.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {


    Owner findByLastName(String lastName);
    Set<Owner> findAllByLastName(String lastName);

}
