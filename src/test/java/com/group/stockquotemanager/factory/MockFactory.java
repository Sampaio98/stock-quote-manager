package com.group.stockquotemanager.factory;

import com.group.stockquotemanager.dto.StockQuoteDTO;
import com.group.stockquotemanager.model.Stock;

import java.time.LocalDate;

public class MockFactory {

    public static StockQuoteDTO mockStockQuoteDTO() {
        StockQuoteDTO stockQuoteDTO = new StockQuoteDTO();
        stockQuoteDTO.setId("test");
        stockQuoteDTO.getQuotes().put(LocalDate.now().minusDays(1).toString(), "10");
        stockQuoteDTO.getQuotes().put(LocalDate.now().toString(), "20");
        return stockQuoteDTO;
    }

    public static Stock mockStock() {
        return new Stock();
    }

}
