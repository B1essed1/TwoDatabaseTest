package com.example.twodatabasetest.config;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.dao.DataAccessException;
import org.springframework.data.neo4j.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.ConnectionHandle;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import scala.util.parsing.combinator.testing.Str;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.sql.SQLException;

//@EnableTransactionManagement(order = Ordered.HIGHEST_PRECEDENCE)
@EnableTransactionManagement()
@Configuration
public class TransactionManager {
    @Bean(name = "t1")
    @Primary
    public PlatformTransactionManager firstTransactionManager(
            @Qualifier("firstFactory") EntityManagerFactory customerEntityManagerFactory) {
        return new JpaTransactionManager(customerEntityManagerFactory);
    }

    @Bean(name = "t2")
    public PlatformTransactionManager secondTransactionManager(
            @Qualifier("secondFactory") EntityManagerFactory first) {
        return new JpaTransactionManager(first);
    }

    @Bean("test")
    public Object secondTransactionManager(
            @Qualifier("t1") PlatformTransactionManager manager, @Qualifier("t2") PlatformTransactionManager manager2) {
        HibernateJpaDialect ss = ((HibernateJpaDialect) ((JpaTransactionManager) manager).getJpaDialect());
        var s = new HibernateJpaDialectCustom("t1", ss);
        ((JpaTransactionManager) manager).setJpaDialect(s);

        HibernateJpaDialect ss2 = ((HibernateJpaDialect) ((JpaTransactionManager) manager2).getJpaDialect());
        var s2 = new HibernateJpaDialectCustom("t2", ss2);
        ((JpaTransactionManager) manager2).setJpaDialect(s2);
        return new String("as");
    }

    public static class HibernateJpaDialectCustom extends HibernateJpaDialect {

        String name;
        HibernateJpaDialect hibernateJpaDialect;

        public HibernateJpaDialectCustom(String name, HibernateJpaDialect hibernateJpaDialect) {
            this.name = name;
            this.hibernateJpaDialect = hibernateJpaDialect;
        }

        @Override
        public void setPrepareConnection(boolean prepareConnection) {
            this.hibernateJpaDialect.setPrepareConnection(prepareConnection);
        }

        @Override
        public void setJdbcExceptionTranslator(SQLExceptionTranslator jdbcExceptionTranslator) {
            this.hibernateJpaDialect.setJdbcExceptionTranslator(jdbcExceptionTranslator);
        }

        @Override
        public Object beginTransaction(EntityManager entityManager, TransactionDefinition definition) throws PersistenceException, SQLException, TransactionException {
            System.out.println(this.name + "=>:" + definition.getName());
            return this.hibernateJpaDialect.beginTransaction(entityManager, definition);
        }

        @Override
        public Object prepareTransaction(EntityManager entityManager, boolean readOnly, String name) throws PersistenceException {
            return this.hibernateJpaDialect.prepareTransaction(entityManager, readOnly, name);
        }

//        @Override
//        protected FlushMode prepareFlushMode(Session session, boolean readOnly) throws PersistenceException {
//            return this.hibernateJpaDialect.prepareFlushMode(session, readOnly);
//        }

        @Override
        public void cleanupTransaction(Object transactionData) {
            this.hibernateJpaDialect.cleanupTransaction(transactionData);
        }

        @Override
        public ConnectionHandle getJdbcConnection(EntityManager entityManager, boolean readOnly) throws PersistenceException, SQLException {
            return this.hibernateJpaDialect.getJdbcConnection(entityManager, readOnly);
        }

        @Override
        public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
            return this.hibernateJpaDialect.translateExceptionIfPossible(ex);
        }

//        @Override
//        protected DataAccessException convertHibernateAccessException(HibernateException ex) {
//            return this.hibernateJpaDialect.convertHibernateAccessException(ex);
//        }

//        @Override
//        protected SessionImplementor getSession(EntityManager entityManager) {
//            return this.hibernateJpaDialect.getSession(entityManager);
//        }

    }

//    @Bean(name = "chainedTransactionManager")
//    public ChainedTransactionManager chainedTransactionManager(
//            @Qualifier("t1") PlatformTransactionManager firstTransactionManager,
//            @Qualifier("t2") PlatformTransactionManager secondTransactionManager) {
//        return new ChainedTransactionManager(firstTransactionManager, secondTransactionManager);
//    }

}
