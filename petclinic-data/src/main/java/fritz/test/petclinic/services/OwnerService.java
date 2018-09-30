package fritz.test.petclinic.services;

import fritz.test.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {


    Owner findByLastName(String lastName);

}
