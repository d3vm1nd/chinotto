package com.d3vm1nd.chinotto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class ChinottoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChinottoApplication.class, args);
	}

}
