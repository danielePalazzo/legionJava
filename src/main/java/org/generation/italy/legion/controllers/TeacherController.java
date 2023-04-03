package org.generation.italy.legion.controllers;

import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractTeacherDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class TeacherController {
    private AbstractTeacherDidacticService service;

    @Autowired
    public TeacherController(AbstractTeacherDidacticService service){
        this.service = service;
    }

    @GetMapping("/showTeacherInsertForm")
    public String showForm(Teacher t){
        return "insert_course";
    }

    @GetMapping("/findWithSkillAndLevel")
    public String findWithSkillAndLevel(Model m, long id, Level level){
        try {
            Iterable<Teacher> teacherIt = service.findWithSkillAndLevel(id, level);
            m.addAttribute("teachers", teacherIt);
            return "result_with_skill_and_level";
        } catch (DataException e){
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }

    @GetMapping("/findById")
    public String findById(Model m, long id){
        try {
            Optional<Teacher> teacherOp = service.findById(id);
            teacherOp.orElse(new Teacher());
            m.addAttribute("teacher", teacherOp);
            return "result_find_by_id";
        } catch (DataException e){
            e.printStackTrace();
            m.addAttribute("error", e.getCause().getMessage());
            return "error";
        }
    }
}
