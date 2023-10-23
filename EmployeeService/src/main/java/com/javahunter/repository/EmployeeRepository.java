package com.javahunter.repository;

import com.javahunter.entity.Department;
import com.javahunter.entity.Employee;
import com.javahunter.enums.EmployeeRoles;
import com.javahunter.enums.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);

    boolean existsByMobileNumber(String mobileNumber);

    Employee findByEmail(String email);

    void deleteByEmail(String email);

    Employee findByEmpSerialNo(String empId);

    List<Employee> findByDepartment(Department department);

    List<Employee> findByPosition(EmployeeRoles position);

    List<Employee> findByEmployeeStatus(EmployeeStatus status);
}
