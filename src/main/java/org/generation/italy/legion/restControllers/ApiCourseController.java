package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.dtos.CourseDto;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.services.abstractions.AbstractCourseDidacticService;
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
    public ResponseEntity<List<CourseDto>> findByTitleContains(@RequestParam(required = false) String part){
        try{
            List<Course> result = service.findCoursesByTitleContains(part);
            return ResponseEntity.ok().body(CourseDto.fromEntityList(result));
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping()
    public ResponseEntity<Iterable<CourseDto>> findWithSkillAndLevel(String titleLike, Boolean isActive, Integer minEdition){
        try{
            Iterable<Course> itCourse = service.findByTitleAndIsActiveAndMinEditions(titleLike, isActive, minEdition);
            return ResponseEntity.ok().body(CourseDto.fromEntityIterable(itCourse));
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<Iterable<CourseDto>> genericSearch(String part,
                                                             @RequestParam(required = false) Boolean isActive,
                                                             @RequestParam(required = false) Integer minEdition){
        try{
            Iterable<Course> result = null;
            if(isActive != null && isActive && minEdition != null){
                result = service.findByTitleAndIsActiveAndMinEditions(part, isActive, minEdition);
            } else if(part != null){
                result = service.findCoursesByTitleContains(part);
            }
            return ResponseEntity.ok().body(CourseDto.fromEntityIterable(result));
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
