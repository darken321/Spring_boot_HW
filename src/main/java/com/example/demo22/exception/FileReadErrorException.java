package com.example.demo22.exception;

public class FileReadErrorException extends RuntimeException {
    public FileReadErrorException(String message) {
        super(message);
    }
}
