package com.javahunter.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException(String resourceName,String fieldName,String fielValue) {
        super(String.format("%s not found with %s = %s",resourceName,fieldName,fielValue));
    }
}
