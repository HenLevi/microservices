package com.code.service_a.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.code.service_a.service.BtcService;

@RestController
@RequestMapping("/api")
public class BtcController {

    private final BtcService btcService;

    public BtcController(BtcService btcService) {
        this.btcService = btcService;
    }

    @GetMapping("/fetch-btc")
    public String fetchBtcPrice() {
        btcService.fetchAndPrintBtcPrice();
        return "Fetching bitcoin price..";
    }
}