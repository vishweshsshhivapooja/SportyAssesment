package org.sporty.controller;

import org.sporty.dto.PurchaseRequest;
import org.sporty.dto.PurchaseResponse;
import org.sporty.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public PurchaseResponse purchaseBooks(@RequestBody PurchaseRequest request) {
        return purchaseService.processPurchase(request);
    }
}
