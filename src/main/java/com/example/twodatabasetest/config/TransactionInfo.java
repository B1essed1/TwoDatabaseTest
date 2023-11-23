package com.example.twodatabasetest.config;

import com.sun.xml.bind.Utils;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.server.Session;

import java.util.Random;
import java.util.UUID;

class TransactionInfo {
     
    private final Long persistenceContextId;
 
    private String transactionId;
 
    private MDC.MDCCloseable mdc;
 
    public TransactionInfo() {
        Random random = new Random();
        this.persistenceContextId = System.currentTimeMillis() + random.nextLong();
        setMdc();
    }
 
    public boolean hasTransactionId() {
        return transactionId != null;
    }
 
    public TransactionInfo setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        setMdc();
        return this;
    }
 
    private void setMdc() {
        this.mdc = MDC.putCloseable(
            "txId",
            String.format(
                " Persistence Context Id: [%d], DB Transaction Id: [%s]",
                persistenceContextId,
                transactionId
            )
        );
    }
 
    public void close() {
        if(mdc != null) {
            mdc.close();
        }
    }
}