package com.alanduran.pet_clinic_spring.controllers;

import com.alanduran.pet_clinic_spring.model.Pet;
import com.alanduran.pet_clinic_spring.services.PetService;
import com.alanduran.pet_clinic_spring.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
        when(petService.findById(1L)).thenReturn(new Pet());
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get("/owners/*/pets/{petId}/visits/new", 1L))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrUpdateVisitForm") );

        verify(petService, times(1)).findById(1L);
    }

    @Test
    void processNewVisitForm() throws Exception {
        mockMvc.perform(post("/owners/*/pets/{petId}/visits/new", 1L)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("date", "2018-11-11")
                        .param("description", "Saturday Night Fever"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
    }

    @Test
    void processNewVisitFormNotValid() throws Exception {
        mockMvc.perform(post("/owners/*/pets/{petId}/visits/new", 1L)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("date", "wrong date value")
                        .param("description", "Saturday Night Fever"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"));
    }
}