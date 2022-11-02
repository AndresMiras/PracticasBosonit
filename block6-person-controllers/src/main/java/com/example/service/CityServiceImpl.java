package com.example.service;

import com.example.interfaces.ICityService;
import com.example.repository.CityRepoImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements ICityService {

    private List<CityRepoImpl> cityList = new ArrayList<CityRepoImpl>();

    public void add(CityRepoImpl city) {
        cityList.add(city);
    }


    // Devuelve el objeto Ciudad en el caso de existir. En el caso contrario devuelve un Obj por defecto.
    private static CityRepoImpl cityOptional(CityRepoImpl city) {
        Optional.empty();
        CityRepoImpl defaultCity = new CityRepoImpl("unknown", 0);
        Optional<CityRepoImpl> cityOpt = Optional.of(city);
        return cityOpt.orElse(defaultCity);
    }

    public CityRepoImpl getLastCityImp() {
        Optional.empty();
        int index = cityList.size() - 1;
        Optional<CityRepoImpl> cityOpt = Optional.of(cityList.get(index));
        return cityOptional(cityList.get(index));
    }

    // Devuelve una lista nueva con las diferentes ciudades.
    public List<CityRepoImpl> getCitys(){
        List<CityRepoImpl> cityListClone = new ArrayList<CityRepoImpl>();
        cityList.forEach(city -> {
            cityListClone.add(cityOptional(city));
        });

        return cityListClone;
    }

    public String register() {
        return "Ciudad registrada con éxito: ";
    }
}
