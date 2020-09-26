package com.group.stockquotemanager.factory;

import com.group.stockquotemanager.dto.StockQuoteDTO;
import com.group.stockquotemanager.dto.integration.StockDTO;
import com.group.stockquotemanager.model.Quote;
import com.group.stockquotemanager.model.Stock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MockFactory {

    static Random random = new Random();

    public static StockQuoteDTO mockStockQuoteDTO() {
        StockQuoteDTO stockQuoteDTO = new StockQuoteDTO();
        stockQuoteDTO.setId("test");
        stockQuoteDTO.getQuotes().put(LocalDate.now().minusDays(1).toString(), "10");
        stockQuoteDTO.getQuotes().put(LocalDate.now().toString(), "20");
        return stockQuoteDTO;
    }

    public static Stock mockStock() {
        Stock stock = new Stock();
        stock.setId("petr4");
        Quote quote = new Quote(null, LocalDate.now().minusDays(2), "10", stock);
        Quote quote2 = new Quote(null, LocalDate.now().minusDays(1), "20", stock);
        stock.getQuotes().addAll(Arrays.asList(quote, quote2));
        return stock;
    }

    public static List<Stock> mockStockList() {
        List<Stock> stocks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            stocks.add(mockStock());
        }
        return stocks;
    }

    public static Set<StockDTO> mockStockDTOList() {
        Set<StockDTO> stocksDTO = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            stocksDTO.add(new StockDTO("id".concat(String.valueOf(random.nextInt())),
                    "description".concat(String.valueOf(random.nextInt()))));
        }
        stocksDTO.add(new StockDTO("petr4", "test"));
        return stocksDTO;
    }
}
