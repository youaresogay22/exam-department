package com.nhnacademy.exam.parser.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 10/05/2023
 */
@Getter
public class DepartmentInfoDto {
    //부서코드
    private String deptId;
    //부서명
    private String deptName;
    //사번
    private String empId;
    //이름
    private String empName;


    @JsonCreator
    public DepartmentInfoDto(@JsonProperty("부서코드") String deptId, @JsonProperty("부서") String deptName, @JsonProperty("사번") String empId, @JsonProperty("이름") String empName) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.empId = empId;
        this.empName = empName;
    }
}
