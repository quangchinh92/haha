package chinhtran.JWTServerApp.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

/** Convert bytes to java.util.Date when executing data retrieval from the cache. */
@Component
@ReadingConverter
public class BytesToDateConverter implements Converter<byte[], Date> {

  /** Convert byte array to java.util.Date. */
  @Override
  public Date convert(byte[] source) {
    if (source == null || source.length == 0) {
      return null;
    }

    // Option A: If your date is stored as a String timestamp (e.g., "1719331200000")
    String timestampStr = new String(source, StandardCharsets.UTF_8);
    return new Date(Long.parseLong(timestampStr));

    /*
    // Option B: If your date is stored as a raw binary 8-byte long
    long value = 0;
    for (byte b : source) {
        value = (value << 8) | (b & 0xff);
    }
    return new Date(value);
    */
  }
}
