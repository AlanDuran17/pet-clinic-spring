package com.alanduran.pet_clinic_spring.services.map;

import com.alanduran.pet_clinic_spring.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles({"map"})
class OwnerMapServiceTest {

    @Mock
    PetTypeMapService petTypeMapService;

    @Mock
    PetMapService PetMapService;

    @InjectMocks
    OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        ownerMapService.save(Owner.builder().id(1L).firstName("Michael").lastName("Scott").build());
    }

    @Test
    void findAll() {
        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(Owner.builder().id(1L).build());
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(1L);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findById() {
        assertEquals(1L, ownerMapService.findById(1L).getId());
    }

    @Test
    void save() {
        ownerMapService.save(Owner.builder().id(2L).build());
        assertEquals(2L, ownerMapService.findById(2L).getId());
    }

    @Test
    void findByLastName() {
        assertEquals(1L, ownerMapService.findByLastName("Scott").getId());
    }
}