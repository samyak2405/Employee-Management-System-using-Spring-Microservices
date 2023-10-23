package com.javahunter.service;

import com.javahunter.dto.request.EmployeeDeleteDto;
import com.javahunter.dto.request.EmployeeRequestDto;
import com.javahunter.dto.response.EmployeeResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    void registerEmployee(EmployeeRequestDto employeeRequestDto);

    boolean updateEmployee(EmployeeRequestDto employeeRequestDto);

    boolean deleteEmployee(EmployeeDeleteDto employeeDeleteDto);

    boolean assignDepartment(String empId);

    List<EmployeeResponseDto> findEmployeesByDept(String departmentName);

    List<EmployeeResponseDto> findEmployeeByPosition(String position);

    List<EmployeeResponseDto> findBySkills(List<String> skills);

    boolean changeEmpDepartment(String empSerialNo,String departmentName);

    boolean changeEmployeeStatus(String empSerialNo,String empStatus);

    List<EmployeeResponseDto> findByEmployeeStatus(String empStatus);
}
