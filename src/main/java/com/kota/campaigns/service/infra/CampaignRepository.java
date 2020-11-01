package com.kota.campaigns.service.infra;

import com.kota.campaigns.service.domain.dto.CTR;
import com.kota.campaigns.service.domain.dto.ClickSummary;
import com.kota.campaigns.service.domain.dto.DailyImpression;
import com.kota.campaigns.service.domain.entity.Campaign;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

import java.time.LocalDate;

public interface CampaignRepository {

  Maybe<ClickSummary> getSummary(String datasource, LocalDate from, LocalDate to);

  Flowable<CTR> getCTR(String datasource, String campaign);

  Flowable<CTR> getCTR(String datasource);

  Flowable<CTR> getCTR();

  Flowable<Campaign> getImpressions(String campaign);

  Flowable<DailyImpression> getDailyImpressions();
}
