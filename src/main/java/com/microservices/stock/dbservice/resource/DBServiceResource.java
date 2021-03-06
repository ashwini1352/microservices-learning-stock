package com.microservices.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.stock.dbservice.model.Quote;
import com.microservices.stock.dbservice.model.Quotes;
import com.microservices.stock.dbservice.repository.QuotesRepository;

@RestController
@RequestMapping("/rest/db")
public class DBServiceResource {

	private QuotesRepository quotesRepository;

	public DBServiceResource(QuotesRepository quotesRepository) {
		this.quotesRepository = quotesRepository;
	}

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username) {
		return getQuotesByUsername(username);

	}

	/**
	 * @param username
	 * @return
	 */
	private List<String> getQuotesByUsername(final String username) {
		return quotesRepository.findByUserName(username).stream().map(Quote::getQuote).collect(Collectors.toList());
	}

	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes) {
		quotes.getQuotes().stream().map(quote -> new Quote(quotes.getUsername(), quote))
				.forEach(quote -> quotesRepository.save(quote));
		return getQuotesByUsername(quotes.getUsername());

	}

}
