package com.example.twodatabasetest.second.repo;

import com.example.twodatabasetest.second.entity.SecondEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional("t2")
public interface SecondRepo extends JpaRepository<SecondEntity, Long> {
}
