package org.generation.italy.legion.model.services.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.CourseEdition;

import java.time.LocalDate;
import java.util.Optional;

public interface AbstractCourseEditionService {

    double getTotalCost();
    Optional<CourseEdition> findFirstByOrderByCostDesc() throws DataException;
    double findAverageCost();
    Iterable<Double> findCostByCost();
    Iterable<CourseEdition> findByCourse(long courseId);
    Iterable<CourseEdition> findByCourseTitleContainsAndStartedAtBetween(String titlePart, LocalDate startAt, LocalDate endAt);
    Iterable<CourseEdition> findMedian();
    Optional<Double> getCourseEditionCostMode();
}
