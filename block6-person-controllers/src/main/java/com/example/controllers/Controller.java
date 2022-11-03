package com.example.controllers;

import com.example.service.PersonServiceImpl;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/controller")
public class Controller {

    @Autowired
    @Qualifier("bean1")
    private PersonServiceImpl personServiceBean1;

    @Autowired
    @Qualifier("bean2")
    private PersonServiceImpl personServiceBean2;

    @Autowired
    @Qualifier("bean3")
    private PersonServiceImpl personServiceBean3;


    // http://localhost:8080/controller/bean?bean=bean1
    @GetMapping("/bean")
    @ResponseBody
    public ResponseEntity<String> getBean(@RequestParam String bean) {
        String jsonString = "";

        // Recogemos el nombre del Bean que vamos a usar.

        // Contiene métodos para serializar objetos y convertirlos en tipo JSON.
        // https://www.baeldung.com/gson-list

        // Dependiendo del parámetro que le pasemos, crea un String con formato JSON, en función de si existe el Bean o no.
        // En caso de no existir devuelve el error.
        switch (bean) {
            case "bean1":
                jsonString = new Gson().toJson(personServiceBean1.getPersonRepo());
                break;
            case "bean2":
                jsonString = new Gson().toJson(personServiceBean2.getPersonRepo());
                break;
            case "bean3":
                jsonString = new Gson().toJson(personServiceBean3.getPersonRepo());
                break;
            default:
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY + " Error, not a valid Bean", HttpStatus.FAILED_DEPENDENCY);
        }

        log.info("Sending object Person: " + jsonString);
        return new ResponseEntity<>(HttpStatus.OK + " Citys: " + jsonString + " ", HttpStatus.OK);
    }
}
