package com.alanduran.pet_clinic_spring.services;

import com.alanduran.pet_clinic_spring.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
