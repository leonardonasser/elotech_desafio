package com.example.elotech.dtos;

import com.example.elotech.models.PersonModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class PersonModelDTO {

    private Long id;
    private String name;

    private String cpf;

    private LocalDate birthDate;

    private List<PersonContactModelDTO> contacts = new ArrayList<>();

    private PersonModelDTO(PersonModel person) {
        this.id = person.getId();
        this.name = person.getName();
        this.cpf = person.getCpf();
        this.birthDate = person.getBirthDate();
        if (!person.getContacts().isEmpty()) {
            this.contacts = PersonContactModelDTO.ofEntities(person.getContacts());
        }
    }

    public static PersonModelDTO ofEntity(PersonModel person) {
        return new PersonModelDTO(person);
    }

    public static List<PersonModelDTO> ofEntities(List<PersonModel> persons) {
        return persons.stream().map(PersonModelDTO::new).collect(Collectors.toList());
    }
    
}
