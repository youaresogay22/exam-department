package com.nhnacademy.exam.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DepartmentAndMemberDTO {
    @JsonProperty("사번")
    private String id;
    @JsonProperty("이름")
    private String name;
    @JsonProperty("부서")
    private String department;
    @JsonProperty("부서코드")
    private String departmentCode;
}
