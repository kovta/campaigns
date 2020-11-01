package com.kota.campaigns.service.infra;

import com.agorapulse.micronaut.bigquery.BigQueryService;
import com.kota.campaigns.service.domain.dto.CTR;
import com.kota.campaigns.service.domain.dto.ClickSummary;
import com.kota.campaigns.service.domain.dto.DailyImpression;
import com.kota.campaigns.service.domain.entity.Campaign;
import io.micronaut.context.annotation.Value;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import javax.inject.Singleton;

@Singleton
public class CampaignRepositoryImpl implements CampaignRepository {

  private final String schema;
  private final String table;
  private final BigQueryService bq;

  public CampaignRepositoryImpl(
      @Value("${bq.schema}") String schema,
      @Value("${bq.table}") String table,
      BigQueryService bq) {
    this.schema = schema;
    this.table = table;
    this.bq = bq;
  }

  public Maybe<Campaign> getById(long id) {
    return bq.query(
            Collections.singletonMap("id", id),
            String.format(
                """
                SELECT *
                FROM %s.%s
                WHERE id = @id
                """,
                schema, table),
            Campaign::build)
        .singleElement();
  }

  public Flowable<CTR> getCTR() {
    return bq.query(
        Collections.emptyMap(),
        String.format(
            """
                SELECT SUM(clicks) / SUM(impressions) as ctr, datasource, campaign
                FROM %s.%s
                GROUP BY datasource, campaign""",
            schema, table),
        CTR::build);
  }

  public Flowable<CTR> getCTR(String datasource) {
    return bq.query(
        Map.of("datasource", datasource),
        String.format(
            """
                SELECT SUM(clicks) / SUM(impressions) as ctr, datasource
                FROM %s.%s
                WHERE datasource = @datasource
                GROUP BY datasource""",
            schema, table),
        CTR::build);
  }

  public Flowable<CTR> getCTR(String datasource, String campaign) {
    return bq.query(
        Map.of("datasource", datasource, "campaign", campaign),
        String.format(
            """
                SELECT SUM(clicks) / SUM(impressions) as ctr, datasource, campaign
                FROM %s.%s
                WHERE datasource = @datasource AND campaign = @campaign
                GROUP BY datasource, campaign""",
            schema, table),
        CTR::build);
  }

  public Maybe<ClickSummary> getSummary(String datasource, LocalDate from, LocalDate to) {
    return bq.query(
            Map.of("datasource", datasource, "from", from, "to", to),
            String.format(
                """
                SELECT SUM(clicks) as clicks, datasource
                FROM %s.%s
                WHERE datasource = @datasource AND date BETWEEN @from AND @to
                GROUP BY datasource""",
                schema, table),
            ClickSummary::build)
        .singleElement();
  }

  public Flowable<Campaign> getImpressions(String campaign) {
    return bq.query(
        Collections.singletonMap("campaign", campaign),
        String.format(
            """
                SELECT *
                FROM %s.%s
                WHERE campaign = @campaign""",
            schema, table),
        Campaign::build);
  }

  public Flowable<DailyImpression> getDailyImpressions() {
    return bq.query(
        Collections.emptyMap(),
        String.format(
            """
                        SELECT SUM(impressions) as impressions, date
                        FROM %s.%s
                        group by date""",
            schema, table),
        DailyImpression::build);
  }
}
