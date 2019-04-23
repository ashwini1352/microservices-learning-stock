package com.microservices.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.stock.dbservice.model.Quotes;
import com.microservices.stock.dbservice.repository.QuotesRepository;

@RestController
@RequestMapping("/rest/db")
public class DBServiceResource {
	private QuotesRepository quotesRepository;

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username) {
		return quotesRepository.findByUserName(username).stream().map(Quotes::getQuote).collect(Collectors.toList());

	}
}
