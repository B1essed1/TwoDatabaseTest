package com.example.twodatabasetest.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "firstFactory",
        transactionManagerRef = "t1",
        basePackages = {
                "com.example.twodatabasetest.first.repo"
        }
)
public class DatasourceConfigFirst {

    @Bean
    @ConfigurationProperties("spring.datasource.db1")
    public DataSourceProperties firstDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource firstSource() {
        return firstDatasourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean(name = "firstFactory")
    public LocalContainerEntityManagerFactoryBean firstFactory(
            @Qualifier("firstSource") DataSource dataSource,
            @Qualifier("entityManagerFactoryBuilder") EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.twodatabasetest.first.repo")
                .build();
    }



}
