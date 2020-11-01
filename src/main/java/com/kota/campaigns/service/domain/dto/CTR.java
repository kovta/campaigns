package com.kota.campaigns.service.domain.dto;

import com.agorapulse.micronaut.bigquery.RowResult;
import io.micronaut.core.annotation.Introspected;

@Introspected
public class CTR {

  double ctr;
  String datasource;
  String campaign;

  public CTR(double ctr, String datasource, String campaign) {
    this.ctr = ctr;
    this.datasource = datasource;
    this.campaign = campaign;
  }

  public static CTR build(RowResult result) {
    return new CTR(
        result.getDoubleValue("ctr"),
        result.getStringValue("datasource"),
        result.getStringValue("campaign"));
  }
}
