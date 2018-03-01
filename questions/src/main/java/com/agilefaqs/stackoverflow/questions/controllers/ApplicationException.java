package com.agilefaqs.stackoverflow.questions.controllers;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

    private String message;
    private HttpStatus status;


    public ApplicationException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public ApplicationException(Throwable throwable, HttpStatus status) {
        super(throwable);
        this.message = throwable.getMessage();
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
