package com.example.block7crud.service;

import com.example.block7crud.entity.PersonEntity;

import java.io.FileNotFoundException;

public interface IPersonService {
    void createPerson(PersonEntity user);
    PersonEntity updateUser(PersonEntity user) throws FileNotFoundException, NoSuchFieldException, IllegalAccessException;
    PersonEntity getPersonById(Long id) throws FileNotFoundException;
    PersonEntity getPersonByName(String name) throws FileNotFoundException;
    void deletePerson(Long id);
}
