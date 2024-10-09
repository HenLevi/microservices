package com.code.service_a.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.code.service_a.exception.ApiException;
import com.code.service_a.model.BtcResponse;
import com.code.service_a.service.BtcService;

@Service
public class BtcServiceImpl implements BtcService {
    private final String apiUrl = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd";
    private final List<Double> btcPrices = new ArrayList<>();
    private final RestTemplate restTemplate = new RestTemplate();
   
    @Override
    @Scheduled(fixedRate = 60000) 
    public void fetchAndPrintBtcPrice() {   //Fetches the current Bitcoin price and prints it.(run every 1 minute)
        try {
            BtcResponse response = restTemplate.getForObject(apiUrl, BtcResponse.class);
            if (response == null || response.getBitcoin() == null) {
                throw new ApiException("API response or BTC field is null");
            }
            Double currentBtcPrice = response.getBitcoin().getUsd();
            storeBtcPrice(currentBtcPrice);
            System.out.println("Current Bitcoin price: " + currentBtcPrice);
        } catch (RestClientException e) {
            throw new ApiException("Error fetching Bitcoin price: " + e.getMessage(), e);
        }
    }
    
    private void storeBtcPrice(Double currentBtcPrice) {
        if (btcPrices.size() >= 10) {
            btcPrices.remove(0);
        }
        btcPrices.add(currentBtcPrice);
    }
    
    
    @Override
    @Scheduled(fixedRate = 600000)
    public void printAvgBtcPrice() {   //Prints the average Bitcoin price over the last 10 minutes.(run every 10 minute)
        if (btcPrices.isEmpty()) {
            System.out.println(" can not calculate average cause there is no data available.");
            return;
        }
        double avgPrice = btcPrices.stream().mapToDouble(d->d).average().orElse(0.0);
        System.out.println("Average Bitcoin price for the last 10 minutes: " + avgPrice);
    }


}