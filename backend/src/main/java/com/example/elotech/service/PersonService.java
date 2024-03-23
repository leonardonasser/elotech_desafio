package com.example.elotech.service;

import com.example.elotech.dtos.PersonContactParams;
import com.example.elotech.dtos.PersonParams;
import com.example.elotech.models.PersonContactModel;
import com.example.elotech.models.PersonModel;
import com.example.elotech.repositories.PersonRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonContactService personContactService;
    public PersonModel findByIdOrThrow(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada!"));
    }

    public List<PersonModel> list() {
        return personRepository.findAll();
    }

    public PersonModel create(PersonParams params) {

        validationsPerson(params);

        PersonModel person = new PersonModel(params.name(), params.cpf(), params.birthDate());

        PersonModel personFinal = personRepository.save(person);
        extractedPersonContacts(personFinal, params);

        return personFinal;
    }

    public PersonModel update(Long id, PersonParams params) {

        validationsPerson(params);

        PersonModel person = findByIdOrThrow(id);
        person.update(params.name(), params.cpf(), params.birthDate());

        PersonModel personFinal = personRepository.save(person);
        extractedPersonContacts(person, params);

        return personFinal;
    }

    public void extractedPersonContacts(PersonModel personModel, PersonParams params) {

        for (PersonContactModel contact : personModel.getContacts()) {
                if (params.contactParamsList()
                        .stream()
                        .noneMatch(c -> c.id() != null
                                && c.id() == contact.getId())) {
                    this.personContactService.delete(contact.getId());
                }
        }

        for (PersonContactParams contact: params.contactParamsList()) {
            if (contact.id() == null) {
                personContactService.create(contact, personModel);
            } else {
                personContactService.update(contact);
            }
        }
    }

    public void delete(Long id) {
        PersonModel person = findByIdOrThrow(id);

        for (PersonContactModel contact: person.getContacts()) {
            personContactService.delete(contact.getId());
        }

        personRepository.delete(person);
    }

    public static boolean validateCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (10 - i) * Integer.parseInt(cpf.substring(i, i + 1));
        }
        int digit = 11 - (sum % 11);
        if (digit > 9) {
            digit = 0;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (11 - i) * Integer.parseInt(cpf.substring(i, i + 1));
        }
        int digit2 = 11 - (sum % 11);
        if (digit2 > 9)
            digit2 = 0;

        return (Integer.parseInt(cpf.substring(9, 10)) == digit
                && Integer.parseInt(cpf.substring(10)) == digit2);
    }

    public static boolean isFutureDate(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        return date.isAfter(currentDate);
    }

    public void validationsPerson(PersonParams params) {
        if (params.contactParamsList().isEmpty()) {
            throw new IllegalArgumentException("Tem que ter ao menos um contato!");
        }

        if (!validateCPF(params.cpf())) {
            throw new IllegalArgumentException("CPF inválido!");
        }

        if (isFutureDate(params.birthDate())) {
            throw new IllegalArgumentException("Data de nascimento não pode ser uma data futura!");
        }
    }

}
