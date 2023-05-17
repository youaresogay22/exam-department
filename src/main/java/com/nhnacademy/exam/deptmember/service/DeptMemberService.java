package com.nhnacademy.exam.deptmember.service;

import com.nhnacademy.exam.deptmember.dto.DeptMemberResponse;
import com.nhnacademy.exam.deptmember.entity.DepartmentMember;
import com.nhnacademy.exam.deptmember.repository.DeptMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 11/05/2023
 */

@Service
@RequiredArgsConstructor
@Transactional
public class DeptMemberService {
    private final DeptMemberRepository deptMemberRepository;

    public void save(DepartmentMember departmentMember){
        deptMemberRepository.save(departmentMember);
    }
    public void removeAll(){
        deptMemberRepository.deleteAll();
    }

    public List<DeptMemberResponse>  getDeptMembers(List<String> deptIds){
        return deptMemberRepository.findDeptMembers(deptIds);
    }
}
