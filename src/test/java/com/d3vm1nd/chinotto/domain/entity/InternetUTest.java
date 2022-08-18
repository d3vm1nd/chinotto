package com.d3vm1nd.chinotto.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.d3vm1nd.chinotto.domain.entity.Internet.InternetBuilder;
import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InternetUTest {

  @BeforeEach
  void setUp() throws Exception {}

  @Test
  void testGetSpeed() throws Exception {
    InternetBuilder internet = Internet.builder();
    internet.startInstant(Instant.now());
    Thread.sleep(1 * 1000);
    internet.endInstant(Instant.now());
    internet.bytes(17);
    Internet internet2 = internet.build();
    BigDecimal bigDecimal = internet2.getSpeed();
    assertThat(bigDecimal).isNotNull();
  }

}
