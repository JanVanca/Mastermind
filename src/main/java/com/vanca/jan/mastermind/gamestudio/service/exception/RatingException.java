package com.vanca.jan.mastermind.gamestudio.service.exception;

public class RatingException extends Exception {
    public RatingException(String message) {
        super(message);
    }

    public RatingException(String message, Throwable cause) {
        super(message, cause);
    }
}
