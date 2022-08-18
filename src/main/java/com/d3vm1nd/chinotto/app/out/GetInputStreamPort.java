package com.d3vm1nd.chinotto.app.out;

import java.io.InputStream;

public interface GetInputStreamPort extends Port {

  InputStream get(String url);

}
