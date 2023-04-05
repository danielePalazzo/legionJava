package org.generation.italy.legion.model.data.abstractions;

import org.generation.italy.legion.model.entities.CourseEdition;
import org.generation.italy.legion.model.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CourseEditionRepository extends JpaRepository<CourseEdition,Long> {

    @Query("SELECT sum(cost) FROM CourseEdition")
    double getTotalCost();
    /*
    Optional<CourseEdition> findMostExpensive();
    double findAverageCost();
    Iterable<Double> findAllDuration();
    Iterable<CourseEdition> findByCourse(long courseId);
    Iterable<CourseEdition> findByCourseTitleAndPeriod(String titlePart,
                                                       LocalDate startAt, LocalDate endAt);
    Iterable<CourseEdition> findMedian();
    Optional<Double> getCourseEditionCostMode();*/

}