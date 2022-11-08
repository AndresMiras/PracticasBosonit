package com.example.beanconfiguration;

import com.example.service.PersonServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

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

    //Método que se encarga de revisar el path que se recoge del http request y nos devuelve el Bean cuyo Qualifier coincida con el valor dado en la petición.
    //Para implementarlo en vez de usar los @Qualifiers y @Autowired, tendríamos que declarar 3 variables en esta clase con su @Qualifier. Una vez hecho eso podríamos poner el @Qualifier a cada Bean para
    //indicarle a cual variable debe acceder. Luego podríamos acceder al contenido, globalmente.
//    public PersonServiceImpl getBean(String bean) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
//        Field selectedBean = Class.forName("com.example.beanconfiguration.beanconfiguration").getDeclaredField(bean);
//        PersonServiceImpl persona = (PersonServiceImpl) selectedBean.get(this);
//        return persona;
//    }

}
