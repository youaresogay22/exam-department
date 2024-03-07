package com.nhnacademy.exam.service;

import com.nhnacademy.exam.domain.DepartmentDTO;
import com.nhnacademy.exam.domain.DepartmentIdDTO;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentIdDTO createDepartment(Department department) {
        departmentRepository.save(department);
        return new DepartmentIdDTO(department.getId());
    }

    public DepartmentDTO findDepartment(String id) {
        return departmentRepository.findDepartmentById(id);
    }

    public void updateDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void deleteDepartment(String id) {
        departmentRepository.deleteById(id);
    }
}
