package com.kota.campaigns.service.infra;

import com.kota.campaigns.service.domain.Campaign;
import io.micronaut.context.ApplicationContext;
import java.util.Map;
import java.util.Optional;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import spock.lang.AutoCleanup;
import spock.lang.Shared;
import spock.lang.Specification;

@MicronautTest
@Testcontainers
public class CampaignServiceTest extends Specification {

  @Container
  static PostgreSQLContainer container =
      (PostgreSQLContainer)
          new PostgreSQLContainer("postgres:latest")
              .withDatabaseName("campaigns")
              .withInitScript("init.sql");

  @Shared @AutoCleanup static ApplicationContext context;

  @BeforeAll
  static void setup() {
    context =
        ApplicationContext.build(
                Map.of(
                    "datasources.default.url", container.getJdbcUrl(),
                    "datasources.default.driverClassName", container.getDriverClassName(),
                    "datasources.default.username", container.getUsername(),
                    "datasources.default.password", container.getPassword()))
            .build()
            .start();
  }

  @Test
  public void test() {
    final CampaignService service = context.getBean(CampaignService.class);
    final Optional<Campaign> campaign = service.get(0L);
    Assertions.assertTrue(campaign.isPresent());
  }
}
