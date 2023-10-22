package com.javahunter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateManagerDto {

    private String departmentName;
    private String m1ManagerName;
    private String m2ManagerName;
}
