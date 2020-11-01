package com.kota.campaigns.service.domain.dto;

import com.agorapulse.micronaut.bigquery.RowResult;
import io.micronaut.core.annotation.Introspected;
import java.time.LocalDate;
import java.time.ZoneId;

public record DailyImpression(long impressions, LocalDate date) {

  public static DailyImpression build(RowResult result) {
    return new DailyImpression(
        result.getLongValue("impressions"),
        LocalDate.ofInstant(result.getTimestampValue("date"), ZoneId.of("UTC").normalized()));
  }
}
