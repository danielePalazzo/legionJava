package org.generation.italy.legion.model.data.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;

import java.util.List;

public interface CourseRepository extends AbstractCrudRepository<Course>{
    List<Course> findByTitleContains(String part) throws DataException;
    int countActiveCourses() throws DataException;
    void deactivateOldest(int n) throws DataException;
    boolean adjustActiveCourses(int NumActive) throws DataException;
    Iterable<Course> findByTitleAndIsActiveAndMinEditions(String part, boolean status, int minEditions) throws DataException;
}
