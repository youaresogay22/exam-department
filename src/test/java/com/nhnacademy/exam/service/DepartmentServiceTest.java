package com.nhnacademy.exam.service;

import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    Department department;
    @MockBean
    DepartmentRepository departmentRepository = Mockito.mock(DepartmentRepository.class);
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setId("test");
        department.setName("name");
        department.setMember(null);

        departmentService = new DepartmentService(departmentRepository);
    }

    @Test
    void exists() {
        departmentService.exists("123");
        verify(departmentRepository, times(1)).existsById(anyString());
    }

    @Test
    void createDepartment() {
        departmentService.createDepartment(new Department());
        verify(departmentRepository, times(1)).save(any());
    }

    @Test
    void findDepartment() {
        when(departmentRepository.findById(anyString())).thenReturn(Optional.of(department));

        departmentService.findDepartment("123");

        verify(departmentRepository, times(1)).findById(anyString());
        Assertions.assertEquals("test", departmentService.findDepartment("test").getId());
    }

    @Test
    void updateDepartment() {
        departmentService.updateDepartment(new Department());
        verify(departmentRepository, times(1)).save(any());
    }

    @Test
    void deleteDepartment() {
        departmentService.deleteDepartment("123");
        verify(departmentRepository, times(1)).deleteById(anyString());
    }
}