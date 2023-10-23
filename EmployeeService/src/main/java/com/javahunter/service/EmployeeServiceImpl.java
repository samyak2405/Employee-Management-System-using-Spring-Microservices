package com.javahunter.service;

import com.javahunter.dto.request.EmployeeDeleteDto;
import com.javahunter.dto.request.EmployeeRequestDto;
import com.javahunter.dto.response.EmployeeResponseDto;
import com.javahunter.entity.Department;
import com.javahunter.entity.Employee;
import com.javahunter.enums.EmployeeRoles;
import com.javahunter.enums.EmployeeStatus;
import com.javahunter.exceptions.*;
import com.javahunter.mapper.EmployeeMapper;
import com.javahunter.repository.DepartmentRepository;
import com.javahunter.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
        String serialNo = String.valueOf(employeeRepository.count()+1);
        employee.setEmpSerialNo(serialNo);
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
            throw new EmployeeNotFoundException("email",employeeRequestDto.getEmail());
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
            throw new EmployeeNotFoundException("email",employeeDeleteDto.getEmail());
        employeeRepository.deleteByEmail(employeeDeleteDto.getEmail());
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean assignDepartment(String empId) {
        log.info("Assign Department to Employee service method");
        List<Department> departments = departmentRepository.findAll();
        if(departments.isEmpty())
            throw new ResourceNotFoundException("No department present");
        Employee employee = employeeRepository.findByEmpSerialNo(empId);
        if(employee==null)
            throw new ResourceNotFoundException("Employee","EmpSerialNo",empId);

        int maxScore = Integer.MIN_VALUE;
        Long departmentId = -1L;
        int i = 0;
        for(Department department:departments){
            int score = 0;
            for(String skill: employee.getEducation().getSkills()){
                boolean found = findSkill(skill,department.getTechStack());
                if(found)
                    score++;
            }
            if(maxScore<score) {
                maxScore = score;
                departmentId = department.getDeptId();
            }
        }
        if(maxScore==Integer.MIN_VALUE)
            throw new DepartmentNotAssignedException(String.format("Employee with EmpSerialNo %s cannot be assigned to any department",empId));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()->new DepartmentNotFoundException("Department Not found"));
        log.info("Department: {}",department);
        employee.setDepartment(department);
        employeeRepository.save(employee);

        return true;
    }

    private boolean findSkill(String skill, List<String> techStack) {
        for (String tech:techStack){
            if(skill.equalsIgnoreCase(tech))
                return true;
        }
        return false;
    }

    /**
     * @param departmentName
     * @return
     */
    @Override
    public List<EmployeeResponseDto> findEmployeesByDept(String departmentName) {

        Department department= departmentRepository.findByDepartmentName(departmentName);
        if(department==null)
            throw new DepartmentNotFoundException(departmentName);
        List<Employee> employees = employeeRepository.findByDepartment(department);
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();

        employees.forEach(employee ->
                employeeResponseDtos.add(
                        EmployeeMapper.mapToEmployeeResponseDto(
                                new EmployeeResponseDto(),employee
                        )));

        return employeeResponseDtos;
    }

    /**
     * @param position
     * @return
     */
    @Override
    public List<EmployeeResponseDto> findEmployeeByPosition(String position) {
       List<Employee> employees = employeeRepository.findByPosition(stringToEmployeeRoles(position));
       if (employees.isEmpty())
           throw new ResourceNotFoundException("Employees","Position",position);
       List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();
       employees.forEach(employee ->
               employeeResponseDtos.add(EmployeeMapper.mapToEmployeeResponseDto(new EmployeeResponseDto(),employee)));
       return employeeResponseDtos;
    }

    /**
     * @param skills
     * @return
     */
    @Override
    public List<EmployeeResponseDto> findBySkills(List<String> skills) {
        return null;
    }

    /**
     * @param departmentName
     * @return
     */
    @Override
    public boolean changeEmpDepartment(String empSerialNo,String departmentName) {
        Department department = departmentRepository.findByDepartmentName(departmentName);
        if (department==null)
            throw new DepartmentNotFoundException(departmentName);
        Employee employee = employeeRepository.findByEmpSerialNo(empSerialNo);
        if (employee==null)
            throw new EmployeeNotFoundException("empSerialNo",empSerialNo);
        employee.setDepartment(department);
        employeeRepository.save(employee);
        return true;
    }

    /**
     * @param empStatus
     * @return
     */
    @Override
    public boolean changeEmployeeStatus(String empSerialNo,String empStatus) {
        Employee employee = employeeRepository.findByEmpSerialNo(empSerialNo);
        if (employee==null)
            throw new EmployeeNotFoundException("empSerialNo",empSerialNo);
        employee.setEmployeeStatus(stringToEmployeeStatus(empStatus));
        employeeRepository.save(employee);
        return true;
    }

    /**
     * @param empStatus
     * @return
     */
    @Override
    public List<EmployeeResponseDto> findByEmployeeStatus(String empStatus) {
        EmployeeStatus status = stringToEmployeeStatus(empStatus);
        List<Employee> employees = employeeRepository.findByEmployeeStatus(status);
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();
        employees.forEach(employee ->
                employeeResponseDtos.add(EmployeeMapper.mapToEmployeeResponseDto(new EmployeeResponseDto(),employee)));
        return employeeResponseDtos;
    }

    private EmployeeStatus stringToEmployeeStatus(String empStatus){
        for(EmployeeStatus status: EmployeeStatus.values()){
            if (status.name().equalsIgnoreCase(empStatus)){
                return status;
            }
        }
        throw new ResourceNotFoundException("Enum value","enum",empStatus);
    }

    private EmployeeRoles stringToEmployeeRoles(String position){
        for(EmployeeRoles roles: EmployeeRoles.values()){
            if (roles.name().equalsIgnoreCase(position)){
                return roles;
            }
        }
        throw new ResourceNotFoundException("Enum ","Position",position);

    }
}
