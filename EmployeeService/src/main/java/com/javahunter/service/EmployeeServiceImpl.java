package com.javahunter.service;

import com.javahunter.dto.request.EmployeeDeleteDto;
import com.javahunter.dto.request.EmployeeRequestDto;
import com.javahunter.entity.Department;
import com.javahunter.entity.Employee;
import com.javahunter.exceptions.EmployeeAlreadyExistException;
import com.javahunter.exceptions.EmployeeNotFoundException;
import com.javahunter.mapper.EmployeeMapper;
import com.javahunter.repository.DepartmentRepository;
import com.javahunter.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * @param employeeRequestDto
     */
    @Override
    public void registerEmployee(EmployeeRequestDto employeeRequestDto) {
        if(employeeRepository.existsByEmail(employeeRequestDto.getEmail()))
            throw new EmployeeAlreadyExistException(String.format("Employee with email %s already exists",employeeRequestDto.getEmail()));
        if(employeeRepository.existsByMobileNumber(employeeRequestDto.getMobileNumber()))
            throw new EmployeeAlreadyExistException(String.format("Employee with mobile number %s already exists",employeeRequestDto.getMobileNumber()));
        Employee employee = EmployeeMapper.mapToEmployee(new Employee(),employeeRequestDto);
        employeeRepository.save(employee);
    }

    /**
     * @param employeeRequestDto
     * @return
     */
    @Transactional
    @Override
    public boolean updateEmployee(EmployeeRequestDto employeeRequestDto) {

        Employee employee = employeeRepository.findByEmail(employeeRequestDto.getEmail());
        if(employee==null)
            throw new EmployeeNotFoundException(employeeRequestDto.getEmail());
        EmployeeMapper.mapToEmployee(employee,employeeRequestDto);
        employeeRepository.save(employee);

        return true;
    }

    /**
     * @param
     * @return
     */
    @Transactional
    @Override
    public boolean deleteEmployee(EmployeeDeleteDto employeeDeleteDto) {
        Employee employee = employeeRepository.findByEmail(employeeDeleteDto.getEmail());
        if (employee==null || !employee.getMobileNumber().equals(employeeDeleteDto.getMobileNumber()))
            throw new EmployeeNotFoundException(employeeDeleteDto.getEmail());
        employeeRepository.deleteByEmail(employeeDeleteDto.getEmail());
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean assignDepartment() {
        List<Employee> employees = employeeRepository.findByDepartment();
        List<Department> departments = departmentRepository.findByEmployeeRequirements();



        return true;
    }


}
