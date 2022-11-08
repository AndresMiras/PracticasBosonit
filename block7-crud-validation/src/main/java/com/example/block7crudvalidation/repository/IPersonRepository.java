package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// Es importante tener en cuenta el tipo de variable de la clase Entidad, el valor de nuestra id es de tipo Long y debe ser así también al extender la interfaz.
public interface IPersonRepository extends CrudRepository<PersonEntity, Long> {

    // Usando el potencial de JPA es capaz de en combinación con SPRING hacer la consulta SQL y buscar el usuario con el campo "name".
    Optional<PersonEntity> findByName(String name);
}
