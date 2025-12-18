package com.codeintune.bookstore.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookRecordDomainException extends RuntimeException {

    private final HttpStatus  httpStatus;

    public BookRecordDomainException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    public BookRecordDomainException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
