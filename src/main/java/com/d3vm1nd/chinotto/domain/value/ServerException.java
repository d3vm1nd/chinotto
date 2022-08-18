package com.d3vm1nd.chinotto.domain.value;

public class ServerException extends RuntimeException {

  private static final long serialVersionUID = -4593624871873692235L;

  public ServerException(Throwable e) {
    super(e);
  }

}
