package com.ust.review.exception;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(String s) {
        super(s);
    }
}
