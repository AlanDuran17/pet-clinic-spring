package com.alanduran.pet_clinic_spring.services;

import com.alanduran.pet_clinic_spring.model.Owner;
import com.alanduran.pet_clinic_spring.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
