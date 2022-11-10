package com.example.block7crudvalidation.service;

import com.example.block7crudvalidation.entities.PersonEntity;
import helper.Compare;
import helper.MyDate;
import com.example.block7crudvalidation.repository.IPersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

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
    public PersonEntity updatePerson(PersonEntity person) {
        return personRepo.save(person);
    }

    @Override
    public PersonEntity getPersonById(Long id) throws EntityNotFoundException {
        return personRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Error EntityNotFoundException... Person not found with Id: " + id));
    }

    @Override
    public List<PersonEntity> getPersonByName(String name) throws EntityNotFoundException {
        return personRepo.findByName(name).orElseThrow(() -> new EntityNotFoundException("Error EntityNotFoundException... Person not found with Name: " + name));
    }

    @Override
    public List<PersonEntity> getAllPerson() {
        return (List<PersonEntity>) personRepo.findAll();
    }

    @Override
    public void deletePerson(Long id) {
        personRepo.deleteById(id);
    }


    // This method analice if the Person Object is Valid.
    public static Boolean validatePersonEntity(PersonEntity person) throws NoSuchFieldException {
        boolean flat = false;

        if(!Compare.isNumber(String.valueOf(person.getId())) && !Objects.isNull(person.getId())){
            throw new NoSuchFieldException("Error, Incorrect Id..., avoid putting a null field: " + person);
        } else if(Objects.isNull(person.getName()) || !(person.getName().length() < 10) || !Compare.isAsciiPrintable(person.getName()) || person.getName().isBlank() ){
            throw new NoSuchFieldException("Error, Incorrect Name..., avoid entering a null field or a string with more than 10 characters: " + person);
        } else if(Objects.isNull(person.getSurname()) || !Compare.isAsciiPrintable(person.getSurname()) || person.getSurname().isBlank() ){
            throw new NoSuchFieldException("Error, Incorrect Surname..., avoid putting a null field: " + person);
        } else if(Objects.isNull(person.getPassword()) || !Compare.isAsciiPrintable(person.getPassword()) || person.getPassword().isBlank() ){
            throw new NoSuchFieldException("Error, Incorrect Password..., avoid putting a null field: " + person);
        } else if(Objects.isNull(person.getCompany_email()) || !Compare.isEmail(person.getCompany_email())){
            throw new NoSuchFieldException("Error, Incorrect Company_email..., avoid putting a null field: " + person);
        } else if(Objects.isNull(person.getPersonal_email()) || !Compare.isEmail(person.getPersonal_email())){
            throw new NoSuchFieldException("Error, Incorrect Personal_email..., avoid putting a null field: " + person);
        } else if(Objects.isNull(person.getAddress()) || !Compare.isAsciiPrintable(person.getAddress()) || person.getAddress().isBlank()){
            throw new NoSuchFieldException("Error, Incorrect Address..., avoid putting a null field: " + person);
        } else if(Objects.isNull(person.getCity()) || !Compare.isAsciiPrintable(person.getCity()) || person.getCity().isBlank()){
            throw new NoSuchFieldException("Error, Incorrect City..., avoid putting a null field: " + person);
        } else if(Objects.isNull(person.getActive())){
            throw new NoSuchFieldException("Error, Incorrect Active..., avoid putting a null field: " + person);
        } else if(Objects.isNull(person.getCreated_date()) || !MyDate.isDateParsableToString(person.getCreated_date(),"yyyy/MM/dd")){
            throw new NoSuchFieldException("Error, Incorrect Created_date..., avoid putting a null field or incorrect Date Object: " + person);
        } else if(Objects.isNull(person.getTermination_date()) || !MyDate.isDateParsableToString(person.getTermination_date(),"yyyy/MM/dd")){
            throw new NoSuchFieldException("Error, Incorrect Termination_date..., avoid putting a null field or incorrect Date Object: " + person);
        } else if(Objects.isNull(person.getImagen_url()) || !Compare.isAsciiPrintable(person.getImagen_url()) || person.getImagen_url().isBlank()){
            throw new NoSuchFieldException("Error, Incorrect Imagen_url..., avoid putting a null field: " + person);
        } else {
            flat = true;
        }

        return flat;
    }
}

