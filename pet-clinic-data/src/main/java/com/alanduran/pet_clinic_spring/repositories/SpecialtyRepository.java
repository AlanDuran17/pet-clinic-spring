package com.alanduran.pet_clinic_spring.repositories;

import com.alanduran.pet_clinic_spring.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
