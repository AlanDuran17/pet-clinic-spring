package com.alanduran.pet_clinic_spring.bootstrap;

import com.alanduran.pet_clinic_spring.model.*;
import com.alanduran.pet_clinic_spring.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = Owner.builder()
                .firstName("Michael")
                .lastName("Scott")
                .address("123 Main Street")
                .city("Scranton, Pennsylvania")
                .telephone("987654321")
                .pets(new HashSet<>()).build();

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.of(2018, 1,1));
        mikesPet.setName("Winston");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Jan");
        owner2.setLastName("Levinson");
        owner2.setAddress("123 Main Street");
        owner2.setCity("Scranton, Pennsylvania");
        owner2.setTelephone("987654321");

        Pet jansPet = new Pet();
        jansPet.setPetType(savedDogType);
        jansPet.setOwner(owner2);
        jansPet.setBirthDate(LocalDate.of(2015, 1,1));
        jansPet.setName("Felix");
        owner2.getPets().add(jansPet);

        ownerService.save(owner2);

        System.out.println("Loaded owners: " + ownerService.findAll());

        Visit catVisit = new Visit();
        catVisit.setPet(jansPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Went to Angela's house");
        visitService.save(catVisit);

        System.out.println("Loaded visits: " + visitService.findAll());

        Vet vet1 = new Vet();
        vet1.setFirstName("John");
        vet1.setLastName("Doe");
        vet1.getSpecialties().add(surgery);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jane");
        vet2.setLastName("Doe");
        vet2.getSpecialties().add(radiology);
        vetService.save(vet2);

        System.out.println("Loaded vets: " + vetService.findAll());
    }
}
