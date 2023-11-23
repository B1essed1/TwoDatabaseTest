package com.example.twodatabasetest.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.data.neo4j.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

//@EnableTransactionManagement(order = Ordered.HIGHEST_PRECEDENCE)
@EnableTransactionManagement()
@Configuration
public class TransactionManager {
    @Bean(name = "t1")
    @Primary
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
