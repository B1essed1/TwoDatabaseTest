package com.example.twodatabasetest.first.repo;

import com.example.twodatabasetest.first.entity.FirstEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FirstRepo extends JpaRepository<FirstEntity, Long> {
}
