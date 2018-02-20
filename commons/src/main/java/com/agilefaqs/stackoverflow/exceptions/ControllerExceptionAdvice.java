package com.agilefaqs.stackoverflow.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionAdvice {

    private static Logger log = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity handleSQLException(HttpServletRequest request, ApplicationException ex){
        log.info("ApplicationException Occured:: URL="+request.getRequestURL(), ex);
        return ResponseEntity
            .status(ex.getStatus())
            .body(ex.getMessage());

    }
}