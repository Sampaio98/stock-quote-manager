package com.group.stockquotemanager.cache;

import com.group.stockquotemanager.dto.integration.StockDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheStock {
    private Set<StockDTO> stockDTOS = new HashSet<>();
}
