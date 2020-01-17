package com.donat.crypto.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.donat.crypto.domain.Candle;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

@Transactional
public interface CandleRepository extends JpaRepository<Candle, Long> {
    @Query(value = "SELECT max(c.time) as time FROM candles as c WHERE c.currency_pair = ?1", nativeQuery = true)
    LocalDateTime giveLatestCandle(String currencyPair);
}