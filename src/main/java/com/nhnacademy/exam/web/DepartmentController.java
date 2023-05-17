package com.nhnacademy.exam.web;


import com.nhnacademy.exam.department.validator.DepartmentValidator;
import com.nhnacademy.exam.department.dto.DepartmentDto;
import com.nhnacademy.exam.department.dto.DepartmentRequest;
import com.nhnacademy.exam.department.dto.DepartmentCreatedResponse;
import com.nhnacademy.exam.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 16/05/2023
 */
@RequiredArgsConstructor
@RequestMapping("/department")
@RestController
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentValidator departmentValidator;

    //등록
    @PostMapping
    public ResponseEntity<DepartmentCreatedResponse> register(@RequestBody DepartmentRequest departmentRequest, BindingResult bindingResult){
        DepartmentCreatedResponse departmentCreatedResponse = departmentService.save(departmentRequest);
        if(bindingResult.hasErrors()){

        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departmentCreatedResponse);
    }

    //수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@PathVariable("id")String deptId, @RequestBody DepartmentRequest departmentRequest){
        departmentService.update(deptId, departmentRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id")String deptId){
        departmentService.deleteByDeptId(deptId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //조회
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("id")String deptId){
        DepartmentDto departmentDto=departmentService.getDepartment(deptId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentDto);
    }

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(departmentValidator);
    }

}

