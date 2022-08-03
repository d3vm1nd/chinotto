package com.d3vm1nd.chinotto.infra.in.spring.internal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.d3vm1nd.chinotto.app.in.DownloadSpeedMeasureUseCase;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerAdapter implements CommandLineRunner {

	private final DownloadSpeedMeasureUseCase downloadSpeedMeasureUseCase;

	@Override
	public void run(String... args) throws Exception {
		downloadSpeedMeasureUseCase.excecute();
	}

}
