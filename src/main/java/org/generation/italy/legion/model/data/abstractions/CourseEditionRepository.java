package org.generation.italy.legion.model.data.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.CourseEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface CourseEditionRepository extends GenericRepository<CourseEdition>, CourseEditionRepositoryCustom {

    @Query("SELECT sum(cost) FROM CourseEdition")
    double getTotalCost();

    //ritorna la CourseEdition più costosa
    Optional<CourseEdition> findFirstByOrderByCostDesc() throws DataException;

    //ritorna il valore mediuo dei costi delle CourseEdition
    @Query("SELECT avg(ce.cost) FROM CourseEdition ce")
    double findAverageCost();
    @Query("SELECT c.duration FROM CourseEdition ce JOIN ce.course c")
    Iterable<Double> findAllDuration();

    Iterable<CourseEdition> findByCourseId(long courseId);

    Iterable<CourseEdition> findByCourseTitleContainsAndStartedAtBetween(String titlePart,
                                                                         LocalDate startAt, LocalDate endAt);

    Iterable<CourseEdition> findMedian();
    Optional<Double> getCourseEditionCostMode();

}