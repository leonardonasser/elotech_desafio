package com.example.elotech.controllers;

import com.example.elotech.dtos.PersonContactModelDTO;
import com.example.elotech.dtos.PersonContactParams;
import com.example.elotech.models.PersonContactModel;
import com.example.elotech.service.PersonContactService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person/contacts")
public class PersonContactController {

    @Autowired
    PersonContactService personContactService;

//    @GetMapping("/{id}")
//    public ResponseEntity<PersonContactModelDTO> get(@PathVariable(value="id") Long id){
//        PersonContactModel person = personContactService.findByIdOrThrow(id);
//        return ResponseEntity.status(HttpStatus.OK).body(PersonContactModelDTO.ofEntity(person));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<PersonContactModelDTO>> list(){
//        List<PersonContactModel> personsContactList = personContactService.list();
//        return ResponseEntity.status(HttpStatus.OK).body(PersonContactModelDTO.ofEntities(personsContactList));
//    }
//
//    @PostMapping
//    public ResponseEntity<PersonContactModelDTO> create(@RequestBody @Valid PersonContactParams params) {
//        PersonContactModel personContact = personContactService.create(params);
//        return ResponseEntity.status(HttpStatus.CREATED).body(PersonContactModelDTO.ofEntity(personContact));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<PersonContactModelDTO> update(@PathVariable(value="id") Long id,
//                                               @RequestBody @Valid PersonContactParams params) {
//        PersonContactModel personContact = personContactService.update(id, params);
//        return ResponseEntity.status(HttpStatus.OK).body(PersonContactModelDTO.ofEntity(personContact));
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Object> delete(@PathVariable(value="id") Long id) {
//        personContactService.delete(id);
//        return ResponseEntity.status(HttpStatus.OK).body("Person deleted successfully.");
//    }

}
