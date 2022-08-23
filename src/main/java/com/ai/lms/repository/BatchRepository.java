package com.ai.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.lms.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer>{
    
}
