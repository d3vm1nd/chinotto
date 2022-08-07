package com.d3vm1nd.chinotto.domain.entity;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.Instant;

import com.d3vm1nd.chinotto.domain.value.SpeedType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Internet {

	private String host;
	private Instant startInstant;
	private int bytes;
	private Instant endInstant;
	private SpeedType speedType;
	@Setter
	private BigDecimal speed;

	public Duration getDuration() {
		return Duration.between(startInstant, endInstant);
	}

	public BigDecimal getSpeed() {
		BigDecimal mbs = BigDecimal.valueOf(bytes).divide(BigDecimal.valueOf(1000000), MathContext.DECIMAL128);
		BigDecimal time = BigDecimal.valueOf(getDuration().toSeconds());
		try {
			return mbs.divide(time, MathContext.DECIMAL128).multiply(BigDecimal.valueOf(8));
		} catch (ArithmeticException e) {
			return BigDecimal.ZERO;
		}

	}

}
