package com.javahunter.exceptions;

import com.javahunter.dto.response.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders httpHeaders,
            HttpStatusCode statusCode,
            WebRequest webRequest
    ){
        Map<String,String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = exception.getBindingResult().getAllErrors();

        validationErrorList.forEach((error)->
        {
            String fieldName = ((FieldError)error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName,validationMsg);
        });

        return new ResponseEntity<>(validationErrors,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception e,
                                                                  WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto
                ,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DepartmentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleDepartmentAlreadyExistsException(DepartmentAlreadyExistsException exception,
                                                                                   WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto
                ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleDepartmentNotFoundException(DepartmentNotFoundException departmentNotFoundException,
                                                                              WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                departmentNotFoundException.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistException employeeAlreadyExistException,
                                                                              WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                employeeAlreadyExistException.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEmployeeNotFoundException(EmployeeNotFoundException employeeNotFoundException,
                                                                              WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                employeeNotFoundException.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto,
                HttpStatus.NOT_FOUND);
    }
}
