package com.donat.crypto.dataFetcher;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.donat.crypto.domain.Candle;
import com.donat.crypto.repository.CandleRepository;
import graphql.schema.DataFetcher;

@Component
public class CryptoGraphQLDataFetchers {

	private CandleRepository candleRepository;
	@Autowired
	public CryptoGraphQLDataFetchers(CandleRepository candleRepository){
		this.candleRepository = candleRepository;
	}

	public DataFetcher<List<Candle>> getCandles() {
		return dataFetchingEnvironment -> {
			String currencyPair = dataFetchingEnvironment.getArgument("currencyPair");
			Integer lastNumber = dataFetchingEnvironment.getArgument("lastNumberOfCandles");
			Integer periodLength = dataFetchingEnvironment.getArgument("periodLength");
			List<Candle> candles = candleRepository
				.getAllByCurrencyPair(currencyPair, lastNumber == null ? 5 * 96 * periodLength : lastNumber * periodLength);
			return transformCandles(candles, periodLength);
		};
	}

	private List<Candle> transformCandles(List<Candle> candles, Integer periodLength) {

		if (periodLength == 1) {
			return candles;
		}

		List<Candle> transformedCandles = new ArrayList<>();
		boolean alreadyStarted = false;
		Candle newCandle = new Candle();
		for (Candle candle : candles) {
			if (isPeriodBegin(candle.getTime(), periodLength)) {
				alreadyStarted = true;
				newCandle = creatingNewCandle(candle);
			} else if (!alreadyStarted) {
				fillingUpNewCandle(newCandle, candle);
			}
			if (isPeriodEnd(candle.getTime(), periodLength) && alreadyStarted) {
				newCandle.setTime(candle.getTime());
				transformedCandles.add(newCandle);
				alreadyStarted = false;
			}
		}
		return transformedCandles;
	}

	private boolean isPeriodEnd(LocalDateTime time, Integer periodLength) {
		if (periodLength == 4) {
			return time.getMinute() == 0;
		} else if (periodLength == 16) {
			return time.getMinute() == 0 && time.getHour() % 4 == 0;
		} else if (periodLength == 96) {
			return time.getMinute() == 0 && time.getHour() == 0;
		} else if (periodLength == 96 * 7) {
			return time.getMinute() == 0 && time.getHour() == 0 && time.getDayOfWeek().equals(DayOfWeek.MONDAY);
		} else {
			return false;
		}
	}

	private boolean isPeriodBegin(LocalDateTime time, Integer periodLength) {
		if (periodLength == 4) {
			return time.getMinute() == 15;
		} else if (periodLength == 16) {
			return time.getMinute() == 15 && time.getHour() % 4 == 0;
		} else if (periodLength == 96) {
			return time.getMinute() == 15 && time.getHour() == 0;
		} else if (periodLength == 96 * 7) {
			return time.getMinute() == 15 && time.getHour() == 0 && time.getDayOfWeek().equals(DayOfWeek.MONDAY);
		} else {
			return false;
		}
	}

	private void fillingUpNewCandle(Candle newCandle, Candle candle) {
		newCandle.setVwap((newCandle.getVwap() * newCandle.getVolume() +
			candle.getVwap() * candle.getVolume()) / (newCandle.getVolume() + candle.getVolume()));
		newCandle.setVolume(newCandle.getVolume() + candle.getVolume());
		newCandle.setCount(newCandle.getCount() + candle.getCount());
		newCandle.setLow(candle.getLow() < newCandle.getLow() ? candle.getLow() : newCandle.getLow());
		newCandle.setHigh(candle.getHigh() > newCandle.getHigh() ? candle.getHigh() : newCandle.getHigh());
		newCandle.setClose(candle.getClose());
	}

	@NotNull
	private Candle creatingNewCandle(Candle candle) {
		Candle newCandle;
		newCandle = new Candle();
		newCandle.setVwap(candle.getVwap());
		newCandle.setVolume(candle.getVolume());
		newCandle.setCount(candle.getCount());
		newCandle.setLow(candle.getLow());
		newCandle.setHigh(candle.getHigh());
		newCandle.setOpen(candle.getOpen());
		newCandle.setClose(candle.getClose());
		newCandle.setCurrencyPair(candle.getCurrencyPair());
		return newCandle;
	}


	public DataFetcher<List<Candle>> getRecentCandles() {
		return dataFetchingEnvironment ->
			candleRepository.getRecentCurrencyPairs();
		}


}
