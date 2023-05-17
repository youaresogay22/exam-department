package com.nhnacademy.exam.department.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 16/05/2023
 */

@Getter
public class DepartmentCreatedResponse {
    @JsonProperty("id")
    private String deptId;

    public DepartmentCreatedResponse(String deptId) {
        this.deptId = deptId;
    }
}
