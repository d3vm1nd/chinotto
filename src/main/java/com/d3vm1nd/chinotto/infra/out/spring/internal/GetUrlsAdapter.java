package com.d3vm1nd.chinotto.infra.out.spring.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.d3vm1nd.chinotto.app.out.GetUrlsPort;

@Component
public class GetUrlsAdapter implements GetUrlsPort {

	@Value("${chinotto.urls}")
	private List<String> urls;

	@Override
	public List<String> get() {
		return new ArrayList<>(urls);
	}

}
