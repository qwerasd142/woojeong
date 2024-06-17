package com.example.woojeong.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "TA_ExchangeRate")
public class ExchangeRate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private long id;


    private String curName;
    private String curUnit;
    private String ttb;

    @CreationTimestamp
    private LocalDateTime createdTime;



}
