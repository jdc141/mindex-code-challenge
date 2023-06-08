package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private  CompensationRepository compensationRepository;

    @Autowired
    private EmployeeService employeeService;


    public CompensationServiceImpl(CompensationRepository compensationRepository) {
        this.compensationRepository = compensationRepository;
    }

    //Create

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation: [{}]", compensation);

        Employee employee = employeeService.read(compensation.getEmployee().getEmployeeId());
        compensation.setEmployee(employee);
        compensationRepository.insert(compensation);
        return compensation;
    }

    //read

    @Override
    public Compensation read(String employeeId) {


        Compensation compensation = compensationRepository.findByEmployee_EmployeeId(employeeId);

        if (compensation == null) {
            throw new RuntimeException("No record of compensation for this employeeId: " + employeeId);
        }
        LOG.debug("Returning compensation for employee: [{}]", employeeId, compensation);

        return compensation;
    }
}
