package org.generation.italy.legion.controllers;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.services.abstractions.AbstractDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {
    private AbstractDidacticService service;

    @Autowired
    public CourseController(AbstractDidacticService service) {
        this.service = service;
        System.out.println(this.service.getClass().getName());
    }
    @GetMapping("/index")
    public String showCourses(Model m){  // Model è un oggetto che trasferisce dati tra il Controller e la View
        try {
            List<Course> courseList = service.findAllCourses();
            m.addAttribute("courses", courseList);
            return "courses";
        } catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }
    @GetMapping("/findCourseById")
    public String findById(Model m, long courseId){
        try {
            Optional<Course> c = service.findCourseById(courseId);
            Course found = c.orElse(new Course());
            m.addAttribute("course", found);
            return "course_detail";
        } catch (DataException e) {
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }
}
