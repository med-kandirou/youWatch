package com.medkandirou.youwatch.Exception;

public class RecordAlreadyExistsException extends RuntimeException{
    private RecordAlreadyExistsException(String message){
        super(message);
    }
}
