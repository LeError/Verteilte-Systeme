package de.dhbw.vs.parking.exceptions;

public class CarNotCheckedOutException extends Exception {

    public CarNotCheckedOutException() {
        super("It was tried to perform a action for a Visit but the visit is still going on! (Car not checked out)");
    }

    public CarNotCheckedOutException(String msg) {
        super(msg);
    }

    public CarNotCheckedOutException(String msg, Throwable trace) {
        super(msg, trace);
    }

}
