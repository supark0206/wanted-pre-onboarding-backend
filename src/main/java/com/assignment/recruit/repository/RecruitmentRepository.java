package com.assignment.recruit.repository;

import com.assignment.recruit.entity.Company;
import com.assignment.recruit.entity.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    Optional<Recruitment> findByIdAndCompany(Long id,Company company);

    Page<Recruitment> findAll(Pageable pageable);

    @Query("select r from Recruitment r" +
            " where r.company.name like %:companyName%" +
            " or r.position like %:position%" +
            " or r.skill like %:skill%" +
            " or r.reward like %:reward%")
    Page<Recruitment> searchRecruitment(Pageable pageable, String companyName,
                                        String position, String skill, String reward);
}
