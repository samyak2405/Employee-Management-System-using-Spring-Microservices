package com.javahunter.mapper;

import com.javahunter.dto.request.DepartmentRequest;
import com.javahunter.dto.response.DepartmentResponse;
import com.javahunter.entity.Department;

public class DepartmentMapper {

    public static Department mapToDepartment(Department department, DepartmentRequest departmentRequest){

        department.setDepartmentName(departmentRequest.getDepartmentName());
        department.setRequiredEmployeeCount(departmentRequest.getRequiredEmployeeCount());
        department.setM1ManagerName(departmentRequest.getM1ManagerName());
        department.setM2ManagerName(departmentRequest.getM2ManagerName());

        return department;
    }

    public static DepartmentResponse mapToDepartmentResponse(DepartmentResponse departmentResponse, Department department){

        departmentResponse.setDepartmentName(department.getDepartmentName());
        departmentResponse.setCurrentEmployeeCount(department.getCurrentEmployeeCount());
        departmentResponse.setM1ManagerName(department.getM1ManagerName());
        departmentResponse.setM2ManagerName(department.getM2ManagerName());

        return departmentResponse;
    }
}
