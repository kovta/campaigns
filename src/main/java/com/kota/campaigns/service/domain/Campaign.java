package com.kota.campaigns.service.domain;

import io.micronaut.core.annotation.Introspected;
import java.time.LocalDate;

@Introspected
public record Campaign(
    long id, LocalDate date, String datasource, String campaign, long clicks, long impressions) {}
