package org.generation.italy.legion.model.services.implementations;

import org.generation.italy.legion.model.data.abstractions.CourseEditionRepository;
import org.generation.italy.legion.model.data.abstractions.CourseRepository;
import org.generation.italy.legion.model.data.abstractions.TeacherRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.entities.CourseEdition;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StandardDidacticService implements AbstractDidacticService {
    //@Autowired
    private CourseRepository courseRepo; // field injection = inietta sul campo
    private CourseEditionRepository courseEditionRepo;
    private TeacherRepository teacherRepo;

    @Autowired
    public StandardDidacticService(CourseRepository courseRepo,
                                   CourseEditionRepository courseEditionRepo,
                                   TeacherRepository teacherRepo) {
        this.courseRepo = courseRepo;
        this.courseEditionRepo = courseEditionRepo;
        this.teacherRepo = teacherRepo;
    }

    //    @Autowired
    public void setCourseRepo(CourseRepository courseRepo) { // setter injection
        this.courseRepo = courseRepo;
    }

    @Override
    public List<Course> findCoursesByTitleContains(String part) throws DataException {
        return courseRepo.findByTitleContains(part);
    }

    @Override
    public boolean adjustActiveCourses(int numActive) throws DataException {
        //chiama il repository per scoprire quanti corsi attivi esistono
        //se i corsi attivi sono <= numActive ritorniamo false per segnalare
        //che non è stato necessario apportare alcuna modifica
        //altrimenti chiameremo un metodo sul repository che cancella gli
        //n corsi più vecchi
        int actives = courseRepo.countActiveCourses();
        if (actives <= numActive) {
            return false;
        }
        courseRepo.deactivateOldest(actives - numActive);
        return true;
    }

    @Override
    public Iterable<Course> findByTitleAndIsActiveAndMinEdition(String part, boolean status, int minEditions) throws DataException {
        return courseRepo.findByTitleAndIsActiveAndMinEdition(part,status,minEditions);
    }

    @Override
    public Iterable<Course> findByTitleAndIsActive(String part, boolean status) throws DataException {
        return courseRepo.findByTitleAndIsActive(part,status);
    }

    @Override
    public Iterable<Course> findByTitleAndMinEdition(String part, int minEditions) throws DataException {
        return courseRepo.findByTitleAndMinEdition(part,minEditions);
    }

    @Override
    public double getTotalCost() {
        return courseEditionRepo.getTotalCost();
    }

    @Override
    public Optional<CourseEdition> findFirstByOrderByCostDesc() throws DataException {
        return courseEditionRepo.findFirstByOrderByCostDesc();
    }

    @Override
    public double findAverageCost() {
        return courseEditionRepo.findAverageCost();
    }

    @Override
    public Iterable<Double> findCostByCost() {
        return courseEditionRepo.findAllDuration();

    }

    @Override
    public Iterable<CourseEdition> findByCourse(long courseId) {
        return courseEditionRepo.findByCourseId(courseId);
    }

    @Override
    public Iterable<CourseEdition> findByCourseTitleContainsAndStartedAtBetween(String titlePart, LocalDate startAt, LocalDate endAt) {
        return courseEditionRepo.findByCourseTitleContainsAndStartedAtBetween(titlePart,startAt,endAt);
    }

    @Override
    public Iterable<CourseEdition> findMedian() {
        return courseEditionRepo.findMedian();
    }

    @Override
    public Optional<Double> getCourseEditionCostMode() {
        return courseEditionRepo.getCourseEditionCostMode();
    }

    @Override
    public Iterable<Teacher> findByLevel(Level teacherLevel) throws DataException {
        return teacherRepo.findByLevel(teacherLevel);
    }

    @Override
    public Iterable<Teacher> findWithSkillAndLevel(long idSkill, Level competenceLevel) throws DataException {
        return teacherRepo.findWithSkillAndLevel(idSkill, competenceLevel);
    }
}
