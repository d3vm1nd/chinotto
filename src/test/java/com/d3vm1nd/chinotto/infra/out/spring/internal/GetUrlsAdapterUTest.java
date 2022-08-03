package com.d3vm1nd.chinotto.infra.out.spring.internal;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.d3vm1nd.chinotto.app.out.GetUrlsPort;

@ExtendWith(MockitoExtension.class)
class GetUrlsAdapterUTest {

	private GetUrlsPort getUrlsPort;
	@InjectMocks
	private GetUrlsAdapter getUrlsAdapter;

	@BeforeEach
	void setUp() throws Exception {
		getUrlsPort = getUrlsAdapter;
	}

	@Test
	void testGet() {
		ReflectionTestUtils.setField(getUrlsAdapter, "urls", Arrays.asList(Arrays.asList("http://www.google.com")));
		List<String> urls = getUrlsPort.get();
		assertThat(urls).isNotEmpty();
	}

}
