package com.example.twodatabasetest.first.repo;

import com.example.twodatabasetest.first.entity.FirstEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstRepo extends JpaRepository<FirstEntity, Long> {
}
