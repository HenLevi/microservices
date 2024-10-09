package com.code.service_a.model;

import lombok.Data;

@Data
public class BtcResponse {
    private PriceData bitcoin;

    @Data
    public static class PriceData {
        private double usd;
    }
}