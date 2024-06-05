package com.example.woojeong.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "exchange_rate_prev")
public class ExchangeRatePrevEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cur_unit;
    private String ttb;
    private LocalDate prevRateDate;


}