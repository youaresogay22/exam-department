package com.nhnacademy.exam.deptmember.repository.impl;

import com.nhnacademy.exam.department.entity.QDepartment;
import com.nhnacademy.exam.deptmember.dto.DeptMemberResponse;
import com.nhnacademy.exam.deptmember.entity.DepartmentMember;
import com.nhnacademy.exam.deptmember.entity.QDepartmentMember;
import com.nhnacademy.exam.deptmember.repository.DeptMemberRepositoryCustom;
import com.nhnacademy.exam.employee.entity.QEmployee;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import java.util.List;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 11/05/2023
 */
public class DeptMemberRepositoryImpl extends QuerydslRepositorySupport implements DeptMemberRepositoryCustom{
    public DeptMemberRepositoryImpl() {
        super(DepartmentMember.class);
    }
    QDepartmentMember qDepartmentMember = QDepartmentMember.departmentMember;
    QEmployee qEmployee = QEmployee.employee;
    QDepartment qDepartment = QDepartment.department;
    @Override
    public List<DeptMemberResponse> findDeptMembers(List<String> deptIds) {
        return from(qDepartmentMember)
                .innerJoin(qDepartmentMember.department, qDepartment)
                .innerJoin(qDepartmentMember.employee, qEmployee)
                .where(qDepartmentMember.department.deptId.in(deptIds))
                .orderBy(qDepartment.deptId.asc(), qEmployee.empId.asc())
                .select(
                    Projections.constructor(DeptMemberResponse.class,
                            qDepartment.deptId, qDepartment.deptName,
                            qEmployee.empId,qEmployee.empName
                    )
                )
                .fetch();
    }
}
