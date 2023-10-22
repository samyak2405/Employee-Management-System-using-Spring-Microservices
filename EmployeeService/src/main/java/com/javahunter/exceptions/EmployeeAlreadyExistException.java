package com.javahunter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyExistException extends RuntimeException {

    public EmployeeAlreadyExistException(String message){
        super(message);
    }
}
