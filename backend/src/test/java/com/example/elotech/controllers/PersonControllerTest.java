package com.example.elotech.controllers;

import com.example.elotech.dtos.PersonContactParams;
import com.example.elotech.dtos.PersonParams;
import com.example.elotech.models.PersonContactModel;
import com.example.elotech.models.PersonModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private PersonContactParams personContactParams;
    private PersonParams personParams;

    private static PersonModel personModel;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        if (personModel == null || personModel.getId() == null) {
            createPerson();
        }
    }

    public void createPerson() throws Exception {
        personContactParams = new PersonContactParams(
                null, "Contato1", "4490908080", "contato@gmail.com");

        List<PersonContactParams> personContactParamsList = new ArrayList<>();
        personContactParamsList.add(personContactParams);

        LocalDate birthDate = LocalDate.of(2001, 8, 24);
        personParams = new PersonParams("Teste", "23659103004", birthDate, personContactParamsList);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String requestBody = objectMapper.writeValueAsString(personParams);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        personModel = objectMapper.readValue(result.getResponse().getContentAsString(), PersonModel.class);
    }

    @Test
    @Order(1)
    public void getPerson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/persons/{id}", this.personModel.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        PersonModel personResult = objectMapper.readValue(result.getResponse().getContentAsString(), PersonModel.class);

        Assertions.assertTrue(personResult.getName().equals(personModel.getName()));
        Assertions.assertTrue(personResult.getCpf().equals(personModel.getCpf()));
        Assertions.assertTrue(personResult.getBirthDate().isEqual(personModel.getBirthDate()));

        Assertions.assertTrue(personResult.getContacts().size() > 0);

        PersonContactModel personContactModel = personResult.getContacts().get(0);
        Assertions.assertTrue(personContactModel.getName().equals("Contato1"));
        Assertions.assertTrue(personContactModel.getPhone().equals("4490908080"));
        Assertions.assertTrue(personContactModel.getEmail().equals("contato@gmail.com"));
    }

    @Test
    @Order(2)
    public void listPerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/persons"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(3)
    public void updatePerson() throws Exception {
        personContactParams = new PersonContactParams(
                null, "Contato2", "4491918181", "contato2@gmail.com");

        List<PersonContactParams> personContactParamsList = new ArrayList<>();
        personContactParamsList.add(personContactParams);

        LocalDate birthDate = LocalDate.of(2002, 9, 21);
        personParams = new PersonParams("Teste2", "30747666032", birthDate, personContactParamsList);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String requestBody = objectMapper.writeValueAsString(personParams);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/persons/{id}", personModel.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        PersonModel personResult = objectMapper.readValue(result.getResponse().getContentAsString(), PersonModel.class);

        Assertions.assertFalse(personResult.getName().equals(personModel.getName()));
        Assertions.assertFalse(personResult.getCpf().equals(personModel.getCpf()));
        Assertions.assertFalse(personResult.getBirthDate().isEqual(personModel.getBirthDate()));

        Assertions.assertTrue(personResult.getName().equals("Teste2"));
        Assertions.assertTrue(personResult.getCpf().equals("30747666032"));
        Assertions.assertTrue(personResult.getBirthDate().isEqual(birthDate));
    }

    @Test
    @Order(4)
    public void deletePerson() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/persons/{id}", this.personModel.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

}
