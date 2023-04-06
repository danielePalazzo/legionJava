package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.dtos.SimpleTeacherDto;
import org.generation.italy.legion.dtos.TeacherDto;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractCrudService;
import org.generation.italy.legion.model.services.abstractions.AbstractDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/teachers") //davanti al prefisso dei metodi avremo "api"
public class ApiTeacherController {
    private AbstractDidacticService teacherService;
    private AbstractCrudService<Teacher> crudService;

    @Autowired
    public ApiTeacherController(AbstractDidacticService teacherService, AbstractCrudService<Teacher> crudService) {
        this.teacherService = teacherService;
        this.crudService = crudService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> findById(@PathVariable long id){
        try {
            Optional<Teacher> teacherOp = crudService.findById(id);
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
    public ResponseEntity<Iterable<SimpleTeacherDto>> findWithSkillAndLevel(@RequestParam(required = false) Long skillId,
                                                                            @RequestParam(required = false) Level level){
        try {
            Iterable<Teacher> teacherIt = teacherService.findWithSkillAndLevel(skillId, level);
            return ResponseEntity.ok().body(SimpleTeacherDto.fromEntityIterator(teacherIt, skillId));
        } catch (DataException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
