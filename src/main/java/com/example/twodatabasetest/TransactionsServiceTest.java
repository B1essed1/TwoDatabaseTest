package com.example.twodatabasetest;

import com.example.twodatabasetest.config.TransactionalCustom;
import com.example.twodatabasetest.first.entity.FirstEntity;
import com.example.twodatabasetest.first.repo.FirstRepo;
import com.example.twodatabasetest.second.entity.SecondEntity;
import com.example.twodatabasetest.second.repo.SecondRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.Random;

@Service
public class TransactionsServiceTest {
    private final FirstRepo first;
    private final SecondRepo second;

    public TransactionsServiceTest(FirstRepo first, SecondRepo second) {
        this.first = first;
        this.second = second;
    }


    @TransactionalCustom(value = "t1", propagation = Propagation.REQUIRES_NEW)
    @TransactionalCustom(value = "t2", propagation = Propagation.REQUIRES_NEW)
    public void save() {
//        alfa();
        FirstEntity firstEntity = new FirstEntity("Transactiontest", new Random().nextInt(35));
        SecondEntity secondEntity = new SecondEntity("TransactionTestCount", new Random().nextInt(34));

        first.save(firstEntity);
        second.save(secondEntity);

    }

}
