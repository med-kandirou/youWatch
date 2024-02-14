package com.medkandirou.youwatch.exception;

public class RecordAlreadyExistsException extends RuntimeException{
    private RecordAlreadyExistsException(String message){
        super(message);
    }
}
