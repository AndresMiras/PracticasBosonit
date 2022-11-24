package com.example.block7crudvalidation.base.service;

import com.example.block7crudvalidation.base.entity.Base;
import com.example.block7crudvalidation.base.infraestructure.repository.BaseRepository;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {
    protected BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository){
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public E save(E entity) {
        // If all fields are correct, is saved.

        // Then can be created...
        try {
            entity = baseRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<E> findAll(){
        try {
            List<E> entities = baseRepository.findAll();
            return entities;
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) {
        try {
            Optional<E> entity = baseRepository.findById(id);
            return entity.get();
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public E update (ID id, E entity) {
        try {
            Optional<E> entityOpt = baseRepository.findById(id);
            E entityUpdate = baseRepository.save(entityOpt.get());
            return entityUpdate;
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean delete(ID id) {
        if (baseRepository.existsById(id)) {
            baseRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Entity can't be deleted, not exist. Id:" + id);
        }
    }
}
