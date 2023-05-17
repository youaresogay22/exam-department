package com.nhnacademy.exam.employee.repository;

import com.nhnacademy.exam.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 11/05/2023
 */
public interface EmployeeRepository extends JpaRepository<Employee,String> {
}
