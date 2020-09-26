package com.group.stockquotemanager.controller;

import com.group.stockquotemanager.factory.MockFactory;
import com.group.stockquotemanager.repository.StockQuoteRepository;
import com.group.stockquotemanager.service.CacheStockService;
import com.group.stockquotemanager.service.StockQuoteManagerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static com.group.stockquotemanager.factory.MockFactory.mockStockQuoteDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StockQuoteManagerController.class)
public class StockQuoteManagerControllerTest extends AbstractMVCController {

    @Mock
    private StockQuoteRepository repository;

    @Mock
    private StockQuoteManagerService service;

    @Mock
    private CacheStockService cacheStockService;

    @Before
    public void before() {
        super.setup();
    }

    @Test
    public void shouldSave() throws Exception {
        when(service.create(any())).thenReturn(MockFactory.mockStock());

        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(mockStockQuoteDTO())))
                .andExpect(status().isCreated());
    }

}