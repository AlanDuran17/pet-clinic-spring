package com.alanduran.pet_clinic_spring.bootstrap;

import com.alanduran.pet_clinic_spring.model.Owner;
import com.alanduran.pet_clinic_spring.model.Pet;
import com.alanduran.pet_clinic_spring.model.PetType;
import com.alanduran.pet_clinic_spring.model.Vet;
import com.alanduran.pet_clinic_spring.services.OwnerService;
import com.alanduran.pet_clinic_spring.services.PetTypeService;
import com.alanduran.pet_clinic_spring.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);




        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Scott");
        owner1.setAddress("123 Main Street");
        owner1.setCity("Scranton, Pennsylvania");
        owner1.setTelephone("987654321");

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
        owner1.setAddress("123 Main Street");
        owner1.setCity("Scranton, Pennsylvania");
        owner1.setTelephone("987654321");

        Pet jansPet = new Pet();
        jansPet.setPetType(savedDogType);
        jansPet.setOwner(owner2);
        jansPet.setBirthDate(LocalDate.of(2015, 1,1));
        jansPet.setName("Felix");
        owner2.getPets().add(jansPet);

        ownerService.save(owner2);

        System.out.println("Loaded owners: " + ownerService.findAll());

        Vet vet1 = new Vet();
        vet1.setFirstName("John");
        vet1.setLastName("Doe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jane");
        vet2.setLastName("Doe");
        vetService.save(vet2);

        System.out.println("Loaded vets: " + vetService.findAll());
    }
}
