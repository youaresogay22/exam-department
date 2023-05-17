package com.nhnacademy.exam.deptmember.repository;

import com.nhnacademy.exam.deptmember.dto.DeptMemberResponse;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 11/05/2023
 */
@NoRepositoryBean
public interface DeptMemberRepositoryCustom {
    List<DeptMemberResponse> findDeptMembers(List<String> deptIds);
}
