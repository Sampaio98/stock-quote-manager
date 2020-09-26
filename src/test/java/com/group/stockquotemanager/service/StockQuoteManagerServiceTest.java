package com.group.stockquotemanager.service;

import com.group.stockquotemanager.repository.StockQuoteRepository;
import com.group.stockquotemanager.service.integration.StockManagerFeign;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.group.stockquotemanager.factory.MockFactory.mockStock;
import static org.mockito.Matchers.any;
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
    public void shouldCreate() {
        when(repository.save(any())).thenReturn(mockStock());

        service.create(mockStock());
    }

}