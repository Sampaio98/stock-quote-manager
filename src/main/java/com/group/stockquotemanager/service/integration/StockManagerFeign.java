package com.group.stockquotemanager.service.integration;

import com.group.stockquotemanager.dto.integration.StockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "http://172.17.0.3:8080/stock", name = "StockManagerFeign")
public interface StockManagerFeign {

    @GetMapping
    List<StockDTO> findAll();
}
