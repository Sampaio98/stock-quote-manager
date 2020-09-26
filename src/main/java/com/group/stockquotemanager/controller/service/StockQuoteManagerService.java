package com.group.stockquotemanager.controller.service;

import com.group.stockquotemanager.controller.dto.integration.StockDTO;
import com.group.stockquotemanager.controller.exception.InvalidStockException;
import com.group.stockquotemanager.controller.model.Stock;
import com.group.stockquotemanager.controller.repository.StockQuoteRepository;
import com.group.stockquotemanager.controller.service.integration.StockManagerFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StockQuoteManagerService {

    @Autowired
    private StockQuoteRepository repository;

    @Autowired
    private StockManagerFeign stockManagerFeign;

    @Autowired
    private CacheStockService cacheStockService;

    public Stock create(Stock stock) {
        validateOnStorage(stock);
        return repository.save(stock);
    }

    private void validateOnStorage(Stock stock) {
        Set<StockDTO> stocksManager = cacheStockService.getCache();
        stocksManager.stream()
                .filter(stockDTO -> stockDTO.getId().equals(stock.getId()))
                .findFirst().orElseGet(() -> findStockName(stock));
    }

    private StockDTO findStockName(Stock stock) {
        List<StockDTO> stocksManager = stockManagerFeign.findAll();
        StockDTO stockDTOFound = stocksManager.stream()
                .filter(stockDTO -> stockDTO.getId().equals(stock.getId()))
                .findFirst()
                .orElseThrow(() -> new InvalidStockException(stock.getId()));
        if (cacheStockService.isCacheEmpty()) {
            cacheStockService.register(stocksManager);
        }
        return stockDTOFound;
    }
}
