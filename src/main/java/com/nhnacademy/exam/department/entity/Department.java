package com.nhnacademy.exam.department.entity;

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
@Table(name = "department")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {
    @Id
    private String deptId;

    private String deptName;

    public Department(String deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    @OneToMany(mappedBy = "department")
    private List<DepartmentMember> departmentMembers = new ArrayList<>();

    public void update(String deptName){
        this.deptName = deptName;
    }
}

