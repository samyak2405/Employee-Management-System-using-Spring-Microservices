package com.javahunter.mapper;

import com.javahunter.dto.request.DepartmentRequest;
import com.javahunter.dto.response.DepartmentResponse;
import com.javahunter.entity.Department;
import com.javahunter.entity.Employee;

import java.util.HashMap;
import java.util.Map;

public class DepartmentMapper {

    public static Department mapToDepartment(Department department, DepartmentRequest departmentRequest){

        department.setDepartmentName(departmentRequest.getDepartmentName());
        department.setRequiredEmployeeCount(departmentRequest.getRequiredEmployeeCount());
        department.setM1ManagerName(departmentRequest.getM1ManagerName());
        department.setM2ManagerName(departmentRequest.getM2ManagerName());
        department.setTechStack(departmentRequest.getTechStack());
        department.setLocation(departmentRequest.getLocation());

        return department;
    }

    public static DepartmentResponse mapToDepartmentResponse(DepartmentResponse departmentResponse, Department department){

        departmentResponse.setDepartmentName(department.getDepartmentName());
        departmentResponse.setCurrentEmployeeCount(department.getCurrentEmployeeCount());
        departmentResponse.setM1ManagerName(department.getM1ManagerName());
        departmentResponse.setM2ManagerName(department.getM2ManagerName());
        departmentResponse.setTechStack(department.getTechStack());
        Map<String,String> employees = new HashMap<>();
        for(Employee e:department.getEmployees()){
            employees.put(e.getEmpSerialNo(),e.getFirstName()+" "+e.getLastName());
        }
        departmentResponse.setEmployees(employees);
        departmentResponse.setLocation(department.getLocation());
        return departmentResponse;
    }
}
