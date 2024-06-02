package com.example.woojeong.Service;

import com.example.woojeong.Entity.ExchangeRateEntity;
import com.example.woojeong.Repository.ExchangeRateRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;

@Service
public class ExchangeRateService {

    private final RestTemplate restTemplate;

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateService(RestTemplate restTemplate, ExchangeRateRepository exchangeRateRepository){
        this.restTemplate = restTemplate;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void updateExchangeRates() {
        String url = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=secpoB02tZriwE8V9hTRbWAxkzu7lM9v&searchdate=20180102&data=AP01";
        ExchangeRateEntity[] exchangeRateEntities = restTemplate.getForObject(url, ExchangeRateEntity[].class);

        if (exchangeRateEntities != null) {
            for (ExchangeRateEntity rate : exchangeRateEntities) {
                exchangeRateRepository.save(rate);
            }
        }

    }
}
