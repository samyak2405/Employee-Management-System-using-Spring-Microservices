package com.javahunter.controller;

import com.javahunter.constants.EmployeeConstants;
import com.javahunter.dto.request.EmployeeDeleteDto;
import com.javahunter.dto.request.EmployeeRequestDto;
import com.javahunter.dto.response.ResponseDto;
import com.javahunter.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> registerEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        employeeService.registerEmployee(employeeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(EmployeeConstants.STATUS_201, EmployeeConstants.MESSAGE_201));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        boolean isUpdated = employeeService.updateEmployee(employeeRequestDto);
        if(!isUpdated)
            return new ResponseEntity<>(
                    new ResponseDto(EmployeeConstants.STATUS_417,EmployeeConstants.MESSAGE_417_UPDATE)
                    ,HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(
                new ResponseDto(EmployeeConstants.STATUS_200, EmployeeConstants.MESSAGE_200)
                ,HttpStatus.OK
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteEmployee(@RequestBody EmployeeDeleteDto employeeDeleteDto){
        boolean isDeleted = employeeService.deleteEmployee(employeeDeleteDto);
        if(!isDeleted)
            return new ResponseEntity<>(
                    new ResponseDto(EmployeeConstants.STATUS_417,EmployeeConstants.MESSAGE_417_DELETE),
                    HttpStatus.EXPECTATION_FAILED
            );
        return new ResponseEntity<>(
          new ResponseDto(EmployeeConstants.STATUS_200,EmployeeConstants.MESSAGE_200),
          HttpStatus.OK
        );
    }

    @PutMapping("/assign-department")
    public ResponseEntity<ResponseDto> assignDepartment(){
        return null;
    }
}
