package org.example.mvc.exceptions;

public class DuplicateWorkerException extends Exception {
    public DuplicateWorkerException(String message) {
        super(message);
    }
}
