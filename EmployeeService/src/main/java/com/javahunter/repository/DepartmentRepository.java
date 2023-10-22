package com.javahunter.repository;

import com.javahunter.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    boolean existsByDepartmentName(String departmentName);

    Department findByDepartmentName(String departmentName);

    void deleteByDepartmentName(String departmentName);

    @Query(value = "SELECT * FROM t_department WHERE current_employee_count < required_employee_count",nativeQuery = true)
    List<Department> findByEmployeeRequirements();
}
