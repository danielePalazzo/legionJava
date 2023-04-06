package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.model.data.abstractions.GenericRepository;
import org.generation.italy.legion.model.services.implementations.GenericService;

public abstract class ApiGenericController<T> {
    private GenericService<T> service;

    public ApiGenericController(GenericRepository<T> repository) {
        this.service = new GenericService<T>(repository);
    }
    /*
    @GetMapping()
    public ResponseEntity<Iterable<T>> findAll(){
        service.findAll();
    }*/
}
