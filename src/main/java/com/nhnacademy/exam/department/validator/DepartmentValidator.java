package com.nhnacademy.exam.department.validator;

import com.nhnacademy.exam.department.dto.DepartmentRequest;
import com.nhnacademy.exam.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 16/05/2023
 */

@Component
@RequiredArgsConstructor
public class DepartmentValidator implements Validator {
    private final DepartmentService departmentService;

    @Override
    public boolean supports(Class<?> clazz) {
        return DepartmentRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DepartmentRequest departmentRequest = (DepartmentRequest) target;
        boolean result = departmentService.existByDeptId(departmentRequest.getDeptId());
        if(result){
            
        }
    }
}
