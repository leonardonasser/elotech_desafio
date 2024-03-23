package com.example.elotech.dtos;

import com.example.elotech.models.PersonContactModel;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class PersonContactModelDTO {

    private Long id;
    private String name;

    private String phone;

    private String email;

    private PersonContactModelDTO(PersonContactModel personContact) {
        this.id = personContact.getId();
        this.name = personContact.getName();
        this.phone = personContact.getPhone();
        this.email = personContact.getEmail();
    }

    public static PersonContactModelDTO ofEntity(PersonContactModel personContact) {
        return new PersonContactModelDTO(personContact);
    }

    public static List<PersonContactModelDTO> ofEntities(List<PersonContactModel> personContacts) {
        return personContacts.stream().map(PersonContactModelDTO::new).collect(Collectors.toList());
    }
    
}
