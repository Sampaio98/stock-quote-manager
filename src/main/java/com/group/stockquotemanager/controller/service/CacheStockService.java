package com.group.stockquotemanager.controller.service;

import com.group.stockquotemanager.controller.cache.CacheStock;
import com.group.stockquotemanager.controller.dto.integration.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CacheStockService {

    @Autowired
    private CacheStock cacheStock;

    public void clean() {
        cacheStock.getStockDTOS().clear();
    }

    public boolean isCacheEmpty() {
        return cacheStock.getStockDTOS().isEmpty();
    }

    public Set<StockDTO> getCache() {
        return cacheStock.getStockDTOS();
    }

    public void register(List<StockDTO> stocksManager) {
        cacheStock.getStockDTOS().addAll(stocksManager);
    }
}
