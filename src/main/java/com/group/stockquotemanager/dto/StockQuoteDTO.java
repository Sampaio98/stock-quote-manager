package com.group.stockquotemanager.dto;

import com.group.stockquotemanager.model.Stock;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class StockQuoteDTO {

    @NotBlank(message = "The Stock Quote 'id' must not be empty!")
    private String id;

    @NotEmpty(message = "The Stock Quote 'quotes' must not be empty!")
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
