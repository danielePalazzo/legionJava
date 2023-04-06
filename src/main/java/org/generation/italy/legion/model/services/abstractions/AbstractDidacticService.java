package org.generation.italy.legion.model.services.abstractions;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.entities.CourseEdition;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AbstractDidacticService extends AbstractCourseService,
                                                    AbstractCourseEditionService,
                                                    AbstractTeacherService{

}
