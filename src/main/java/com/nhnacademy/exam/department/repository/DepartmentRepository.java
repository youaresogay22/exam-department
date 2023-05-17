package com.nhnacademy.exam.department.repository;

import com.nhnacademy.exam.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 11/05/2023
 */
public interface DepartmentRepository extends JpaRepository<Department,String> {
}
