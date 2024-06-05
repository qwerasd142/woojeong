package com.example.woojeong.DTO;


public class ExchateRatePrevDTO {

    private String currency;
    private double rate;
    private double prevRate;
    private String change;

    public ExchateRatePrevDTO(String currency, double rate, double prevRate,String change){
        this.currency = currency;
        this.rate = rate;
        this.prevRate = prevRate;
        this.change = change;
    }

    private String ExchangeChange() {
        if(prevRate == 0) {
            return "No Data";
        } else if (rate > prevRate){
            return "Up";
        } else if (rate < prevRate) {
            return "Down";
        } else {
            return "No Change";
        }
    }
}
