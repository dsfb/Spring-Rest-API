package com.example.payroll.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeIncompleteAdvice {

    @ResponseBody
    @ExceptionHandler(EmployeeIncompleteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeIncompleteHandler(EmployeeIncompleteException ex){
        // classe de tratamento de resposta das exceptions
        return ex.getMessage();
    }
}
