package com.d3vm1nd.chinotto.infra.out.net.internal;

import com.d3vm1nd.chinotto.app.out.GetInputStreamPort;
import com.d3vm1nd.chinotto.domain.value.ServerException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.springframework.stereotype.Component;

@Component
public class GetInputStreamAdapter implements GetInputStreamPort {

  @Override
  public InputStream get(String url) {
    try {
      return new URL(url).openStream();
    } catch (IOException e) {
      throw new ServerException(e);
    }
  }

}
