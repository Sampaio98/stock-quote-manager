package com.group.stockquotemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.stockquotemanager.StockQuoteManagerApplication;
import com.group.stockquotemanager.config.JacksonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@TestPropertySource(locations = "classpath:test-application.properties")
@ContextConfiguration(classes = {StockQuoteManagerApplication.class, JacksonConfig.class})
public class AbstractMVCController {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    protected ObjectMapper mapper;

    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
