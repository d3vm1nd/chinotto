package com.d3vm1nd.chinotto;

import static org.assertj.core.api.Assertions.assertThat;

import com.d3vm1nd.chinotto.domain.entity.Internet;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
class ChinottoApplicationTests {

  @Autowired
  private ApplicationContext applicationContext;

  @MockBean(answer = Answers.RETURNS_DEEP_STUBS)
  private KafkaTemplate<String, Internet> kafkaTemplate;

  @Test
  void contextLoads() {
    assertThat(applicationContext).isNotNull();
  }

}
