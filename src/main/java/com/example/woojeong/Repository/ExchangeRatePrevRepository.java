package com.example.woojeong.Repository;

import com.example.woojeong.Entity.ExchangeRatePrevEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRatePrevRepository extends JpaRepository<ExchangeRatePrevEntity, Long> {

    List<ExchangeRatePrevEntity> findByPrevRateDate(LocalDate prevRateDate);
}
