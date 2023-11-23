/*
package com.example.twodatabasetest.config;

import org.hibernate.BaseSessionEventListener;
import org.hibernate.Session;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.boot.spi.MetadataBuildingOptions;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.spi.SessionFactoryImplementor;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TransactionInfoSessionEventListener
        extends BaseSessionEventListener {

    private final TransactionInfo transactionInfo;

    private EntityManager entityManager;

    */
/**
     * Executes when a JPA EntityManager is created.
     *//*

    public TransactionInfoSessionEventListener() {
        transactionInfo = new TransactionInfo();
    }

    */
/**
     * Executes after a JDBC statement was executed.
     *//*

    @Override
    public void jdbcExecuteStatementEnd() {
        if (!transactionInfo.hasTransactionId()) {
            resolveTransactionId();
        }
    }

    */
/**
     * Executes after the commit or rollback was called
     * on the JPA EntityTransaction.
     *//*

    @Override
    public void transactionCompletion(boolean successful) {
        transactionInfo.setTransactionId(null);
    }

    */
/**
     * Executes after JPA EntityManager is closed.
     *//*

    @Override
    public void end() {
        transactionInfo.close();
    }

    private EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = SpringTransactionUtils.currentEntityManager();
        }
        return entityManager;
    }

    private void resolveTransactionId() {
        EntityManager entityManager = getEntityManager();
        SessionFactoryImplementor sessionFactory = entityManager
                .getEntityManagerFactory()
                .unwrap(SessionFactoryImplementor.class);

        entityManager.unwrap(Session.class).doWork(connection -> {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         Database.of(sessionFactory.getJdbcServices().getDialect())
                                 .dataSourceProvider()
                                 .queries()
                                 .transactionId()
                 )) {
                if (resultSet.next()) {
                    transactionInfo.setTransactionId(resultSet.getString(1));
                }
            }
        });
    }
}

*/
