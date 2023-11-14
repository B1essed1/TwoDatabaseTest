package com.example.twodatabasetest.controller;

import com.example.twodatabasetest.TransactionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class RestControllerTest {
    private final TransactionsService service;

    public RestControllerTest(TransactionsService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<?> test() {
        for (int i = 0; i <200 ; i++) {
            service.save();
        }
        return ResponseEntity.ok("success");
    }
}
