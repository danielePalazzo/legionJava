package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.model.services.abstractions.AbstractCourseEditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/courseEditions")
public class ApiCourseEditionController {

    private AbstractCourseEditionService service;

    @Autowired
    public ApiCourseEditionController(AbstractCourseEditionService service){
        this.service = service;
    }

    @GetMapping("/total")
    public ResponseEntity<Double> findWithSkillAndLevel(){

        Double total = service.getTotalCost();
        return ResponseEntity.ok().body(total);

    }
}
