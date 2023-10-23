package com.javahunter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String fieldName,String fieldValue){
        super(String.format("Employee with %s : %s not found",fieldName,fieldValue));
    }
}
