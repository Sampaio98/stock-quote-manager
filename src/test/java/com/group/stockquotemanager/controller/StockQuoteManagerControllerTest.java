package com.group.stockquotemanager.controller;

import com.group.stockquotemanager.service.StockQuoteManagerService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.group.stockquotemanager.factory.MockFactory.mockStock;
import static com.group.stockquotemanager.factory.MockFactory.mockStockQuoteDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StockQuoteManagerController.class)
public class StockQuoteManagerControllerTest extends AbstractMVCController {

    @MockBean
    private StockQuoteManagerService service;

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

}