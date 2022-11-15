package com.example.block7crudvalidation.person.repository;

import com.example.block7crudvalidation.person.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// It is important to take into account the type of variable of the Entity class, the value of our id is of type Long and it must be like that when extending the interface.
public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {

    // These methods auto-implement the JPA Query functionality.
    Optional<List<PersonEntity>> findByName(String name);

//    @Query(value = "SELECT * FROM Person p where p.name = ?1", nativeQuery = true )
//    List<PersonEntity> findbyName(String name);
}
