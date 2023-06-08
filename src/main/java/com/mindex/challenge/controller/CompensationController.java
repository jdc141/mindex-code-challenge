package com.mindex.challenge.controller;


import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
    Create two new Compensation REST endpoints.
    One to create and one to read by employeeId.
    These should persist and query the Compensation from the persistence layer. -> I take this to mean that this should create entries in the DB.
*/


@RestController
public class CompensationController {
    private final CompensationService compensationService;

    @Autowired
    CompensationController(CompensationService compensationService){
        this.compensationService = compensationService;
    }

    //One to create
    @PostMapping("/Compensation")
    public Compensation createCompensation(@RequestBody Compensation compensation) {

        return compensationService.create(compensation);
    }

    //one to read by employeeId.
    @GetMapping("/Compensation/{employeeId}")
    public Compensation getCompensationByEmployeeID(@PathVariable String employeeId){

        return compensationService.read(employeeId);
    }


}
