package com.javahunter.dto.request;

import com.javahunter.entity.Address;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private Address temporaryAddress;
    private Address permanentAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String mobileNumber;
    private String email;
    private String password;
}
