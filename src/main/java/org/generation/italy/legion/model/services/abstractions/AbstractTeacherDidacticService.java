package org.generation.italy.legion.model.services.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;

public interface AbstractTeacherDidacticService extends AbstractCrudDidacticService<Teacher>{
    Iterable<Teacher> findByLevel(Level teacherLevel) throws DataException;
    Iterable<Teacher> findWithSkillAndLevel(long idSkill, Level competenceLevel) throws DataException;
}
