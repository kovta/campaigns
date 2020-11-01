package com.kota.campaigns.service.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kota.campaigns.service.base.CampaignsBaseTest;
import com.kota.campaigns.service.domain.dto.CTR;
import io.micronaut.http.HttpResponse;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.reactivex.Flowable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spock.lang.AutoCleanup;
import spock.lang.Shared;

@MicronautTest
public class CampaignControllerTest extends CampaignsBaseTest {

  @Shared @AutoCleanup static CampaignController controller;

  @BeforeAll
  static void setup() {
    controller = getContext().getBean(CampaignController.class);
  }

  @Test
  void testCtr() {
    final Flowable<CTR> result = controller.fetchCTR(null, null);
    assertEquals(Flowable.just(new CTR(2.0, "test1", "a")), result);
  }
}
