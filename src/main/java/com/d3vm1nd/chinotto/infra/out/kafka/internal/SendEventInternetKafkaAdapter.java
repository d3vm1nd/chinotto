package com.d3vm1nd.chinotto.infra.out.kafka.internal;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.d3vm1nd.chinotto.app.out.SendEventPort;
import com.d3vm1nd.chinotto.domain.entity.Internet;
import com.d3vm1nd.chinotto.domain.value.BusinessException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SendEventInternetKafkaAdapter implements SendEventPort<Internet> {

	private final KafkaTemplate<String, Internet> kafkaTemplate;
	@Value("${chinotto.kafka.topic.download}")
	private String topic;

	@Override
	public void send(Internet internet) {
		try {
			kafkaTemplate.send(new ProducerRecord<>(topic, internet.getSpeedType().name(), internet)).get();
		} catch (ExecutionException e) {
			throw new BusinessException(e);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new BusinessException(e);
		}

	}

}
