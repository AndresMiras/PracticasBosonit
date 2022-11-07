package com.example.block7crud.controllers;

import com.example.block7crud.entity.PersonEntity;
import com.example.block7crud.service.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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
    public PersonEntity getPersonbyId(@PathVariable Long id) throws FileNotFoundException {
        return personServ.getPersonById(id);
    }

    // http://localhost:8080/person/name/Edwin
    @GetMapping(value="name/{name}")
    public PersonEntity getPersonbyName(@PathVariable String name) throws FileNotFoundException {
        return personServ.getPersonByName(name);
    }

    // Usar un método post con un formato de ejemplo JSON {id=13, name="Manuel", address=puerta Nº2}
    @PostMapping(value="/insert")
    public void insertUser(@RequestBody PersonEntity person){
        personServ.createPerson(person);
        log.info("Person insert success!: " + person.toString());
    }

    // http://localhost:8080/person/update
    @PutMapping(value="/update")
    public PersonEntity updateUser( @RequestBody PersonEntity person) throws Exception {

        try {
            //Si el usuario no existe se lanza excepcion.
            personServ.getPersonById(person.getId());
            //Si existe y los campos no son blanks o nulls, el usuario se actualiza.
            personServ.updateUser(person);
            log.info("Person update success!: " + person);
        } catch (FileNotFoundException userNotFound) {
            log.warn(userNotFound.getMessage() + person.toString());
        } catch (NoSuchFieldException e) {
            log.warn("Update User Failed, SuchFieldException" + e.getMessage());
        } catch (IllegalAccessException e) {
            log.warn("Update User Failed, IllegalAccessException " + e.getMessage());
        }

        return person;
    }

    // http://localhost:8080/person/delete/14
    @DeleteMapping(value="/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        personServ.deletePerson(id);
        log.info("Delete Person success: Id: " + id);
    }
}
