package com.example.payroll.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super(String.format("Could not find employee defined by id: %d.", id));
    }
}
