package com.pppp0722.shoesstore.repository.exception;

public class JdbcEmptyResultException extends RuntimeException {

    public JdbcEmptyResultException() {

    }

    public JdbcEmptyResultException(String message) {
        super(message);
    }
}
