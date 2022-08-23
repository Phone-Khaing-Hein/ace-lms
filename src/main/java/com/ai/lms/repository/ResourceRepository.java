package com.ai.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ai.lms.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {

	int countByModuleId(Integer id);
}
