package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.dtos.SimpleTeacherDto;
import org.generation.italy.legion.dtos.TeacherDto;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractTeacherDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "api/teachers") //davanti al prefisso dei metodi avremo "api"
public class ApiTeacherController {
    private AbstractTeacherDidacticService service;

    @Autowired
    public ApiTeacherController(AbstractTeacherDidacticService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> findById(@PathVariable long id){
        try {
            Optional<Teacher> teacherOp = service.findById(id);
            if(teacherOp.isPresent()){
                return ResponseEntity.ok().body(TeacherDto.fromEntity(teacherOp.get()));
            }
            return ResponseEntity.notFound().build();
        } catch (DataException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping()
    public ResponseEntity<Iterable<SimpleTeacherDto>> findWithSkillAndLevel(@RequestParam(required = false) Long skillId, @RequestParam(required = false) Level level){
        try {
            Iterable<Teacher> teacherIt = service.findWithSkillAndLevel(skillId, level);
            return ResponseEntity.ok().body(SimpleTeacherDto.fromEntityIterator(teacherIt, skillId));
        } catch (DataException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
