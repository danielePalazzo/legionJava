package org.generation.italy.legion.model.services.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;

import java.util.Optional;

public interface AbstractCrudService<T>{
    Iterable<T> findAll() throws DataException;
    Optional<T> findById(long id) throws DataException;
    T create(T entity) throws DataException;
    void update(T entity) throws EntityNotFoundException, DataException;
    void deleteById(long id) throws EntityNotFoundException, DataException;
}
