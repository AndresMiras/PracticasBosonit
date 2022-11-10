package com.example.block7crudvalidation.controllers;

import com.example.block7crudvalidation.dto.PersonSimpleDTO;
import com.example.block7crudvalidation.entities.PersonEntity;
import com.example.block7crudvalidation.errors.CustomError;
import com.example.block7crudvalidation.service.IPersonService;
import com.example.block7crudvalidation.service.PersonServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<Object> getPersonById(@PathVariable Long id) throws EntityNotFoundException {

        try {
            return ResponseEntity.ok().body(new PersonSimpleDTO(personServ.getPersonById(id)));
        } catch (EntityNotFoundException e) {
            // Throws a new Custom error in response of don't found coincidence with any id.
            CustomError error404 = new CustomError(HttpStatus.NOT_FOUND, e.getMessage());
            // Save the error in log...
            log.warn(error404.toString());
            return ResponseEntity.status(404).body(error404);
        }
    }

    // http://localhost:8080/person/name/Edwin
    @GetMapping(value = "name/{name}")
    public ResponseEntity<Object> getPersonByName(@PathVariable String name) throws EntityNotFoundException {

        try {
            List<PersonEntity> person = personServ.getPersonByName(name);
            return ResponseEntity.ok().body(new PersonSimpleDTO(person.get(0)));
        } catch (EntityNotFoundException | IndexOutOfBoundsException e) {
            // Throws a new Custom error in response of don't found coincidence with any name.
            CustomError error404 = new CustomError(HttpStatus.NOT_FOUND, e.getMessage());
            // Save the error in log...
            log.warn(error404.toString());
            return ResponseEntity.status(404).body(error404);
        }
    }

    // Usar un método post con un formato de ejemplo JSON {id=13, name="Manuel", address=puerta Nº2}
    @PostMapping(value = "/insert")
    public ResponseEntity<Object> insertPerson(@RequestBody PersonEntity person) {

        try {
            if (PersonServiceImp.validatePersonEntity(person)) {
                personServ.createPerson(person);
                log.info("Person insert success!: " + person.toString());
            }
        } catch (NoSuchFieldException e) {

            // it means that you are making a request that the server understands, but cannot process.
            // One of the fields is incorrect or doesn't exist (422)
            CustomError error422 = new CustomError(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
            log.warn(error422.toString());
            return ResponseEntity.status(422).body(error422);
        }

        // indicates that the request was successful and led to the creation of a resource. The new resource is effectively created before this response is sent.
        // And the new resource is returned in the body of the message, its location is the URL of the request or the content of the Location header
        return ResponseEntity.status(201).body(new PersonSimpleDTO(person));
    }

    // http://localhost:8080/person/update
    // The method returns a refined personDTO object that contains only non-sensitive information.
    @PutMapping(value = "/update")
    public ResponseEntity<Object> updatePerson(@RequestBody PersonEntity person){

        try {
            if (PersonServiceImp.validatePersonEntity(person)) {
                // If the user doesn't exist throws exception.
                personServ.getPersonById(person.getId());
                // If the fields aren't blanks or nulls, user update.
                personServ.updatePerson(person);
                log.info("Person update success!: " + new PersonSimpleDTO(person));
            }
        } catch (EntityNotFoundException e) {

            // Throws a new Custom error in response of don't found coincidence with any name.
            CustomError error404 = new CustomError(HttpStatus.NOT_FOUND, e.getMessage());

            // Save the error in log...
            log.warn(error404.toString());
            return ResponseEntity.status(404).body(error404);
        } catch (NoSuchFieldException e) {

            // it means that you are making a request that the server understands, but cannot process.
            // One of the fields is incorrect or doesn't exist (422)
            CustomError error422 = new CustomError(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
            log.warn(error422.toString());
            return ResponseEntity.status(422).body(error422);
        }

        return ResponseEntity.status(202).body(new PersonSimpleDTO(person));
    }

    // http://localhost:8080/person/delete/14
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable Long id) {
        try {
            personServ.getPersonById(id);
            personServ.deletePerson(id);
            log.info("Delete Person success: Id: " + id);
            return ResponseEntity.status(202).body("Delete Person success: Id: " + id);
        } catch (EntityNotFoundException  e) {

            // Throws a new Custom error in response of don't found coincidence with any name.
            CustomError error404 = new CustomError(HttpStatus.NOT_FOUND, e.getMessage());

            // Save the error in log...
            log.warn(error404.toString());
            return ResponseEntity.status(404).body(error404);
        }
    }

    // http://localhost:8080/person/all
    // These method get all people in DB and convert to personDTO.
    @GetMapping(value = "/all")
    public ResponseEntity<List<PersonSimpleDTO>> getAll() {
        List<PersonSimpleDTO> personDTOS = new ArrayList<>();

        personServ.getAllPerson().forEach(p -> {
            personDTOS.add(new PersonSimpleDTO(p));
        });

        return ResponseEntity.ok().body(personDTOS);
    }
}
