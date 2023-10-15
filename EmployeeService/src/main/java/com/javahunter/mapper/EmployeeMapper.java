package com.javahunter.mapper;

import com.javahunter.dto.request.EmployeeRequest;
import com.javahunter.dto.response.EmployeeResponse;
import com.javahunter.entity.Employee;

public class EmployeeMapper {

    public static Employee mapToEmployee(EmployeeRequest employeeRequest, Employee employee){

        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setTemporaryAddress(employeeRequest.getTemporaryAddress());
        employee.setPermanentAddress(employeeRequest.getPermanentAddress());
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setMobileNumber(employeeRequest.getMobileNumber());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPassword(employeeRequest.getPassword());

        return employee;
    }

    public static EmployeeResponse mapToEmployeeResponse(Employee employee, EmployeeResponse employeeResponse){

        employeeResponse.setFirstName(employee.getFirstName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setTemperaryAddress(employee.getTemporaryAddress());
        employeeResponse.setPermanentAddress(employee.getPermanentAddress());
        employeeResponse.setMobileNumber(employee.getMobileNumber());
        employeeResponse.setEmail(employee.getEmail());

        return employeeResponse;
    }
}
