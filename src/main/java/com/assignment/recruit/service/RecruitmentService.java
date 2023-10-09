package com.assignment.recruit.service;

import com.assignment.recruit.dto.request.RecruitmentRequest;

public interface RecruitmentService {
    public Long register(Long companyId, RecruitmentRequest recruitmentRequest);

    public Long update(Long companyId, Long recruitmentId, RecruitmentRequest recruitmentRequest);
}
