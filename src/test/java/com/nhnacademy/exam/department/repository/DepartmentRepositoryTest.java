package com.nhnacademy.exam.department.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 15/05/2023
 */

@DataJpaTest
@ActiveProfiles("dev")
class DepartmentRepositoryTest {

//    @Autowired
//    DepartmentRepository departmentRepository;
//
//    @Test
//    @DisplayName("department 등록")
//    void createDepartment(){
//        String deptId = "A001";
//        String deptName="1팀";
//
//        departmentRepository.save(new Department(deptId,deptName));
//        Department department = departmentRepository.findById(deptId).orElse(null);
//        Assertions.assertThat(department.getDeptName()).isEqualTo(deptName);
//    }

}