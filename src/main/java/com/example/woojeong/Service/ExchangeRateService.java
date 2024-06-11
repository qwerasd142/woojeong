package com.example.woojeong.Service;

import com.example.woojeong.Entity.ExchangeRateEntity;
import com.example.woojeong.Entity.ExchangeRatePrevEntity;
import com.example.woojeong.Repository.ExchangeRatePrevRepository;
import com.example.woojeong.Repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    @Autowired
    private ExchangeRatePrevRepository exchangeRatePrevRepository;

//  주말일 경우 금요일 데이터를 가지고 옴  
    private String getSearchdate(LocalDate rateDate) {
        DayOfWeek dayOfWeek = rateDate.getDayOfWeek();
        // 토요일
        if (dayOfWeek.getValue() == 6)
            return rateDate.minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 일요일
        if (dayOfWeek.getValue() == 7)
            return rateDate.minusDays(2).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        return rateDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

//  오늘 환율 데이터
    public List<ExchangeRateEntity> fetchRatesFromApi(LocalDate rateDate) {
        String apiUrl = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=secpoB02tZriwE8V9hTRbWAxkzu7lM9v&searchdate=%s&data=AP01";
        String formattedDate = getSearchdate(rateDate);
        String url = String.format(apiUrl, formattedDate);

        RestTemplate restTemplate = new RestTemplate();
        ExchangeRateEntity[] rates = restTemplate.getForObject(url, ExchangeRateEntity[].class);

        for(ExchangeRateEntity rate : rates) {
            String ttb = rate.getTtb().replaceAll(",","");
            rate.setTtb(ttb);
        }

        return Arrays.asList(rates);
    }


    public List<ExchangeRateEntity> getExchangeRates(LocalDate rateDate){
        List<ExchangeRateEntity> rates = exchangeRateRepository.findByRateDate(rateDate);
        if(rates.isEmpty()){
            rates = fetchRatesFromApi(rateDate);
            exchangeRateRepository.saveAll(rates);
        }
        return rates;
    }

    //   전날 환율 데이터
    public List<ExchangeRatePrevEntity> fetchPrevRatesFromApi(LocalDate rateDate) {
        String apiUrl = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=secpoB02tZriwE8V9hTRbWAxkzu7lM9v&searchdate=%s&data=AP01";
        String formattedDate = getSearchdate(rateDate);
        String url = String.format(apiUrl, formattedDate);

        RestTemplate restTemplate = new RestTemplate();
        ExchangeRatePrevEntity[] rates = restTemplate.getForObject(url, ExchangeRatePrevEntity[].class);

        for(ExchangeRatePrevEntity rate : rates) {
            String ttb = rate.getTtb().replaceAll(",","");
            rate.setTtb(ttb);
        }

        return Arrays.asList(rates);
    }


    public List<ExchangeRatePrevEntity> getExchangePrevRates(LocalDate prevRateDate){
        List<ExchangeRatePrevEntity> rates = exchangeRatePrevRepository.findByPrevRateDate(prevRateDate);
        if(rates.isEmpty()){
            rates = fetchPrevRatesFromApi(prevRateDate);
            exchangeRatePrevRepository.saveAll(rates);
        }
        return rates;
    }


}
