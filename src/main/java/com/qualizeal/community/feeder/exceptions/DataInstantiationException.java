package com.qualizeal.community.feeder.exceptions;

public class DataInstantiationException extends RuntimeException{
    public DataInstantiationException() {
        super("Unable to read the data file");
    }
}
