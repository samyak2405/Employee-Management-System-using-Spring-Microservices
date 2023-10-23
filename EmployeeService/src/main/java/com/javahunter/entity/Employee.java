package com.javahunter.entity;

import com.javahunter.enums.EmployeeRoles;
import com.javahunter.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "t_employee")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    private String empSerialNo;

    private String firstName;

    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String email;

    private String mobileNumber;

    private String password;

    @Enumerated(EnumType.STRING)
    private EmployeeRoles position;

    @Embedded
    private Address address;

    @Embedded
    private Education education;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
