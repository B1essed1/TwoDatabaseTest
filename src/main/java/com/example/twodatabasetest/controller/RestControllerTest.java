package com.example.twodatabasetest.controller;

import com.example.twodatabasetest.TransactionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("api/test")
public class RestControllerTest {
    private final TransactionsService service;

    public RestControllerTest(TransactionsService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<?> test() throws SQLException {
        for (int i = 0; i < 100; i++) {
                 service.save();
        }
        return ResponseEntity.ok("f*ck");
    }
}
