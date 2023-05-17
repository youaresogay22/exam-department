package com.nhnacademy.exam.deptmember.repository;

import com.nhnacademy.exam.deptmember.entity.DepartmentMember;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 11/05/2023
 */
public interface DeptMemberRepository extends JpaRepository<DepartmentMember,Long>, DeptMemberRepositoryCustom {
}
