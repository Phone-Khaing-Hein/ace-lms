package com.ai.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ai.lms.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

	List<Module> findByCourseId(int courseId);

	Module findByName(String name);

}
