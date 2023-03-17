package org.generation.italy.legion.model.data.implementations;

import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.entities.WithId;
import org.hibernate.Session;

public class HibernateTestUtils {
    public static long insertCourse(Course c, Session s){
        s.persist(c);
        s.flush();
        return c.getId();
    }

    public static <T extends WithId> long insertEntity(T entity, Session s){
        s.persist(entity);
        s.flush();
        return entity.getId();
    }
}
