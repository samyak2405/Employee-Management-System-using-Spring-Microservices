package com.javahunter.exceptions;

public class DepartmentNotAssignedException extends RuntimeException{
    public DepartmentNotAssignedException(String message){
        super(message);
    }
}
