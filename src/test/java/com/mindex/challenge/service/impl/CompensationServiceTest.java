package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
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
public class CompensationServiceTest {

    private String getCompensationUrl;
    private String postCompensationUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompensationService compensationService;

    @Autowired
    private CompensationRepository compensationRepository;

    @Before
    public void setup(){
        getCompensationUrl = "http://localhost:" + port + "/Compensation/{id}";
        postCompensationUrl = "http://localhost:" + port + "/Compensation";
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateRead(){
        Compensation testCompensation = new Compensation();
        Employee testEmployee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        testCompensation.setEmployee(testEmployee);
        testCompensation.setEffectiveDate("2023-6-8");
        testCompensation.setSalary("50000");
        compensationRepository.save(testCompensation);

        //Create
        Compensation createdCompensation = restTemplate.postForEntity(postCompensationUrl, testCompensation, Compensation.class).getBody();
        assertEquals(createdCompensation.getSalary(), createdCompensation.getSalary());
        assertCompensationEquivalence(testCompensation, createdCompensation);

        //Read
        ResponseEntity<Compensation> responseEntity = restTemplate.getForEntity(getCompensationUrl, Compensation.class, testEmployee.getEmployeeId() );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Compensation returnedCompensation = responseEntity.getBody();
        assertCompensationEquivalence(testCompensation, returnedCompensation);
    }


    @Test
    public void testReadCompensation() {
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("b7839309-5593-463b-a7e3-5de1c168beb3");
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary("1000000");
        testCompensation.setEffectiveDate("2023-6-8");
        compensationRepository.save(testCompensation);



        ResponseEntity<Compensation> responseEntity = restTemplate.getForEntity(getCompensationUrl, Compensation.class, testEmployee.getEmployeeId() );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Compensation returnedCompensation = responseEntity.getBody();

        assertCompensationEquivalence(testCompensation, returnedCompensation);



    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployee(), actual.getEmployee());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
}
