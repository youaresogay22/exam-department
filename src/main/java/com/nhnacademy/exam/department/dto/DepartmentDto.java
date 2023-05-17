package com.nhnacademy.exam.department.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import java.util.Objects;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 10/05/2023
 */
@Getter
@ToString
public class DepartmentDto {
    @JsonProperty("id")
    private String deptId;
    @JsonProperty("name")
    private String deptName;

    public String getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public DepartmentDto(String deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentDto that = (DepartmentDto) o;
        return Objects.equals(deptId, that.deptId) && Objects.equals(deptName, that.deptName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptId, deptName);
    }
}
