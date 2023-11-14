package com.example.twodatabasetest.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef = "productTransactionManager",
        basePackages = {
                "com.example.twodatabasetest.second.repo"
        }
)
public class DatasourceConfigSecond {

    private final Environment env;

    public DatasourceConfigSecond(Environment env) {
        this.env = env;
    }


    @Bean(name = "second")
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.first.driver-class-name")));
        dataSource.setUrl(env.getProperty("db2.datasource.second.jdbc-url"));
        dataSource.setUsername(env.getProperty("db2.datasource.second.username"));
        dataSource.setPassword(env.getProperty("db2.datasource.second.password"));
        return dataSource;
    }

    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    barEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("second") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("spring.jpa.show-sql", true);

        return
                builder.dataSource(dataSource)
                        .packages("com.example.twodatabasetest.second.entity")
                        .persistenceUnit("second_db")
                        .properties(properties)
                        .build();
    }

    @Bean(name = "t2")
    public PlatformTransactionManager productTransactionManager(
            @Qualifier("productEntityManagerFactory") EntityManagerFactory productEntityManagerFactory) {
        return new JpaTransactionManager(productEntityManagerFactory);
    }


}
