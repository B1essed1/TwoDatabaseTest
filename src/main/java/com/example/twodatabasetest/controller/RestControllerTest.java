package com.example.twodatabasetest.controller;

import com.example.twodatabasetest.TransactionsService;
import com.example.twodatabasetest.TransactionsServiceMain;
import com.example.twodatabasetest.TransactionsServiceTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("api/test")
public class RestControllerTest {
    private final TransactionsServiceMain service;

    private final TransactionsServiceTest transactionsServiceTest;

    public RestControllerTest(TransactionsServiceMain service, TransactionsServiceTest transactionsServiceTest) {
        this.service = service;
        this.transactionsServiceTest = transactionsServiceTest;
    }


    @GetMapping
    ResponseEntity<?> test() throws SQLException {
        for (int i = 0; i < 100; i++) {
                 service.save();
        }
        return ResponseEntity.ok("f*ck");
    }

    @GetMapping("test1")
    ResponseEntity<?> test1() throws SQLException {
        for (int i = 0; i < 100; i++) {
            transactionsServiceTest.save();
        }
        return ResponseEntity.ok("f*ck");
    }
}
