package com.example.twodatabasetest.second.service;

import com.example.twodatabasetest.config.ApiResponse;
import com.example.twodatabasetest.first.entity.FirstEntity;
import com.example.twodatabasetest.first.repo.FirstRepo;
import com.example.twodatabasetest.second.entity.SecondEntity;
import com.example.twodatabasetest.second.repo.SecondRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Service
public class SecondService {

    private final EntityManager entityManager;
    private final SecondRepo secondRepo;

    public SecondService(EntityManager entityManager, SecondRepo firstRepo) {
        this.entityManager = entityManager;
        this.secondRepo = firstRepo;
    }

/*
    public ApiResponse saveSecond(SecondEntity entity){
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            secondRepo.save(entity);
        } catch (Exception exception){
            return ApiResponse.builder()
                    .isSuccess(false)
                    .res(tx)
                    .build();
        }
        return ApiResponse.builder()
                .isSuccess(true)
                .res(tx)
                .build();
    }*/
}
