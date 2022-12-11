package com.example.pgCryptoExample.service;

import com.example.pgCryptoExample.domain.CustomerDetails;
import com.example.pgCryptoExample.domain.CustomerDetailsCustomRepository;
import com.example.pgCryptoExample.domain.CustomerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CustomerDetailsService {

    @Autowired
    CustomerDetailsCustomRepository customerDetailsRepository;

    @Transactional
    public CustomerDetails saveCustomerDetails(CustomerDetails customerDetails) {
        return this.customerDetailsRepository.saveCustomerDetails(customerDetails);
    }

    public CustomerDetails getCustomerDetails(UUID id) {
        return null;
    }
}
