package com.example.twodatabasetest.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityTransaction;
import java.sql.Connection;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private Connection res;
    private boolean  isSuccess;
}
