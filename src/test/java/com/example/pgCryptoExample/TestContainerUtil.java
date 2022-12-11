package com.example.pgCryptoExample;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.DockerHealthcheckWaitStrategy;

public class TestContainerUtil {

    public static PostgreSQLContainer startContainer() {
        PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:13.4")
                .withDatabaseName("integration-tests-db")
                .withUsername("sa")
                .withPassword("sa");
        postgreSQLContainer.start();
        postgreSQLContainer.waitingFor(new DockerHealthcheckWaitStrategy());
        return postgreSQLContainer;
    }

    public static void initializeConnectionUrl(ConfigurableApplicationContext applicationContext, PostgreSQLContainer container) {
        TestPropertyValues.of(
                "spring.datasource.url=" + container.getJdbcUrl(),
                "spring.datasource.username=" + container.getUsername(),
                "spring.datasource.password=" + container.getPassword()
                             ).applyTo(applicationContext.getEnvironment());
    }

}