package com.group.stockquotemanager.controller.dto;

import com.group.stockquotemanager.controller.model.Stock;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class StockQuoteDTO {

    private String id;

    private Map<String, String> quotes;

    public StockQuoteDTO() {
        quotes = new LinkedHashMap<>();
    }

    public StockQuoteDTO fromEntity(Stock stock) {
        this.id = stock.getId();
        stock.getQuotes().forEach(quote -> quotes.put(quote.getDate().toString(), quote.getValue()));
        return this;
    }
}
