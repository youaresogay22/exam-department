package com.nhnacademy.exam.deptmember.repository;

import com.nhnacademy.exam.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 15/05/2023
 */
@ActiveProfiles(value = "dev")
@DataJpaTest
class DeptMemberRepositoryTest {

    @Autowired
    private DeptMemberRepository deptMemberRepository;
    @Autowired
    private DepartmentRepository departmentRepository;



}