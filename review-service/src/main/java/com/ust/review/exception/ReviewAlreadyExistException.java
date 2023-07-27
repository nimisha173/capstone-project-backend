package com.ust.review.exception;

public class ReviewAlreadyExistException extends RuntimeException {
    public ReviewAlreadyExistException(String s) {
        super(s);
    }
}
