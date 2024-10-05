package com.alanduran.pet_clinic_spring.repositories;

import com.alanduran.pet_clinic_spring.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
