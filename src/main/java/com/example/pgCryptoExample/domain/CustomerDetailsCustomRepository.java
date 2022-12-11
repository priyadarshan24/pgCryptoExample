package com.example.pgCryptoExample.domain;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerDetailsCustomRepository  {

    @Value("${pii.protection.public.key}")
    String publicKey;

    @Value("${pii.protection.private.key}")
    String privateKey;

    @Value("${pii.protection.private.key.password}")
    String privateKeyPassword;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    CustomerDetailsRepository  customerDetailsRepository;

    public CustomerDetails saveCustomerDetails(CustomerDetails customerDetails) {
        Session curSession = entityManager.unwrap(Session.class);
        curSession.doWork(connection -> {
            connection.createStatement().execute(String.format("SET LOCAL var.public_key = '%s'", this.publicKey));
        });

        CustomerDetails save = customerDetailsRepository.save(customerDetails);
        return save;
    }
}