package org.generation.italy.legion.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.entities.CourseEdition;
import org.generation.italy.legion.model.entities.Teacher;

import java.time.LocalDate;
import java.util.stream.StreamSupport;

public class CourseEditionDto {
    private long id;
    private String startedAt;
    private double cost;

    public CourseEditionDto(long id, String startedAt, double cost) {
        this.id = id;
        this.startedAt = startedAt;
        this.cost = cost;
    }

    public static CourseEditionDto fromEntity(CourseEdition ce){
        return new CourseEditionDto(ce.getId(),
                ce.getStartedAt() != null? ce.getStartedAt().toString() : "",
                ce.getCost());
    }

    public static Iterable<CourseEditionDto> fromEntityIterator(Iterable<CourseEdition> t){
        return StreamSupport.stream(t.spliterator(), false)
                .map(CourseEditionDto::fromEntity).toList();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
