package org.generation.italy.legion.controllers;

import org.generation.italy.legion.model.services.abstractions.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;

public class CrudController<T> {
    private AbstractCrudService<T> service;
    private Class<?> entityClass;

    @Autowired
    public CrudController(AbstractCrudService<T> service){
        this.service = service;
    }
}
