package com.codeintune.bookstore.controller.advice;

import com.codeintune.bookstore.dto.error.ApiError;
import com.codeintune.bookstore.exception.BookRecordDomainException;
import com.codeintune.bookstore.exception.BookSaleDomainException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
@Hidden
public class GlobalExceptionHandler {

    private final MessageSource messageSource;


    @ExceptionHandler(BookRecordDomainException.class)
    public ResponseEntity<@NonNull ApiError> handleBookRecordDomainException(BookRecordDomainException ex, Locale locale) {
        ApiError apiError = new ApiError();
        apiError.setMessage(messageSource.getMessage(ex.getKey(), ex.getArguments(),locale));
        apiError.setStatus(ex.getHttpStatus().value());
        apiError.setTimestamp(Instant.now());

        return ResponseEntity.status(ex.getHttpStatus()).body(apiError);
    }

    @ExceptionHandler(BookSaleDomainException.class)
    public ResponseEntity<@NonNull ApiError> handleBookSaleDomainException(BookSaleDomainException ex, Locale locale) {
        ApiError apiError = new ApiError();
        apiError.setMessage(messageSource.getMessage(ex.getKey(), ex.getArguments(),locale));
        apiError.setStatus(ex.getHttpStatus().value());
        apiError.setTimestamp(Instant.now());

        return ResponseEntity.status(ex.getHttpStatus()).body(apiError);
    }
}
