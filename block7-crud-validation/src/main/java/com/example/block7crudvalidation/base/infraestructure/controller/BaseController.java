package com.example.block7crudvalidation.base.infraestructure.controller;

import com.example.block7crudvalidation.base.entity.Base;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
// ? = extends Object.

public interface BaseController<E extends Base, ID extends Serializable> {
    public Object getAll();
    public Object getOne(@PathVariable ID id);
    public Object save(@RequestBody E entity);
    public Object update(@PathVariable ID id, @RequestBody E entity);
    public Object delete(@PathVariable ID id);
}
