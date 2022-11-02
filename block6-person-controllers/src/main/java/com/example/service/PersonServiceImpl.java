package com.example.service;

import com.example.interfaces.IPersonRepo;
import com.example.interfaces.IPersonService;
import com.example.repository.PersonRepoImpl;
import org.springframework.stereotype.Service;

@Service("PersonServiceImplBean")
public class PersonServiceImpl implements IPersonService {

    private IPersonRepo personRepo;

    // método para crear una nueva persona.
    public void createPerson(String name, String population, int age) {
        personRepo = new PersonRepoImpl(name, population, age);
    }

    public IPersonRepo getPersonRepo() {
        return personRepo;
    }

    @Override
    public String register() {
        return personRepo.register();
    }

}
