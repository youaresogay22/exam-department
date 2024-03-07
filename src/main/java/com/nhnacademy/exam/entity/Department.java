package com.nhnacademy.exam.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Department {

    @Id
    private String id;
    private String name;
    private String department;
    @Column(name = "departmentcode")
    private String departmentCode;

}
