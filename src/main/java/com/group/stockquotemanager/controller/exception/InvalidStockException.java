package com.group.stockquotemanager.controller.exception;

import static java.text.MessageFormat.format;

public class InvalidStockException extends RuntimeException {
    private static final String INVALID_STOCK_MESSAGE = "The stock {0} is not on our storage!";

    public InvalidStockException(String message) {
        super(format(INVALID_STOCK_MESSAGE, message));
    }
}
