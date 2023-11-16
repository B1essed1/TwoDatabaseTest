package com.example.twodatabasetest;

import com.example.twodatabasetest.first.entity.FirstEntity;
import com.example.twodatabasetest.first.repo.FirstRepo;
import com.example.twodatabasetest.second.repo.SecondRepo;
import com.example.twodatabasetest.second.entity.SecondEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class TransactionsService {
    private final FirstRepo first;
    private final SecondRepo second;

    public TransactionsService( FirstRepo first, SecondRepo second) {
        this.first = first;
        this.second = second;
    }


    @Transactional(value = "chainedTransactionManager", propagation = Propagation.REQUIRED)
    public void save(){
        FirstEntity firstEntity = new FirstEntity("Transactiontest", new Random().nextInt(15));
        SecondEntity secondEntity = new SecondEntity("TransactionTestCount", new Random().nextInt(15));
        try {
            first.save(firstEntity);
        } catch (Exception e){
            System.out.println(e);
        }
        try{
            second.save(secondEntity);

        }catch (Exception e){
            System.out.println(e);
        }
    }


    @Transactional(value = "chainedTransactionManager", propagation = Propagation.REQUIRED)
    public void save(int i){
        FirstEntity firstEntity = new FirstEntity("Transactiontest", new Random().nextInt(15));
        SecondEntity secondEntity = new SecondEntity("TransactionTestCount", new Random().nextInt(15));
        if (i == 2) throw new NullPointerException();
        try {
            first.save(firstEntity);
        } catch (Exception e){
            System.out.println(e);
        }
        try{
            second.save(secondEntity);

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
