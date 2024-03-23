package com.example.elotech.service;

import com.example.elotech.dtos.PersonContactParams;
import com.example.elotech.models.PersonContactModel;
import com.example.elotech.models.PersonModel;
import com.example.elotech.repositories.PersonContactRepository;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonContactService {

    @Autowired
    private PersonContactRepository personContactRepository;


    //    public PersonContact buscarPersonContactPeloUsuario(Usuario usuario) {
    //        return personRepository.findPersonContactByUsuarioResponsavel(usuario);
    //    }

    public PersonContactModel findByIdOrThrow(Long id) {
        return personContactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contato não encontrado!"));
    }

    public Boolean existsById(Long id) {
        return personContactRepository.findById(id).isPresent();
    }

    public List<PersonContactModel> list() {
        return personContactRepository.findAll();
    }

    public PersonContactModel create(PersonContactParams params, PersonModel personModel) {

        if (!isValidEmail(params.email())) {
            throw new IllegalArgumentException("Sintaxe de email incorreta!");
        }

        PersonContactModel personContact = new PersonContactModel(params.name(), params.phone(),
                params.email(), personModel);

        return personContactRepository.save(personContact);
    }

    public PersonContactModel update(PersonContactParams params) {

        if (!isValidEmail(params.email())) {
            throw new IllegalArgumentException("Sintaxe de email incorreta!");
        }

        PersonContactModel personContact = findByIdOrThrow(params.id());
        personContact.update(params.name(), params.phone(),
                params.email());

        return personContactRepository.save(personContact);
    }

    public void delete(Long id) {
        PersonContactModel personContact = findByIdOrThrow(id);
        personContactRepository.delete(personContact);
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
