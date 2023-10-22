package com.javahunter.repository;

import com.javahunter.entity.Employee;
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

    @Query(value = "SELECT * FROM t_employee WHERE department=null",nativeQuery = true)
    List<Employee> findByDepartment();
}
