package com.example.pgCryptoExample.service;

import com.example.pgCryptoExample.domain.CustomerDetails;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

import static com.example.pgCryptoExample.TestContainerUtil.initializeConnectionUrl;
import static com.example.pgCryptoExample.TestContainerUtil.startContainer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

@Testcontainers
@SpringBootTest
@ContextConfiguration(initializers = {CustomerDetailsServiceDBIntegrationTest.Initializer.class})
class CustomerDetailsServiceDBIntegrationTest {

    public static PostgreSQLContainer postgreSQLContainer;

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @BeforeAll
    static void beforeAll() throws SQLException {
        postgreSQLContainer = startContainer();
        Connection connection = createConnection();
        connection.prepareStatement("CREATE EXTENSION pgcrypto;").execute();
    }

    private static Connection createConnection() throws SQLException
    {
        return DriverManager.getConnection(postgreSQLContainer.getJdbcUrl(), "sa", "sa");
    }


    @Test
    public void shouldSaveCustomerCibilDetailsInEncryptedFormat() {
        CustomerDetails customerDetails = new CustomerDetails(UUID.randomUUID(), "700", "Sachin Tendulkar", true);
        CustomerDetails customerDetailsPersisted = this.customerDetailsService.saveCustomerDetails(customerDetails);

        assertThat(customerDetailsPersisted.getCibilScore(), not("700"));
    }

    @Test
    public void shouldReturnCustomerCibilDetailsInNonEncryptedFormat() {

    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            initializeConnectionUrl(applicationContext, postgreSQLContainer);
        }
    }
}