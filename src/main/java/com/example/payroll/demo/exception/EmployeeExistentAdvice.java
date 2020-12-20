package com.example.payroll.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeExistentAdvice {

    @ResponseBody
    @ExceptionHandler(EmployeeExistentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeExistentHandler(EmployeeExistentException ex){
        // classe de tratamento de resposta das exceptions
        return ex.getMessage();
    }
}
