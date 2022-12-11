package com.example.pgCryptoExample.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, UUID> {
}