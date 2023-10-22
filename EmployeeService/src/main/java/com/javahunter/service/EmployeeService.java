package com.javahunter.service;

import com.javahunter.dto.request.EmployeeDeleteDto;
import com.javahunter.dto.request.EmployeeRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    void registerEmployee(EmployeeRequestDto employeeRequestDto);

    boolean updateEmployee(EmployeeRequestDto employeeRequestDto);

    boolean deleteEmployee(EmployeeDeleteDto employeeDeleteDto);

    boolean assignDepartment();
}
