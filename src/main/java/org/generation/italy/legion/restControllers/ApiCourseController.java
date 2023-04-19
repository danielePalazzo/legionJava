package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.dtos.CourseDto;
import org.generation.italy.legion.model.data.abstractions.GenericRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.services.abstractions.AbstractCurriculumService;
import org.generation.italy.legion.model.services.implementations.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/courses")
public class ApiCourseController {

    private AbstractCurriculumService courseService;
    private GenericService<Course> crudService;

    @Autowired
    public ApiCourseController(AbstractCurriculumService courseService,
                               GenericRepository<Course> courseRepo) {
        this.courseService = courseService;
        this.crudService = new GenericService<>(courseRepo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> findById(@PathVariable long id){
        Optional<Course> courseOp = crudService.findById(id);
        if(courseOp.isPresent()){
            return ResponseEntity.ok().body(CourseDto.fromEntity(courseOp.get()));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        try {
            Optional<Course> oCourse = crudService.findById(id);
            if (oCourse.isEmpty()){

            }
            crudService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
          //è sempre una 200 tipo ok
    }

    @GetMapping()
    public ResponseEntity<Iterable<CourseDto>> findWithSkillAndLevel(@RequestParam() String titleLike,
                                                                     @RequestParam(required = false) Boolean isActive,
                                                                     @RequestParam(required = false) Integer minEdition){
        try{
            Iterable<Course> itCourse;
            if (isActive == null && minEdition == null){
                itCourse = courseService.findCoursesByTitleContains(titleLike);
            } else if(minEdition == null){
                itCourse = courseService.findByTitleAndIsActive(titleLike, isActive);
            }else if(isActive == null){
                itCourse = courseService.findByTitleAndMinEdition(titleLike, minEdition);
            }else if(titleLike != null){
                itCourse = courseService.findByTitleAndIsActiveAndMinEdition(titleLike, isActive, minEdition);
            }else {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().body(CourseDto.fromEntityIterable(itCourse));
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
