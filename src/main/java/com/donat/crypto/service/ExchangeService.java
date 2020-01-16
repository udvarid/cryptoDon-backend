package com.donat.crypto.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.kraken.dto.marketdata.KrakenOHLC;
import org.knowm.xchange.kraken.service.KrakenMarketDataServiceRaw;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.donat.crypto.domain.Candle;
import com.donat.crypto.repository.CandleRepository;

@Service
public class ExchangeService {

    private CandleRepository candleRepository;

    public ExchangeService(CandleRepository candleRepository) {
        this.candleRepository = candleRepository;
    }

    private List<CurrencyPair> fillCurrencyPairs() {
        List<CurrencyPair> currencyPairs = new ArrayList<>();
        currencyPairs.add(CurrencyPair.BTC_USD);
        currencyPairs.add(CurrencyPair.ETH_USD);
        currencyPairs.add(CurrencyPair.NEO_USD);
        currencyPairs.add(CurrencyPair.LTC_USD);
        currencyPairs.add(CurrencyPair.DASH_USD);
        return currencyPairs;
    }

    public void getCandles() {
        List<CurrencyPair> currencyPairs = fillCurrencyPairs();
        Exchange kraken = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());
        KrakenMarketDataServiceRaw krakenService = (KrakenMarketDataServiceRaw) kraken.getMarketDataService();


        for (CurrencyPair currencyPair : currencyPairs) {
            try {
                fillDataBase(krakenService
                    .getKrakenOHLC(currencyPair, 15, 1l)
                    .getOHLCs(), currencyPair);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillDataBase(List<KrakenOHLC> ohlCs, CurrencyPair currencyPair) {
        //TODO ezt kicserélni egy adatbázisból kiszedett, a currency pair-hoz tartozó utolsó dátum lekéréssel
        LocalDateTime finalDate = LocalDateTime.now();
        for (KrakenOHLC ohlC : ohlCs) {
            if (newCandle(ohlC.getTime(), finalDate)) {
                Candle candle = createCandleEntity(currencyPair, ohlC);
                candleRepository.saveAndFlush(candle);
            }
        }
    }

    private Candle createCandleEntity(CurrencyPair currencyPair, KrakenOHLC ohlC) {
        Candle candle = new Candle();
        //TODO itt a time-ból csinálni Localdate-t
        Date datum = new Date(ohlC.getTime() * 1000);
        candle.setTime(LocalDateTime.now());
        candle.setCurrencyPair(currencyPair.toString());
        candle.setClose(ohlC.getClose().doubleValue());
        candle.setOpen(ohlC.getOpen().doubleValue());
        candle.setHigh(ohlC.getHigh().doubleValue());
        candle.setLow(ohlC.getLow().doubleValue());
        candle.setCount(ohlC.getCount());
        candle.setVolume(ohlC.getVolume().doubleValue());
        candle.setVwap(ohlC.getVwap().doubleValue());
        return candle;
    }

    private boolean newCandle(long time, LocalDateTime finalDate) {
        //TODO localDatetime-ot csinálni és összehasonlítani a finalDate-el
        return true;
    }
}
