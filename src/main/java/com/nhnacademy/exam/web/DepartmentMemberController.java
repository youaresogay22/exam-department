package com.nhnacademy.exam.web;

import com.nhnacademy.exam.deptmember.dto.DeptMemberResponse;
import com.nhnacademy.exam.deptmember.service.DeptMemberService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 11/05/2023
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/department-members")
public class DepartmentMemberController {
    private final DeptMemberService deptMemberService;

    @GetMapping
    public List<DeptMemberResponse> getDepartmentMembers(@RequestParam(value = "departmentIds") String departmentIds) throws MissingServletRequestParameterException {
        if(Strings.isBlank(departmentIds)){
            throw new MissingServletRequestParameterException("departmentIds","String");
        }
        List<String> deptIds = List.of(departmentIds.split(","));
        return deptMemberService.getDeptMembers(deptIds);
    }
}
