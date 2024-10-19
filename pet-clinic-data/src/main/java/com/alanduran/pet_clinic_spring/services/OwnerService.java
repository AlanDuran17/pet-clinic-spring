package com.alanduran.pet_clinic_spring.services;

import com.alanduran.pet_clinic_spring.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
