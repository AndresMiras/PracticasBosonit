package com.example.block7crudvalidation.service;

import com.example.block7crudvalidation.entities.PersonEntity;

import java.io.FileNotFoundException;
import java.util.List;

public interface IPersonService {
    void createPerson(PersonEntity user);
    PersonEntity updatePerson(PersonEntity user) throws FileNotFoundException, NoSuchFieldException, IllegalAccessException;
    PersonEntity getPersonById(Long id) throws FileNotFoundException;
    PersonEntity getPersonByName(String name) throws FileNotFoundException;

    List<PersonEntity> getAllPerson();
    void deletePerson(Long id);
}
