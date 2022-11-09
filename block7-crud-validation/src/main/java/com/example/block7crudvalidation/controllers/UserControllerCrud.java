package com.example.block7crudvalidation.controllers;

import com.example.block7crudvalidation.dto.PersonDTO;
import com.example.block7crudvalidation.entities.PersonEntity;
import com.example.block7crudvalidation.service.IPersonService;
import com.example.block7crudvalidation.service.PersonServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RestController
@RequestMapping(value = "/person") // Do the conversion to Java Object auto.
public class UserControllerCrud {

    @Autowired
    private IPersonService personServ;

    // http://localhost:8080/person/14
    // The method returns a refined personDTO object that contains only non-sensitive information.
    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> getPersonbyId(@PathVariable Long id) throws FileNotFoundException {
        return ResponseEntity.ok().body(new PersonDTO(personServ.getPersonById(id)));
    }

    // http://localhost:8080/person/name/Edwin
    @GetMapping(value = "name/{name}")
    public ResponseEntity<PersonDTO> getPersonbyName(@PathVariable String name) throws FileNotFoundException {
        return ResponseEntity.ok().body(new PersonDTO(personServ.getPersonByName(name)));
    }

    // Usar un método post con un formato de ejemplo JSON {id=13, name="Manuel", address=puerta Nº2}
    @PostMapping(value = "/insert")
    public ResponseEntity<PersonDTO> insertUser(@RequestBody PersonEntity person) {
        try {
            if (PersonServiceImp.validatePersonEntity(person)) {
                personServ.createPerson(person);
                log.info("Person insert success!: " + person.toString());
            }
        } catch (NoSuchFieldException e) {
            log.warn(e.getMessage());
            return ResponseEntity.badRequest().body(new PersonDTO(person));
        }
        return ResponseEntity.ok().body(new PersonDTO(person));
    }

    // http://localhost:8080/person/update
    // The method returns a refined personDTO object that contains only non-sensitive information.
    @PutMapping(value = "/update")
    public ResponseEntity<PersonDTO> updateUser(@RequestBody PersonEntity person){
        try {
            if (PersonServiceImp.validatePersonEntity(person)) {
                // If the user exists throws exception.
                personServ.getPersonById(person.getId());
                // If the user exists and fields aren't blanks or nulls, the user get the update.
                personServ.updatePerson(person);
                log.info("Person update success!: " + person);
            }
        } catch (FileNotFoundException userNotFound) {
            log.warn(userNotFound.getMessage() + person);
        } catch (NoSuchFieldException e) {
            log.warn("Update User Failed, SuchFieldException" + e.getMessage());
        } catch (IllegalAccessException e) {
            log.warn("Update User Failed, IllegalAccessException " + e.getMessage());
        }

        return ResponseEntity.ok().body(new PersonDTO(person));
    }

    // http://localhost:8080/person/delete/14
    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        personServ.deletePerson(id);
        log.info("Delete Person success: Id: " + id);
    }

    // http://localhost:8080/person/all
    // These method get all people in DB and convert to personDTO.
    @GetMapping(value = "/all")
    public ResponseEntity<List<PersonDTO>> getAll() {
        List<PersonDTO> personDTOS = new ArrayList<>();

        personServ.getAllPerson().forEach(p -> {
            personDTOS.add(new PersonDTO(p));
        });

        return ResponseEntity.ok().body(personDTOS);
    }
}
