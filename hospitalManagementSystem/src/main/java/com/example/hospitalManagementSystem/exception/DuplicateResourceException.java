package com.example.hospitalManagementSystem.exception;

import org.springframework.http.HttpStatus;

public class DuplicateResourceException  extends BusinessException {
    public DuplicateResourceException(String message) {
        super(message, "DUPLICATE_RESOURCE", HttpStatus.CONFLICT);
    }
}
