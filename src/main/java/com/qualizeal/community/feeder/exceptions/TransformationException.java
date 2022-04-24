package com.qualizeal.community.feeder.exceptions;

public class TransformationException extends RuntimeException{
    public TransformationException() {
        super("Unable to perform the transformation");
    }
}
