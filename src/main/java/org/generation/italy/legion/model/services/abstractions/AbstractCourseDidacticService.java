package org.generation.italy.legion.model.services.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;

import java.util.List;

public interface AbstractCourseDidacticService extends AbstractCrudDidacticService<Course>{

    List<Course> findCoursesByTitleContains(String part) throws DataException;
    boolean adjustActiveCourses(int numActive) throws DataException;
    Iterable<Course> findByTitleAndIsActiveAndMinEditions(String part, boolean status, int minEditions) throws DataException;
}
