package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// It is important to take into account the type of variable of the Entity class, the value of our id is of type Long and it must be like that when extending the interface.
public interface IPersonRepository extends CrudRepository<PersonEntity, Long> {

    // These methods auto-implement the JPA Query functionality.
    Optional<PersonEntity> findByName(String name);
}
