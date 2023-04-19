package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.dtos.SimpleTeacherDto;
import org.generation.italy.legion.dtos.TeacherDto;
import org.generation.italy.legion.model.data.abstractions.GenericRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractTeachingService;
import org.generation.italy.legion.model.services.implementations.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/teachers") //davanti al prefisso dei metodi avremo "api"
public class ApiTeacherController {
    private AbstractTeachingService teacherService;
    private GenericService<Teacher> crudService;

    @Autowired
    public ApiTeacherController(AbstractTeachingService teacherService,
                                GenericRepository<Teacher> teacherRepo) {
        this.teacherService = teacherService;
        this.crudService = new GenericService<>(teacherRepo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> findById(@PathVariable long id){
        Optional<Teacher> teacherOp = crudService.findById(id);
        if(teacherOp.isPresent()){
            return ResponseEntity.ok().body(TeacherDto.fromEntity(teacherOp.get()));
        }
        return ResponseEntity.notFound().build();
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

    //URI = identificativo di una risorsa
    //URL = identificatore e locatore della posizione(anche logica) della risorsa
    @PostMapping()
    public ResponseEntity<TeacherDto> create(@RequestBody TeacherDto teacherDto){
        try {
            Teacher teacher = teacherDto.toEntity();
            Teacher teacherResult = crudService.create(teacher);
            return  ResponseEntity.created(URI.create("/api/teachers/"+teacherResult.getId())).body(TeacherDto.fromEntity(teacherResult));
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody TeacherDto teacherDto, @PathVariable long id){
        try {
            if (teacherDto.getId() != id){
                return ResponseEntity.badRequest().build();
            }
            Teacher teacher = teacherDto.toEntity();
            crudService.update(teacher);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        try {
            crudService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


}
