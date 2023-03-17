package org.generation.italy.legion.model.services.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Course;

import java.util.List;
import java.util.Optional;

public interface AbstractDidacticService {
    List<Course> findAllCourses() throws DataException;
    Optional<Course> findCourseById(long id) throws DataException;

    List<Course> findCoursesByTitleContains(String part) throws DataException;

    Course saveCourse(Course course) throws DataException;

    void updateCourse(Course course) throws EntityNotFoundException,DataException;

    void deleteCourseById(long id) throws EntityNotFoundException,DataException;

    boolean adjustActiveCourses(int numActive) throws DataException; //se corsi attivi > numActive disattiva i più vecchi

}
