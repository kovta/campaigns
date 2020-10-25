package com.kota.campaigns.service.domain;

import io.micronaut.core.annotation.Introspected;

import java.time.LocalDate;

@Introspected
public record CampaignStat(
        long id,
        LocalDate date,
        String datasource,
        String campaign,
        int clicks,
        int impressions
) {}
