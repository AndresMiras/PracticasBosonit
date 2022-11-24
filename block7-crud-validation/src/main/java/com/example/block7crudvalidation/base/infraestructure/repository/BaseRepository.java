package com.example.block7crudvalidation.base.infraestructure.repository;

import com.example.block7crudvalidation.base.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

// Esta interfaz extiende de Base entidad, con E y ID creamos una interfaz modelo que implementara automáticamente en las clases que la extiendan, el modelo de JpaRepository
// con el tipo de Entidad y el tipo de Id que se use, siempre que se siga el modelo genérico de Base.
@NoRepositoryBean
public interface BaseRepository<E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {
}
