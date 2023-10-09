package com.assignment.recruit.repository;

import com.assignment.recruit.entity.Company;
import com.assignment.recruit.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    Optional<Recruitment> findByIdAndCompany(Long id,Company company);

}
