package org.generation.italy.legion.model.services.implementations;

import org.generation.italy.legion.model.data.abstractions.CourseEditionRepository;
import org.generation.italy.legion.model.data.abstractions.TeacherRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractCrudDidacticService;
import org.generation.italy.legion.model.services.abstractions.AbstractDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StandardTeacherDidacticService implements AbstractCrudDidacticService<Teacher>, AbstractDidacticService {
    private TeacherRepository repo;

    @Autowired
    public StandardTeacherDidacticService(TeacherRepository repo){
        this.repo = repo;
    }

    @Override
    public Iterable<Teacher> findByLevel(Level teacherLevel) throws DataException {
        return repo.findByLevel(teacherLevel);
    }

    @Override
    public Iterable<Teacher> findWithSkillAndLevel(long idSkill, Level competenceLevel) {
        return repo.findWithSkillAndLevel(idSkill, competenceLevel);
    }

    @Override
    public Iterable<Teacher> findAll() throws DataException {
        return repo.findAll();
    }

    public Optional<Teacher> findById(long id) throws DataException {
        return repo.findById(id);
    }

    @Override
    public Teacher create(Teacher entity) throws DataException {
        return repo.save(entity);
    }

    @Override
    public void update(Teacher entity) throws EntityNotFoundException, DataException {
        repo.save(entity);
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException, DataException {
        repo.deleteById(id);
    }
}
