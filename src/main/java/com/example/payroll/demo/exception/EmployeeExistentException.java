package com.example.payroll.demo.exception;

public class EmployeeExistentException extends RuntimeException {
    public EmployeeExistentException() {
        super("The provided employee already exists.");
    }
}
