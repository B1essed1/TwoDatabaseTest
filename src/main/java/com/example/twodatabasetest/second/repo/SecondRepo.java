package com.example.twodatabasetest.second.repo;

import com.example.twodatabasetest.config.Transactional2;
import com.example.twodatabasetest.second.entity.SecondEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(value = "t2", propagation = Propagation.SUPPORTS)
//@Transactional2(propagation = Propagation.SUPPORTS)
public interface SecondRepo extends JpaRepository<SecondEntity, Long> {
    @Override
    @Transactional(value = "t2")
//    @Transactional2
    <S extends SecondEntity> S save(S entity);
}
