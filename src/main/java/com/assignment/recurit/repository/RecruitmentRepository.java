package com.assignment.recurit.repository;

import com.assignment.recurit.entity.Company;
import com.assignment.recurit.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    Optional<Recruitment> findByIdAndCompany(Long id,Company company);

}
