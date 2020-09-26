package com.group.stockquotemanager.controller;

import com.group.stockquotemanager.exception.InvalidStockException;
import com.group.stockquotemanager.exception.StockNotFoundException;
import com.group.stockquotemanager.service.CacheStockService;
import com.group.stockquotemanager.service.StockQuoteManagerService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static com.group.stockquotemanager.factory.MockFactory.mockStock;
import static com.group.stockquotemanager.factory.MockFactory.mockStockDTOList;
import static com.group.stockquotemanager.factory.MockFactory.mockStockList;
import static com.group.stockquotemanager.factory.MockFactory.mockStockQuoteDTO;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class StockQuoteManagerControllerTest extends AbstractMVCController {

    @MockBean
    private StockQuoteManagerService service;

    @Autowired
    private CacheStockService cacheStockService;

    @Before
    public void setup() {
        super.setup();
    }

    @Test
    public void shouldSaveTest() throws Exception {
        when(service.create(any())).thenReturn(mockStock());

        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(mockStockQuoteDTO())))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldNotSaveStock() throws Exception {
        when(service.create(any())).thenThrow(new InvalidStockException("stock"));

        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(mockStockQuoteDTO())))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("The stock stock is not on our storage!"));
    }

    @Test
    public void shouldNotSaveWithoutBody() throws Exception {
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages").isArray());
    }

    @Test
    public void shouldFindAllWithSuccess() throws Exception {
        when(service.findAll()).thenReturn(mockStockList());

        mockMvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    public void shouldFindByIdWithSuccess() throws Exception {
        when(service.findById(any())).thenReturn(mockStock());

        mockMvc.perform(get("/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("petr4"))
                .andExpect(jsonPath("$.quotes").isMap());
    }

    @Test
    public void shouldNotFindById() throws Exception {
        when(service.findById(any())).thenThrow(new StockNotFoundException("1"));

        mockMvc.perform(get("/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Stock 1 not found!"));
    }

    @Test
    public void shouldCleanCache() throws Exception {
        cacheStockService.register(new ArrayList<>(mockStockDTOList()));

        mockMvc.perform(delete("/stockcache")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertTrue("Cache was not cleaned!", cacheStockService.isCacheEmpty());
    }

}