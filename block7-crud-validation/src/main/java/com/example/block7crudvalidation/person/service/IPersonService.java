package com.example.block7crudvalidation.person.service;

import com.example.block7crudvalidation.person.dto.PersonDTORequest;
import com.example.block7crudvalidation.person.dto.PersonDTOResponse;

import java.util.List;

public interface IPersonService {
    void createPerson(PersonDTORequest user);
    void updatePerson(PersonDTORequest user);
    PersonDTOResponse getPersonById(Long id);
    PersonDTOResponse getPersonByName(String name);
    List<PersonDTOResponse> getAllPerson();
    void deletePerson(Long id);
}
