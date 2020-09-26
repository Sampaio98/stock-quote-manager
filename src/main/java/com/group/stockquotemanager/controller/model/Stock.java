package com.group.stockquotemanager.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group.stockquotemanager.controller.dto.StockQuoteDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "STOCK_QUOTE")
public class Stock {

    @Id
    @EqualsAndHashCode.Include
    @NotBlank(message = "The Stock Quote 'id' must not be empty!")
    private String id;

    @JsonIgnoreProperties(value = "stock")
    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<Quote> quotes;

    public Stock() {
        quotes = new ArrayList<>();
    }

    public Stock fromDTO(StockQuoteDTO stockQuoteDTO) {
        this.id = stockQuoteDTO.getId();
        stockQuoteDTO.getQuotes().forEach((date, value) -> {
            Quote quote = Quote.builder()
                    .date(LocalDate.parse(date))
                    .value(value)
                    .stock(this)
                    .build();
            this.quotes.add(quote);
        });

        return this;
    }

}
