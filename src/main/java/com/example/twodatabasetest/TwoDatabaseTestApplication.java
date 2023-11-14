package com.example.twodatabasetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class    TwoDatabaseTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwoDatabaseTestApplication.class, args);
    }

}
