package com.d3vm1nd.chinotto.domain.value;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -2736590672131676845L;
	
	public BusinessException(Throwable e) {
		super(e);
	}

}
