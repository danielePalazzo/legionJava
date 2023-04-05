package org.generation.italy.legion.model.services.implementations;

import org.generation.italy.legion.model.data.abstractions.CourseEditionRepository;
import org.generation.italy.legion.model.services.abstractions.AbstractCourseEditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseEditionService implements AbstractCourseEditionService {

    private CourseEditionRepository repo;

    @Autowired
    public CourseEditionService(CourseEditionRepository repo){
        this.repo = repo;
    }

    @Override
    public double getTotalCost() {
        return repo.getTotalCost();
    }
}
