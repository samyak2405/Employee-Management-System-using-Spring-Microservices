package com.javahunter.controller;

import com.javahunter.dto.request.EmployeeRequest;
import com.javahunter.dto.response.ResponseDto;
import com.javahunter.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/employee")
@AllArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerEmployee(@RequestBody EmployeeRequest employeeRequest){
        log.info("Inside controller to create employee");
        employeeService.createEmployee(employeeRequest);
        log.info("Employee created successfully");
        return new ResponseEntity<>(new ResponseDto(String
                .format("Employee with email %s created successfully"
                        ,employeeRequest.getEmail()),"200 OK"),HttpStatus.OK);
    }

}
