package com.kota.campaigns.service.app;

import com.kota.campaigns.service.domain.Campaign;
import com.kota.campaigns.service.infra.CampaignService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;
import java.util.Optional;

@Controller("/campaigns")
public class CampaignController {

    @Inject
    CampaignService service;

    @Get
    public String ping() {
        final Optional<Campaign> campaign = service.get(0L);
        return String.valueOf(campaign.isPresent());
    }

}
