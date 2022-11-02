package com.example.repository;

import com.example.interfaces.IPersonRepo;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepoImpl implements IPersonRepo {

    private String name;
    private String population;
    private int age;

    // Se genera un constructor vacío para que al iniciar el contenedor bean, no de error al pedir los atributos de clase, de esta forma al estar vacío logro la instancia
    public PersonRepoImpl() {
    }

    public PersonRepoImpl(String name, String population, int age) {
        this.name = name;
        this.population = population;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPopulation() {
        return population;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String register() {
        return "Person: " + name + " is registered";
    }

    @Override
    public String toString() {
        return "PersonRepoImpl{" +
                "name='" + name + '\'' +
                ", population='" + population + '\'' +
                ", age=" + age +
                '}';
    }
}
