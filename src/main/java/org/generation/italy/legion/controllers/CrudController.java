package org.generation.italy.legion.controllers;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractCrudDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

public class CrudController<T> {
    private AbstractCrudDidacticService<T> service;
    private Class<?> entityClass;

    @Autowired
    public CrudController(AbstractCrudDidacticService<T> service){
        this.service = service;
    }
}
