package com.donat.crypto.repository;

import com.donat.crypto.events.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
}
