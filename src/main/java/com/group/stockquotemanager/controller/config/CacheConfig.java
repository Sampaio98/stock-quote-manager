package com.group.stockquotemanager.controller.config;

import com.group.stockquotemanager.controller.cache.CacheStock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public CacheStock cacheStock() {
        return new CacheStock();
    }
}
