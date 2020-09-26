package com.group.stockquotemanager.controller.service.integration;

import com.group.stockquotemanager.controller.dto.integration.StockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8080/stock", name = "StockManagerFeign")
public interface StockManagerFeign {

    @GetMapping
    List<StockDTO> findAll();
}
