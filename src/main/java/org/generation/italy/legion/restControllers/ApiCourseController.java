package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.dtos.CourseDto;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.services.abstractions.AbstractCourseService;
import org.generation.italy.legion.model.services.abstractions.AbstractCrudService;
import org.generation.italy.legion.model.services.abstractions.AbstractDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/courses")
public class ApiCourseController {

    private AbstractDidacticService courseService;
    private AbstractCrudService<Course> crudService;

    @Autowired
    public ApiCourseController(AbstractDidacticService courseService, AbstractCrudService<Course> crudService) {
        this.courseService = courseService;
        this.crudService = crudService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> findById(@PathVariable long id){
        try {
            Optional<Course> courseOp = crudService.findById(id);
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
