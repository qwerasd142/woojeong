package com.example.woojeong.Controller;

import com.example.woojeong.Entity.ExchangeRateEntity;
import com.example.woojeong.Entity.ExchangeRatePrevEntity;
import com.example.woojeong.Service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping("/api/exchange-rates")
    public List<ExchangeRateEntity> getExchangeRates(@RequestParam("date") String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return exchangeRateService.getExchangeRates(localDate);
    }

    @GetMapping("/api/exchange-rates-prev")
    public List<ExchangeRatePrevEntity> getExchangeRatesPrev(@RequestParam("date") String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate rateDate = LocalDate.parse(date, formatter);
        LocalDate previousDate = rateDate.minusDays(1);
        return exchangeRateService.getExchangePrevRates(previousDate);
    }

}
