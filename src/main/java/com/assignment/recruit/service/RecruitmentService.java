package com.assignment.recruit.service;

import com.assignment.recruit.dto.request.RecruitmentRequest;
import com.assignment.recruit.entity.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentService {
    public Long register(Long companyId, RecruitmentRequest recruitmentRequest);

    public Long update(Long companyId, Long recruitmentId, RecruitmentRequest recruitmentRequest);

    public Long remove(Long companyId, Long recruitmentId);

    public Page<Recruitment> recruitmentList(Pageable pageable);

    public Page<Recruitment> searchRecruitmentList(Pageable pageable, String search);


}
