package com.javahunter.controller;

import com.javahunter.constants.EmployeeConstants;
import com.javahunter.dto.request.EmployeeDeleteDto;
import com.javahunter.dto.request.EmployeeRequestDto;
import com.javahunter.dto.response.EmployeeResponseDto;
import com.javahunter.dto.response.ResponseDto;
import com.javahunter.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Slf4j
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

    @PutMapping("/assign-department/{empId}")
    public ResponseEntity<ResponseDto> assignDepartment(@PathVariable("empId")String empId){
        log.info("Assign Department to Employee with Serial no: "+empId);
        boolean isAssigned = employeeService.assignDepartment(empId);
        if(!isAssigned)
            return new ResponseEntity<>(new ResponseDto(EmployeeConstants.STATUS_417,EmployeeConstants.MESSAGE_417_UPDATE),HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(new ResponseDto(EmployeeConstants.STATUS_200,EmployeeConstants.MESSAGE_200),HttpStatus.OK);
    }

    @GetMapping("/get/by/department/{departmentName}")
    public ResponseEntity<List<EmployeeResponseDto>> listEmployeeByDepartment(@PathVariable("departmentName") String departmentName){
        List<EmployeeResponseDto> employees = employeeService.findEmployeesByDept(departmentName);
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

    @GetMapping("/get/by/position/{position}")
    public ResponseEntity<List<EmployeeResponseDto>> listEmployeeByPosition(@PathVariable("position")String position){
        List<EmployeeResponseDto> employees = employeeService.findEmployeeByPosition(position);
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

    @GetMapping("/get/by/skills")
    public ResponseEntity<List<EmployeeResponseDto>> listEmployeeBySkills(@RequestBody List<String> skills){
        List<EmployeeResponseDto> employees = employeeService.findBySkills(skills);
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

    @PutMapping("/update/emp-dept/{empSerialNo}/{departmentName}")
    public ResponseEntity<ResponseDto> changeEmployeeDepartment(@PathVariable String empSerialNo,@PathVariable("departmentName")String departmentName){
        boolean isUpdated = employeeService.changeEmpDepartment(empSerialNo,departmentName);
        if(!isUpdated)
            return new ResponseEntity<>(
                    new ResponseDto(EmployeeConstants.STATUS_417,EmployeeConstants.MESSAGE_417_UPDATE),
                    HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(
                new ResponseDto(EmployeeConstants.STATUS_200,EmployeeConstants.MESSAGE_200),
                HttpStatus.OK);
    }

    @PutMapping("/update/status/{empSerialNo}/{empStatus}")
    public ResponseEntity<ResponseDto> changeEmployeeStatus(@PathVariable String empSerialNo,@PathVariable String empStatus){
        boolean isUpdated = employeeService.changeEmployeeStatus(empSerialNo,empStatus);
        if(!isUpdated)
            return new ResponseEntity<>(
                    new ResponseDto(EmployeeConstants.STATUS_417,EmployeeConstants.MESSAGE_417_UPDATE),
                    HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(
                new ResponseDto(EmployeeConstants.STATUS_200,EmployeeConstants.MESSAGE_200),
                HttpStatus.OK);
    }

    @GetMapping("/get/by/status/{empStatus}")
    public ResponseEntity<List<EmployeeResponseDto>> getEmployeeByStatus(@PathVariable String empStatus){
        List<EmployeeResponseDto> employees = employeeService.findByEmployeeStatus(empStatus);
        return new ResponseEntity<>(
                employees,
                HttpStatus.OK
        );
    }
}
