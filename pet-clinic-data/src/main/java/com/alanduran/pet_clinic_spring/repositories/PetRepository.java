package com.alanduran.pet_clinic_spring.repositories;

import com.alanduran.pet_clinic_spring.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
