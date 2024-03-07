package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.entity.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("dev")
class DepartmentRepositoryTest {
    Department department;

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setId("test");
        department.setName("name");
        department.setMember(null);

        entityManager.persistAndFlush(department);
    }

    @Test
    void create() {
        Department department2 = new Department();
        department2.setId("test2");
        department2.setName("name2");
        department2.setMember(null);

        departmentRepository.save(department2);

        Assertions.assertTrue(departmentRepository.existsById("test2"));
    }

    @Test
    void read() {
        Assertions.assertEquals("test", departmentRepository.findById("test").get().getId());
    }

    @Test
    void update() {
        Department department2 = new Department();
        department2.setId("test");
        department2.setName("name2");
        department2.setMember(null);

        departmentRepository.save(department2);

        Assertions.assertEquals("name2", departmentRepository.findById("test").get().getName());
    }

    @Test
    void delete() {
        departmentRepository.deleteById("test");
        Assertions.assertFalse(departmentRepository.existsById("test"));
    }

}