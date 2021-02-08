package com.group.stockquotemanager.exception.handler;

import com.group.stockquotemanager.dto.DefaultErrorDTO;
import com.group.stockquotemanager.dto.RequiredFieldErrorDTO;
import com.group.stockquotemanager.exception.InvalidStockException;
import com.group.stockquotemanager.exception.StockNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<?> handleStockNotFoundException(StockNotFoundException e, HttpHeaders headers, WebRequest request) {
        return super.handleExceptionInternal(e, new DefaultErrorDTO(e.getMessage()), headers, NOT_FOUND, request);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> handleDateTimeParseException(DateTimeParseException e, HttpHeaders headers, WebRequest request) {
        log.info(e.getMessage());
        return super.handleExceptionInternal(e, new DefaultErrorDTO("The given date is not on yyyy-MM-dd format!"), headers, BAD_REQUEST, request);
    }

    @ExceptionHandler(InvalidStockException.class)
    public ResponseEntity<?> handleInvalidStockException(InvalidStockException e, HttpHeaders headers, WebRequest request) {
        return super.handleExceptionInternal(e, new DefaultErrorDTO(e.getMessage()), headers, UNPROCESSABLE_ENTITY, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> messages = e.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return super.handleExceptionInternal(e, new RequiredFieldErrorDTO(messages), headers, BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaughtException(Exception e, HttpHeaders headers, WebRequest request) {
        log.info(e.getMessage());
        return super.handleExceptionInternal(e, new DefaultErrorDTO("Ocorreu um erro durante o uso, estamos trabalhando nisso!"), headers, INTERNAL_SERVER_ERROR, request);
    }
}
