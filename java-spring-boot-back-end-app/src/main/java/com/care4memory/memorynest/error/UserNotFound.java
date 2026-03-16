package com.care4memory.memorynest.error;

public class UserNotFound extends Exception {

    public UserNotFound(String message) {
        super(message);
    }
    public UserNotFound(String message, Exception e) {
        super(message, e);
    }
}
