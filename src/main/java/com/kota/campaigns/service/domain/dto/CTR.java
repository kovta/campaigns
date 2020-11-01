package com.kota.campaigns.service.domain.dto;

import com.agorapulse.micronaut.bigquery.RowResult;

public record CTR(double ctr, String datasource, String campaign) {

  public static CTR build(RowResult result) {
    return new CTR(
        result.getDoubleValue("ctr"),
        result.getStringValue("datasource"),
        result.getStringValue("campaign"));
  }
}
