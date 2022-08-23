package com.ai.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.lms.entity.Batch;
import com.ai.lms.repository.BatchRepository;

@Service
public class BatchService {
	
	@Autowired
	private BatchRepository repo;

	public void save(Batch batch) {
		repo.save(batch);
	}

	public List<Batch> findAll() {
		return repo.findAll();
	}
    
}
