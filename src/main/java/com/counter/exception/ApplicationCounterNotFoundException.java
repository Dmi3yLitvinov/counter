package com.counter.exception;

public class ApplicationCounterNotFoundException extends RuntimeException {
    public ApplicationCounterNotFoundException(String name) {
        super("Counter '%s' does not exist".formatted(name));
    }
}
