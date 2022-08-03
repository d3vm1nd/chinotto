package com.d3vm1nd.chinotto.app.usecase;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.d3vm1nd.chinotto.app.in.DownloadSpeedMeasureUseCase;
import com.d3vm1nd.chinotto.app.out.GetInputStreamPort;
import com.d3vm1nd.chinotto.app.out.GetUrlsPort;
import com.d3vm1nd.chinotto.app.out.SendEventPort;
import com.d3vm1nd.chinotto.domain.entity.Internet;
import com.d3vm1nd.chinotto.domain.value.ServerException;

@ExtendWith(MockitoExtension.class)
class DownloadSpeedMeasureUseCaseImplUTest {

	private static final String SOME_VALUE_RANDOM = "SOME VALUE RANDOM";
	private DownloadSpeedMeasureUseCase downloadSpeedMeasureUseCase;
	@InjectMocks
	private DownloadSpeedMeasureUseCaseImpl downloadSpeedMeasureUseCaseImpl;
	@Mock
	private GetUrlsPort getUrlsPort;
	@Mock
	private GetInputStreamPort getInputStreamPort;
	@Mock
	private SendEventPort<Internet> sendEventPort;

	@BeforeEach
	void setUp() throws Exception {
		downloadSpeedMeasureUseCase = downloadSpeedMeasureUseCaseImpl;
	}

	@Test
	void test() {
		when(getUrlsPort.get()).thenReturn(Arrays.asList("http://www.google.com"));
		when(getInputStreamPort.get(Mockito.anyString()))
				.thenReturn(new ByteArrayInputStream(SOME_VALUE_RANDOM.getBytes()));
		assertThatCode(() -> downloadSpeedMeasureUseCase.excecute()).doesNotThrowAnyException();
		verify(getUrlsPort).get();
		verify(sendEventPort).send(Mockito.any(Internet.class));
	}

	@Test
	void testByteSize() {
		when(getUrlsPort.get()).thenReturn(Arrays.asList("http://www.google.com"));
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 999999999; i++) {
			stringBuilder.append("A");
		}
		when(getInputStreamPort.get(Mockito.anyString()))
				.thenReturn(new ByteArrayInputStream(stringBuilder.toString().getBytes()));
		assertThatCode(() -> downloadSpeedMeasureUseCase.excecute()).doesNotThrowAnyException();
		verify(getUrlsPort).get();
		verify(sendEventPort).send(Mockito.any(Internet.class));
	}

	@Test
	void testException() {
		when(getUrlsPort.get()).thenReturn(Arrays.asList("http://www.google.com"));
		when(getInputStreamPort.get(Mockito.anyString())).thenThrow(ServerException.class);
		assertThatCode(() -> downloadSpeedMeasureUseCase.excecute()).doesNotThrowAnyException();
		verify(getUrlsPort).get();
		verify(sendEventPort).send(Mockito.any(Internet.class));
	}

}
