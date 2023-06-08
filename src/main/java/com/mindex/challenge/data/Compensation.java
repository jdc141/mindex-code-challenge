package com.mindex.challenge.data;

/*
    Create a new type, Compensation.
    A Compensation has the following fields: employee, salary, and effectiveDate.
 */


public class Compensation {
    private Employee employee;
    private String salary;
    private String effectiveDate;

    public Compensation() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}

