package com.donat.crypto.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.donat.crypto.domain.Candle;

@Transactional
public interface CandleRepository extends JpaRepository<Candle, Long> {

}
