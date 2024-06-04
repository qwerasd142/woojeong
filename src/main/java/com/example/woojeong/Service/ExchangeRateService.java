package com.example.woojeong.Service;

import com.example.woojeong.Entity.ExchangeRateEntity;
import com.example.woojeong.Repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public List<ExchangeRateEntity> fetchRatesFromApi(LocalDate rateDate) {
        String apiUrl = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=secpoB02tZriwE8V9hTRbWAxkzu7lM9v&searchdate=%s&data=AP01";
        String formattedDate = rateDate.toString().replace("-","");
        String url = String.format(apiUrl, formattedDate);

        RestTemplate restTemplate = new RestTemplate();
        ExchangeRateEntity[] rates = restTemplate.getForObject(url, ExchangeRateEntity[].class);

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



}
