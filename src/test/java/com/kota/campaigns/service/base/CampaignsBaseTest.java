package com.kota.campaigns.service.base;

import io.micronaut.context.ApplicationContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.util.Map;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import spock.lang.Specification;

@MicronautTest
@Testcontainers
public class CampaignsBaseTest extends Specification {

  @Container
  static PostgreSQLContainer container =
      (PostgreSQLContainer)
          new PostgreSQLContainer("postgres:latest")
              .withDatabaseName("campaigns")
              .withInitScript("init.sql");

  protected static ApplicationContext getContext() {
    return ApplicationContext.build(
            Map.of(
                "datasources.default.url", container.getJdbcUrl(),
                "datasources.default.driverClassName", container.getDriverClassName(),
                "datasources.default.username", container.getUsername(),
                "datasources.default.password", container.getPassword()))
        .build()
        .start();
  }
}
