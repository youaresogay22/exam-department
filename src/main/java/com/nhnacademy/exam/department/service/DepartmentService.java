package com.nhnacademy.exam.department.service;

import com.nhnacademy.exam.department.dto.DepartmentDto;
import com.nhnacademy.exam.department.dto.DepartmentRequest;
import com.nhnacademy.exam.department.dto.DepartmentCreatedResponse;
import com.nhnacademy.exam.department.entity.Department;
import com.nhnacademy.exam.department.repository.DepartmentRepository;
import com.nhnacademy.exam.exception.DepartmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;


/**
 * @Author : marco@nhnacademy.com
 * @Date : 11/05/2023
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public void removeAll(){
        departmentRepository.deleteAll();
    }
    public List<Department> saveAll(Collection<Department> c){
        return departmentRepository.saveAll(c);
    }

    public DepartmentCreatedResponse save(DepartmentRequest departmentRequest){
        Department department = departmentRepository.save(new Department(departmentRequest.getDeptId(), departmentRequest.getDeptName()));
        return new DepartmentCreatedResponse(department.getDeptId());
    }

    public DepartmentDto getDepartment(String deptId){
        Department department = getDepartmentFromEntity(deptId);
        if(Objects.isNull(department)){
            throw new DepartmentNotFoundException(deptId);
        }
        return new DepartmentDto(department.getDeptId(), department.getDeptName());
    }

    public boolean existByDeptId(String deptId){
        return departmentRepository.existsById(deptId);
    }

    public void deleteByDeptId(String deptId){
        if(existByDeptId(deptId)) {
            departmentRepository.deleteById(deptId);
            return;
        }
        throw new DepartmentNotFoundException(deptId);
    }

    public void update(String deptId, DepartmentRequest departmentRequest){
        Department department = getDepartmentFromEntity(deptId);
        department.update(departmentRequest.getDeptName());
    }

    private Department getDepartmentFromEntity(String deptId){
        Department department = departmentRepository.findById(deptId).orElse(null);
        if(Objects.isNull(department)){
            throw new DepartmentNotFoundException(deptId);
        }
        return department;
    }
}
