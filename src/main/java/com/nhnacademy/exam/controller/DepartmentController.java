package com.nhnacademy.exam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.domain.DepartmentDTO;
import com.nhnacademy.exam.domain.DepartmentIdDTO;
import com.nhnacademy.exam.domain.DepartmentNameDTO;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.exception.DuplicateDepartmentIdException;
import com.nhnacademy.exam.exception.NoSuchDepartmentIdException;
import com.nhnacademy.exam.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping("/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentIdDTO createDepartment(@RequestBody DepartmentDTO departmentDTO) throws JsonProcessingException {

        Department department = objectMapper.convertValue(departmentDTO, Department.class);

        if (departmentService.exists(department.getId())) {
            throw new DuplicateDepartmentIdException();
        } else {
            departmentService.createDepartment(department);
            return objectMapper.convertValue(departmentDTO.getId(), DepartmentIdDTO.class);
        }
    }

    @GetMapping("/departments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartmentDTO findDepartment(@PathVariable String id) {

        if (!departmentService.exists(id)) {
            throw new NoSuchDepartmentIdException();
        } else
            return departmentService.findDepartment(id);

    }

    @PutMapping("/departments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDepartment(@PathVariable String id,
                                 @RequestBody DepartmentNameDTO departmentNameDTO) {
        Department department = objectMapper.convertValue(departmentNameDTO, Department.class);

        if (!departmentService.exists(id)) {
            throw new NoSuchDepartmentIdException();
        } else
            departmentService.updateDepartment(department);

    }

    @DeleteMapping("/departments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable String id) {

        if (!departmentService.exists(id)) {
            throw new NoSuchDepartmentIdException();
        } else
            departmentService.deleteDepartment(id);

    }
}
