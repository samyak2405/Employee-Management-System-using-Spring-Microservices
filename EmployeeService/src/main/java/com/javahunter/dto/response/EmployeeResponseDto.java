package com.javahunter.dto.response;

import com.javahunter.entity.Address;
import com.javahunter.entity.Department;
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
public class EmployeeResponseDto {

    private String firstName;

    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String email;

    private String mobileNumber;

    private EmployeeRoles position;

    private EmployeeStatus empStatus;

    private Address address;

    private Education education;

    private DepartmentDetails departmentDetails;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentDetails{
        private String departmentName;
        private Long currentEmployeeCount;
        private String m1ManagerName;
        private String m2ManagerName;
    }
}
