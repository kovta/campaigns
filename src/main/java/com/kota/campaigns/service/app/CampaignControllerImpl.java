package com.kota.campaigns.service.app;

import com.google.common.base.Strings;
import com.kota.campaigns.service.domain.dto.CTR;
import com.kota.campaigns.service.domain.dto.ClickSummary;
import com.kota.campaigns.service.domain.dto.DailyImpression;
import com.kota.campaigns.service.infra.CampaignRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.time.LocalDate;
import javax.annotation.Nullable;

@Controller("/campaigns")
public class CampaignControllerImpl implements CampaignController {

  CampaignRepository repository;

  public CampaignControllerImpl(CampaignRepository repository) {
    this.repository = repository;
  }

  @Override
  public Maybe<ClickSummary> fetchSummary(String datasource, LocalDate from, LocalDate to) {
    return repository.getSummary(datasource, from, to);
  }

  @Get(uri = "/ctr{?datasource,campaign}")
  public Flowable<CTR> fetchCTR(
      @Nullable @QueryValue String datasource, @Nullable @QueryValue String campaign) {
    if (!Strings.isNullOrEmpty(datasource) && !Strings.isNullOrEmpty(campaign)) {
      return repository.getCTR(datasource, campaign);
    } else if (!Strings.isNullOrEmpty(datasource)) {
      return repository.getCTR(datasource);
    } else {
      return repository.getCTR();
    }
  }

  @Override
  public Flowable<DailyImpression> fetchImpressions() {
    return repository.getDailyImpressions();
  }
}
