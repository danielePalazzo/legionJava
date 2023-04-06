package org.generation.italy.legion.model.services.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.entities.CourseEdition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AbstractCurriculumService {

    List<Course> findCoursesByTitleContains(String part) throws DataException;
    boolean adjustActiveCourses(int numActive) throws DataException; //se corsi attivi > numActive disattiva i più vecchi
    Iterable<Course> findByTitleAndIsActiveAndMinEdition(String part,boolean status,int minEditions) throws DataException;
    Iterable<Course> findByTitleAndIsActive(String part,boolean status) throws DataException;
    Iterable<Course> findByTitleAndMinEdition(String part,int minEditions) throws DataException;

    double getTotalCost();
    Optional<CourseEdition> findFirstByOrderByCostDesc() throws DataException;
    double findAverageCost();
    Iterable<Double> findCostByCost();
    Iterable<CourseEdition> findByCourse(long courseId);
    Iterable<CourseEdition> findByCourseTitleContainsAndStartedAtBetween(String titlePart, LocalDate startAt, LocalDate endAt);
    Iterable<CourseEdition> findMedian();
    Optional<Double> getCourseEditionCostMode();
}
