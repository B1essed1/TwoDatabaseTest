package com.example.twodatabasetest.first.repo;

import com.example.twodatabasetest.config.TransactionalCustom;
import com.example.twodatabasetest.first.entity.FirstEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

@Repository
//@Transactional(value = "t1",propagation = Propagation.SUPPORTS)
//@TransactionalCustom(propagation = Propagation.SUPPORTS)
public interface FirstRepo extends JpaRepository<FirstEntity, Long> {
    @Override
//    @Transactional(value = "t1")
    @TransactionalCustom
    <S extends FirstEntity> S save(S entity);
}
