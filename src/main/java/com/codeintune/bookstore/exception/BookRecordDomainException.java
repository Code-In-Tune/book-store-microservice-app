package com.codeintune.bookstore.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookRecordDomainException extends RuntimeException {

    private final HttpStatus  httpStatus;
    private final String key;
    private final Object[] arguments;


    public BookRecordDomainException(HttpStatus httpStatus, String message, String key, Object... arguments) {
        super(message);
        this.httpStatus = httpStatus;
        this.key = key;
        this.arguments = arguments;
    }
}
