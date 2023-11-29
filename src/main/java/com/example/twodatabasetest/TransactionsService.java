package com.example.twodatabasetest;

import com.example.twodatabasetest.config.ApiResponse;
import com.example.twodatabasetest.config.Transactional1;
import com.example.twodatabasetest.config.Transactional2;
import com.example.twodatabasetest.first.entity.FirstEntity;
import com.example.twodatabasetest.first.repo.FirstRepo;
import com.example.twodatabasetest.first.service.FirstService;
import com.example.twodatabasetest.second.repo.SecondRepo;
import com.example.twodatabasetest.second.entity.SecondEntity;
import com.example.twodatabasetest.second.service.SecondService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityTransaction;
import java.sql.SQLException;
import java.util.Random;

@Service
public class TransactionsService {
    private final FirstRepo first;
    private final SecondRepo second;

    private final FirstService firstService;
    private final SecondService secondService;

    public TransactionsService(FirstRepo first, SecondRepo second, FirstService firstService, SecondService secondService) {
        this.first = first;
        this.second = second;
        this.firstService = firstService;
        this.secondService = secondService;
    }


//    @Transactional(value = "t2",propagation = Propagation.REQUIRES_NEW)
//    @Transactional2(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    @Transactional1
    public void save() {
//        alfa();
        FirstEntity firstEntity = new FirstEntity("Transactiontest", new Random().nextInt(35));
        SecondEntity secondEntity = new SecondEntity("TransactionTestCount", new Random().nextInt(34));

        first.save(firstEntity);
        second.save(secondEntity);

    }

//    private void alfa() {
//        FirstEntity firstEntity = new FirstEntity("Transactiontest", new Random().nextInt(25));
//        SecondEntity secondEntity = new SecondEntity("TransactionTestCount", new Random().nextInt(30));
//        System.out.println("worked");
//
//        first.save(firstEntity);
//
//
//        System.out.println("worked 22");
//        second.save(secondEntity);
//    }


    public void save(int i) {
        FirstEntity firstEntity = new FirstEntity("Transactiontest", new Random().nextInt(15));
        SecondEntity secondEntity = new SecondEntity("TransactionTestCount", new Random().nextInt(15));
        if (i == 2) throw new NullPointerException();
        try {
            first.save(firstEntity);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            second.save(secondEntity);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void customSave() throws SQLException {
        FirstEntity firstEntity = new FirstEntity("Transactiontest", new Random().nextInt(15));
        SecondEntity secondEntity = new SecondEntity("TransactionTestCount", new Random().nextInt(15));
        ApiResponse response1 = new ApiResponse();
        ApiResponse response2 = new ApiResponse();

        try {
          response1 = firstService.saveFirst(firstEntity);
        } catch (Exception e) {
            response1.setSuccess(false);
        }
        try {
       //  response2 = secondService.saveSecond(secondEntity);
        } catch (Exception e) {
            response2.setSuccess(false);
        }

        if (!response1.isSuccess()|| !response2.isSuccess()){
            try {
                response1.getRes().rollback();
                response2.getRes().rollback();
            } catch (Exception e){

            }
        }

    }
}
