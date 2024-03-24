package com.example.elotech.controllers;

import com.example.elotech.dtos.PersonModelDTO;
import com.example.elotech.dtos.PersonParams;
import com.example.elotech.models.PersonModel;
import com.example.elotech.service.PersonService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonModelDTO> get(@PathVariable(value="id") Long id){
        PersonModel person = personService.findByIdOrThrow(id);
        return ResponseEntity.status(HttpStatus.OK).body(PersonModelDTO.ofEntity(person));
    }

    @GetMapping
    public ResponseEntity<List<PersonModelDTO>> list(){
        List<PersonModel> personsList = personService.list();
        return ResponseEntity.status(HttpStatus.OK).body(PersonModelDTO.ofEntities(personsList));
    }

    @PostMapping
    public ResponseEntity<PersonModelDTO> create(@RequestBody @Valid PersonParams params) {
        PersonModel person = personService.create(params);
        return ResponseEntity.status(HttpStatus.CREATED).body(PersonModelDTO.ofEntity(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonModelDTO> update(@PathVariable(value="id") Long id,
                                               @RequestBody @Valid PersonParams params) {
        PersonModel person = personService.update(id, params);
        return ResponseEntity.status(HttpStatus.OK).body(PersonModelDTO.ofEntity(person));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable(value="id") Long id) {
        personService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Person deleted successfully.");
    }

}
