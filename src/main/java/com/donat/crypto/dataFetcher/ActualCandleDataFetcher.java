package com.donat.crypto.dataFetcher;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.donat.crypto.domain.Candle;
import com.donat.crypto.repository.CandleRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class ActualCandleDataFetcher implements DataFetcher<List<Candle>> {
	private CandleRepository candleRepository;
	@Autowired
	public ActualCandleDataFetcher(CandleRepository candleRepository){
		this.candleRepository = candleRepository;
	}
	@Override
	public List<Candle> get(DataFetchingEnvironment dataFetchingEnvironment) {
		return candleRepository.getRecentCurrencyPairs();
	}
}
