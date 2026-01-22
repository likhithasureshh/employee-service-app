package com.module23.demo1.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long empid;
    private String empName;
    private String empEmail;
    private String isActive;
    private String doj;
}
