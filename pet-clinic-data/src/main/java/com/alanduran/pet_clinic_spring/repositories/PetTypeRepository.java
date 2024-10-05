package com.alanduran.pet_clinic_spring.repositories;

import com.alanduran.pet_clinic_spring.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
