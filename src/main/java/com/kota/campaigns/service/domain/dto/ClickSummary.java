package com.kota.campaigns.service.domain.dto;

import com.agorapulse.micronaut.bigquery.RowResult;
import io.micronaut.core.annotation.Introspected;

@Introspected
public class ClickSummary {

  private long clicks;
  private String datasource;

  public ClickSummary(long clicks, String datasource) {
    this.clicks = clicks;
    this.datasource = datasource;
  }

  public static ClickSummary build(RowResult result) {
    return new ClickSummary(result.getLongValue("clicks"), result.getStringValue("datasource"));
  }
}
