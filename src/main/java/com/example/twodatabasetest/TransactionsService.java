package com.example.twodatabasetest;

import com.example.twodatabasetest.first.entity.FirstEntity;
import com.example.twodatabasetest.first.repo.FirstRepo;
import com.example.twodatabasetest.second.repo.SecondRepo;
import com.example.twodatabasetest.second.entity.SecondEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {
    private final FirstRepo first;
    private final SecondRepo second;

    public TransactionsService( FirstRepo first, SecondRepo second) {
        this.first = first;
        this.second = second;
    }


    @Async
    public void save(){
        FirstEntity firstEntity = new FirstEntity("Name", 1);
        SecondEntity secondEntity = new SecondEntity("Senisadfas", 1);
        try {
            first.save(firstEntity);
        } catch (Exception e){
            System.out.println(e);
        }

        try {
            second.save(secondEntity);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
