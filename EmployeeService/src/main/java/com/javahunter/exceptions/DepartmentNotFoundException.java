package com.javahunter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String departmentName){
        super(String.format("Department %s not found",departmentName));
    }
}
