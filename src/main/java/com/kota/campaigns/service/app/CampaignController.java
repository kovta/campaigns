package com.kota.campaigns.service.app;

import com.kota.campaigns.service.domain.dto.CTR;
import com.kota.campaigns.service.domain.dto.ClickSummary;
import com.kota.campaigns.service.domain.dto.DailyImpression;
import io.micronaut.http.annotation.QueryValue;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

import javax.annotation.Nullable;
import java.time.LocalDate;

public interface CampaignController {

    Maybe<ClickSummary> fetchSummary(String datasource, LocalDate from, LocalDate to);

    Flowable<CTR> fetchCTR(@Nullable @QueryValue String datasource, @Nullable @QueryValue String campaign);

    Flowable<DailyImpression> fetchImpressions();

}
