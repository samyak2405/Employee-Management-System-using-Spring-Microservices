package com.javahunter.dto.request;

import com.javahunter.entity.Address;
import com.javahunter.entity.Education;
import com.javahunter.enums.EmployeeRoles;
import com.javahunter.enums.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDto {

    private String firstName;

    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String email;

    private String mobileNumber;

    private String password;

    private EmployeeRoles position;

    private Address address;

    private Education education;

    private EmployeeStatus empStatus;
}
