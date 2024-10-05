package com.alanduran.pet_clinic_spring.repositories;

import com.alanduran.pet_clinic_spring.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
