package com.example.block7crud.service;

import com.example.block7crud.entity.PersonEntity;
import com.example.block7crud.repository.IPersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Arrays;

@Slf4j
@Service
public class PersonServiceImp implements IPersonService {
    @Autowired
    private IPersonRepository personRepo;

    @Override
    public void createPerson(PersonEntity person) {
        personRepo.save(person);
    }

    @Override
    public PersonEntity updateUser(PersonEntity person) throws NoSuchFieldException, SecurityException, IllegalAccessException {
        personRepo.save(person);
        return person;
    }

    @Override
    public PersonEntity getPersonById(Long id) throws FileNotFoundException {
        return personRepo.findById(id).orElseThrow(() -> new FileNotFoundException("Error FileNotFoundException... Person not found with Id: " + id));
    }

    @Override
    public PersonEntity getPersonByName(String name) throws FileNotFoundException {
        return personRepo.findByName(name).orElseThrow(() -> new FileNotFoundException("Error FileNotFoundException... Person not found with Name: " + name));
    }

    @Override
    public void deletePerson(Long id) {
        personRepo.deleteById(id);
    }
}

