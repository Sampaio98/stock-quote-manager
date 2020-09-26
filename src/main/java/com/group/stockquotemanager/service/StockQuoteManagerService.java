package com.group.stockquotemanager.service;

import com.group.stockquotemanager.dto.integration.StockDTO;
import com.group.stockquotemanager.exception.InvalidStockException;
import com.group.stockquotemanager.exception.StockNotFoundException;
import com.group.stockquotemanager.model.Stock;
import com.group.stockquotemanager.repository.StockQuoteRepository;
import com.group.stockquotemanager.service.integration.StockManagerFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class StockQuoteManagerService {

    @Autowired
    private StockQuoteRepository repository;

    @Autowired
    private StockManagerFeign stockManagerFeign;

    @Autowired
    private CacheStockService cacheStockService;

    public List<Stock> findAll() {
        return repository.findAll();
    }

    public Stock findById(String id) {
        return repository.findById(id).orElseThrow(() -> new StockNotFoundException(id));
    }

    public Stock create(Stock stock) {
        validateOnStorage(stock);
        return repository.save(stock);
    }

    private void validateOnStorage(Stock stock) {
        log.info("Checking on cache");
        Set<StockDTO> stocksManager = cacheStockService.getCache();
        stocksManager.stream()
                .filter(stockDTO -> stockDTO.getId().equals(stock.getId()))
                .findFirst().orElseGet(() -> findStockName(stock));
    }

    private StockDTO findStockName(Stock stock) {
        log.info("Checking on database");
        List<StockDTO> stocksManager = stockManagerFeign.findAll();
        StockDTO stockDTOFound = stocksManager.stream()
                .filter(stockDTO -> stockDTO.getId().equals(stock.getId()))
                .findFirst()
                .orElseThrow(() -> new InvalidStockException(stock.getId()));
        if (cacheStockService.isCacheEmpty()) {
            log.info("Register on cache");
            cacheStockService.register(stocksManager);
        }
        return stockDTOFound;
    }
}
