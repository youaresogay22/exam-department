package com.nhnacademy.exam.employee.entity;

import com.nhnacademy.exam.deptmember.entity.DepartmentMember;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 11/05/2023
 */

@Entity
@Table(name = "employee")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Employee {
    @Id
    private String empId;
    private String empName;

    public Employee(String empId, String empName) {
        this.empId = empId;
        this.empName = empName;
    }

    @OneToMany(mappedBy = "employee")
    private List<DepartmentMember> departmentMembers = new ArrayList<>();
}
