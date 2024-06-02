package com.example.woojeong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WoojeongApplication {

    public static void main(String[] args) {
        SpringApplication.run(WoojeongApplication.class, args);
    }

}
