package com.nhnacademy.exam.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import java.time.LocalDateTime;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 12/05/2023
 */

@Getter
public class ErrorResponse {
    private final String title;
    private final int status;
    private final String instance;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime timestamp;

    public ErrorResponse(String title, int status, String instance) {
        this.title = title;
        this.status = status;
        this.instance = instance;
        this.timestamp = LocalDateTime.now();
    }

}
