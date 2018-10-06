package it.frigir.services;

import it.frigir.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {


    Owner findByLastName(String lastName);

}
