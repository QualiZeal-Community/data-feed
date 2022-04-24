package com.qualizeal.community.feeder.exceptions;

public class MappingException extends RuntimeException{
    public MappingException() {
        super("Unable to convert data to map or object");
    }
}
