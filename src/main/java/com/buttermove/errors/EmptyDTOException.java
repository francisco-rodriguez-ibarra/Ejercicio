package com.buttermove.errors;

public class EmptyDTOException extends Exception{
    public EmptyDTOException(String errorMessage) {
        super(errorMessage);
    }
}
