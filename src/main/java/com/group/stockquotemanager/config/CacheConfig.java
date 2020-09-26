package com.group.stockquotemanager.config;

import com.group.stockquotemanager.cache.CacheStock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public CacheStock cacheStock() {
        return new CacheStock();
    }
}
