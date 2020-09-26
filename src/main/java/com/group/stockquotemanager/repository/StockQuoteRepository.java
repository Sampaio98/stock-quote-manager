package com.group.stockquotemanager.repository;

import com.group.stockquotemanager.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockQuoteRepository extends JpaRepository<Stock, String> {
}
