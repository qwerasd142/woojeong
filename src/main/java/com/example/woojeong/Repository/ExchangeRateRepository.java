package com.example.woojeong.Repository;

import com.example.woojeong.Entity.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {

    List<ExchangeRateEntity> findByRateDate(LocalDate rateDate);
}
