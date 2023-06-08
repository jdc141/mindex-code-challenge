package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    For the field "numberOfReports", this should equal the total number of reports under a given employee.
    The number of reports is determined to be the number of directReports for an employee and all of their distinct reports.
 */


@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
    @Autowired
    private EmployeeService employeeService;

    @Override
    public ReportingStructure getReportingStructure(String employeeId) {


        Employee employee = employeeService.read(employeeId);

        int numberOfReports = calcNumberOfReports(employee);
        ReportingStructure reportingStructure = new ReportingStructure(employee, numberOfReports);

        LOG.debug("Creating reporting structure: [{}]", reportingStructure);

        return reportingStructure;
    }

    private int calcNumberOfReports(Employee employee) {

        List<Employee> directReportsArr = employee.getDirectReports();
        //We can do this bc employee has a getDirectReports method that returns a list, we then call the .size() list method to return an int of the number of elements in directReports list object
        int directReports = employee.getDirectReports().size();
        int distinctReports = 0;
        //now, we need to get the directReporter's directReports or in the documentation "distinct reports"
        for (Employee dr : directReportsArr) {
            Employee temp = employeeService.read(dr.getEmployeeId());
            if (temp.getDirectReports() != null) {
                distinctReports += temp.getDirectReports().size();
            }
        }
        return directReports + distinctReports;
    }
}
