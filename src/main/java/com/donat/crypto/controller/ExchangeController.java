package com.donat.crypto.controller;

import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.donat.crypto.service.ExchangeService;

@RestController
@RequestMapping("/api/exchange")
@Transactional
public class ExchangeController {

	private ExchangeService exchangeService;

	public ExchangeController(ExchangeService exchangeService) {
		this.exchangeService = exchangeService;
	}

	@GetMapping("/getCandle")
	public void getCandles() {
		exchangeService.getCandles();
	}

}
