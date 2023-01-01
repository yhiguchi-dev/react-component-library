package com.example.domain.model.loginsession;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

public class LastLoginAt {
  LocalDateTime value;

  public LastLoginAt(LocalDateTime value) {
    this.value = value;
  }

  public LastLoginAt() {}

  public static LastLoginAt ofEpochMilli(long value) {
    Instant instant = Instant.ofEpochMilli(value);
    ZoneId zoneId = TimeZone.getDefault().toZoneId();
    return new LastLoginAt(LocalDateTime.ofInstant(instant, zoneId));
  }
}
