package com.example.block7crudvalidation.base.infraestructure.controller;

import com.example.block7crudvalidation.base.entity.Base;
import com.example.block7crudvalidation.base.service.BaseServiceImpl;
import com.example.block7crudvalidation.exceptions.CustomError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


// Esta clase hereda los servicios de BaseController
public abstract class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long> {

    @Autowired
    protected S service;

    // Estos métodos no devuelven DTOs, no los usaré en todas las entidades con respecto a sus end points.

    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public Object getAll() {
        return CustomError.buildWithObject(200, "All ", service.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Object getOne(@PathVariable Long id) {
        return CustomError.buildWithObject(200, "Id:" + id + " was found!", service.findById(id));
    }

    @PostMapping("/insert")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Object save(@Valid @RequestBody E entity) {
        return CustomError.buildWithObject(201, "Entity:" + entity + " was created!", service.save(entity));
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Object update(@PathVariable Long id,@Valid @RequestBody E entity) {
        return CustomError.buildWithObject(201, "Entity:" + entity + " was created!", service.update(id, entity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Object delete(@PathVariable Long id) {
        return CustomError.buildWithObject(204, "Entity with Id:" + id + " was deleted!", service.delete(id));
    }

}
