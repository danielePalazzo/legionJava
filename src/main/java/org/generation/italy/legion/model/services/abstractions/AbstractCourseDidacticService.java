package org.generation.italy.legion.model.services.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;

import java.util.List;

public interface AbstractCourseDidacticService extends AbstractCrudDidacticService<Course>{

    List<Course> findCoursesByTitleContains(String part) throws DataException;
    boolean adjustActiveCourses(int numActive) throws DataException; //se corsi attivi > numActive disattiva i più vecchi
    Iterable<Course> findByTitleAndIsActiveAndMinEdition(String part,boolean status,int minEditions) throws DataException;
    Iterable<Course> findByTitleAndIsActive(String part,boolean status) throws DataException;
    Iterable<Course> findByTitleAndMinEdition(String part,int minEditions) throws DataException;

}
