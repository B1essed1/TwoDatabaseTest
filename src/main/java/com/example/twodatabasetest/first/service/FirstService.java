package com.example.twodatabasetest.first.service;

import com.example.twodatabasetest.config.ApiResponse;
import com.example.twodatabasetest.first.entity.FirstEntity;
import com.example.twodatabasetest.first.repo.FirstRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

@Service
public class FirstService {

    private final FirstRepo firstRepo;
    private final EntityManager entityManager;

    private final DataSource dataSource;

    private final DataSource dataSource2;

    public FirstService(FirstRepo firstRepo,
                        EntityManager entityManager,
                        DataSource dataSource,
                        @Qualifier("second") DataSource dataSource2) {
        this.firstRepo = firstRepo;
        this.entityManager = entityManager;
        this.dataSource = dataSource;
        this.dataSource2 = dataSource2;
    }

    public  ApiResponse saveFirst(FirstEntity entity) throws SQLException {
        Connection connection =  dataSource.getConnection();
        Savepoint savepoint  = connection.setSavepoint();
        try {
            firstRepo.save(entity);
        } catch (Exception exception){
            return ApiResponse.builder()
                    .isSuccess(false)
                    .res(connection)
                    .build();
        }
         return ApiResponse.builder()
                .isSuccess(true)
                .res(connection)
                .build();
    }
}
