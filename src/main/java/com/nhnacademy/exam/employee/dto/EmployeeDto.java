package com.nhnacademy.exam.employee.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 10/05/2023
 */

@Getter
@Setter
@ToString
public class EmployeeDto {
    @JsonProperty("id")
    private final String empId;
    @JsonProperty("name")
    private final String empName;
    @JsonIgnore
    private final Set<String> deptIdSet = new HashSet<>();

    public EmployeeDto(String empId, String empName) {
        this.empId = empId;
        this.empName = empName;
    }

    public void addDeptId(String deptId){
        deptIdSet.add(deptId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto that = (EmployeeDto) o;
        return Objects.equals(empId, that.empId) && Objects.equals(empName, that.empName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, empName);
    }
}
