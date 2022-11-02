package com.example.interfaces;

import com.example.repository.CityRepoImpl;

import java.util.List;

public interface ICityService {
    void add(CityRepoImpl city);
    public CityRepoImpl getLastCityImp();
    String register();

    List<CityRepoImpl> getCitys();
}
