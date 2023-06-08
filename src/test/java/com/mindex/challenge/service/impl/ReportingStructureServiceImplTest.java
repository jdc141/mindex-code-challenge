package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String getReportingStructureUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup(){
        getReportingStructureUrl = "http://localhost:" + port + "/ReportingStructure/{id}";
    }

    @Test
    public void testRead() {
        Employee testEmployee = employeeRepository.findByEmployeeId("03aa1462-ffa9-4978-901b-7c001562cf6f");
        ReportingStructure testReportingStructure = new ReportingStructure(testEmployee, 2);

        //Read
        ResponseEntity<ReportingStructure> responseEntity = restTemplate.getForEntity(getReportingStructureUrl, ReportingStructure.class, testEmployee.getEmployeeId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ReportingStructure returnedReportingStructure = responseEntity.getBody();
        assertReportStructureEquivalence(testReportingStructure, returnedReportingStructure);


        }
        private static void assertReportStructureEquivalence(ReportingStructure expected, ReportingStructure actual) {
        assertEquals(expected.getEmployee(), actual.getEmployee());
        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
        }
}

