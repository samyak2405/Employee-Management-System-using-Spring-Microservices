package com.javahunter.controller;

import com.javahunter.constants.DepartmentConstants;
import com.javahunter.dto.request.DepartmentRequest;
import com.javahunter.dto.request.UpdateManagerDto;
import com.javahunter.dto.response.DepartmentResponse;
import com.javahunter.dto.response.ResponseDto;
import com.javahunter.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/department",produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createDepartment(@RequestBody DepartmentRequest departmentRequest){
        departmentService.createDepartment(departmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(DepartmentConstants.STATUS_201,DepartmentConstants.MESSAGE_201));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<DepartmentResponse>> getAllDepartment(){
        List<DepartmentResponse> departmentResponses = departmentService.getAllDepartments();
        return ResponseEntity.ok()
                .body(departmentResponses);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateDepartment(@RequestBody DepartmentRequest departmentRequest){
        boolean isUpdated = departmentService.updateDepartment(departmentRequest);
        if(!isUpdated)
            return new ResponseEntity<>(new ResponseDto(DepartmentConstants.STATUS_417,DepartmentConstants.MESSAGE_417_UPDATE),HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(new ResponseDto(DepartmentConstants.STATUS_200,DepartmentConstants.MESSAGE_200),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{departmentName}")
    public ResponseEntity<ResponseDto> deleteDepartment(@PathVariable("departmentName")String departmentName){
        boolean isDeleted = departmentService.deleteDepartment(departmentName);
        if(!isDeleted)
            return new ResponseEntity<>(new ResponseDto(DepartmentConstants.STATUS_417,DepartmentConstants.MESSAGE_417_DELETE),HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(new ResponseDto(DepartmentConstants.STATUS_200,DepartmentConstants.MESSAGE_200),HttpStatus.OK);
    }

    @PutMapping("/update/manager")
    public ResponseEntity<ResponseDto> updateManagers(@RequestBody UpdateManagerDto updateManagerDto){
        boolean isUpdated = departmentService.updateManagers(updateManagerDto);
        if(!isUpdated)
            return new ResponseEntity<>(new ResponseDto(DepartmentConstants.STATUS_417,DepartmentConstants.MESSAGE_417_UPDATE),HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(new ResponseDto(DepartmentConstants.STATUS_200,DepartmentConstants.MESSAGE_200),HttpStatus.OK);

    }
}
