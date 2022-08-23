package com.ai.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.lms.repository.ModuleRepository;
import com.ai.lms.entity.Module;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository repository;

    public List<Module> findAll() {
        return repository.findAll();
    }

	public int save(Module module) {
		var m = repository.save(module);
		return m.getId();
	}

	public List<Module> findByCourseId(int courseId) {
		return repository.findByCourseId(courseId);
	}

	public Module findByName(String name) {
		return repository.findByName(name);
	}
}
