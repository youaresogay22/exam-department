package com.nhnacademy.exam.parser.dto;

import com.nhnacademy.exam.department.dto.DepartmentDto;
import com.nhnacademy.exam.department.entity.Department;
import com.nhnacademy.exam.employee.dto.EmployeeDto;
import com.nhnacademy.exam.employee.entity.Employee;

import java.util.*;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 10/05/2023
 */

public class DepartmentParseResultDto {
    private final Set<EmployeeDto> employeeDtoSet;
    private final Set<DepartmentDto> departmentDtoSet;

    public DepartmentParseResultDto(Set<EmployeeDto> employeeDtoSet, Set<DepartmentDto> departmentDtoSet) {
        this.employeeDtoSet = employeeDtoSet;
        this.departmentDtoSet = departmentDtoSet;
    }

    public Set<EmployeeDto> getEmployeeDtoSet() {
        return Objects.isNull(employeeDtoSet) ? Collections.emptySet() : employeeDtoSet;
    }

    public Set<DepartmentDto> getDepartmentDtoSet() {
        return Objects.isNull(departmentDtoSet) ? Collections.emptySet() : departmentDtoSet;
    }

    public Map<String, Department> getDepartmentMap(){
        Map<String,Department> departmentMap = new HashMap<>();
        for (DepartmentDto departmentDto : getDepartmentDtoSet()) {
            departmentMap.put(departmentDto.getDeptId(), new Department(departmentDto.getDeptId(),departmentDto.getDeptName()));
        }
        return departmentMap;
    }

    public Map<String, Employee> getEmployeeMap(){
        Map<String, Employee> employeeMap = new HashMap<>();
        for (EmployeeDto employeeDto : getEmployeeDtoSet()) {
            employeeMap.put(employeeDto.getEmpId(), new Employee(employeeDto.getEmpId(),employeeDto.getEmpName()));
        }
        return employeeMap;
    }

}
