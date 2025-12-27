package com.codeintune.bookstore.dto.error;

import lombok.Data;

import java.time.Instant;

@Data
public class ApiError {

    private String message;
    private Integer status;
    private Instant timestamp;
}
