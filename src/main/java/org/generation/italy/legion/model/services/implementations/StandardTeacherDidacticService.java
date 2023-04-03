package org.generation.italy.legion.model.services.implementations;

import org.aspectj.weaver.loadtime.ConcreteAspectCodeGen;
import org.generation.italy.legion.model.data.abstractions.TeacherRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractCrudDidacticService;
import org.generation.italy.legion.model.services.abstractions.AbstractTeacherDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StandardTeacherDidacticService implements AbstractCrudDidacticService<Teacher>, AbstractTeacherDidacticService {
    private TeacherRepository repo;

    @Autowired
    public StandardTeacherDidacticService(TeacherRepository repo){
        this.repo = repo;
    }

    @Override
    public Iterable<Teacher> findWithCompetenceByLevel(Level teacherLevel) throws DataException {
        return repo.findWithCompetenceByLevel(teacherLevel);
    }

    @Override
    public Iterable<Teacher> findWithSkillAndLevel(long idSkill, Level competenceLevel) {
        return repo.findWithSkillAndLevel(idSkill, competenceLevel);
    }

    @Override
    public List<Teacher> findAll() throws DataException {
        return repo.findAll();
    }

    @Override
    public Optional<Teacher> findById(long id) throws DataException {
        return repo.findById(id);
    }

    @Override
    public Teacher create(Teacher entity) throws DataException {
        return repo.create(entity);
    }

    @Override
    public void update(Teacher entity) throws EntityNotFoundException, DataException {
        repo.update(entity);
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException, DataException {
        repo.deleteById(id);
    }
}
