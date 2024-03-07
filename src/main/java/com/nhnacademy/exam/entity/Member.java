package com.nhnacademy.exam.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Member {
    @Id
    private String id;
    private String name;
    @ManyToOne
    private Department department;
}
