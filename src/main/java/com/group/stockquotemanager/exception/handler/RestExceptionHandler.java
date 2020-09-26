package com.group.stockquotemanager.exception.handler;

import com.group.stockquotemanager.dto.DefaultErrorDTO;
import com.group.stockquotemanager.exception.InvalidStockException;
import com.group.stockquotemanager.exception.StockNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<?> handleStockNotFoundException(StockNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(new DefaultErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> handleDateTimeParseException(DateTimeParseException e) {
        log.info(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(new DefaultErrorDTO("The given date is not on yyyy-MM-dd format!"));
    }

    @ExceptionHandler(InvalidStockException.class)
    public ResponseEntity<?> handleInvalidStockException(InvalidStockException e) {
        return ResponseEntity.status(BAD_REQUEST).body(new DefaultErrorDTO(e.getMessage()));
    }
}
