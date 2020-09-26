package com.group.stockquotemanager.exception;

import java.text.MessageFormat;

public class StockNotFoundException extends RuntimeException {
    private static final String STOCK_NOT_FOUND_MESSAGE = "Stock {0} not found!";

    public StockNotFoundException(String id) {
        super(MessageFormat.format(STOCK_NOT_FOUND_MESSAGE, id));
    }

}
