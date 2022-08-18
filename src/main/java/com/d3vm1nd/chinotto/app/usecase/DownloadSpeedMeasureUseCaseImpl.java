package com.d3vm1nd.chinotto.app.usecase;

import com.d3vm1nd.chinotto.app.in.DownloadSpeedMeasureUseCase;
import com.d3vm1nd.chinotto.app.out.GetInputStreamPort;
import com.d3vm1nd.chinotto.app.out.GetUrlsPort;
import com.d3vm1nd.chinotto.app.out.SendEventPort;
import com.d3vm1nd.chinotto.domain.entity.Internet;
import com.d3vm1nd.chinotto.domain.entity.Internet.InternetBuilder;
import com.d3vm1nd.chinotto.domain.value.SpeedType;
import java.io.BufferedInputStream;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DownloadSpeedMeasureUseCaseImpl implements DownloadSpeedMeasureUseCase {

  private static final int BYTE_ARRAY_BUFFER = 1024;
  private final GetUrlsPort getUrlsPort;
  private final GetInputStreamPort getInputStreamPort;
  private final SendEventPort<Internet> sendEventPort;

  @Override
  public void excecute() {
    check();
  }

  public void check() {
    List<String> urls = getUrlsPort.get();
    urls.stream().forEach((String url) -> {
      log.debug(url);
      InternetBuilder internet = Internet.builder();
      internet.host(url);
      internet.speedType(SpeedType.DOWNLOAD);
      internet.startInstant(Instant.now());
      try (BufferedInputStream in = new BufferedInputStream(getInputStreamPort.get(url))) {
        int bytes = 0;
        int bytesRead;
        byte[] dataBuffer = new byte[BYTE_ARRAY_BUFFER];
        while ((bytesRead = in.read(dataBuffer, 0, BYTE_ARRAY_BUFFER)) != -1) {
          bytes = bytesRead + bytes;
        }
        log.debug(Integer.toString(bytes));
        internet.endInstant(Instant.now());
        internet.bytes(bytes);
        log.debug(internet.build().toString());
        sendEventPort.send(internet.build());
      } catch (Exception e) {
        internet.endInstant(Instant.now());
        sendEventPort.send(internet.build());
        log.error("ERROR", e);
        log.debug(internet.build().toString());
      }
    });

  }

}
