package com.example.twodatabasetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EntityScan(basePackages = {
        "com.example.twodatabasetest.second.entity"
        ,"com.example.twodatabasetest.first.entity"
})

public class    TwoDatabaseTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwoDatabaseTestApplication.class, args);
    }

}
