package com.group.stockquotemanager.controller;

import com.group.stockquotemanager.dto.StockQuoteDTO;
import com.group.stockquotemanager.model.Stock;
import com.group.stockquotemanager.service.CacheStockService;
import com.group.stockquotemanager.service.StockQuoteManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
public class StockQuoteManagerController {

    @Autowired
    private StockQuoteManagerService service;

    @Autowired
    private CacheStockService cacheStockService;

    @PostMapping
    public ResponseEntity<StockQuoteDTO> save(@Valid @RequestBody StockQuoteDTO stockQuoteDTO) {
        Stock stock = new Stock().fromDTO(stockQuoteDTO);
        service.create(stock);
        return ResponseEntity.status(CREATED).body(stockQuoteDTO);
    }

    @GetMapping
    public ResponseEntity<List<StockQuoteDTO>> findAll() {
        List<Stock> stocks = service.findAll();
        List<StockQuoteDTO> stockQuotesDTO = stocks.stream()
                .map(stock -> new StockQuoteDTO().fromEntity(stock))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(stockQuotesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockQuoteDTO> findById(@PathVariable String id) {
        Stock stock = service.findById(id);
        StockQuoteDTO stockQuoteDTO = new StockQuoteDTO().fromEntity(stock);
        return ResponseEntity.ok().body(stockQuoteDTO);
    }

    @DeleteMapping("/stockcache")
    public ResponseEntity<Void> cleanCache() {
        log.info("Received request to clean the cache");
        cacheStockService.clean();
        return ResponseEntity.ok().build();
    }
}
