package com.example.block7crudvalidation.service;

import com.example.block7crudvalidation.entities.PersonEntity;

import java.io.FileNotFoundException;

public interface IPersonService {
    void createPerson(PersonEntity user);
    PersonEntity updateUser(PersonEntity user) throws FileNotFoundException, NoSuchFieldException, IllegalAccessException;
    PersonEntity getPersonById(Long id) throws FileNotFoundException;
    PersonEntity getPersonByName(String name) throws FileNotFoundException;
    void deletePerson(Long id);
}
