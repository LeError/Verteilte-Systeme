package de.dhbw.vs.parking.exceptions;

public class IllegalDataStateException extends Exception {

    public IllegalDataStateException() {
        super();
    }

    public IllegalDataStateException(String msg) {
        super(msg);
    }

    public IllegalDataStateException(String msg, Throwable trace) {
        super(msg, trace);
    }

}