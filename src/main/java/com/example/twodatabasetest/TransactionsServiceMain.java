package com.example.twodatabasetest;

import com.example.twodatabasetest.config.Transactional1;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

@Service
public class TransactionsServiceMain {

    private final TransactionsService transactionsService;

    public TransactionsServiceMain(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @Transactional1
    public void save() {
        transactionsService.save();
    }

}
