package com.example.hospitalManagementSystem.exception;

// whenever any resource not found then this class method show custom message

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}
