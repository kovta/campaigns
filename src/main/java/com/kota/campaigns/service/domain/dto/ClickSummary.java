package com.kota.campaigns.service.domain;

import com.agorapulse.micronaut.bigquery.RowResult;
import io.micronaut.core.annotation.Introspected;

@Introspected
public record ClickSummary(long clicks, String datasource) {

  public static ClickSummary build(RowResult result) {
    return new ClickSummary(result.getLongValue("clicks"), result.getStringValue("datasource"));
  }
}
