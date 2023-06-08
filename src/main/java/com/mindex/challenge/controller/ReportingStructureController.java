package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*
    This new type should have a new REST endpoint created for it.
    This new endpoint should accept an employeeId and return the fully filled out ReportingStructure for the specified employeeId.
    The values should be computed on the fly and will not be persisted. -> I believe this is meaning don't save this in the db, the request should traverse the employee object and return the calculated total reports...
 */
@RestController
public class ReportingStructureController {
    @Autowired
    private ReportingStructureService reportingStructureService;

    @GetMapping("/ReportingStructure/{employeeId}")
    public ReportingStructure getReportingStructure(@PathVariable String employeeId) {
        return reportingStructureService.getReportingStructure(employeeId);
    }
}
