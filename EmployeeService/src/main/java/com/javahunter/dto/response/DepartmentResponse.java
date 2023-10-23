package com.javahunter.dto.response;

import com.javahunter.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {
    private String departmentName;
    private Long currentEmployeeCount;
    private String m1ManagerName;
    private String m2ManagerName;
    private String location;
    private List<String> techStack;
    private Map<String,String> employees;
}
