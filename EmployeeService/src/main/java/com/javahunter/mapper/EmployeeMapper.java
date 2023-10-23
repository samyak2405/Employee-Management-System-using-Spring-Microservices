package com.javahunter.mapper;

import com.javahunter.dto.request.EmployeeRequestDto;
import com.javahunter.dto.response.EmployeeResponseDto;
import com.javahunter.entity.Department;
import com.javahunter.entity.Employee;

public class EmployeeMapper {

    public static Employee mapToEmployee(Employee employee, EmployeeRequestDto employeeRequest){

        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setEmail(employeeRequest.getEmail());
        employee.setMobileNumber(employeeRequest.getMobileNumber());
        employee.setPassword(employeeRequest.getPassword());
        employee.setPosition(employeeRequest.getPosition());
        employee.setEmployeeStatus(employeeRequest.getEmpStatus());
        employee.setAddress(employeeRequest.getAddress());
        employee.setEducation(employeeRequest.getEducation());

        return employee;
    }

    public static EmployeeResponseDto mapToEmployeeResponseDto(EmployeeResponseDto responseDto, Employee employee){

        responseDto.setFirstName(employee.getFirstName());
        responseDto.setLastName(employee.getLastName());
        responseDto.setDateOfBirth(employee.getDateOfBirth());
        responseDto.setEmail(employee.getEmail());
        responseDto.setMobileNumber(employee.getMobileNumber());
        responseDto.setPosition(employee.getPosition());
        responseDto.setEmpStatus(employee.getEmployeeStatus());
        responseDto.setAddress(employee.getAddress());
        responseDto.setEducation(employee.getEducation());
        //Department can be null
        Department department = employee.getDepartment();
        EmployeeResponseDto.DepartmentDetails departmentDetails = new EmployeeResponseDto.DepartmentDetails();
        departmentDetails.setDepartmentName(department.getDepartmentName());
        departmentDetails.setCurrentEmployeeCount(department.getCurrentEmployeeCount());
        departmentDetails.setM1ManagerName(department.getM1ManagerName());
        departmentDetails.setM2ManagerName(department.getM2ManagerName());
        responseDto.setDepartmentDetails(departmentDetails);

        return responseDto;
    }
}
