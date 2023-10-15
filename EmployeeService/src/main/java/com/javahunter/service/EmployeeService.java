package com.javahunter.service;

import com.javahunter.dto.request.EmployeeRequest;
import com.javahunter.entity.Employee;
import com.javahunter.entity.User;
import com.javahunter.mapper.EmployeeMapper;
import com.javahunter.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;
    /**
     * @param employeeRequest
     */
    @Override
    public void createEmployee(EmployeeRequest employeeRequest) {
        log.info("Inside Create Employee Service");

        Employee employee = new Employee();

        EmployeeMapper.mapToEmployee(employeeRequest,employee);
        employee.setCreatedAt(LocalDateTime.now());
        employee.setCreatedBy(employeeRequest.getFirstName());

        employeeRepository.save(employee);
    }
}
