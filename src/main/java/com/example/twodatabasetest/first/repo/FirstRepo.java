package com.example.twodatabasetest.first.repo;

import com.example.twodatabasetest.config.Transactional1;
import com.example.twodatabasetest.config.Transactional2;
import com.example.twodatabasetest.first.entity.FirstEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
//@Transactional(value = "t1",propagation = Propagation.SUPPORTS)
@Transactional1(propagation = Propagation.SUPPORTS)
public interface FirstRepo extends JpaRepository<FirstEntity, Long> {
    @Override
//    @Transactional(value = "t1")
    @Transactional1
    <S extends FirstEntity> S save(S entity);
}
