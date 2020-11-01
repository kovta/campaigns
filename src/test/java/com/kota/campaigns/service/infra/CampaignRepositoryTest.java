package com.kota.campaigns.service.infra;

import com.kota.campaigns.service.base.CampaignsBaseTest;
import com.kota.campaigns.service.domain.dto.CTR;
import com.kota.campaigns.service.domain.dto.ClickSummary;
import com.kota.campaigns.service.domain.entity.Campaign;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;
import spock.lang.AutoCleanup;
import spock.lang.Shared;

@MicronautTest
@Testcontainers
public class CampaignRepositoryTest extends CampaignsBaseTest {

  @Shared @AutoCleanup static CampaignRepositoryImpl repository;

  @BeforeAll
  static void setup() {
    repository = getContext().getBean(CampaignRepositoryImpl.class);
  }

  @Test
  public void testGet() {
    final Maybe<Campaign> campaign = repository.getById(0L);
    Assertions.assertNotNull(campaign.blockingGet());
  }

  @Test
  public void testOverallCTR() {
    final Flowable<CTR> ctr = repository.getCTR();
    Assertions.assertEquals(7, ctr.toList().blockingGet().size());
  }

  @Test
  public void testCTR() {
    final Flowable<CTR> ctr = repository.getCTR("test1", "a");
    Assertions.assertEquals(
        List.of(new CTR(0.08461538461538462, "test1", "a")), ctr.toList().blockingGet());
  }

  @Test
  public void testSummary() {
    final Maybe<ClickSummary> summary =
        repository.getSummary("test2", LocalDate.now().minusDays(3), LocalDate.now());
    Assertions.assertEquals(45, summary.blockingGet().clicks());
  }

  @Test
  public void testImpressions() {
    final Flowable<Campaign> summary = repository.getImpressions("a");
    Assertions.assertEquals(
        260, summary.toList().blockingGet().stream().mapToLong(Campaign::impressions).sum());
  }
}
