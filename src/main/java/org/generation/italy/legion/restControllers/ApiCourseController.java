package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.dtos.CourseDto;
import org.generation.italy.legion.dtos.SimpleTeacherDto;
import org.generation.italy.legion.dtos.TeacherDto;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.entities.Level;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractCourseDidacticService;
import org.generation.italy.legion.model.services.abstractions.AbstractTeacherDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/courses")
public class ApiCourseController {
    private AbstractCourseDidacticService service;

    @Autowired
    public ApiCourseController(AbstractCourseDidacticService service){
        this.service = service;
    }

//    inserire un metodo che ritorni il corso per titoloLike

//    che il metodo di ricerca supporti più ricerche
//    nel caso in cui uno degli altri parametri di ricerca non sìa presente in nessun corso, che mi ritorni i corsi con i parametri presenti.
//    ci deve essere sempre almeno un paramtetro (titleLike)
//    all'occorrenza creare altre query

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> findById(@PathVariable long id){
        try {
            Optional<Course> courseOp = service.findById(id);
            if(courseOp.isPresent()){
                return ResponseEntity.ok().body(CourseDto.fromEntity(courseOp.get()));
            }
            return ResponseEntity.notFound().build();
        } catch (DataException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping()
    public ResponseEntity<Iterable<CourseDto>> findWithSkillAndLevel(@RequestParam(required = false) String titleLike,
                                                      @RequestParam(required = false) boolean isActive,
                                                      @RequestParam(required = false) int minEdition){
        try{
            Iterable<Course> itCourse = service.findByTitleAndIsActiveAndMinEditions(titleLike, isActive, minEdition);
            return ResponseEntity.ok().body(CourseDto.fromEntityIterable(itCourse));
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
