package com.ai.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.lms.entity.Resource;
import com.ai.lms.repository.ResourceRepository;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository repository;
    
	public void save(Resource resource) {
		repository.save(resource);
	}

	public int findResourceCount(Integer id) {
		return repository.countByModuleId(id);
	}
}
