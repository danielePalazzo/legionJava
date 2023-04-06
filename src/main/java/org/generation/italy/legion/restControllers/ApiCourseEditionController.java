package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.dtos.CourseEditionDto;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.CourseEdition;
import org.generation.italy.legion.model.services.abstractions.AbstractCourseEditionService;
import org.generation.italy.legion.model.services.abstractions.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/courseEditions")
public class ApiCourseEditionController {

    private AbstractCourseEditionService courseEditionService;
    private AbstractCrudService<CourseEdition> crudService;

    @Autowired
    public ApiCourseEditionController(AbstractCourseEditionService courseEditionService, AbstractCrudService<CourseEdition> crudService) {
        this.courseEditionService = courseEditionService;
        this.crudService = crudService;
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getTotalCost(){
        double total = courseEditionService.getTotalCost();
        return ResponseEntity.ok().body(total);
    }

    @GetMapping("/expensive")
    public ResponseEntity<CourseEditionDto> findFirstByOrderByCostDesc(){

        try {
            Optional<CourseEdition> oCE = courseEditionService.findFirstByOrderByCostDesc();
            if (oCE.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(CourseEditionDto.fromEntity(oCE.get()));
        } catch (DataException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/average")
    public ResponseEntity<Double> findAverageCost(){
        double average = courseEditionService.findAverageCost();
        return ResponseEntity.ok().body(average);
    }

    @GetMapping("/allDurations")
    public ResponseEntity<Iterable<Double>> findDurationFromCourse(){
        Iterable<Double> iDouble = courseEditionService.findCostByCost();
        return ResponseEntity.ok().body(iDouble);
    }

    @GetMapping("/cost/mode")
    public ResponseEntity<Double> getCourseEditionCostMode(){
        Optional<Double> oMode = courseEditionService.getCourseEditionCostMode();;
        return oMode.map(aDouble -> ResponseEntity.ok().body(aDouble)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<Iterable<CourseEditionDto>> courseEditionGeneral(@RequestParam(required = false) Long courseId,
                                                                             @RequestParam(required = false) String titlePart,
                                                                             @RequestParam(required = false) LocalDate startAt,
                                                                             @RequestParam(required = false) LocalDate endAt,
                                                                             @RequestParam(required = false) Boolean findMedian){
        if (findMedian != null && findMedian){
            Iterable<CourseEdition> iCE = courseEditionService.findMedian();
            return ResponseEntity.ok().body(CourseEditionDto.fromEntityIterator(iCE));
        }
        if (titlePart != null && startAt != null && endAt != null){
            Iterable<CourseEdition> iCE = courseEditionService.findByCourseTitleContainsAndStartedAtBetween(titlePart,startAt,endAt);
            return ResponseEntity.ok().body(CourseEditionDto.fromEntityIterator(iCE));
        }
        if (courseId != null){
            Iterable<CourseEdition> iCE = courseEditionService.findByCourse(courseId);
            return ResponseEntity.ok().body(CourseEditionDto.fromEntityIterator(iCE));
        }
        return ResponseEntity.notFound().build();
    }
}
