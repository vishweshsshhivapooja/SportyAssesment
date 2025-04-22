package org.sporty.controller;

import org.sporty.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class LoyaltyController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}/loyalty")
    public String getLoyaltyPoints(@PathVariable Long customerId) {
        return customerService.getCustomerById(customerId)
                .map(customer -> "Loyalty Points: " + customer.getLoyaltyPoints())
                .orElse("Customer not found");
    }
}
