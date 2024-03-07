package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.domain.DepartmentDTO;
import com.nhnacademy.exam.domain.DepartmentIdDTO;
import com.nhnacademy.exam.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    DepartmentDTO findDepartmentById(String id);
}
