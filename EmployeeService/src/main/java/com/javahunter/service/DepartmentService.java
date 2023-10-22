package com.javahunter.service;

import com.javahunter.dto.request.DepartmentRequest;
import com.javahunter.dto.request.UpdateManagerDto;
import com.javahunter.dto.response.DepartmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    public void createDepartment(DepartmentRequest departmentRequest);

    public List<DepartmentResponse> getAllDepartments();

    public boolean updateDepartment(DepartmentRequest departmentRequest);

    public boolean deleteDepartment(String departmentName);

    boolean updateManagers(UpdateManagerDto updateManagerDto);
}
