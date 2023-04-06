package org.generation.italy.legion.controllers;

import jakarta.validation.Valid;
import org.generation.italy.legion.model.data.abstractions.GenericRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.services.abstractions.AbstractCurriculumService;
import org.generation.italy.legion.model.services.implementations.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;


public class CourseController {
    private AbstractCurriculumService service;
    private GenericService<Course> crudService;

    @Autowired
    public CourseController(AbstractCurriculumService service,
                            GenericRepository<Course> courseRepo) {
        this.service = service;
        this.crudService = new GenericService<>(courseRepo);
    }

    @GetMapping("/home")
    public String home(){
            return "home";
    }

    @GetMapping("/findCourseByTitleLike")
    public String findCourseByTitleLike(){
        return "find_course_by_title_like";
    }

    @GetMapping("/findTeacherWithSkillAndLevel")
    public String findTeacherCourseByTitleLike(){
        return "find_course_by_title_like";
    }

    @GetMapping("/showCourseInsertForm")
    public String showForm(Course c){
        return "insert_course";
    }
/*
    @GetMapping("/index")
    public String showCourses(Model m){  // Model è un oggetto che trasferisce dati tra il Controller e la View
        try {
            List<Course> courseList = service.findAll();
            m.addAttribute("courses", courseList);
            return "courses";
        } catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }*/
    @GetMapping("/findCourseById")
    public String findById(Model m, long courseId){
        try {
            Optional<Course> c = crudService.findById(courseId);
            Course found = c.orElse(new Course());
            m.addAttribute("course", found);
            return "course_detail";
        } catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }

    @GetMapping("/saveCourse")
    public String insertCourse(Model m, @Valid Course c, BindingResult result){
        if (result.hasErrors()){
            return "insert_course";
        }
        try {
            crudService.create(c);
            return "home";
        } catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }

    @GetMapping("/findCoursesByTitleContains")
    public String findCoursesByTitleContains(Model m, String part){
        try {
            List<Course> courseList = service.findCoursesByTitleContains(part);
            m.addAttribute("courses", courseList);
            return "result_by_title_like";
        } catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }

//    @GetMapping("/findCoursesByTitleContains")
//    public String findCourse(Model m, String part){
//        try {
//            List<Course> courseList = service.;
//            m.addAttribute("courses", courseList);
//            return "result_by_title_like";
//        } catch (DataException e) {
//            e.printStackTrace();
//            m.addAttribute("error", e.getCause().getMessage());
//            return "error";
//        }
//    }

}
