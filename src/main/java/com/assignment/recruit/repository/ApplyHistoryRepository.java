package com.assignment.recruit.repository;

import com.assignment.recruit.entity.ApplyHistory;
import com.assignment.recruit.entity.Recruitment;
import com.assignment.recruit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplyHistoryRepository extends JpaRepository<ApplyHistory,Long> {

    Optional<ApplyHistory> findByRecruitmentAndUser(Recruitment recruitment, User user);
}
