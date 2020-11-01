package com.kota.campaigns.service.domain.entity;

import com.agorapulse.micronaut.bigquery.RowResult;
import io.micronaut.core.annotation.Introspected;
import java.time.LocalDate;
import java.time.ZoneId;

@Introspected
public class Campaign {
  long id;
  LocalDate date;
  String datasource;
  String campaign;
  long clicks;
  long impressions;

  public Campaign(
      long id, LocalDate date, String datasource, String campaign, long clicks, long impressions) {
    this.id = id;
    this.date = date;
    this.datasource = datasource;
    this.campaign = campaign;
    this.clicks = clicks;
    this.impressions = impressions;
  }

  public static Campaign build(RowResult result) {
    return new Campaign(
        result.getLongValue("id"),
        LocalDate.ofInstant(result.getTimestampValue("date"), ZoneId.of("UTC").normalized()),
        result.getStringValue("datasource"),
        result.getStringValue("campaign"),
        result.getLongValue("clicks"),
        result.getLongValue("impressions"));
  }
}
