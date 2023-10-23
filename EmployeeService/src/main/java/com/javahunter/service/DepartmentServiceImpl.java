package com.javahunter.service;

import com.javahunter.dto.request.DepartmentRequest;
import com.javahunter.dto.request.UpdateManagerDto;
import com.javahunter.dto.request.UpdateTechStackDto;
import com.javahunter.dto.response.DepartmentResponse;
import com.javahunter.entity.Department;
import com.javahunter.exceptions.DepartmentAlreadyExistsException;
import com.javahunter.exceptions.DepartmentNotFoundException;
import com.javahunter.exceptions.ResourceNotFoundException;
import com.javahunter.mapper.DepartmentMapper;
import com.javahunter.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * @param departmentRequest
     */
    @Override
    public void createDepartment(DepartmentRequest departmentRequest) {
        if(departmentRepository.existsByDepartmentName(departmentRequest.getDepartmentName()))
            throw new DepartmentAlreadyExistsException("Department Already exists");
        Department department = DepartmentMapper.mapToDepartment(new Department(),departmentRequest);
        departmentRepository.save(department);
    }

    /**
     * @return
     */
    @Override
    public List<DepartmentResponse> getAllDepartments() {

        List<Department> departments = departmentRepository.findAll();
        if(departments.isEmpty())
            throw new ResourceNotFoundException("No department found");
        List<DepartmentResponse> departmentResponses = new ArrayList<>();
        departments.forEach(department ->
                departmentResponses.add(
                        DepartmentMapper.mapToDepartmentResponse(
                                new DepartmentResponse(),
                                department
                        )));
        return departmentResponses;
    }

    /**
     * @param departmentRequest
     */
    @Transactional
    @Override
    public boolean updateDepartment(DepartmentRequest departmentRequest) {
        if(!departmentRepository.existsByDepartmentName(departmentRequest.getDepartmentName()))
            throw new DepartmentNotFoundException(departmentRequest.getDepartmentName());
        Department department = departmentRepository.findByDepartmentName(departmentRequest.getDepartmentName());
        DepartmentMapper.mapToDepartment(department,departmentRequest);
        departmentRepository.save(department);
        return true;
    }

    /**
     * @param departmentName
     */
    @Transactional
    @Override
    public boolean deleteDepartment(String departmentName) {
        if(!departmentRepository.existsByDepartmentName(departmentName))
            throw new DepartmentNotFoundException(departmentName);
        departmentRepository.deleteByDepartmentName(departmentName);
        return true;
    }

    /**
     *
     * @param updateManagerDto
     * @return
     */
    @Transactional
    @Override
    public boolean updateManagers(UpdateManagerDto updateManagerDto) {
        if(!departmentRepository.existsByDepartmentName(updateManagerDto.getDepartmentName()))
            throw new DepartmentNotFoundException(updateManagerDto.getDepartmentName());
        Department department = departmentRepository.findByDepartmentName(updateManagerDto.getDepartmentName());
        if(validateString(updateManagerDto.getM1ManagerName()))
            department.setM1ManagerName(updateManagerDto.getM1ManagerName());
        if(validateString(updateManagerDto.getM2ManagerName()))
            department.setM2ManagerName(updateManagerDto.getM2ManagerName());
        departmentRepository.save(department);
        return true;
    }

    @Transactional
    @Override
    public boolean updateTechStack(UpdateTechStackDto updateTechStackDto){
        Department department = departmentRepository.findByDepartmentName(updateTechStackDto.getDepartmentName());
        if(department==null)
            throw new DepartmentNotFoundException(updateTechStackDto.getDepartmentName());
        department.setTechStack(updateTechStackDto.getTechStack());
        departmentRepository.save(department);
        return true;
    }

    private boolean validateString(String s){
        return !s.isBlank() && !s.isEmpty();
    }
}
