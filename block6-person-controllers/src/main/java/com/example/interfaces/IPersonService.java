package com.example.interfaces;

import com.example.repository.PersonRepoImpl;

public interface IPersonService {
    String register();
    void createPerson(String name, String population, int age);
    IPersonRepo getPersonRepo();
}
