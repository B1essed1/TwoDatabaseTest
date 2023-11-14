package com.example.twodatabasetest.config;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "firstFactory",
        transactionManagerRef = "t1",
        basePackages = {
                "com.example.twodatabasetest.first.repo"
        }
)
public class DatasourceConfigFirst {

    private final Environment env;

    public DatasourceConfigFirst(Environment env) {
        this.env = env;
    }

    @Primary
    @Bean(name = "first")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource customerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.first.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.first.jdbc-url"));
        dataSource.setUsername(env.getProperty("spring.datasource.first.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.first.password"));
        return dataSource;
    }

    @Primary
    @Bean(name = "firstFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("first") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("spring.jpa.show-sql", true);

        return builder.dataSource(dataSource)
                .packages("com.example.twodatabasetest.first.entity")
                .properties(properties)
                .persistenceUnit("first_db")
                .build();
    }

    @Primary
    @Bean(name = "t1")
    public PlatformTransactionManager customerTransactionManager(
            @Qualifier("firstFactory") EntityManagerFactory customerEntityManagerFactory ) {
        return new JpaTransactionManager(customerEntityManagerFactory);
    }

}
