package com.kota.campaigns.service.infra;

import com.agorapulse.micronaut.bigquery.BigQueryService;
import com.agorapulse.micronaut.bigquery.RowResult;
import com.kota.campaigns.service.domain.Campaign;
import io.micronaut.context.annotation.Value;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Singleton;

@Singleton
public class CampaignService {

  private final String schema;
  private final String table;
  private final BigQueryService bq;

  public CampaignService(
      @Value("${bq.schema}") String schema,
      @Value("${bq.table}") String table,
      BigQueryService bq) {
    this.schema = schema;
    this.table = table;
    this.bq = bq;
  }

  public Optional<Campaign> get(long id) {
    return bq.querySingle(
        Collections.singletonMap("id", id),
        String.format("select * from %s.%s where id = @id", schema, table),
        CampaignService::build);
  }

  private static Campaign build(RowResult result) {
    return new Campaign(
        result.getLongValue("id"),
        LocalDate.ofInstant(result.getTimestampValue("date"), ZoneId.of("UTC").normalized()),
        result.getStringValue("datasource"),
        result.getStringValue("campaign"),
        result.getLongValue("clicks"),
        result.getLongValue("impressions"));
  }
}
