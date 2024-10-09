package com.alanduran.pet_clinic_spring.services.jpa;

import com.alanduran.pet_clinic_spring.model.Owner;
import com.alanduran.pet_clinic_spring.repositories.OwnerRepository;
import com.alanduran.pet_clinic_spring.repositories.PetRepository;
import com.alanduran.pet_clinic_spring.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerJpaService ownerJpaService;

    Owner expected;

    @BeforeEach
    void setUp() {
        expected = Owner.builder().id(1L).firstName("Michael").lastName("Scott").build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName("Scott")).thenReturn(expected);
        Owner actual = ownerJpaService.findByLastName("Scott");
        assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        Set<Owner> expectedSet = new HashSet<>();
        expectedSet.add(expected);
        when(ownerRepository.findAll()).thenReturn(expectedSet);

        Set<Owner> actual = ownerJpaService.findAll();

        assertEquals(expectedSet, actual);
        assertEquals(1, actual.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(expected));
        assertEquals(expected, ownerJpaService.findById(1L));
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(expected);
        assertNotNull(ownerJpaService.save(expected));
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> ownerJpaService.delete(expected));
        verify(ownerRepository).delete(expected);
    }

    @Test
    void deleteById() {
        assertDoesNotThrow(() -> ownerJpaService.deleteById(1L));
        verify(ownerRepository).deleteById(1L);
    }
}