package com.example.payroll.demo.exception;

public class EmployeeIncompleteException extends RuntimeException {
    public EmployeeIncompleteException() {
        super("The provided employee doesn't have all the required data.");
    }
}
