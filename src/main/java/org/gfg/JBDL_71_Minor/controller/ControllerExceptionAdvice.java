package org.gfg.JBDL_71_Minor.controller;

import lombok.extern.slf4j.Slf4j;
import org.gfg.JBDL_71_Minor.exceptions.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionAdvice {

    @ExceptionHandler(value = TransactionException.class)
    public ResponseEntity<String> takeAction(TransactionException transactionException) {
        log.error("transactionException occurred: {}", transactionException);
        return new ResponseEntity<>(transactionException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
