package com.codeintune.bookstore.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookSaleDomainException extends RuntimeException {

    private final HttpStatus httpStatus;

    public BookSaleDomainException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public BookSaleDomainException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
