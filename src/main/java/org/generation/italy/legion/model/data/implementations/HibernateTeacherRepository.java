package org.generation.italy.legion.model.data.implementations;

import org.generation.italy.legion.model.data.abstractions.TeacherRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.hibernate.Session;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import static org.generation.italy.legion.model.data.HibernateConstants.*;

public class HibernateTeacherRepository extends GenericCrudRepository<Teacher> {

    public HibernateTeacherRepository(Session session) {
        super(session, Teacher.class);
    }

    public Iterable<Teacher> findByLevel(Level teacherLevel) throws DataException {
        return session.createSelectionQuery(HQL_FIND_TEACHER_BY_LEVEL, Teacher.class)
                .setParameter("level", teacherLevel).list();
    }

    public Iterable<Teacher> findWithSkillAndLevel(long idSkill, Level competenceLevel) {
        return session.createSelectionQuery(HQL_FIND_TEACHER_BY_SKILL_LEVEL, Teacher.class)
              .setParameter("level", competenceLevel)
              .setParameter("id", idSkill).list();
    }

    //una funzione che trovi tutti i teacher che hanno insegnato a N editionModule
    public Iterable<Teacher> findTeachersByNCourseEdition(int n){
        return session.createSelectionQuery(HQL_FIND_TEACHERS_BY_COURSE_EDITION, Teacher.class)
              .setParameter("n", n).list();
    }

}
