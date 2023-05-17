package com.nhnacademy.exam.department.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 16/05/2023
 */

@Getter
public class DepartmentRequest {
    @JsonProperty("id")
    @NotBlank
    private String deptId;
    @JsonProperty("name")
    @NotBlank
    private String deptName;
}
