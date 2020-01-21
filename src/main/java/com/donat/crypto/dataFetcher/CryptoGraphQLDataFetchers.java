package com.donat.crypto.dataFetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.donat.crypto.repository.CandleRepository;
import graphql.schema.DataFetcher;

@Component
public class CryptoGraphQLDataFetchers {

	private CandleRepository candleRepository;
	@Autowired
	public CryptoGraphQLDataFetchers(CandleRepository candleRepository){
		this.candleRepository = candleRepository;
	}

	public DataFetcher getCandles() {
		return dataFetchingEnvironment -> {
			String currencyPair = dataFetchingEnvironment.getArgument("currencyPair");
			Integer lastNumber = dataFetchingEnvironment.getArgument("lastNumberOfCandles");
			return candleRepository.getAllByCurrencyPair(currencyPair, lastNumber == null ? 5 * 96: lastNumber);
		};
	}

	public DataFetcher getRecentCandles() {
		return dataFetchingEnvironment ->
			candleRepository.getRecentCurrencyPairs();
		}


}
