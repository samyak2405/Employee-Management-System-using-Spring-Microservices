package com.javahunter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {
    private String departmentName;
    private Long requiredEmployeeCount;
    private String m1ManagerName;
    private String m2ManagerName;
    private String location;
    private List<String> techStack;
}
