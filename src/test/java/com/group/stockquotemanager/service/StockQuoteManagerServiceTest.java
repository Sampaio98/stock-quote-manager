package com.group.stockquotemanager.service;

import com.group.stockquotemanager.model.Stock;
import com.group.stockquotemanager.repository.StockQuoteRepository;
import com.group.stockquotemanager.service.integration.StockManagerFeign;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static com.group.stockquotemanager.factory.MockFactory.mockStock;
import static com.group.stockquotemanager.factory.MockFactory.mockStockDTOList;
import static com.group.stockquotemanager.factory.MockFactory.mockStockList;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockQuoteManagerServiceTest {

    @InjectMocks
    private StockQuoteManagerService service;

    @Mock
    private StockQuoteRepository repository;

    @Mock
    private StockManagerFeign stockManagerFeign;

    @Mock
    private CacheStockService cacheStockService;

    @Test
    public void shouldFindAll() {
        when(repository.findAll()).thenReturn(mockStockList());

        List<Stock> stock = service.findAll();

        assertNotEquals("Stock is null!", new ArrayList<>(), stock);
        verify(repository).findAll();
    }

    @Test
    public void shouldFindById() {
        when(repository.findById(any())).thenReturn(Optional.of(mockStock()));

        Stock stock = service.findById("1");

        assertNotNull("Stock is not found!", stock);
        verify(repository).findById("1");
    }

    @Test
    public void shouldCreateWithoutCache() {
        doNothing().when(cacheStockService).register(any());
        when(cacheStockService.getCache()).thenReturn(new HashSet<>());
        when(stockManagerFeign.findAll()).thenReturn(new ArrayList<>(mockStockDTOList()));
        when(repository.save(any())).thenReturn(mockStock());
        when(cacheStockService.isCacheEmpty()).thenReturn(true);

        Stock stock = service.create(mockStock());

        assertNotNull("Stock was not saved!", stock);
        verify(cacheStockService).register(any());
        verify(cacheStockService).getCache();
        verify(cacheStockService).isCacheEmpty();
        verify(stockManagerFeign).findAll();
        verify(repository).save(any());
    }

    @Test
    public void shouldCreateWithCache() {
        when(cacheStockService.getCache()).thenReturn(mockStockDTOList());
        when(repository.save(any())).thenReturn(mockStock());

        Stock stock = service.create(mockStock());

        assertNotNull("Stock was not saved!", stock);
        verify(cacheStockService).getCache();
        verify(repository).save(any());
    }

}