package com.alanduran.pet_clinic_spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Person(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
