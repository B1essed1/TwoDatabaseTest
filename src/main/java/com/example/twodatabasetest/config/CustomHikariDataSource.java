package com.example.twodatabasetest.config;

import com.zaxxer.hikari.HikariConfig;

public class CustomHikariDataSource extends HikariConfig {
    public CustomHikariDataSource(){
        setMaximumPoolSize(5);
        setConnectionTimeout(5000);
        setAutoCommit(false);
        setIdleTimeout(5);
    }
}
