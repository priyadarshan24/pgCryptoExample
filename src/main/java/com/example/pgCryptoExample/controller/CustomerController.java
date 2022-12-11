package com.example.pgCryptoExample.controller;

import com.example.pgCryptoExample.domain.CustomerDetails;
import com.example.pgCryptoExample.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerDetailsService customerDetailsService;

    @PostMapping
    public ResponseEntity<CustomerDetails> createCustomer(@RequestBody CustomerDetails customerDetails) {
        return ResponseEntity.ok(this.customerDetailsService.saveCustomerDetails(customerDetails));
    }

    @GetMapping
    public ResponseEntity<CustomerDetails> getCustomer(@RequestParam(name = "id") UUID id) {
        return ResponseEntity.ok(this.customerDetailsService.getCustomerDetails(id));
    }
}
