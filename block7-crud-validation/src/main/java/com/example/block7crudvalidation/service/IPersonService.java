package com.example.block7crudvalidation.service;

import com.example.block7crudvalidation.entities.PersonEntity;

import javax.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface IPersonService {
    void createPerson(PersonEntity user);
    PersonEntity updatePerson(PersonEntity user);
    PersonEntity getPersonById(Long id) throws EntityNotFoundException;
    List<PersonEntity> getPersonByName(String name);
    List<PersonEntity> getAllPerson();
    void deletePerson(Long id);
}
