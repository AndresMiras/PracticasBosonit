package com.example.beanconfiguration;

import com.example.service.PersonServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonBeansConfig {

    @Bean
    public PersonServiceImpl bean1() {
        PersonServiceImpl personService = new PersonServiceImpl();
        personService.createPerson("Santiago", "Madrid", 22);
        return personService;
    }

    @Bean
    public PersonServiceImpl bean2() {
        PersonServiceImpl personService = new PersonServiceImpl();
        personService.createPerson("Berto", "Coruña", 36);
        return personService;
    }

    @Bean
    public PersonServiceImpl bean3() {
        PersonServiceImpl personService = new PersonServiceImpl();
        personService.createPerson("Jeremías", "Canarias", 26);
        return personService;
    }

}
