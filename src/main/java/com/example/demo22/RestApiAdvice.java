package com.example.demo22;

import com.example.demo22.exception.CannotReadFileException;
import com.example.demo22.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class RestApiAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDTO handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ExceptionDTO(e.getMessage(), UUID.randomUUID(), ZonedDateTime.now(), null);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDTO handleUserNotFoundException(ResourceNotFoundException e) {
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .uuid(UUID.randomUUID())
                .message(e.getMessage())
                .exceptionServerTime(ZonedDateTime.now())
                .build();
        log.warn(String.valueOf(exceptionDTO));
        return new ExceptionDTO(e.getMessage(), UUID.randomUUID(), ZonedDateTime.now(), null);
    }

    @ExceptionHandler(CannotReadFileException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDTO handleCannotCreateFileException(CannotReadFileException e) {
        return new ExceptionDTO(e.getMessage(), UUID.randomUUID(), ZonedDateTime.now(), null);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDTO handleCannotParseException(NumberFormatException e) {
        return new ExceptionDTO(e.getMessage(), UUID.randomUUID(), ZonedDateTime.now(), null);
    }

}
