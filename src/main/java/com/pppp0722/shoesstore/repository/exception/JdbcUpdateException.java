package com.pppp0722.shoesstore.repository.exception;

public class JdbcUpdateException extends RuntimeException {

    public JdbcUpdateException() {

    }

    public JdbcUpdateException(String message) {
        super(message);
    }
}
