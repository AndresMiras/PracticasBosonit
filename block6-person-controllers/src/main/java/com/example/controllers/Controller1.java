package com.example.controllers;

import com.example.interfaces.ICityService;
import com.example.interfaces.IPersonService;
import com.example.repository.CityRepoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value="/controller1")
public class Controller1 {

    @Autowired
    @Qualifier("PersonServiceImplBean")
    private IPersonService personService;

    @GetMapping(value = "/addPerson")
    @ResponseBody
    public ResponseEntity<String> addPerson(@RequestHeader("name") String name, @RequestHeader("population") String population, @RequestHeader("age") int age) {
        personService.createPerson(name, population, age);
        HttpHeaders headers = new HttpHeaders();
        headers.add("name", personService.getPersonRepo().getName());
        headers.add("population", personService.getPersonRepo().getPopulation());
        headers.add("age", String.valueOf(personService.getPersonRepo().getAge()));
        log.info(personService.register() + " " + headers);
        return new ResponseEntity<>(HttpStatus.OK + " " + headers + " ", HttpStatus.OK);
    }

    @Autowired
    private ICityService cityService;

    @RequestMapping(value = "/addCity", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity addPerson(@RequestBody CityRepoImpl cityPosted){
        cityService.add(cityPosted);
        HttpHeaders headers = new HttpHeaders();
        headers.add("name", cityService.getLastCityImp().getName());
        headers.add("population", String.valueOf(cityService.getLastCityImp().getPopulation()));
        log.info(cityService.register() + String.valueOf(headers));
        return new ResponseEntity<>(HttpStatus.OK + " " + headers + " ", HttpStatus.OK);
    }

}
