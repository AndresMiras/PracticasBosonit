package com.example.block7crudvalidation.person.service;

import com.example.block7crudvalidation.person.entity.Person;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonDTORequest;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonDTOResponse;

import java.util.List;

public interface PersonService {
    Person createPerson(PersonDTORequest user);
    void updatePerson(PersonDTORequest user);
    PersonDTOResponse getPersonById(Long id);
    public Object getFullPersonById(Long id);
    PersonDTOResponse getPersonByName(String name);
    List<PersonDTOResponse> getAllPerson();
    void deletePerson(Long id);
}
