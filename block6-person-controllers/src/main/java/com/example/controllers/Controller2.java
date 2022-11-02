package com.example.controllers;


import com.example.interfaces.ICityService;
import com.example.interfaces.IPersonService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(value="/controller2")
public class Controller2 {

    @Autowired
    @Qualifier("PersonServiceImplBean")
    private IPersonService personService;

    @GetMapping("/getPerson")
    @ResponseBody
    public ResponseEntity<String> getPersonService() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("name", personService.getPersonRepo().getName());
        headers.add("population", personService.getPersonRepo().getPopulation());
        headers.add("age", String.valueOf(personService.getPersonRepo().getAge()));
        log.info("Sending object Person: " + headers);
        return new ResponseEntity<>(HttpStatus.OK + " " + headers + " ", HttpStatus.OK);
    }

    @Autowired
    private ICityService cityService;

    @GetMapping("/getCitys")
    @ResponseBody
    public ResponseEntity<String> getCitys() {
        // El método devuelve una lista con todas las ciudades disponibles, ver el repositorio con la documentación.
        // Contiene métodos para serializar objetos y convertirlos en tipo JSON.
        // https://www.baeldung.com/gson-list
        String jsonString = new Gson().toJson(cityService.getCitys());
        log.info("Sending object Person: " + jsonString);
        return new ResponseEntity<>(HttpStatus.OK + " Citys: " + jsonString + " ", HttpStatus.OK);
    }
}
