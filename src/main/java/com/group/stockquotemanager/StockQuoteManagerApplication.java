package com.group.stockquotemanager;

import com.group.stockquotemanager.controller.model.Quote;
import com.group.stockquotemanager.controller.model.Stock;
import com.group.stockquotemanager.controller.repository.StockQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDate;

@SpringBootApplication
@EnableFeignClients
public class StockQuoteManagerApplication implements CommandLineRunner {

	@Autowired
	private StockQuoteRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(StockQuoteManagerApplication.class, args);
	}

	//TODO REGISTRAR-SE NO STOCK-MANAGER TODA VEZ Q A APLICAÇÃO SUBIR

	@Override
	public void run(String... args) throws Exception {
		Stock stock = new Stock();
		stock.setId("PETR4");

		Quote quote = new Quote();
		quote.setDate(LocalDate.now().minusDays(1));
		quote.setValue("10 conto");
		quote.setStock(stock);

		Quote quote2 = new Quote();
		quote2.setDate(LocalDate.now());
		quote2.setValue("20 conto");
		quote2.setStock(stock);

		stock.getQuotes().add(quote);
		stock.getQuotes().add(quote2);

		repository.save(stock);
	}
}
