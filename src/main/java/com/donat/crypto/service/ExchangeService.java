package com.donat.crypto.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.kraken.dto.marketdata.KrakenOHLC;
import org.knowm.xchange.kraken.dto.marketdata.KrakenOHLCs;
import org.knowm.xchange.kraken.service.KrakenMarketDataServiceRaw;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class ExchangeService {

    private Exchange kraken = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());
    private MarketDataService marketDataService = kraken.getMarketDataService();


    public void getCryptoData() {
        KrakenMarketDataServiceRaw krakenService = (KrakenMarketDataServiceRaw) marketDataService;
        KrakenOHLCs krakenOHLC = null;
        try {
            krakenOHLC = krakenService.getKrakenOHLC(CurrencyPair.BTC_USD, 60, 1l);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (KrakenOHLC ohlc : krakenOHLC.getOHLCs()) {
            Date datum = new Date(ohlc.getTime() * 1000);
            System.out.println(datum + " O:"  + ohlc.getOpen() + " C:"  + ohlc.getClose() + " V:"  + ohlc.getVolume());
        }
    }


}
