package com.example.block7crud.controllers;

import com.example.block7crud.entity.PersonEntity;
import com.example.block7crud.service.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintDefinitionException;
import javax.validation.Valid;
import java.io.FileNotFoundException;

@Slf4j
@Component
@RestController
@RequestMapping(value="/person") // Hará la conversión del objeto Java en la respuesta http.
public class UserControllerCrud {

    @Autowired
    private IPersonService personServ;

    // http://localhost:8080/person/14
    @GetMapping(value="/{id}")
    public PersonEntity getPersonById(@Valid @PathVariable Long id) throws FileNotFoundException {

        return personServ.getPersonById(id);
    }

    // http://localhost:8080/person/name/Edwin
    @GetMapping(value="name/{name}")
    public PersonEntity getPersonbyName(@Valid @PathVariable String name) throws FileNotFoundException {
        return personServ.getPersonByName(name);
    }

    // Usar un método post con un formato de ejemplo JSON {id=13, name="Manuel", address=puerta Nº2}
    @PostMapping(value="/insert")
    public void insertUser(@Valid @RequestBody PersonEntity person, BindingResult br){
        // looking for entity errors with BindingResult.
        if(br.hasErrors()) throw new ConstraintDefinitionException(br.getAllErrors().get(0).getDefaultMessage());
        try {
            personServ.createPerson(person);
            log.info("Person insert success!: " + person.toString());
        } catch (Exception e) {
            log.warn("Person insert Error: " + person.toString() + " / " + e);
        }
    }

    // http://localhost:8080/person/update
    @PutMapping(value="/update")
    public ResponseEntity<PersonEntity> updateUser(@Valid @RequestBody PersonEntity person, BindingResult br) {
        // looking for entity errors with BindingResult.
        if(br.hasErrors()) throw new ConstraintDefinitionException(br.getAllErrors().get(0).getDefaultMessage());

        try {
            // If Person doesn't exist, throw a new exception.
            personServ.getPersonById(person.getId());
            // If the user exists and fields aren't blanks or nulls, the user get the update.
            personServ.updateUser(person);
            log.info("Person update success!: " + person);
        } catch (IndexOutOfBoundsException userNotFound) {
            log.warn(userNotFound.getMessage() + person.toString());
        } catch (NoSuchFieldException e) {
            log.warn("Update User Failed, SuchFieldException" + e.getMessage());
        } catch (IllegalAccessException e) {
            log.warn("Update User Failed, IllegalAccessException " + e.getMessage());
        } catch (Exception e) {
            log.warn("Update User Failed, Exception " + e.getMessage());
        }

        return ResponseEntity.ok().body(person);
    }

    // http://localhost:8080/person/delete/14
    @DeleteMapping(value="/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        try {
            personServ.deletePerson(id);
            log.info("Delete Person success: Id: " + id);
        } catch (Exception e) {
            log.warn("Delete Person Error: Id: " + id + " / " + e);
        }
    }
}
