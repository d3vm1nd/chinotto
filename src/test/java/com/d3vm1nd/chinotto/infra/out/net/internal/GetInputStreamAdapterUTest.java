package com.d3vm1nd.chinotto.infra.out.net.internal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import com.d3vm1nd.chinotto.app.out.GetInputStreamPort;
import com.d3vm1nd.chinotto.domain.value.ServerException;
import java.io.InputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetInputStreamAdapterUTest {

  private GetInputStreamPort getInputStreamPort;
  @InjectMocks
  private GetInputStreamAdapter getInputStreamAdapter;

  @BeforeEach
  void setUp() throws Exception {
    getInputStreamPort = getInputStreamAdapter;
  }

  @Test
  void testGet() {
    String url = "http://mirror.mia11.us.leaseweb.net/speedtest/100mb.bin";
    InputStream inputStream = getInputStreamPort.get(url);
    assertThat(inputStream).isNotEmpty();
  }

  @Test
  void testGetBusinessException() {
    String url = "mirror.mia11.us.leaseweb.net/speedtest/100mb.bin";
    assertThatCode(() -> getInputStreamPort.get(url)).isInstanceOf(ServerException.class);
  }

}
