package com.alanduran.pet_clinic_spring.repositories;

import com.alanduran.pet_clinic_spring.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);
}
