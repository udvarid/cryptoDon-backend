package com.donat.crypto.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "candles")
@Data
public class Candle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime time;

	@Column(name = "currency_pair", length = 50, nullable = false)
	private String currencyPair;

	private double open;

	private double high;

	private double low;

	private double close;

	private double vwap;

	private double volume;

	private long count;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Candle candle = (Candle) o;

		return id != null ? id.equals(candle.id) : candle.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
