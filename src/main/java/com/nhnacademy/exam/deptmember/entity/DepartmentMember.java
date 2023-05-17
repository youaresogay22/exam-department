package com.nhnacademy.exam.deptmember.entity;

import com.nhnacademy.exam.department.entity.Department;
import com.nhnacademy.exam.employee.entity.Employee;
import lombok.*;

import javax.persistence.*;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 11/05/2023
 */

@Entity
@Table(name = "department_members",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_dept_emp_id", columnNames = {"deptId","empId"})
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DepartmentMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptId", foreignKey = @ForeignKey(name = "fk_dept_id", value = ConstraintMode.CONSTRAINT))
    private Department department;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empId", foreignKey = @ForeignKey(name = "fk_emp_id",value = ConstraintMode.CONSTRAINT))
    private Employee employee;

    public DepartmentMember(Department department, Employee employee) {
        this.department = department;
        this.employee = employee;
    }
}
