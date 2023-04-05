package org.generation.italy.legion.model.data.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Iterable<Teacher> findByLevel(Level teacherLevel) throws DataException;

    @Query("from Teacher t where exists(from Competence co where co.level = :level and co.person = t and co.skill.id = :id);")
    Iterable<Teacher> findWithSkillAndLevel(long id, Level level);
}
