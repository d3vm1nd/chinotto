package com.d3vm1nd.chinotto.app.out;

public interface SendEventPort<E> extends Port {

  void send(E internet);

}
