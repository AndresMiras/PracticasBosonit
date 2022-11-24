package com.example.block7crudvalidation.base.service;

import com.example.block7crudvalidation.base.entity.Base;

import java.io.Serializable;
import java.util.List;

// Generalización de crud a través de una base CRUD service.
public interface BaseService <E extends Base, ID extends Serializable> {
    public List<E> findAll() throws Exception;
    public E findById(ID id) throws Exception;
    public E save(E entity) throws Exception;
    public E update(ID id, E entity) throws Exception;
    public boolean delete(ID id) throws Exception;
}
