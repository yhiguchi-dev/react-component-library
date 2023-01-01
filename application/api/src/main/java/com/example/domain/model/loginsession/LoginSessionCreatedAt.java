package com.example.domain.model.loginsession;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

/** セッション作成日時 */
public class LoginSessionCreatedAt {
  LocalDateTime value;

  public LoginSessionCreatedAt(LocalDateTime value) {
    this.value = value;
  }

  public LoginSessionCreatedAt() {}

  public static LoginSessionCreatedAt ofEpochMilli(long value) {
    Instant instant = Instant.ofEpochMilli(value);
    ZoneId zoneId = TimeZone.getDefault().toZoneId();
    return new LoginSessionCreatedAt(LocalDateTime.ofInstant(instant, zoneId));
  }

  public LocalDateTime plusSeconds(int second) {
    return value.plusSeconds(second);
  }

  public boolean isAfter(LocalDateTime localDateTime) {
    return value.isAfter(localDateTime);
  }

  public LocalDateTime value() {
    return value;
  }
}
