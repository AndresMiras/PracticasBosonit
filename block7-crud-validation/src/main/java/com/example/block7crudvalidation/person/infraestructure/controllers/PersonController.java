package com.example.block7crudvalidation.person.infraestructure.controllers;

import com.example.block7crudvalidation.person.infraestructure.dto.PersonDTORequest;
import com.example.block7crudvalidation.exceptions.CustomError;
import com.example.block7crudvalidation.person.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

// See errors in controllerAdvisor, exceptions.

@Slf4j
@Component
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personServ;

    // http://localhost:8080/person/14
    // The method returns a refined personDTO object that contains only non-sensitive information. If the person doesn't exist throw error.
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok().body(CustomError.buildWithObject(200, "Person found, success.", personServ.getPersonById(id)));
    }

    @GetMapping(value = "/full/{id}")
    public ResponseEntity<Object> getFullPersonById(@PathVariable Long id) {
        return ResponseEntity.ok().body(CustomError.buildWithObject(200, "Person found, success.", personServ.getFullPersonById(id)));
    }

    // http://localhost:8080/person/name/Edwin
    // The method returns a refined personDTO object that contains only non-sensitive information.
    @GetMapping(value = "name/{name}")
    public ResponseEntity<Object> getPersonByName(@PathVariable String name) {
        return ResponseEntity.ok().body(CustomError.buildWithObject(200, "Person found, success.", personServ.getPersonByName(name)));
    }

    // http://localhost:8080/person/insert
    // This method try to create one person.
    @PostMapping(value = "/insert")
    public ResponseEntity<Object> insertPerson(@RequestBody PersonDTORequest person) {
        log.info(person.toString());
       personServ.createPerson(person);
       return ResponseEntity.status(201).body(CustomError.build(201,"Person created, Success!"));
    }

    // http://localhost:8080/person/update
    // The method returns a refined personDTO object that contains only non-sensitive information.
    @PutMapping(value = "/update")
    public ResponseEntity<Object> updatePerson(@RequestBody PersonDTORequest person) {

        // If the user doesn't exist or fields aren't blanks / nulls, throws exception.
        personServ.updatePerson(person);
        return ResponseEntity.status(201).body(CustomError.build(201,"Person created, Success!"));
    }

    // http://localhost:8080/person/delete/14
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable Long id) {
        personServ.deletePerson(id);
        return ResponseEntity.status(202).body("Delete Person success: Id: " + id);
    }

    // http://localhost:8080/person/all
    // These method get all people in DB and convert to personDTO.
    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAll() {
        // The following method finds all saved people and returns a list of DTO people.
        return ResponseEntity.ok().body(CustomError.buildWithObject(200,"Person objects was found... Success!", personServ.getAllPerson()));
    }
}
