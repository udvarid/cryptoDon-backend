package com.donat.crypto.controller;

import javax.transaction.Transactional;

import graphql.ExecutionInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.donat.crypto.service.ExchangeService;
import com.donat.crypto.service.GraphQLService;
import graphql.ExecutionResult;

@RestController
@RequestMapping("/api/exchange")
@Transactional
public class ExchangeController {

	private ExchangeService exchangeService;
	private GraphQLService graphQLService;

	public ExchangeController(ExchangeService exchangeService, GraphQLService graphQLService) {
		this.exchangeService = exchangeService;
		this.graphQLService = graphQLService;
	}

	@GetMapping("/getCandle")
	public void getCandles() {
		exchangeService.getCandles();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCandleData(@RequestBody QueryData query) {
		ExecutionInput input = ExecutionInput.newExecutionInput().
				query(query.getQuery()).
				variables(query.getVariables()).
				build();
		ExecutionResult execute = graphQLService.getGraphQL().execute(input);
		return new ResponseEntity<>(execute, HttpStatus.OK);
	}

}
