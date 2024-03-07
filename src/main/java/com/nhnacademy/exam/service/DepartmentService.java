package com.nhnacademy.exam.service;

import com.nhnacademy.exam.domain.DepartmentDTO;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public boolean exists(String id) {
        return departmentRepository.existsById(id);
    }

    public void createDepartment(Department department) {
        departmentRepository.save(department);
    }

    public DepartmentDTO findDepartment(String id) {
        Department department = departmentRepository.findById(id).orElse(null);

        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        
        return dto;
    }

    public void updateDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void deleteDepartment(String id) {
        departmentRepository.deleteById(id);
    }
}
