package com.example.twodatabasetest.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.neo4j.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@EnableTransactionManagement
@Configuration
public class TransactionManager {

    private final DatasourceConfigFirst first;
    private final DatasourceConfigSecond second;

    public TransactionManager(DatasourceConfigFirst first, DatasourceConfigSecond second) {
        this.first = first;
        this.second = second;
    }

    @Primary
    @Bean(name = "t1")
    public PlatformTransactionManager firstTransactionManager(
            @Qualifier("firstFactory") EntityManagerFactory customerEntityManagerFactory ) {
        return new JpaTransactionManager(customerEntityManagerFactory);
    }

    @Bean(name = "t2")
    public PlatformTransactionManager secondTransactionManager(
            @Qualifier("secondFactory") EntityManagerFactory first) {
        return new JpaTransactionManager(first);
    }

    @Bean(name = "chainedTransactionManager")
    public ChainedTransactionManager chainedTransactionManager(
            @Qualifier("t1") PlatformTransactionManager firstTransactionManager,
            @Qualifier("t2") PlatformTransactionManager secondTransactionManager) {
        return new ChainedTransactionManager(firstTransactionManager, secondTransactionManager);
    }
}
