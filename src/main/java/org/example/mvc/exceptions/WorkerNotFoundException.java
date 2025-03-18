package org.example.mvc.exceptions;

public class WorkerNotFoundException extends Exception {
    public WorkerNotFoundException(String message) {
        super(message);
    }
}
