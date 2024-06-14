package com.example.woojeong.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "TA_exchange_rate_prev")
public class ExchangeRatePrevEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String curName;
    private String curUnit;
    private String ttbDay;
    private String ttbWeek;
    private String ttbMonth;
    private LocalDate prevRateDate;
}
