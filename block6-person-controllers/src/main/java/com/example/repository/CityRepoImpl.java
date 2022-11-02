package com.example.repository;

import com.example.interfaces.ICityRepo;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepoImpl implements ICityRepo {
    private String name;
    private int population;

    public CityRepoImpl() {
    }

    public CityRepoImpl(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public CityRepoImpl(CityRepoImpl cityRepo) {
        this.name = cityRepo.getName();
        this.population = cityRepo.getPopulation();
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }


}
