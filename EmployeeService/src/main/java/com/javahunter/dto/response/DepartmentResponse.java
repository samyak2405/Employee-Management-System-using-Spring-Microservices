package com.javahunter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {
    private String departmentName;
    private Long currentEmployeeCount;
    private String m1ManagerName;
    private String m2ManagerName;
}
