package com.javahunter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Entity
@Table(name = "t_department")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Department extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;
    private String departmentName;
    private Long currentEmployeeCount = 0L;
    private Long requiredEmployeeCount = 0L;
    private String m1ManagerName;
    private String m2ManagerName;
    private String location;
    private List<String> techStack;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

}
