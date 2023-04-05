package org.generation.italy.legion.model.data.implementations;

import jakarta.persistence.EntityManager;
import org.generation.italy.legion.model.entities.CourseEdition;
import org.hibernate.Session;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

public class HibernateCourseEditionRepository extends GenericCrudRepository<CourseEdition> {
    public HibernateCourseEditionRepository(EntityManager em){
        super(em, CourseEdition.class);
    }

}
