package com.example.block7crudvalidation.person.service;

import com.example.block7crudvalidation.person.infraestructure.dto.PersonDTORequest;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonDTOResponse;
import com.example.block7crudvalidation.person.entity.Person;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonOutPutFullStudentDTO;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonOutPutFullTeacherDTO;
import com.example.block7crudvalidation.person.infraestructure.repository.PersonRepository;
import com.example.block7crudvalidation.student.entity.Student;
import com.example.block7crudvalidation.student.infraestructure.repository.StudentRepository;
import com.example.block7crudvalidation.teacher.entity.Teacher;
import com.example.block7crudvalidation.teacher.infraestructure.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.block7crudvalidation.exceptions.EntityNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Person createPerson(PersonDTORequest personDTORequest) {

        Person person = new Person();

        // If all fields are correct, person is saved.
        if(personDTORequest.validateData()) {

            // Then person can be created...
            person = personRepo.save(personDTORequest.entity());
            log.info("Person created success: " + personDTORequest);
        };

        return person;
    }

    @Override
    public void updatePerson(PersonDTORequest personDTORequest) {
        if(personRepo.findById(personDTORequest.getId()).isPresent()){
            // If all fields are correct, person is saved.
            if(personDTORequest.validateData()) personRepo.save(personDTORequest.entity());
            log.info("Person updated success: ( " + personDTORequest + " )");
        } else {
            throw new EntityNotFoundException("Person not found with Id: " + personDTORequest.getId());
        }
    }

    @Override
    public PersonDTOResponse getPersonById(Long id) {
        PersonDTOResponse personDTOResponse = new PersonDTOResponse(personRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found with Id: " + id)));
        log.info("Person with id was found: " + personDTOResponse );
        return personDTOResponse;
    }

    @Override
    public Object getFullPersonById(Long id) {
        Person person = personRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found with Id: " + id));

        // Busco si la Id del objeto es un estudiante.

        List<Student> students = studentRepository
                .findAll()
                .stream()
                .filter(student -> student.getPerson().getId().equals(person.getId()))
                .collect(Collectors.toList());

        if(!students.isEmpty()) {
            PersonOutPutFullStudentDTO personOutPutFullStudentDTO = new PersonOutPutFullStudentDTO(person, students.get(0).convertToStudentOutPutDTOFull());
            log.info("Student with id was found: " + personOutPutFullStudentDTO );
            return personOutPutFullStudentDTO;
        };

        // Busco si la Id del objeto es un profesor.

        List<Teacher> teachers = teacherRepository
                .findAll()
                .stream()
                .filter(teacher -> teacher.getPerson().getId().equals(person.getId()))
                .collect(Collectors.toList());

        if(!teachers.isEmpty()) {
            PersonOutPutFullTeacherDTO personOutPutFullTeacherDTO = new PersonOutPutFullTeacherDTO(person, teachers.get(0).convertToTeacherOutPutFullDTO());
            log.info("Teacher with id was found: " + personOutPutFullTeacherDTO );
            return personOutPutFullTeacherDTO;
        };

        return null;
    }

    @Override
    public PersonDTOResponse getPersonByName(String name) {
        Optional<List<Person>> persons = personRepo.findByName(name);
        if(persons.isPresent()) throw new EntityNotFoundException("Person not found with Name: " + name);
        PersonDTOResponse personDTOResponse = new PersonDTOResponse(persons.get().get(0));
        log.info("Person with name was found: " + personDTOResponse );
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

