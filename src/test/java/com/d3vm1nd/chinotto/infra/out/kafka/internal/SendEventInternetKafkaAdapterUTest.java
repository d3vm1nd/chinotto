package com.d3vm1nd.chinotto.infra.out.kafka.internal;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.concurrent.ListenableFuture;

import com.d3vm1nd.chinotto.app.out.SendEventPort;
import com.d3vm1nd.chinotto.domain.entity.Internet;
import com.d3vm1nd.chinotto.domain.value.BusinessException;

@ExtendWith(MockitoExtension.class)
class SendEventInternetKafkaAdapterUTest {

	private SendEventPort<Internet> sendEventPort;
	@InjectMocks
	private SendEventInternetKafkaAdapter sendEventInternetKafkaAdapter;
	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private KafkaTemplate<String, Internet> kafkaTemplate;

	@BeforeEach
	void setUp() throws Exception {
		sendEventPort = sendEventInternetKafkaAdapter;
	}

	@Test
	void testSend() {
		ReflectionTestUtils.setField(sendEventInternetKafkaAdapter, "topic", "TOPIC.SOME");
		Internet internet = new EasyRandom().nextObject(Internet.class);
		assertThatCode(() -> sendEventPort.send(internet)).doesNotThrowAnyException();
	}

	@Test
	@SuppressWarnings("unchecked")
	void testSendExecutionException() throws InterruptedException, ExecutionException {
		ReflectionTestUtils.setField(sendEventInternetKafkaAdapter, "topic", "TOPIC.SOME");
		ListenableFuture<SendResult<String, Internet>> listenableFuture = Mockito.mock(ListenableFuture.class,
				RETURNS_DEEP_STUBS);
		Internet internet = new EasyRandom().nextObject(Internet.class);
		when(kafkaTemplate.send(Mockito.any(ProducerRecord.class))).thenReturn(listenableFuture);
		when(listenableFuture.get()).thenThrow(ExecutionException.class);
		assertThatCode(() -> sendEventPort.send(internet)).isInstanceOf(BusinessException.class);
	}

	@Test
	@SuppressWarnings("unchecked")
	void testSendInterruptedException() throws InterruptedException, ExecutionException {
		ReflectionTestUtils.setField(sendEventInternetKafkaAdapter, "topic", "TOPIC.SOME");
		ListenableFuture<SendResult<String, Internet>> listenableFuture = Mockito.mock(ListenableFuture.class,
				RETURNS_DEEP_STUBS);
		Internet internet = new EasyRandom().nextObject(Internet.class);
		when(kafkaTemplate.send(Mockito.any(ProducerRecord.class))).thenReturn(listenableFuture);
		when(listenableFuture.get()).thenThrow(InterruptedException.class);
		assertThatCode(() -> sendEventPort.send(internet)).isInstanceOf(BusinessException.class);
	}

}
