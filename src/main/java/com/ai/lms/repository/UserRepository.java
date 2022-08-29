package com.ai.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ai.lms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByLoginId(String loginId);

    List<User> findByBatchId(int batchId);

    List<User> findByBatchIdAndStatus(int batchId, int status);
}
