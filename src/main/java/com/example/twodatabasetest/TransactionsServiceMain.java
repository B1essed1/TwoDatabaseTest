package com.example.twodatabasetest;

import com.example.twodatabasetest.config.TransactionalCustom;
import org.springframework.stereotype.Service;

@Service
public class TransactionsServiceMain {

    private final TransactionsService transactionsService;

    public TransactionsServiceMain(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @TransactionalCustom
    public void save() {
        transactionsService.save();
    }

}
