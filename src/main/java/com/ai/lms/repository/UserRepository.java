package com.ai.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ai.lms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByLoginId(String loginId);
}
