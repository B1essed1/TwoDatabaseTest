package com.example.twodatabasetest.config;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration

//@EnableTransactionManagement
@EnableJpaRepositories(
       entityManagerFactoryRef = "secondFactory",
        transactionManagerRef = "t2",
        basePackages = {
                "com.example.twodatabasetest.second.repo"
        }
)
public class DatasourceConfigSecond {

    @Bean
    @ConfigurationProperties("spring.datasource.db2")
    public DataSourceProperties secondDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource secondSource() {
        return secondDatasourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean(name = "secondFactory")
    public LocalContainerEntityManagerFactoryBean secondFactory(
            @Qualifier("secondSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.twodatabasetest.second.repo")
                .build();
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

}
