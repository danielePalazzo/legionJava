package org.generation.italy.legion.model.services.abstractions;

import org.generation.italy.legion.model.data.abstractions.CourseEditionRepository;
import org.generation.italy.legion.model.data.abstractions.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface AbstractCourseEditionService {

    double getTotalCost();

}
