package com.codeintune.bookstore.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookSaleDomainException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String key;
    private final String message;
    private final Object[] arguments;

    public BookSaleDomainException(HttpStatus httpStatus, String message, String key, Object... arguments) {
        this.httpStatus = httpStatus;
        this.key = key;
        this.message = message;
        this.arguments = arguments;
    }
}
