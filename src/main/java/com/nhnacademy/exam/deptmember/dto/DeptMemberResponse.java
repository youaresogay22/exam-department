package com.nhnacademy.exam.deptmember.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhnacademy.exam.department.dto.DepartmentDto;
import com.nhnacademy.exam.employee.dto.EmployeeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 11/05/2023
 */

@Getter
@NoArgsConstructor
public class DeptMemberResponse {

    @JsonProperty("department")
    private DepartmentDto departmentDto;
    @JsonProperty("employee")
    private EmployeeDto employeeDto;

    public DeptMemberResponse(String deptId,String deptName, String empId, String empName) {
        this.departmentDto = new DepartmentDto(deptId,deptName);
        this.employeeDto = new EmployeeDto(empId,empName);
    }
}
