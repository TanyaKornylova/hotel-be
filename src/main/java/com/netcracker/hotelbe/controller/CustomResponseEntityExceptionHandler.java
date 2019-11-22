package com.netcracker.hotelbe.controller;


import com.google.common.base.Throwables;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> fieldErrors = ex.getBindingResult().getAllErrors();
        List<String> errorMessages = fieldErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handlePSQLException(RuntimeException runtimeException) {
        Throwable rootCause = Throwables.getRootCause(runtimeException);
        String logMessage;
        String responseMessage = runtimeException.getMessage();
        if (rootCause instanceof PSQLException) {
            logMessage = rootCause.getMessage();
            responseMessage = logMessage.split("Detail: ")[1];
            if (logger.isErrorEnabled()) {
                logger.error(logMessage);
            }
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }
}
