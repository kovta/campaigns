package com.kota.campaigns.service.domain.dto;

import com.agorapulse.micronaut.bigquery.RowResult;

public record ClickSummary(long clicks, String datasource) {

  public static ClickSummary build(RowResult result) {
    return new ClickSummary(result.getLongValue("clicks"), result.getStringValue("datasource"));
  }
}
