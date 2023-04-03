package org.generation.italy.legion.model.services.implementations;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.data.implementations.GenericCrudRepository;
import org.generation.italy.legion.model.services.abstractions.AbstractCrudDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class GenericCrudDidacticService<T> implements AbstractCrudDidacticService<T> {

    private GenericCrudRepository<T> repo; // field injection = inietta sul campo

    @Autowired
    public GenericCrudDidacticService(GenericCrudRepository<T> repo){ // constructor injection = inietta sul costruttore (è opzionale l'annotazione)
        this.repo = repo;       //iniezione delle dipendenze (tecnica) -> inversione del controllo (design pattern) o inversione delle dipendenze
        System.out.println(this.repo.getClass().getName());
    }

    public void setRepo(GenericCrudRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<T> findAll() throws DataException {
        return repo.findAll();
    }

    @Override
    public Optional<T> findById(long id) throws DataException {
        return repo.findById(id);
    }

    @Override
    @Transactional
    public T create(T entity) throws DataException {
        return repo.create(entity);
    }

    @Override
    public void update(T entity) throws EntityNotFoundException, DataException {
        repo.update(entity);
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException, DataException {
        repo.deleteById(id);
    }
}
