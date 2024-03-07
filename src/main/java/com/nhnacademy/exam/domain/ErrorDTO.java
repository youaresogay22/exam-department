package com.nhnacademy.exam.domain;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Data
public class ErrorDTO {
    private String title;
    private HttpStatus status;
    private Timestamp timestamp;
}
