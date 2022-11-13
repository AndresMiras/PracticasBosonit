package com.example.block7crudvalidation.person.service;

import com.example.block7crudvalidation.person.dto.PersonDTORequest;
import com.example.block7crudvalidation.person.dto.PersonDTOResponse;
import com.example.block7crudvalidation.person.entities.PersonEntity;
import com.example.block7crudvalidation.person.repository.IPersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.block7crudvalidation.exceptions.EntityNotFoundException;

import java.util.*;

@Slf4j
@Service
public class PersonServiceImp implements IPersonService {

    @Autowired
    private IPersonRepository personRepo;

    @Override
    public void createPerson(PersonDTORequest personDTORequest) {
        log.info(personDTORequest.toString());
        // If all fields are correct, person is saved.
        if(personDTORequest.validateData()) {

            // Then person can be created...
            personRepo.save(personDTORequest.getEntity());
            log.info("Person created success: ( " + personDTORequest.getResponseDTO() + " )");
        };
    }

    @Override
    public void updatePerson(PersonDTORequest personDTORequest) {
        if(personRepo.findById(personDTORequest.getId()).isPresent()){
            // If all fields are correct, person is saved.
            if(personDTORequest.validateData()) personRepo.save(personDTORequest.getEntity());
            log.info("Person updated success: ( " + personDTORequest.getResponseDTO() + " )");
        } else {
            throw new EntityNotFoundException("Person not found with Id: " + personDTORequest.getId());
        }
    }

    @Override
    public PersonDTOResponse getPersonById(Long id) {
        PersonDTOResponse personDTOResponse = new PersonDTOResponse(personRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found with Id: " + id)));
        log.info("Person with id was found: ( " + personDTOResponse + " )" );
        return personDTOResponse;
    }

    @Override
    public PersonDTOResponse getPersonByName(String name) {
        Optional<List<PersonEntity>> persons = personRepo.findByName(name);
        if(persons.isPresent()) throw new EntityNotFoundException("Person not found with Name: " + name);
        PersonDTOResponse personDTOResponse = new PersonDTOResponse(persons.get().get(0));
        log.info("Person with name was found: ( " + personDTOResponse + " )" );
        return personDTOResponse;
    }

    @Override
    public List<PersonDTOResponse> getAllPerson() {
        List<PersonDTOResponse> personDTOResponses = new ArrayList<>();
        // Look for a
        personRepo.findAll().forEach(p -> {
            personDTOResponses.add(new PersonDTOResponse(p));
        });

        if(personDTOResponses.isEmpty()) throw new EntityNotFoundException("People collection doesn't exist: ");
        log.info("List of people, has been sent... ");
        return personDTOResponses;
    }

    @Override
    public void deletePerson(Long id) {

        // If person exist we can continue.
        if(personRepo.findById(id).isPresent()) {

            // If all fields are correct, person is saved.
            personRepo.deleteById(id);
            log.info("Person deleted with Id:" + id + " success.");
        } else {
            throw new EntityNotFoundException("Person not found with Id: " + id);
        }
    }

}

